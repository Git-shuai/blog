package com.tian.blog.enums;

/**
 * @author tian
 * @date 2020/8/13
 */
public enum ResponseCode {

    /**
     *
     */
    ERROR_CODE(999,"操作失败！"),
    ERROR_QUERY_CODE(999,"查询失败，数据库中没有！"),
    ERROR_LOGIN_CODE(999,"登录失败，数据库中没有！"),
    ERROR_INSERT_CODE(999,"用户名已经存在请换一个试试！"),
    ERROR_SMS_CODE(999,"验证码错误！请重新输入"),



    SUCCESS_CODE(200,"操作成功"),
    SUCCESS_INSERT_CODE(200,"插入成功！"),
    SUCCESS_QUERY_CODE(200,"查询成功"),
    SUCCESS_UPDATE_CODE(200,"更新成功"),
    SUCCESS_ALIBABA_CODE(200,"验证码已发送，请在30分钟内输入")
    ;


    private int code;
    private String message;

    ResponseCode(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
