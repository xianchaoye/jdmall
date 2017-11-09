package com.eshop.content.api;


import com.eshop.common.utils.EUIDataGridResult;
import com.eshop.common.utils.MallResult;
import com.eshop.manager.entity.TbContent;

import java.util.List;

public interface ContentService {

    MallResult insertContent(TbContent content);

    MallResult deleteContent(Long[] ids);

    public EUIDataGridResult getContentList(long contentCid, Integer page, Integer pageSize);

    public List<TbContent> getContentByCid(long cid);
}
