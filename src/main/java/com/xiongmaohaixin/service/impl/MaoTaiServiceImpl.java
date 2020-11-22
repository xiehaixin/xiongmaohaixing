package com.xiongmaohaixin.service.impl;

import com.xiongmaohaixin.service.IMaoTaiService;
import com.xiongmaohaixin.utils.GZH_mao_tai;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * ClassName:MaoTaiServiceImpl
 * Package:com.xiongmaohaixin.service.impl
 * Description:请为该功能做描述
 *
 * @Date:2020/11/17 11:01
 * @Author:XHX
 */
@Service
public class MaoTaiServiceImpl implements IMaoTaiService {

    private GZH_mao_tai mt = new GZH_mao_tai();

    private String code = "";

    @Override
    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public void clearCode() {
        this.code = "";
    }

    @Override
    public String getCode() {
        return this.code;
    }

    /** 获取验证码 */
    @Override
    public String registerGetSlideCode(String lamboKey, String token){
        Map<String,String> param = new HashMap<>();
        String result = mt.goPostHttps("/api/rsv-server/anon/register/getSlideCode", param, lamboKey,token);
        return result;
    }

}
