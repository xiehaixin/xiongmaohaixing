package com.xiongmaohaixin.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * ClassName:WeiXinConfig
 * Package:com.xiongmaohaixin.config
 * Description:公众号配置
 *
 * @Date:2020/11/16 16:35
 * @Author:XHX
 */
@Component
public class WeiXinConfig {
    @Value("${weixin.mp.token}")
    private String token;
    @Value("${weixin.mp.EncodingAESKey}")
    private String EncodingAESKey;
    @Value("${weixin.mp.appId}")
    private String appId;
    @Value("${weixin.mp.secret}")
    private String secret;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getEncodingAESKey() {
        return EncodingAESKey;
    }

    public void setEncodingAESKey(String encodingAESKey) {
        EncodingAESKey = encodingAESKey;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }

    @Override
    public String toString() {
        return "WeiXinConfig [" +
                "token=" + token +
                ", EncodingAESKey=" + EncodingAESKey +
                ", appId=" + appId +
                ", secret=" + secret +
                ']';
    }
}
