package com.lunshi.matchmaking.service.impl;

import com.common.util.AbstractBaseDao;
import com.common.util.DefaultBaseService;
import com.lunshi.matchmaking.domain.ItemInfo;
import com.lunshi.matchmaking.service.ItemInfoService;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class ItemInfoServiceImpl extends DefaultBaseService<ItemInfo> implements ItemInfoService {

    @Override
    public AbstractBaseDao<ItemInfo> getBaseDao() {
        return null;
    }
}
