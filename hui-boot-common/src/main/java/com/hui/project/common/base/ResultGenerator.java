package com.hui.project.common.base;


import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 响应结果生成工具
 */
public class ResultGenerator {

    private static Logger log = LoggerFactory.getLogger("socketLog");

    private static final String DEFAULT_SUCCESS_MESSAGE = "成功";

    public static Result getSuccessResult() {
        return new Result().setCode(ResultCode.SUCCESS).setMessage(DEFAULT_SUCCESS_MESSAGE);
    }

    public static Result getSuccessResult(Object data) {
        return new Result().setCode(ResultCode.SUCCESS).setMessage(DEFAULT_SUCCESS_MESSAGE).setData(data);
    }

    public static Result getSuccessResult(Object data, String message) {
        return new Result().setCode(ResultCode.SUCCESS).setMessage(message).setData(data);
    }


    public static Result getFailResult(String message) {
        return new Result().setCode(ResultCode.FAIL).setMessage(message);
    }

    public static Result getFailResult(Integer code, String message) {
        return new Result().setCode(code).setMessage(message);
    }

    public static Result getFailResult(Integer code, String message, Object data) {
        return new Result().setCode(code).setMessage(message).setData(data);
    }

    public static Result getFailResultLog(String message) {
        log.error(message);
        return new Result().setCode(ResultCode.FAIL).setMessage(message);
    }


    public static Result getFailResultLog(String message, Object logData, String action) {
        log.error(action + " , " + message + "，logData = " + JSONObject.toJSONString(logData));
        return new Result().setCode(ResultCode.FAIL).setMessage(message);
    }

    public static Result getFailResultLog(String message, Object logData, Exception e) {
        log.error(message + "，logData = " + JSONObject.toJSONString(logData) + ",exception=" + e.getMessage());
        return new Result().setCode(ResultCode.FAIL).setMessage(message);
    }

}
