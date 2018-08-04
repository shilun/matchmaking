package com.lunshi.matchmaking.domain.module;

import com.common.util.IGlossary;

public enum TokenTypeEnum implements IGlossary {
    ETH,
    BTC,
    USDT(ETH,BTC),
    EOS,
    LTC,
    BCH
    ;
    TokenTypeEnum() {
    }
    TokenTypeEnum(TokenTypeEnum ...main) {
        this.parents=main;
    }

    private String tokenName;
    private TokenTypeEnum[] parents;
    @Override
    public String getName() {
        return name();
    }

    @Override
    public Integer getValue() {
        return null;
    }
}
