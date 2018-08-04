package com.lunshi.matchmaking.service.dto;

import com.common.util.IGlossary;

/**
 * 撮合类型
 */
public enum ItemTypeEnum implements IGlossary {
    SELL("卖", 1),
    BUY("买", 2);

    ItemTypeEnum(String name, Integer value) {
    }

    @Override
    public String getName() {
        return null;
    }

    @Override
    public Integer getValue() {
        return null;
    }
}
