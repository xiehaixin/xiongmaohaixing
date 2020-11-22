package com.xiongmaohaixin.controller;

import com.xiongmaohaixin.service.IMaoTaiService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/maotaiApi")
public class MaotaiApiController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private IMaoTaiService maoTaiService;

    @RequestMapping("/register/getSlideCode")
    public String registerGetSlideCode(String lamboKey, String token){
        return maoTaiService.registerGetSlideCode(lamboKey,token);
    }
    
}
