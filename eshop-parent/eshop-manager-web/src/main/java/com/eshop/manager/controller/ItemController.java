package com.eshop.manager.controller;

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
}
