package com.lunshi.matchmaking.worker;

import com.common.util.GlosseryEnumUtils;
import com.common.util.model.YesOrNoEnum;
import com.lunshi.matchmaking.domain.OrderInfo;
import com.lunshi.matchmaking.domain.ItemInfo;
import com.lunshi.matchmaking.domain.module.ItemTypeEnum;
import com.lunshi.matchmaking.service.ItemInfoService;
import com.lunshi.matchmaking.service.OrderInfoService;
import com.lunshi.matchmaking.service.ReceiverService;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.mail.FetchProfile;
import java.math.BigDecimal;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * 撮合线程
 */
@Service
public class MatchMakingWorker implements InitializingBean {
    @Resource
    private ReceiverService receiverService;

    @Resource
    private ItemInfoService itemInfoService;

    @Resource
    private OrderInfoService orderInfoService;

    volatile AtomicBoolean atomicBoolean = new AtomicBoolean(false);

    private List<ItemInfo> buys = new ArrayList();
    private List<ItemInfo> selles = new ArrayList();

    private List<ItemInfo> cancleBuys = new ArrayList<>();
    private List<ItemInfo> cancleSelles = new ArrayList<>();

    public MatchMakingWorker() {

    }

    @Override
    public void afterPropertiesSet() throws Exception {
        ItemInfo query = new ItemInfo();
        query.setStatus(YesOrNoEnum.NO.getValue());
        List<ItemInfo> list = itemInfoService.query(query);
        for (ItemInfo info : list) {
            if (ItemTypeEnum.BUY.name().equals(info.getType())) {
                buys.add(info);
            } else {
                selles.add(info);
            }
        }
        execute();
    }

    /**
     * 二十个线程来
     */
    private ExecutorService fixedThreadPool = Executors.newFixedThreadPool(20);


    public void cancle(ItemInfo item) {
        ItemTypeEnum typeEnum = GlosseryEnumUtils.getItem(ItemTypeEnum.class, item.getType());
        if (typeEnum == ItemTypeEnum.BUY) {
            synchronized (cancleBuys) {
                cancleBuys.add(item);
            }
        }
        if (typeEnum == ItemTypeEnum.SELL) {
            synchronized (cancleSelles) {
                cancleSelles.add(item);
            }
        }
    }

    /**
     * 自动执行任务
     */
    public void execute() {
        if (!atomicBoolean.getAndSet(true)) {
            receiverService.doExecute(selles,buys);
            synchronized (cancleBuys) {
                buys.removeAll(cancleBuys);
                cancleBuys.clear();
            }

            buys.sort(new Comparator<ItemInfo>() {
                @Override
                public int compare(ItemInfo o1, ItemInfo o2) {
                    return o1.getPrice().compareTo(o2.getPrice());
                }
            });
            selles.removeAll(cancleSelles);
            synchronized (cancleSelles) {
                selles.removeAll(cancleSelles);
                cancleSelles.clear();
            }
            selles.sort(new Comparator<ItemInfo>() {
                @Override
                public int compare(ItemInfo o1, ItemInfo o2) {
                    return o1.getPrice().compareTo(o2.getPrice());
                }
            });
            List delBuys = new ArrayList();
            List delSels = new ArrayList();
            for (ItemInfo buyItem : buys) {
                for (ItemInfo sellItem : selles) {
                    //对于交易完成的直接退出
                    if (buyItem.getAmount().compareTo(BigDecimal.ZERO) == 0) {
                        break;
                    }
                    int zero = sellItem.getAmount().compareTo(BigDecimal.ZERO);
                    //对于交易完成的直接下一单
                    if (zero == 0) {
                        //退出
                        continue;
                    }

                    if (buyItem.getPrice().compareTo(sellItem.getPrice()) >= 0) {
                        OrderInfo order = new OrderInfo();
                        order.setBuyId(buyItem.getId());
                        order.setSellId(sellItem.getId());
                        order.setBuyPrice(sellItem.getPrice());
                        int i = buyItem.getAmount().compareTo(sellItem.getAmount());
                        if (i > 0) {
                            order.setBuyAmount(sellItem.getAmount());
                            order.setSellAmount(sellItem.getAmount());
                            order.setSellPrice(sellItem.getPrice());

                            sellItem.setStatus(YesOrNoEnum.YES.getValue());
                            //更新当前广告数量
                            buyItem.setAmount(buyItem.getAmount().subtract(sellItem.getAmount()));
                            //更新当前卖
                            sellItem.setAmount(BigDecimal.ZERO);

                            //更新sell信息
                            ItemInfo temp = new ItemInfo();
                            temp.setId(sellItem.getId());
                            temp.setStatus(YesOrNoEnum.YES.getValue());
                            temp.setAmount(BigDecimal.ZERO);
                            itemInfoService.save(temp);

                            //更新buy信息
                            temp = new ItemInfo();
                            temp.setId(buyItem.getId());
                            temp.setAmount(buyItem.getAmount());
                            itemInfoService.save(temp);
                            delSels.add(sellItem);
                        }
                        if (i == 0) {
                            order.setBuyAmount(sellItem.getAmount());
                            order.setSellAmount(sellItem.getAmount());
                            order.setSellPrice(sellItem.getPrice());

                            buyItem.setAmount(BigDecimal.ZERO);
                            buyItem.setStatus(YesOrNoEnum.YES.getValue());
                            sellItem.setAmount(BigDecimal.ZERO);
                            sellItem.setStatus(YesOrNoEnum.YES.getValue());


                            ItemInfo temp = new ItemInfo();
                            temp.setId(sellItem.getId());
                            temp.setStatus(YesOrNoEnum.YES.getValue());
                            temp.setAmount(BigDecimal.ZERO);
                            itemInfoService.save(temp);

                            //更新buy信息
                            temp = new ItemInfo();
                            temp.setId(buyItem.getId());
                            temp.setAmount(BigDecimal.ZERO);
                            temp.setStatus(YesOrNoEnum.YES.getValue());
                            itemInfoService.save(temp);

                            delBuys.add(buyItem);
                            delSels.add(sellItem);
                        }
                        if (i < 0) {
                            order.setBuyAmount(buyItem.getAmount());
                            order.setSellAmount(buyItem.getAmount());
                            order.setSellPrice(sellItem.getPrice());

                            sellItem.setAmount(sellItem.getAmount().subtract(buyItem.getAmount()));

                            ItemInfo temp = new ItemInfo();
                            temp.setId(sellItem.getId());
                            temp.setAmount(sellItem.getAmount());
                            itemInfoService.save(temp);

                            //更新buy信息
                            temp = new ItemInfo();
                            temp.setId(buyItem.getId());
                            temp.setAmount(BigDecimal.ZERO);
                            temp.setStatus(YesOrNoEnum.YES.getValue());
                            itemInfoService.save(temp);
                            delBuys.add(buyItem);
                        }
                        orderInfoService.save(order);
                    } else {
                        break;
                    }
                }
            }
            selles.removeAll(delSels);
            buys.removeAll(delBuys);
            atomicBoolean.set(false);
        }
    }

}
