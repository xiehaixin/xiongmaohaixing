package com.xiongmaohaixin.bean;

/**
 * ClassName:enterpriseWeChatCallBack
 * Package:com.eviano2o.bean
 * Description:微信消息
 *
 * @Date:2020/11/12 11:36
 * @Author:XHX
 */
public class WeChatCallBack {

    private String signature;
    private String timestamp;
    private String nonce;
    private String echostr;

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getNonce() {
        return nonce;
    }

    public void setNonce(String nonce) {
        this.nonce = nonce;
    }

    public String getEchostr() {
        return echostr;
    }

    public void setEchostr(String echostr) {
        this.echostr = echostr;
    }

    @Override
    public String toString() {
        return "WeChatCallBack [" +
                "signature=" + signature +
                ", timestamp=" + timestamp +
                ", nonce=" + nonce +
                ", echostr=" + echostr +
                ']';
    }
}
