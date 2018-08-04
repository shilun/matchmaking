package com.lunshi.matchmaking.service;

import com.common.util.model.YesOrNoEnum;
import com.lunshi.matchmaking.domain.OrderInfo;
import com.lunshi.matchmaking.domain.ItemInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;

@Service
public class ExecuterService {
    @Resource
    private ReceiverService receiverService;

    @Resource
    private ItemInfoService itemInfoService;

    @Resource
    private OrderInfoService orderInfoService;

    volatile AtomicBoolean atomicBoolean = new AtomicBoolean(false);

    private List<ItemInfo> buys = new ArrayList();
    private List<ItemInfo> selles = new ArrayList();

    /**
     * 自动执行任务
     */
    public void execute() {
        if (atomicBoolean.getAndSet(false)) {
            receiverService.doExecute(buys, selles);
            buys.sort(new Comparator<ItemInfo>() {
                @Override
                public int compare(ItemInfo o1, ItemInfo o2) {
                    return o1.getPrice().compareTo(o2.getPrice());
                }
            });
            selles.sort(new Comparator<ItemInfo>() {
                @Override
                public int compare(ItemInfo o1, ItemInfo o2) {
                    return o2.getPrice().compareTo(o1.getPrice());
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

                            sellItem.setStatus(YesOrNoEnum.YES);
                            //更新当前广告数量
                            buyItem.setAmount(buyItem.getAmount().subtract(sellItem.getAmount()));
                            //更新当前卖
                            sellItem.setAmount(BigDecimal.ZERO);

                            //更新sell信息
                            ItemInfo temp = new ItemInfo();
                            temp.setId(sellItem.getId());
                            temp.setStatus(YesOrNoEnum.YES);
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
                            buyItem.setStatus(YesOrNoEnum.YES);
                            sellItem.setAmount(BigDecimal.ZERO);
                            sellItem.setStatus(YesOrNoEnum.YES);


                            ItemInfo temp = new ItemInfo();
                            temp.setId(sellItem.getId());
                            temp.setStatus(YesOrNoEnum.YES);
                            temp.setAmount(BigDecimal.ZERO);
                            itemInfoService.save(temp);

                            //更新buy信息
                            temp = new ItemInfo();
                            temp.setId(buyItem.getId());
                            temp.setAmount(BigDecimal.ZERO);
                            temp.setStatus(YesOrNoEnum.YES);
                            itemInfoService.save(temp);

                            delBuys.add(buyItem);
                            delSels.add(sellItem);
                        }
                        if (i < 0) {
                            order.setBuyAmount(buyItem.getAmount());
                            order.setSellAmount(buyItem.getAmount());
                            order.setSellPrice(sellItem.getPrice());


                            buyItem.setStatus(YesOrNoEnum.YES);
                            buyItem.setAmount(BigDecimal.ZERO);
                            sellItem.setAmount(sellItem.getAmount().subtract(buyItem.getAmount()));

                            ItemInfo temp = new ItemInfo();
                            temp.setId(sellItem.getId());
                            temp.setAmount(sellItem.getAmount());
                            itemInfoService.save(temp);

                            //更新buy信息
                            temp = new ItemInfo();
                            temp.setId(buyItem.getId());
                            temp.setAmount(BigDecimal.ZERO);
                            temp.setStatus(YesOrNoEnum.YES);
                            itemInfoService.save(temp);
                            delBuys.add(buyItem);
                        }
                        orderInfoService.insert(order);
                    }
                    else{
                        break;
                    }
                }
            }
            selles.removeAll(delSels);
            buys.removeAll(delBuys);
            atomicBoolean.set(true);
        }
    }

}
