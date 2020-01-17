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
		String min = (String) params.get("min");
		String max = (String) params.get("max");
		String sort = (String)params.get("sort");
		Integer iSort = null;
		if(StringUtils.isNotBlank(sort)) {
			iSort =  Integer.parseInt(sort);
		}
		QueryWrapper<GoodsEntity> queryWrapper = new QueryWrapper<>();
		queryWrapper
			.eq(StringUtils.isNotBlank(name),"name", name)
			.like(StringUtils.isNotBlank(key),"intro", key)
			.between(StringUtils.isNotBlank(min) && StringUtils.isNotBlank(max), "price", min, max)
			.gt(StringUtils.isNotBlank(min),  "price", min)
			.le(StringUtils.isNotBlank(max), "price", max);
		if(iSort!=null) {
			queryWrapper
			.orderByDesc(iSort==1, "price")
			.orderByAsc(iSort==2, "price")
			.orderByDesc(iSort==3, "num")
			.orderByAsc(iSort==4, "num");
		}
			

		IPage<GoodsEntity> page = this.page(new Query<GoodsEntity>().getPage(params), queryWrapper);

		return new PageUtils(page);
	}

}