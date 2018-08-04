package com.lunshi.matchmaking.domain.module;

import com.common.util.IGlossary;

/**
 * 撮合货币对
 */
public enum TokenPareTypeEnum implements IGlossary {

    USDT_BTC(TokenTypeEnum.USDT,TokenTypeEnum.BTC),
    USDT_ETH(TokenTypeEnum.USDT,TokenTypeEnum.ETH),
    USDT_BCH(TokenTypeEnum.USDT,TokenTypeEnum.BCH),
    USDT_LTC(TokenTypeEnum.USDT,TokenTypeEnum.LTC),
    USDT_EOS(TokenTypeEnum.USDT,TokenTypeEnum.EOS),
    ;

    TokenPareTypeEnum(TokenTypeEnum in, TokenTypeEnum out) {
        this.in = in;
        this.out = out;
    }

    private TokenTypeEnum in;
    private TokenTypeEnum out;

    public TokenTypeEnum getIn() {
        return in;
    }

    public TokenTypeEnum getOut() {
        return out;
    }

    @Override
    public String getName() {
        return name();
    }

    @Override
    public Integer getValue() {
        return null;
    }
}
