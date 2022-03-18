package com.hui.project.common.base;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 分页模型
 *
 * @author hui
 */
@Data
@Accessors(chain = true)
@ApiModel(value = "分页对象", description = "分页对象")
public class PageModel implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "当前页", notes = "当前页")
    protected Long current;
    @ApiModelProperty(value = "分页大小", notes = "分页大小")
    protected Long size;
    @ApiModelProperty(value = "多个升序字段", notes = "多个升序字段")
    protected String[] ascs;
    @ApiModelProperty(value = "多个降序字段", notes = "多个降序字段")
    protected String[] descs;
    @ApiModelProperty(value = "优化查询总数", notes = "优化查询总数")
    protected Boolean optimizeCountSql;
    @ApiModelProperty(value = "是否查询总数", notes = "是否查询总数")
    protected Boolean searchCount;

    public PageModel() {
        this.current = 1L;
        this.size = 10L;
        this.optimizeCountSql = true;
        this.searchCount = true;
    }

    /**
     * 使用Page<T>对象获得返回数据的分页封装
     *
     * @param iPage Page
     */
    //public PageModel(IPage<T> iPage) {
    //	this.wrapperByIPage(iPage);
    //}
    //
    //private void wrapperByIPage(IPage<T> iPage) {
    //	if (null == iPage || CollectionUtils.isEmpty(iPage.getRecords())) {
    //		return;
    //	}
    //	this.current = iPage.getCurrent();
    //	this.size = iPage.getSize();
    //	this.ascs = iPage.ascs();
    //	this.descs = iPage.descs();
    //	//this.totalPage = iPage.getPages();
    //	//this.records = iPage.getRecords();
    //	iPage = null;
    //}
}
