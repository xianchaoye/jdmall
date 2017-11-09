package com.eshop.manager.api;


import com.eshop.common.utils.CatResult;
import com.eshop.common.utils.EUITreeNode;

import java.util.List;

/**
 * Created by ADMIN on 2017/10/26.
 */
public interface ItemCatService {
    List<EUITreeNode> getItemCatNode(long parentId);
    public CatResult getItemCatList();
}
