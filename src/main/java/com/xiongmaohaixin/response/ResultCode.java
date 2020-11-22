package com.xiongmaohaixin.response;

/**
 * ClassName:ResultCode
 * Package:com.evian.sqct.response
 * Description:响应码枚举
 *
 * @Date:2020/6/11 10:02
 * @Author:XHX
 */
public enum ResultCode {

    /** 服务系统异常 */
    CODE_ERROR_SYSTEM(0,"服务系统异常"),
    /** 成功 */
    CODE_SUC(1,"成功"),
    /** 茅台token过期 */
    MAO_TAI_ERROR_TOKEN_PAST_DUE(2,"token过期"),
    /** 请求参数错误 */
    CODE_ERROR_PARAM(101,"请求参数错误"),
    /** 自定义异常 */
    CUSTOM_ERROR(150,"服务系统异常");

    /** 自定义code */
    public final static int CUSTOM_CODE = 150;

    private int code;
    private  String message;

    ResultCode(int code, String message) {
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
