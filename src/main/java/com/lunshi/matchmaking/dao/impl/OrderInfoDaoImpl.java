package com.lunshi.matchmaking.dao.impl; 
import java.util.List;

import com.common.util.DefaultBaseDao;

import com.lunshi.matchmaking.dao.OrderInfoDao;
import com.lunshi.matchmaking.domain.OrderInfo;
import org.springframework.stereotype.Component;

/**
 * 
 * @desc 撮合结果订单 order_info
 *
 */
@Component
public class OrderInfoDaoImpl extends DefaultBaseDao<OrderInfo> implements OrderInfoDao  {
	private static final long serialVersionUID = 1L;
	private final static String NAMESPACE = "com.lunshi.matchmaking.dao.OrderInfoDao.";
	
	@Override
	public String getNameSpace(String statement) {		
		return NAMESPACE+statement;
	}
	
}
