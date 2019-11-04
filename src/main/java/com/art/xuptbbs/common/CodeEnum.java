package com.art.xuptbbs.common;

/**
 * 响应状态码和说明
 */
public enum  CodeEnum {
    SUCCESS(0,"SUCCESS"),
    FAIL(1, "失败，未知错误！"),
    EMAIL_FAIL(1,"验证码出错"),
    PHOTO_FAIL(1,"文件不能为空"),
    PSWWORRD_FAIL(1,"两次输入的密码不一致"),
    SAME_FAIL(1, "关键信息重复"),
    EMAIL_USED(1, "邮箱已被注册过");

    private final int code;
    private final String msg;

    CodeEnum(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }
    public int getCode() {
        return code;
    }
    public String getMsg() {
        return msg;
    }
}
