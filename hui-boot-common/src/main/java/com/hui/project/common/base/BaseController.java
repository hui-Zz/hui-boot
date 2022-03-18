package com.hui.project.common.base;

import cn.hutool.core.util.StrUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hui.project.common.page.PageDomain;
import com.hui.project.common.page.PageUtils;
import com.hui.project.common.page.TableDataInfo;
import com.hui.project.common.page.TableSupport;
import com.hui.project.common.utils.SqlUtil;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * BaseController
 *
 * @author hui
 */
@Slf4j
public class BaseController {
    protected final Logger logger = LoggerFactory.getLogger(this.getClass());
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
     * 设置请求分页数据
     */
    protected void startPage() {
        PageUtils.startPage();
    }

    /**
     * 设置请求排序数据
     */
    protected void startOrderBy() {
        PageDomain pageDomain = TableSupport.buildPageRequest();
        if (StrUtil.isNotBlank(pageDomain.getOrderBy())) {
            String orderBy = SqlUtil.escapeOrderBySql(pageDomain.getOrderBy());
            PageHelper.orderBy(orderBy);
        }
    }

    /**
     * 响应请求分页数据
     */
    @SuppressWarnings({"rawtypes", "unchecked"})
    protected TableDataInfo getDataTable(List<?> list) {
        PageDomain pageDomain = TableSupport.buildPageRequest();
        Integer pageNum = pageDomain.getPageNum();
        Integer pageSize = pageDomain.getPageSize();
        TableDataInfo rspData = new TableDataInfo();
        rspData.setRows(list);
        rspData.setPageNum(pageNum);
        rspData.setPageSize(pageSize);
        rspData.setTotal(new PageInfo(list).getTotal());
        return rspData;
    }

    /**
     * 响应请求分页数据(修改总数)
     */
    @SuppressWarnings({"rawtypes", "unchecked"})
    protected TableDataInfo getDataTable(List<?> list, Long total) {
        TableDataInfo rspData = getDataTable(list);
        if (total != null) {
            rspData.setTotal(total);
        }
        return rspData;
    }

}
