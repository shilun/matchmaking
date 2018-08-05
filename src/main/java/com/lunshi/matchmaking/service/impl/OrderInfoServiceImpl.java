package com.lunshi.matchmaking.service.impl; 

import java.util.List;

import javax.annotation.Resource;

import com.common.util.AbstractBaseDao;
import com.common.util.DefaultBaseService;

import com.lunshi.matchmaking.domain.OrderInfo;
import com.lunshi.matchmaking.dao.OrderInfoDao;
import com.lunshi.matchmaking.service.OrderInfoService;
import org.springframework.stereotype.Service;


/**
 * 
 * @desc 撮合结果订单 order_info
 *
 */
@Service
public class OrderInfoServiceImpl extends DefaultBaseService<OrderInfo> implements OrderInfoService  {

	@Resource
	private OrderInfoDao orderInfoDao;
	
	
	@Override
	public AbstractBaseDao<OrderInfo> getBaseDao() {
		return orderInfoDao;
	}
	
}
