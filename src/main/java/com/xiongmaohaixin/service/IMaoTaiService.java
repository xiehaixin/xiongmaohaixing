package com.xiongmaohaixin.service;

/**
 * ClassName:IMaoTaiService
 * Package:com.xiongmaohaixin.service
 * Description:茅台功能
 *
 * @Date:2020/11/17 10:59
 * @Author:XHX
 */
public interface IMaoTaiService {
    void setCode(String code);

    void clearCode();

    String getCode();

    String registerGetSlideCode(String lamboKey, String token);

    String getLamboKey();
}
