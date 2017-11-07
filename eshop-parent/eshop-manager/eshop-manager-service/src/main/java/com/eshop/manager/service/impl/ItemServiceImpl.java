package com.eshop.manager.service.impl;

import com.eshop.manager.api.ItemService;
import com.eshop.manager.entity.TbItem;
import com.eshop.manager.mapper.TbItemMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by ADMIN on 2017/11/7.
 */
@Service("itemService")
public class ItemServiceImpl implements ItemService {
    @Autowired
    private TbItemMapper itemMapper;
    @Override
    public TbItem getItemById(long itemId) {
        return itemMapper.selectByPrimaryKey(itemId);
    }
}
