package com.xiongmaohaixin.controller;

import com.xiongmaohaixin.exception.BaseRuntimeException;
import com.xiongmaohaixin.response.ResultCode;
import com.xiongmaohaixin.service.IAnalysisMessageService;
import com.xiongmaohaixin.service.IMaoTaiService;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/api/maotaiApi")
public class MaotaiApiController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private IMaoTaiService maoTaiService;

    @Autowired
    private IAnalysisMessageService analysisMessageService;


    /**
     * 获取验证码
     * @return
     */
    @PostMapping("/register/getSlideCode")
    public String registerGetSlideCode(){
        String token = maoTaiService.getCode();
        if(StringUtils.isBlank(token)){
            token = UUID.randomUUID().toString().replaceAll("-","").toUpperCase();
        }
        return maoTaiService.registerGetSlideCode(token+"#"+analysisMessageService.getXHXMaoTaiOpenId(), token);
    }

}
