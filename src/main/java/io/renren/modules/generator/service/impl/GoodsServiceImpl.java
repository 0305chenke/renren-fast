package io.renren.modules.generator.service.impl;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;

import io.renren.modules.generator.dao.GoodsDao;
import io.renren.modules.generator.entity.GoodsEntity;
import io.renren.modules.generator.service.GoodsService;

@Service("goodsService")
public class GoodsServiceImpl extends ServiceImpl<GoodsDao, GoodsEntity> implements GoodsService {

	@Override
	public PageUtils queryPage(Map<String, Object> params) {
		String key = (String) params.get("key");
		String name = (String) params.get("value");
		
		QueryWrapper<GoodsEntity> queryWrapper = new QueryWrapper<>();
		queryWrapper
			.eq(StringUtils.isNotBlank(name),"name", name)
			.like(StringUtils.isNotBlank(key),"intro", key);

		IPage<GoodsEntity> page = this.page(new Query<GoodsEntity>().getPage(params), queryWrapper);

		return new PageUtils(page);
	}

}