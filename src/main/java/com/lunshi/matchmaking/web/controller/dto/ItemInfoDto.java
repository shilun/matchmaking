package com.lunshi.matchmaking.web.controller.dto;

import com.lunshi.matchmaking.domain.module.ItemTypeEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;

@ApiModel(description = "撮合信息")
public class ItemInfoDto {

    @ApiModelProperty(value = "业务标识id")
    private Long refId;
    /**
     * 撮合货币对类型
     */
    @ApiModelProperty(value = "撮合货币对类型",example = "USDT_BTC")
    private String refType;
    /**
     * 数据类型
     */
    @ApiModelProperty(value = "撮合销售类型")
    private ItemTypeEnum type;
    /**
     * 剩余数量
     */
    @ApiModelProperty(value = "数量")
    private BigDecimal amount;
    /**
     * 价格
     */
    @ApiModelProperty(value = "单价")
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
