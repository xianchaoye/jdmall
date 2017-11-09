package com.eshop.manager.api;


import com.eshop.common.utils.EUIDataGridResult;
import com.eshop.common.utils.MallResult;
import com.eshop.manager.entity.TbItemParam;

/**
 * 商品规格列表
 * Created by ADMIN on 2017/10/30.
 */
public interface ItemParamService {
    public MallResult getItemParamByCid(long cid);
    public MallResult insertItemParam(TbItemParam itemParam);

}
