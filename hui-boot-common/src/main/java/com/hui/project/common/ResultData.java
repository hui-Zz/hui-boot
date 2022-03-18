package com.hui.project.common;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "返回说明")
public class ResultData<T> {

    @ApiModelProperty(value = "成功标识；true：成功；false:失败")
    private boolean success;

    @ApiModelProperty(value = "返回状态码；200:成功")
    private String code;

    @ApiModelProperty(value = "描述信息")
    private String message;

    private T data;

}