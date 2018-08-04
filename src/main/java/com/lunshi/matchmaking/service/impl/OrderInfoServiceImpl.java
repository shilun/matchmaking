package com.lunshi.matchmaking.service.impl;

import com.common.mongo.AbstractMongoService;
import com.lunshi.matchmaking.domain.OrderInfo;
import com.lunshi.matchmaking.service.OrderInfoService;
import org.springframework.stereotype.Service;

@Service
public class OrderInfoServiceImpl extends AbstractMongoService<OrderInfo> implements OrderInfoService {
    @Override
    protected Class<OrderInfo> getEntityClass() {
        return OrderInfo.class;
    }
}
