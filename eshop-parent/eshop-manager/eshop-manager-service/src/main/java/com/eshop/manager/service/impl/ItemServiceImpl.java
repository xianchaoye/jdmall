package com.eshop.manager.service.impl;

import com.eshop.common.utils.EUIDataGridResult;
import com.eshop.common.utils.IDUtils;
import com.eshop.common.utils.MallResult;
import com.eshop.manager.api.ItemService;
import com.eshop.manager.entity.TbItem;
import com.eshop.manager.entity.TbItemDesc;
import com.eshop.manager.entity.TbItemExample;
import com.eshop.manager.entity.TbItemParamItem;
import com.eshop.manager.mapper.TbItemDescMapper;
import com.eshop.manager.mapper.TbItemMapper;
import com.eshop.manager.mapper.TbItemParamItemMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * Created by ADMIN on 2017/11/7.
 */
@Service("itemService")
public class ItemServiceImpl implements ItemService {
    @Autowired
    private TbItemMapper itemMapper;
    @Autowired
    private TbItemDescMapper itemDescMapper;
    @Autowired
    private TbItemParamItemMapper itemParamItemMapper;
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

    @Override
    public MallResult addNewItem(TbItem item, String desc, String itemParam) throws Exception {
        item.setId(IDUtils.genItemId());
        item.setStatus((byte)1);
        item.setCreated(new Date());
        item.setUpdated(new Date());
        itemMapper.insert(item);
        MallResult mallResult = addItemDesc(item.getId(), desc);
        if (mallResult.getStatus() != 200)
        {
            throw new Exception("添加新产品出错");
        }
        //添加规格参数
        mallResult = insertItemParamItem(item.getId(), itemParam);
        if (mallResult.getStatus() != 200) {
            throw new Exception();
        }
        return  MallResult.ok();
    }
    /**
     * 添加商品描述
     * @param itemId
     * @param itemDesc
     * @return
     */
    private MallResult addItemDesc(long itemId,String itemDesc){
        TbItemDesc desc = new TbItemDesc();
        desc.setItemId(itemId);
        desc.setItemDesc(itemDesc);
        desc.setUpdated(new Date());
        desc.setCreated(new Date());
        itemDescMapper.insert(desc);
        return MallResult.ok();
    }
    /**
     * 添加规格参数
     * <p>Title: insertItemParamItem</p>
     * <p>Description: </p>
     * @param itemId
     * @param itemParam
     * @return
     */
    private MallResult insertItemParamItem(Long itemId, String itemParam) {
        //创建一个pojo
        TbItemParamItem itemParamItem = new TbItemParamItem();
        itemParamItem.setItemId(itemId);
        itemParamItem.setParamData(itemParam);
        itemParamItem.setCreated(new Date());
        itemParamItem.setUpdated(new Date());
        //向表中插入数据
        itemParamItemMapper.insert(itemParamItem);

        return MallResult.ok();

    }
    @Override
    public MallResult deleteItemByIds(Long[] ids) {
        TbItemExample example = new TbItemExample();
        TbItemExample.Criteria criteria = example.createCriteria();
        criteria.andIdIn(Arrays.asList(ids));
        int i = itemMapper.deleteByExample(example);
        if (i>0){
            return MallResult.ok();
        }
        return MallResult.build(500, "处理异常");
    }
}
