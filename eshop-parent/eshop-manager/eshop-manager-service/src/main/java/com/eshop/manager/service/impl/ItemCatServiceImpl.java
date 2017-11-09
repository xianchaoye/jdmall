package com.eshop.manager.service.impl;

import com.eshop.common.utils.CatNode;
import com.eshop.common.utils.CatResult;
import com.eshop.common.utils.EUITreeNode;
import com.eshop.manager.api.ItemCatService;
import com.eshop.manager.entity.TbItemCat;
import com.eshop.manager.entity.TbItemCatExample;
import com.eshop.manager.mapper.TbItemCatMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ADMIN on 2017/10/26.
 */
@Service("itemCatService")
public class ItemCatServiceImpl implements ItemCatService {

    @Autowired
    private TbItemCatMapper itemCatMapper;

    /**
     * 查询商品类目节点
     *
     * @param parentId
     * @return
     */
    @Override
    public List<EUITreeNode> getItemCatNode(long parentId) {
        TbItemCatExample example = new TbItemCatExample();
        TbItemCatExample.Criteria criteria = example.createCriteria();
        criteria.andParentIdEqualTo(parentId);
        List<TbItemCat> tbItemCats = itemCatMapper.selectByExample(example);
        List<EUITreeNode> nodes = new ArrayList<EUITreeNode>();
        for (TbItemCat cat : tbItemCats) {
            EUITreeNode node = new EUITreeNode(cat.getId(), cat.getName(), cat.getIsParent() ? "closed" : "open");
            nodes.add(node);
        }
        return nodes;
    }

    @Override
    public CatResult getItemCatList() {

        CatResult catResult = new CatResult();
        //查询分类列表
        catResult.setData(getCatList(0));
        return catResult;
    }

    /**
     * 查询分类列表
     * <p>Title: getCatList</p>
     * <p>Description: </p>
     *
     * @param parentId
     * @return
     */
    private List<?> getCatList(long parentId) {
        //创建查询条件
        TbItemCatExample example = new TbItemCatExample();
        TbItemCatExample.Criteria criteria = example.createCriteria();
        criteria.andParentIdEqualTo(parentId);
        //执行查询
        List<TbItemCat> list = itemCatMapper.selectByExample(example);
        //返回值list
        List resultList = new ArrayList<>();
        //向list中添加节点
        int count = 0;
        for (TbItemCat tbItemCat : list) {
            //判断是否为父节点
            if (tbItemCat.getIsParent()) {
                CatNode catNode = new CatNode();
                if (parentId == 0) {
                    catNode.setName("<a href='/products/" + tbItemCat.getId() + ".shtml'>" + tbItemCat.getName() + "</a>");
                } else {
                    catNode.setName(tbItemCat.getName());
                }
                catNode.setUrl("/products/" + tbItemCat.getId() + ".shtml");
                catNode.setItem(getCatList(tbItemCat.getId()));

                resultList.add(catNode);
                count++;
                //第一层只取14条记录
                if (parentId == 0 && count >= 14) {
                    break;
                }
                //如果是叶子节点
            } else {
                resultList.add("/products/" + tbItemCat.getId() + ".shtml|" + tbItemCat.getName());
            }
        }
        return resultList;
    }
}
