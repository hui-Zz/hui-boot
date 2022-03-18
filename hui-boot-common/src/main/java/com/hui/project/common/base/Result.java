package com.hui.project.common.base;

import com.alibaba.fastjson.JSON;

import java.io.Serializable;


/**
 * 统一API响应结果封装
 */
public class Result<T> implements Serializable {

    private int code;
    private String message;
    private T data;

    public boolean fail() {
        return this.code != ResultCode.SUCCESS.getCode();
    }

    public boolean succ() {
        return this.code == ResultCode.SUCCESS.getCode();
    }

    public Result setCode(ResultCode resultCode) {
        this.code = resultCode.code;
        return this;
    }

    public int getCode() {
        return code;
    }

    public Result setCode(int code) {
        this.code = code;
        return this;
    }

    public String getMessage() {
        return message;
    }

    public Result setMessage(String message) {
        this.message = message;
        return this;
    }

    public T getData() {
        return data;
    }

    public Result setData(T data) {
        this.data = data;
        return this;
    }

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }
}
