package com.eshop.manager.service.impl;

import com.eshop.common.utils.MallResult;
import com.eshop.manager.api.ItemParamService;
import com.eshop.manager.entity.TbItemParam;
import com.eshop.manager.entity.TbItemParamExample;
import com.eshop.manager.mapper.TbItemParamMapper;
import com.github.pagehelper.PageHelper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * Created by ADMIN on 2017/10/30.
 */
@Service("itemParamService")
public class ItemParamServiceImpl implements ItemParamService {
    @Autowired
    private TbItemParamMapper itemParamMapper;
    @Override
    public MallResult getItemParamByCid(long cid) {
        TbItemParamExample example = new TbItemParamExample();
        TbItemParamExample.Criteria criteria = example.createCriteria();
        criteria.andItemCatIdEqualTo(cid);
        List<TbItemParam> list = itemParamMapper.selectByExampleWithBLOBs(example);
        //判断是否查询到结果
        if (list != null && list.size() > 0) {
            return MallResult.ok(list.get(0));
        }

        return MallResult.ok();
    }

    @Override
    public MallResult insertItemParam(TbItemParam itemParam) {
        //补全pojo
        itemParam.setCreated(new Date());
        itemParam.setUpdated(new Date());
        //插入到规格参数模板表
        itemParamMapper.insert(itemParam);
        return MallResult.ok();
    }
}
