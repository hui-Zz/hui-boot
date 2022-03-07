package com.hui.project.common.base;

import com.alibaba.fastjson.util.TypeUtils;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Objects;

/**
 * BaseController
 *
 * @author hui
 */
@Slf4j
public class BaseController {
	/**
	 * 默认每页条目10,最大条目数100
	 */
	protected final long PAGE_DEFAULT_LIMIT = 10L;
	protected final long PAGE_MAX_LIMIT = 100L;
	@Autowired
	protected HttpServletRequest request;
	@Autowired
	protected HttpServletResponse response;

	/**
	 * 默认Fallback方法
	 */
	//public void defaultFallback() {
	//	throw new BaseException("太拥挤了, 请稍后再试~", 500);
	//}

	/**
	 * 获取当前用户id
	 */
	public Long currentUserId() {
		String uid = request.getHeader("USER-ID");
		Assert.notNull(uid, "用户登录ID异常");
		return Long.valueOf(uid);
	}

	/**
	 * 获取分页对象
	 *
	 * @return
	 */
	protected <T> IPage<T> getPage(PageModel pageModel) {
		return getPage(pageModel, false);
	}

	/**
	 * 获取分页对象
	 *
	 * @param openSort
	 * @return
	 */
	protected <T> Page<T> getPage(PageModel pageModel, boolean openSort) {
		if (Objects.isNull(pageModel)) {
			pageModel = new PageModel();
		}
		int index = 1;
		// 页数
		Long cursor = TypeUtils.castToLong(pageModel.getCurrent());
		cursor = Objects.isNull(cursor) ? index : cursor;
		// 分页大小
		Long limit = TypeUtils.castToLong(pageModel.getSize());
		limit = Objects.isNull(limit) ? PAGE_DEFAULT_LIMIT : limit;
		// 是否查询分页
		Boolean searchCount = TypeUtils.castToBoolean(pageModel.getSearchCount());
		searchCount = Objects.isNull(searchCount) ? true : searchCount;
		limit = limit > PAGE_MAX_LIMIT ? PAGE_MAX_LIMIT : limit;
		Page<T> page = new Page<>(cursor, limit, searchCount);
		if (openSort) {
			page.setAsc(pageModel.getAscs());
			page.setDesc(pageModel.getDescs());
		}
		return page;
	}

}
