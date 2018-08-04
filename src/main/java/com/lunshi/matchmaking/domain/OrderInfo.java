package com.lunshi.matchmaking.domain;

import com.common.util.AbstractBaseEntity;

import java.math.BigDecimal;


public class OrderInfo extends AbstractBaseEntity {
    /**
     * 业务标识
     */
    private Long buyId;

    /**
     * 当前交易数量
     */
    private BigDecimal buyAmount;

    /**
     * 交易价格
     */
    private BigDecimal buyPrice;

    /**
     * 对标方业务标识
     */
    private Long sellId;

    /**
     * 卖出数量
     */
    private BigDecimal sellAmount;

    /**
     * 卖出价格
     */
    private BigDecimal sellPrice;


    public Long getBuyId() {
        return buyId;
    }

    public void setBuyId(Long buyId) {
        this.buyId = buyId;
    }

    public BigDecimal getBuyAmount() {
        return buyAmount;
    }

    public void setBuyAmount(BigDecimal buyAmount) {
        this.buyAmount = buyAmount;
    }

    public BigDecimal getBuyPrice() {
        return buyPrice;
    }

    public void setBuyPrice(BigDecimal buyPrice) {
        this.buyPrice = buyPrice;
    }

    public Long getSellId() {
        return sellId;
    }

    public void setSellId(Long sellId) {
        this.sellId = sellId;
    }

    public BigDecimal getSellAmount() {
        return sellAmount;
    }

    public void setSellAmount(BigDecimal sellAmount) {
        this.sellAmount = sellAmount;
    }

    public BigDecimal getSellPrice() {
        return sellPrice;
    }

    public void setSellPrice(BigDecimal sellPrice) {
        this.sellPrice = sellPrice;
    }
}
