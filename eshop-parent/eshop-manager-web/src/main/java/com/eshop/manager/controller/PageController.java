package com.eshop.manager.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 页面跳转controller
 * Created by ADMIN on 2017/10/26.
 */
@Controller
public class PageController {
    /**
     * 跳转首页
     * @return
     */
    @RequestMapping("/")
    public String index() {
        return "index";
    }

    @RequestMapping("/{pageName}")
    public String returnPage(@PathVariable String pageName) {
        return pageName;
    }
}

