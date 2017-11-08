package com.eshop.manager.api;

import com.eshop.common.utils.EUIDataGridResult;
import com.eshop.common.utils.MallResult;
import com.eshop.manager.entity.TbItem;

/**
 * Created by ADMIN on 2017/11/7.
 */
public interface ItemService {

    TbItem getItemById(long itemId);

    EUIDataGridResult getItemForPage(Integer page, Integer rows);

    MallResult deleteItemByIds(Long[] ids);
}
