package com.lunshi.matchmaking.service;


import com.lunshi.matchmaking.domain.ItemInfo;
import com.lunshi.matchmaking.domain.module.ItemTypeEnum;

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
     * 取消撮合
     * @param refId 参照业务标识
     * @param refType 货币对类型
     * @param type 卖入/卖出类型
     */
    public void cancle(Long refId,String refType,ItemTypeEnum type);

    /**
     * 对撮合接收到的数据加入撮合线程
     *
     * @param sellList
     */
    public void doExecute(List<ItemInfo> sellList, List<ItemInfo> buyList);
}
