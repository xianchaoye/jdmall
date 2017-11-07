package com.eshop.manager.service.impl;

import com.eshop.common.utils.EUIDataGridResult;
import com.eshop.manager.api.ItemService;
import com.eshop.manager.entity.TbItem;
import com.eshop.manager.entity.TbItemExample;
import com.eshop.manager.mapper.TbItemMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

    @Override
    public EUIDataGridResult getItemForPage(Integer page, Integer rows) {
        TbItemExample example = new TbItemExample();
        PageHelper.startPage(page,rows);
        List<TbItem> tbItems = itemMapper.selectByExample(example);
        PageInfo pageInfo = new PageInfo(tbItems);
        EUIDataGridResult result = new EUIDataGridResult(pageInfo.getTotal(),tbItems);
        return result;
    }
}
