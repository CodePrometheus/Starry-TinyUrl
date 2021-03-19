package com.star.domain;

/**
 * 响应结果包装类
 *
 * @Author: zzStar
 * @Date: 03-19-2021 10:50
 */
public class ResultGenerator {

    /**
     * 默认响应结果
     */
    private static final String DEFAULT_SUCCESS_MESSAGE = "SUCCESS";

    public static AjaxResult<String> genSuccessResult() {
        return new AjaxResult<String>()
                .setCode(AjaxResultCode.SUCCESS)
                .setMessage(DEFAULT_SUCCESS_MESSAGE);
    }

    public static <T> AjaxResult<T> genSuccessResult(T data) {
        return new AjaxResult<T>()
                .setCode(AjaxResultCode.SUCCESS)
                .setMessage(DEFAULT_SUCCESS_MESSAGE)
                .setData(data);
    }

    public static AjaxResult<String> genFailResult(String message) {
        return new AjaxResult<String>()
                .setCode(AjaxResultCode.FAIL)
                .setMessage(message);
    }
}
