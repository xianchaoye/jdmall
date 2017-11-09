package com.eshop.manager.api;


import com.eshop.common.utils.EUITreeNode;
import com.eshop.common.utils.MallResult;

import java.util.List;

public interface ContentCategoryService {

	List<EUITreeNode> getCategoryList(long parentId);
	MallResult insertContentCategory(long parentId, String name);
}
