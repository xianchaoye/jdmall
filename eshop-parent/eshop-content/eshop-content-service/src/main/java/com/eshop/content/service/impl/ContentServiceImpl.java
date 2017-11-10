package com.eshop.content.service.impl;

import com.eshop.common.utils.EUIDataGridResult;
import com.eshop.common.utils.JsonUtils;
import com.eshop.common.utils.MallResult;
import com.eshop.content.api.ContentService;
import com.eshop.manager.entity.TbContent;
import com.eshop.manager.entity.TbContentExample;
import com.eshop.manager.mapper.TbContentMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Date;
import java.util.List;


/**
 * 内容管理
 * <p>Title: ContentServiceImpl</p>
 * <p>Description: </p>
 * <p>Company: www.itcast.com</p>
 *
 * @version 1.0
 */
@Service("contentService")
public class ContentServiceImpl implements ContentService {

    @Autowired
    private TbContentMapper contentMapper;

    @Value("${INDEX_CONTENT}")
    private String INDEX_CONTENT;
    @Autowired
    private JedisClient jedisClient;
    @Override
    public MallResult insertContent(TbContent content) {
        //补全pojo内容
        content.setCreated(new Date());
        content.setUpdated(new Date());
        contentMapper.insert(content);

        //添加缓存同步逻辑
        try {
            jedisClient.hdel(INDEX_CONTENT,content.getCategoryId()+"");
        } catch (Exception e) {
            e.printStackTrace();
        }

        return MallResult.ok();
    }

    @Override
    public MallResult deleteContent(Long[] ids) {
        TbContentExample example = new TbContentExample();
        TbContentExample.Criteria criteria = example.createCriteria();
        criteria.andIdIn(Arrays.asList(ids));
        int i = contentMapper.deleteByExample(example);
        if (i > 0) {
            return MallResult.ok();
        }

        return MallResult.build(500, "删除失败异常");
    }

    @Override
    public EUIDataGridResult getContentList(long contentCid, Integer page, Integer pageSize) {
        //从缓存中取内容
        /*try {
			String result = jedisClient.hget(INDEX_CONTENT_REDIS_KEY, contentCid + "");
			if (!StringUtils.isBlank(result)) {
				//把字符串转换成list
				List<TbContent> resultList = JsonUtils.jsonToList(result, TbContent.class);
				return resultList;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}*/

        //根据内容分类id查询内容列表
        TbContentExample example = new TbContentExample();
        TbContentExample.Criteria criteria = example.createCriteria();
        criteria.andCategoryIdEqualTo(contentCid);
        PageHelper.startPage(page, pageSize);
        //执行查询
        List<TbContent> list = contentMapper.selectByExample(example);
        PageInfo info = new PageInfo(list);
        EUIDataGridResult result = new EUIDataGridResult(info.getTotal(), list);

        //向缓存中添加内容
		/*try {
			//把list转换成字符串
			String cacheString = JsonUtils.objectToJson(list);
			jedisClient.hset(INDEX_CONTENT_REDIS_KEY, contentCid + "", cacheString);

		} catch (Exception e) {
			e.printStackTrace();
		}*/

        return result;
    }

    @Override
    public List<TbContent> getContentByCid(long cid) {
        //先查询缓存
        //添加缓存不能影响正常业务逻辑
        try {
            //查询缓存
            String json = jedisClient.hget(INDEX_CONTENT, cid + "");
            //查询到结果，把json转换成List返回
            if (StringUtils.isNotBlank(json)) {
                List<TbContent> list = JsonUtils.jsonToList(json, TbContent.class);
                return list;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        //缓存中没有命中，需要查询数据库
        TbContentExample example = new TbContentExample();
        TbContentExample.Criteria criteria = example.createCriteria();
        //设置查询条件
        criteria.andCategoryIdEqualTo(cid);
        //执行查询
        List<TbContent> list = contentMapper.selectByExample(example);
        //把结果添加到缓存
        try {
            jedisClient.hset(INDEX_CONTENT, cid + "", JsonUtils.objectToJson(list));
        } catch (Exception e) {
            e.printStackTrace();
        }
        //返回结果
        return list;
    }

}
