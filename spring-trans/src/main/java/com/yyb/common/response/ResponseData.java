package com.yyb.common.response;


import java.io.Serializable;

/**
 * 响应数据封装
 */
public class ResponseData implements Serializable {

    public static String DEFAULT_SUCCESS_MESSAGE = "操作成功";
    public static String DEFAULT_FAIL_MESSAGE = "操作失败";

    /**
     * 状态，-1为失败，1为成功，其他为各种异常情况
     */
    private Integer code;
    /**
     * 返回数据
     */
    private Object data;
    /**
     * 状态消息
     */
    private String message;

    public ResponseData() {
    }

    public ResponseData(Integer code) {
        super();
        this.code = code;
        this.data = null;
    }

    public ResponseData(Integer code, String message) {
        super();
        this.code = code;
        this.message = message;
        this.data = null;
    }

    public ResponseData(Integer code, Object data) {
        super();
        this.code = code;
        this.data = data;
    }

    public ResponseData(Integer code, String message, Object data) {
        super();
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public static ResponseData success() {
        return new ResponseData(HttpStateEnum.SUCCESS.getCode(), DEFAULT_SUCCESS_MESSAGE);
    }

    public static ResponseData success(Object data) {
        return new ResponseData(HttpStateEnum.SUCCESS.getCode(), data);
    }

    public static ResponseData success(Object data, String message) {
        return new ResponseData(HttpStateEnum.SUCCESS.getCode(), message, data);
    }

    public static ResponseData fail() {
        return new ResponseData(HttpStateEnum.FAIL.getCode(), DEFAULT_FAIL_MESSAGE);
    }

    public static ResponseData fail(String message) {
        return new ResponseData(HttpStateEnum.FAIL.getCode(), message);
    }

    public static ResponseData fail(Integer code, String message) {
        return new ResponseData(code, message);
    }

    public static ResponseData fail(Object data, String message) {
        return new ResponseData(HttpStateEnum.FAIL.getCode(), message, data);
    }

    public void setResult(Integer code, String message) {
        this.code = code;
        this.message = message;
        this.data = null;
    }

    public void setResult(Object data, Integer code, String message) {
        this.code = code;
        this.message = message;
        this.data = null;
    }

    /**
     * @return the code
     */
    public Integer getCode() {
        return code;
    }

    /**
     * @param code the code to set
     */
    public void setCode(Integer code) {
        this.code = code;
    }

    /**
     * @return the data
     */
    public Object getData() {
        return data;
    }

    /**
     * @param data the data to set
     */
    public void setData(Object data) {
        this.data = data;
    }

    /**
     * @return the message
     */
    public String getMessage() {
        return message;
    }

    /**
     * @param message the message to set
     */
    public void setMessage(String message) {
        this.message = message;
    }

}
