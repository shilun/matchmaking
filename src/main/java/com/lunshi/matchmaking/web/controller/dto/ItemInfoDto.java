package com.lunshi.matchmaking.web.controller.dto;

import com.lunshi.matchmaking.domain.module.ItemTypeEnum;

import java.math.BigDecimal;

public class ItemInfoDto {

    private Long refId;
    /**
     * 撮合货币对类型
     */
    private String refType;
    /**
     * 数据类型
     */
    private ItemTypeEnum type;
    /**
     * 原始数量
     */
    private BigDecimal totalAmount;
    /**
     * 剩余数量
     */
    private BigDecimal amount;
    /**
     * 价格
     */
    private BigDecimal price;

    public Long getRefId() {
        return refId;
    }

    public void setRefId(Long refId) {
        this.refId = refId;
    }

    public String getRefType() {
        return refType;
    }

    public void setRefType(String refType) {
        this.refType = refType;
    }

    public ItemTypeEnum getType() {
        return type;
    }

    public void setType(ItemTypeEnum type) {
        this.type = type;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
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
}
