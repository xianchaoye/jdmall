package com.eshop.manager.controller;

import com.eshop.common.utils.EUITreeNode;
import com.eshop.common.utils.MallResult;
import com.eshop.content.api.ContentCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;


/**
 * 内容分类管理
 * <p>Title: ContentCategoryController</p>
 * <p>Description: </p>
 * <p>Company: www.itcast.com</p> 
 * @version 1.0
 */
@Controller
@RequestMapping("/content/category")
public class ContentCategoryController {

	@Autowired
	private ContentCategoryService contentCategoryService;
	
	@RequestMapping("/list")
	@ResponseBody
	public List<EUITreeNode> getContentCatList(@RequestParam(value="id", defaultValue="0")Long parentId) {
		List<EUITreeNode> list = contentCategoryService.getCategoryList(parentId);
		return list;
	}
	
	@RequestMapping("/create")
	@ResponseBody
	public MallResult createContentCategory(Long parentId, String name) {
		MallResult result = contentCategoryService.insertContentCategory(parentId, name);
		return result;
	}
}
