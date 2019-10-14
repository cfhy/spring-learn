package com.yyb.common.response;

/**
 * 返回状态
 */
public enum HttpStateEnum {
    //成功
    SUCCESS(1, "成功"),
    //失败
    FAIL(-1, "失败");

    private Integer code;
    private String desc;

    HttpStateEnum(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public Integer getCode() {
        return code;
    }
}
