package com.xiongmaohaixin.response;

/**
 * ClassName:ResultVO
 * Package:com.evian.sqct.response
 * Description:自定义统一响应体
 *
 * @Date:2020/6/11 9:59
 * @Author:XHX
 */
public class ResultVO<T> {
    private int code;
    private String message;
    private T data;

    public ResultVO(T data) {
        this(ResultCode.CODE_SUC, data);
    }

    public ResultVO() {
        this(ResultCode.CODE_SUC);
    }

    public ResultVO(int code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }


    public ResultVO(ResultCode code, T data) {
        this.code = code.getCode();
        this.message = code.getMessage();
        this.data = data;
    }


    public ResultVO(ResultCode code) {
        this.code = code.getCode();
        this.message = code.getMessage();
        this.data = (T)"";
    }

    public ResultVO(int code, String message) {
        this.code = code;
        this.message = message;
        this.data = (T)"";
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public T getData() {
        return data;
    }

    public ResultVO<T> setCode(int code) {
        this.code = code;
        return this;
    }

    public ResultVO<T> setMessage(String message) {
        this.message = message;
        return this;
    }

    public ResultVO<T> setData(T data) {
        this.data = data;
        return this;
    }

    public ResultVO<T> setCode(ResultCode resultCode) {
        this.code = resultCode.getCode();
        this.message = resultCode.getMessage();
        return this;
    }
}
