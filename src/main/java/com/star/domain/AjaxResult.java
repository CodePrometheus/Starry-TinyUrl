package com.star.domain;

import com.alibaba.fastjson.JSON;

/**
 * @Author: zzStar
 * @Date: 03-19-2021 10:47
 */
public class AjaxResult<T> {

    /**
     * 状态码
     */
    private int code;
    /**
     * 返回信息
     */
    private String message;
    /**
     * 返回数据
     */
    private T data;


    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public AjaxResult<T> setCode(AjaxResultCode resultCode) {
        this.code = resultCode.code();
        return this;
    }

    public AjaxResult<T> setMessage(String message) {
        this.message = message;
        return this;
    }

    public T getData() {
        return data;
    }

    public AjaxResult<T> setData(T data) {
        this.data = data;
        return this;
    }


    /**
     * 得到JSON格式字符串
     *
     * @return JSON格式字符串
     */
    public String toJsonStr() {
        return JSON.toJSONString(this);
    }
}
