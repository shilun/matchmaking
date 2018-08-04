package com.lunshi.matchmaking.service;


import com.lunshi.matchmaking.domain.ItemInfo;
import com.lunshi.matchmaking.service.dto.ItemTypeEnum;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 撮合接收通知服务
 */
public interface ReceiverService {

    /**
     * 新增交易
     *
     * @param item
     */
    public void addItem(ItemInfo item);

    /**
     * 对撮合接收到的数据加入撮合线程
     *
     * @param sellList
     */
    public void doExecute(List<ItemInfo> sellList, List<ItemInfo> buyList);
}
