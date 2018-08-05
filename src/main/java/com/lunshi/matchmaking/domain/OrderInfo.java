package com.lunshi.matchmaking.domain;   

import java.math.BigDecimal;
import java.util.Date;

import com.common.util.AbstractBaseEntity;
/**
 * 
 * @desc 撮合结果订单 order_info
 *
 */
public class OrderInfo extends AbstractBaseEntity implements java.io.Serializable {
	private static final long serialVersionUID = 1L;
	/**买标识*/
	private Long buyId;
	/**买交易数量*/
	private BigDecimal buyAmount;
	/**交易价格*/
	private BigDecimal buyPrice;
	/**售标识*/
	private Long sellId;
	/**售数量*/
	private BigDecimal sellAmount;
	/**售价格*/
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
