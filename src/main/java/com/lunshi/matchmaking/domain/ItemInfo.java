package com.lunshi.matchmaking.domain;

import com.common.util.AbstractBaseEntity;
import com.common.util.model.YesOrNoEnum;
import com.lunshi.matchmaking.service.dto.ItemTypeEnum;

import java.math.BigDecimal;

/**
 * 撮合对象
 */
public class ItemInfo  extends AbstractBaseEntity {
    /**
     * 数据类型
     */
    private ItemTypeEnum type;


    /**
     * 原始数量
     */
    private BigDecimal totalAmount;
    /**
     * 数量
     */
    private BigDecimal amount;

    /**
     * 价格
     */
    private BigDecimal price;

    /**
     * 交易手费比例
     */
    private BigDecimal feePercent;
    /**
     * 状态
     */
    private YesOrNoEnum status;

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    public BigDecimal getFeePercent() {
        return feePercent;
    }

    public void setFeePercent(BigDecimal feePercent) {
        this.feePercent = feePercent;
    }

    public ItemTypeEnum getType() {
        return type;
    }

    public void setType(ItemTypeEnum type) {
        this.type = type;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public YesOrNoEnum getStatus() {
        return status;
    }

    public void setStatus(YesOrNoEnum status) {
        this.status = status;
    }
}
