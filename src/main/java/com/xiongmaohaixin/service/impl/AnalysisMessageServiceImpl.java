package com.xiongmaohaixin.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.xiongmaohaixin.bean.WeChatPostXML;
import com.xiongmaohaixin.bean.WxMsgType;
import com.xiongmaohaixin.config.WeiXinConfig;
import com.xiongmaohaixin.service.IAnalysisMessageService;
import com.xiongmaohaixin.service.IMaoTaiService;
import com.xiongmaohaixin.utils.HttpClientUtil;
import com.xiongmaohaixin.utils.JacksonUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.MessageFormat;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * ClassName:AnalysisMessageServiceImpl
 * Package:com.xiongmaohaixin.service.impl
 * Description:解析信息
 *
 * @Date:2020/11/17 10:46
 * @Author:XHX
 */
@Service
public class AnalysisMessageServiceImpl implements IAnalysisMessageService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    /** 茅台openid */
    private final String XHX_MAO_TAI_OPENID = "mQMtJNLoZfN7fZHbqLTIfs2bWuloHOjQOTQoXMsYoFk=";

    /** 最长告白的openid */
    private final String XHX_ZUI_CHANG_OPENID = "oUze51XrnmckSPUcY5x3cuAOZ4Zw";

    private final List<String> haoshuiOpenId = Arrays.asList("oGLfUwE5ZHCB_d5tWdXaxZ-MOq_k","oGLfUwDl5_EOnZvNmrE6MMBlrPWo");

    private String haoshuiXHXOpenId = "oGLfUwE5ZHCB_d5tWdXaxZ-MOq_k";


    @Override
    public String getXHXMaoTaiOpenId() {
        return XHX_MAO_TAI_OPENID;
    }

    @Autowired
    private IMaoTaiService maoTaiService;

    @Autowired
    private WeiXinConfig weiXinConfig;

    private String accessToken;

    @Override
    public void message(WeChatPostXML xml) {
        String content = xml.getContent();
        if(WxMsgType.TEXT.equals(xml.getMsgType())
                && XHX_ZUI_CHANG_OPENID.equals(xml.getFromUserName())
                && !StringUtils.isBlank(content)
                && content.length()==32){
            maoTaiService.setCode(content);
            customSend("ok，开始监控");
        }
    }

    @Override
    public void customSend(String text) {
        String url = "https://api.weixin.qq.com/cgi-bin/message/custom/send?access_token="+getAccessToken();
        ObjectMapper mapper = new ObjectMapper();
        ObjectNode personNode = mapper.createObjectNode();
        personNode.put("touser",XHX_ZUI_CHANG_OPENID);
        personNode.put("msgtype","text");
        ObjectNode textNode = mapper.createObjectNode();
        textNode.put("content",text);
        personNode.set("text",textNode);
        try {
            String post = mapper.writeValueAsString(personNode);
            String post1 = HttpClientUtil.post(url, post);
            System.out.println(url);
            System.out.println(post);
            Map<String, Object> map = JacksonUtils.json2map(post1);
            Integer errcode = (Integer) map.get("errcode");
            if(errcode!=0){
                logger.error(post1);
            }
        } catch (Exception e) {
            logger.error("{}",e);
        }
    }

    @Override
    public String getAccessToken() {
        if(StringUtils.isBlank(accessToken)){
            String url = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid={0}&secret={1}";
            url = MessageFormat.format(url,weiXinConfig.getAppId(),weiXinConfig.getSecret());
            String s = HttpClientUtil.get(url);
            logger.info("url:{},result:{}",url,s);
            try {
                Map<String, Object> map = JacksonUtils.json2map(s);
                String access_token = (String) map.get("access_token");
                if(StringUtils.isBlank(access_token)){
                    this.accessToken = "";
                    throw new RuntimeException();
                }
                this.accessToken = access_token;
            } catch (IOException e) {
                throw new RuntimeException();
            }
        }
        return accessToken;
    }

    @Override
    public String getHaoshuiXHXOpenId() {
        return haoshuiXHXOpenId;
    }

    @Override
    public void haoshuiCustomSend(String openId, String content) {
        try {
            String url = "https://sqweixin.haoshui.com.cn/weixin/sendXhxMessage?openId={0}&message={1}";
            url = MessageFormat.format(url,openId,URLEncoder.encode(content,"UTF-8"));
            HttpClientUtil.get(url);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void haoshuiCustomSend(String content) {
        for (String openId:haoshuiOpenId){
            try {
                String url = "https://sqweixin.haoshui.com.cn/weixin/sendXhxMessage?openId={0}&message={1}";
                url = MessageFormat.format(url,openId,URLEncoder.encode(content,"UTF-8"));
                HttpClientUtil.get(url);
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
    }
}
