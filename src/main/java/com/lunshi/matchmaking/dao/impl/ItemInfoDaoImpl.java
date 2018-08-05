package com.lunshi.matchmaking.dao.impl;

import com.common.util.DefaultBaseDao;
import com.lunshi.matchmaking.dao.ItemInfoDao;
import com.lunshi.matchmaking.domain.ItemInfo;
import org.springframework.stereotype.Component;

/**
 * 
 * @desc 撮合需求 item_info
 *
 */
@Component
public class ItemInfoDaoImpl extends DefaultBaseDao<ItemInfo> implements ItemInfoDao  {
	private static final long serialVersionUID = 1L;
	private final static String NAMESPACE = "com.lunshi.matchmaking.dao.ItemInfoDao.";
	
	@Override
	public String getNameSpace(String statement) {		
		return NAMESPACE+statement;
	}
	
}
