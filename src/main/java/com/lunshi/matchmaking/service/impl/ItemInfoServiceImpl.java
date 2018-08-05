package com.lunshi.matchmaking.service.impl;

import com.common.util.AbstractBaseDao;
import com.common.util.DefaultBaseService;
import com.lunshi.matchmaking.dao.ItemInfoDao;
import com.lunshi.matchmaking.domain.ItemInfo;
import com.lunshi.matchmaking.service.ItemInfoService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;


/**
 * 
 * @desc 撮合需求 item_info
 *
 */
@Service
public class ItemInfoServiceImpl extends DefaultBaseService<ItemInfo> implements ItemInfoService  {

	@Resource
	private ItemInfoDao itemInfoDao;
	
	
	@Override
	public AbstractBaseDao<ItemInfo> getBaseDao() {
		return itemInfoDao;
	}
	
}
