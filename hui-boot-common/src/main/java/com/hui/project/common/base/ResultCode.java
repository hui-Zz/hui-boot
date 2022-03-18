package com.hui.project.common.base;

/**
 * 响应码枚举，参考HTTP状态码的语义
 */
public enum ResultCode {

    //成功
    SUCCESS(200),

    //======客户端错误======

    //失败
    FAIL(400),

    //未认证（签名错误）
    UNAUTHORIZED(401),

    //接口不存在
    NOT_FOUND(404),

    //账号不存在
    NO_USER(406),

    //账号或密码错误
    USER_PWD_ERROR(408),

    //帐号已被禁用
    DISABLED_USER(409),

    //请求参数为null
    REQUEST_PARAM_NULL(448),

    //请求参数错误
    REQUEST_PARAM_ERROR(449),

    //======服务端错误======

    //服务器内部错误
    INTERNAL_SERVER_ERROR(500),


    //返回数据错误
    RESPONSE_DATA_NULL(508),

    //返回数据错误
    RESPONSE_DATA_ERROR(509);


    public int code;

    ResultCode(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}
