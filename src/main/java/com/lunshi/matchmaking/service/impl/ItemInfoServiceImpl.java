package com.lunshi.matchmaking.service.impl;

import com.common.mongo.AbstractMongoService;
import com.lunshi.matchmaking.domain.ItemInfo;
import com.lunshi.matchmaking.service.ItemInfoService;
import org.springframework.stereotype.Service;

@Service
public class ItemInfoServiceImpl extends AbstractMongoService<ItemInfo> implements ItemInfoService {
    @Override
    protected Class<ItemInfo> getEntityClass() {
        return ItemInfo.class;
    }
}
