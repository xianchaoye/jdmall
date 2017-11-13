package com.eshop.search.controller;

import com.eshop.common.utils.MallResult;
import com.eshop.common.utils.SearchResult;
import com.eshop.search.service.SearchService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by ADMIN on 2017/11/3.
 */
@Controller
public class SolrController {
    @Autowired
    private SearchService searchService;

    @RequestMapping(value = "/search", method = RequestMethod.GET)
    public String search(@RequestParam("q") String queryString,
                             @RequestParam(defaultValue = "1") Integer page,
                             @RequestParam(defaultValue = "60") Integer rows,
                             Model model) {
        //查询条件不能为空
        if (StringUtils.isBlank(queryString)) {

        }
        SearchResult searchResult = null;
        try {
            queryString = new String(queryString.getBytes("iso8859-1"), "utf-8");
            searchResult = searchService.search(queryString, page,rows);
            //把结果传递给页面
            model.addAttribute("query", queryString);
            model.addAttribute("totalPages", searchResult.getPageCount());
            model.addAttribute("itemList", searchResult.getItemList());
            model.addAttribute("page", page);
        } catch (Exception e) {
            e.printStackTrace();
        }
        //返回逻辑视图
        return "search";

    }
}
