package com.xiongmaohaixin;

import com.xiongmaohaixin.service.IMaoTaiService;
import com.xiongmaohaixin.service.impl.MaoTaiServiceImpl;
import com.xiongmaohaixin.utils.GZH_mao_tai;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class Test02 {

    public static String token = "073aWfll276P064Ehvnl2YZx3m1aWflt";
    public static String openid = "mQMtJNLoZfN7fZHbqLTIfs2bWuloHOjQOTQoXMsYoFk=";

    public static void main(String[] args) {
        GZH_mao_tai mt = new GZH_mao_tai();
        Map<String,String> param = new HashMap<>();
//        param.put("custId","******");
        token = UUID.randomUUID().toString().replaceAll("-","").toUpperCase();
        param.put("token",token+"&code");
        String s = mt.goPostHttps("/api/rsv-server/anon/wechat/getOpneId", param, token + "#" + openid, token);
//        String s = mt.goPostHttps("/api/rsv-server/anon/consumer/checkUser", param, token + "#" + openid, token);
//        String s = mt.goPostHttps("/api/rsv-server/anon/register/getSlideCode", null, token + "#" + openid, token);
        System.out.println(s);
    }

}
