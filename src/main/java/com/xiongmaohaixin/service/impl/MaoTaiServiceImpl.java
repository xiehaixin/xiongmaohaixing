package com.xiongmaohaixin.service.impl;

import com.xiongmaohaixin.service.IMaoTaiService;
import org.springframework.stereotype.Service;

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
}
