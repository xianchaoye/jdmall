package com.eshop.manager.controller;

import com.eshop.common.utils.EUIDataGridResult;
import com.eshop.common.utils.MallResult;
import com.eshop.manager.api.ContentService;
import com.eshop.manager.entity.TbContent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * 内容管理Controller
 * <p>Title: ContentController</p>
 * <p>Description: </p>
 * <p>Company: www.itcast.com</p> 
 * @version 1.0
 */
@Controller
@RequestMapping("/content")
public class ContentController {

	@Autowired
	private ContentService contentService;
	
	@RequestMapping("/save")
	@ResponseBody
	public MallResult insertContent(TbContent content) {
		MallResult result = contentService.insertContent(content);
		return result;
	}

	/**
	 * 根据类别id查询内容列表
	 * @param categoryId
	 * @param page
	 * @param rows
	 * @return
	 */
	@RequestMapping("/query/list")
	@ResponseBody
	public EUIDataGridResult queryListForPage(long categoryId,Integer page, Integer rows) {
		return contentService.getContentList(categoryId,page,rows);
	}
}
