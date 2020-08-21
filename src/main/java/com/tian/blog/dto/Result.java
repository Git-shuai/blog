package com.tian.blog.dto;

import com.tian.blog.enums.ResponseCode;

/**
 * @author tian
 * @date 2020/8/13
 */
public class Result<T> {
    private int code;
    private String message;
    private T data;

    public Result() {
    }


    public Result(ResponseCode responseCode, T data) {
        this.code = responseCode.getCode();
        this.message = responseCode.getMessage();
        this.data = data;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

}
