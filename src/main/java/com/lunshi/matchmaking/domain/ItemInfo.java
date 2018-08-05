package com.lunshi.matchmaking.domain;   

import java.math.BigDecimal;
import java.util.Date;

import com.common.util.AbstractBaseEntity;
/**
 * 
 * @desc 撮合需求 item_info
 *
 */
public class ItemInfo extends AbstractBaseEntity implements java.io.Serializable {
	private static final long serialVersionUID = 1L;
	/**撮合类型*/
	private Long refType;
	/**数据类型*/
	private String type;
	/**原始数量*/
	private BigDecimal totalAmount;
	/**剩余数量*/
	private BigDecimal amount;
	/**价格*/
	private BigDecimal  price;

	private Integer status;

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Long getRefType() {
		return refType;
	}

	public void setRefType(Long refType) {
		this.refType = refType;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
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
