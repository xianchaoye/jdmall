package com.eshop.manager.controller;

import com.eshop.common.utils.MallResult;
import com.eshop.search.service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * 索引库维护Controller
 * <p>Title: IndexManagerController</p>
 * <p>Description: </p>
 * <p>Company: www.itcast.cn</p> 
 * @version 1.0
 */
@Controller
public class IndexManagerController {
	
	@Autowired
	private SearchService searchService;

	@RequestMapping("/index/import")
	@ResponseBody
	public MallResult importIndex() {
		MallResult taotaoResult = searchService.importItemsToIndex();
		return taotaoResult;
	}
}
