package com.lunshi.matchmaking.service;


import com.lunshi.matchmaking.domain.ItemInfo;
import com.lunshi.matchmaking.service.dto.ItemTypeEnum;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
/**
 * 撮合接收通知服务
 */
public class ReceiverService {
    List<ItemInfo> sells = new ArrayList<ItemInfo>();

    List<ItemInfo> buys = new ArrayList<ItemInfo>();

    /**
     * 新增交易
     *
     * @param item
     */
    public void addItem(ItemInfo item) {
        if (item.getType() == ItemTypeEnum.SELL) {
            sells.add(item);
        }
        if (item.getType() == ItemTypeEnum.BUY) {
            buys.add(item);
        }

    }

    /**
     * 执行撮合
     * @param sellList
     */
    public synchronized void doExecute(List<ItemInfo> sellList, List<ItemInfo> buyList) {
        synchronized (sells) {
            sellList.addAll(sells);
            sells.clear();
            synchronized (buys) {
                buyList.addAll(buys);
                buys.clear();
            }

        }
    }
}
