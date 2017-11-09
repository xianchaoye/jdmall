package com.eshop.manager.service.impl;

import com.eshop.common.utils.EUIDataGridResult;
import com.eshop.common.utils.JsonUtils;
import com.eshop.common.utils.MallResult;
import com.eshop.manager.api.ContentService;
import com.eshop.manager.entity.TbContent;
import com.eshop.manager.entity.TbContentExample;
import com.eshop.manager.mapper.TbContentMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;


/**
 * 内容管理
 * <p>Title: ContentServiceImpl</p>
 * <p>Description: </p>
 * <p>Company: www.itcast.com</p> 
 * @version 1.0
 */
@Service("contentService")
public class ContentServiceImpl implements ContentService {

	@Autowired
	private TbContentMapper contentMapper;
	/*@Value("${INDEX_CONTENT_REDIS_KEY}")
	private String INDEX_CONTENT_REDIS_KEY;
	@Autowired
	private JedisClient jedisClient;*/
	@Override
	public MallResult insertContent(TbContent content) {
		//补全pojo内容
		content.setCreated(new Date());
		content.setUpdated(new Date());
		contentMapper.insert(content);
		
		//添加缓存同步逻辑
		try {
			//HttpClientUtil.doGet(REST_BASE_URL + REST_CONTENT_SYNC_URL + content.getCategoryId());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return MallResult.ok();
	}

	@Override
	public EUIDataGridResult getContentList(long contentCid,Integer page,Integer pageSize) {
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
		PageHelper.startPage(page,pageSize);
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

}
