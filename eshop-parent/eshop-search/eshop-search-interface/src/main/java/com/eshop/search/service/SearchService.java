package com.eshop.search.service;


import com.eshop.common.utils.MallResult;
import com.eshop.common.utils.SearchResult;

public interface SearchService {
	MallResult importItemsToIndex();
	SearchResult search(String queryString, int page, int rows) throws Exception;
}
