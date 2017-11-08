package com.eshop.manager.controller;

import com.eshop.common.utils.EUIDataGridResult;
import com.eshop.common.utils.MallResult;
import com.eshop.manager.api.ItemService;
import com.eshop.manager.entity.TbItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by ADMIN on 2017/11/7.
 */
@Controller
public class ItemController {

    @Autowired
    private ItemService itemService;

    @RequestMapping(value = "/item/{itemId}")
    @ResponseBody
    public TbItem getItem(@PathVariable long itemId) {
        TbItem item = itemService.getItemById(itemId);
        return item;
    }
    /**
     * 获取商品列表
     * @param page
     * @param rows
     * @return
     */
    @RequestMapping("/item/list")
    @ResponseBody
    public EUIDataGridResult getItemForPage(Integer page, Integer rows){
        return itemService.getItemForPage(page, rows);
    }
    /**
     * 删除商品
     * @return
     */
    @RequestMapping("/item/delete")
    @ResponseBody
    public MallResult getItemForPage(Long[] ids){
        return itemService.deleteItemByIds(ids);
    }
}
