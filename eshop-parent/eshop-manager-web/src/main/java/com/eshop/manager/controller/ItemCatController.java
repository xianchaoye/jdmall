package com.eshop.manager.controller;

import com.eshop.common.utils.EUITreeNode;
import com.eshop.manager.api.ItemCatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * 商品类别Controller
 * Created by ADMIN on 2017/10/26.
 */
@Controller
@RequestMapping("/item/cat")
public class ItemCatController {

    @Autowired
    private ItemCatService itemCatService;

    /**
     * 查询商品类目
     * @param parentId
     * @return
     */
    @RequestMapping("/list")
    @ResponseBody
    public List<EUITreeNode> getItemCatNodes(@RequestParam(value = "id",defaultValue = "0")long parentId){
        return itemCatService.getItemCatNode(parentId);
    }


}
