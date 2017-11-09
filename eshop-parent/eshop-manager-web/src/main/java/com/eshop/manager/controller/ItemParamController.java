package com.eshop.manager.controller;


import com.eshop.common.utils.MallResult;
import com.eshop.manager.api.ItemParamService;
import com.eshop.manager.entity.TbItemParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by ADMIN on 2017/10/31.
 */
@Controller
@RequestMapping("/item/param")
public class ItemParamController {
    @Autowired
    private ItemParamService itemParamService;

    /**
     * 根据类目id选择规格模板
     * @param itemCatId
     * @return
     */
    @RequestMapping("/query/itemcatid/{itemCatId}")
    @ResponseBody
    public MallResult getItemParamByCid(@PathVariable Long itemCatId) {
        MallResult result = itemParamService.getItemParamByCid(itemCatId);
        return result;
    }

    /**
     * 保存模板
     * @param cid
     * @param paramData
     * @return
     */
    @RequestMapping("/save/{cid}")
    @ResponseBody
    public MallResult insertItemParam(@PathVariable Long cid, String paramData) {
        //创建pojo对象
        TbItemParam itemParam = new TbItemParam();
        itemParam.setItemCatId(cid);
        itemParam.setParamData(paramData);
        MallResult result = itemParamService.insertItemParam(itemParam);
        return result;
    }
}

