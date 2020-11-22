package com.xiongmaohaixin.controller;

import com.xiongmaohaixin.bean.WeChatCallBack;
import com.xiongmaohaixin.bean.WeChatPostXML;
import com.xiongmaohaixin.config.WeiXinConfig;
import com.xiongmaohaixin.service.IAnalysisMessageService;
import com.xiongmaohaixin.service.IMaoTaiService;
import com.xiongmaohaixin.utils.GZH_mao_tai;
import com.xiongmaohaixin.wxDeciphering.AesException;
import com.xiongmaohaixin.wxDeciphering.WXBizMsgCrypt;
import org.apache.commons.lang.StringUtils;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.Enumeration;

/**
 * ClassName:WeixinController
 * Package:com.xiongmaohaixin.controller
 * Description:请为该功能做描述
 *
 * @Date:2020/11/16 16:25
 * @Author:XHX
 */
@RestController
@RequestMapping("/weixin/event")
public class WeixinController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private WeiXinConfig weiXinConfig;

    @Autowired
    IAnalysisMessageService analysisMessageService;

    @Autowired
    private IMaoTaiService maoTaiService;

    @RequestMapping("/callCack")
    public String callBack(WeChatCallBack callBack, HttpServletRequest request) throws IOException, AesException, DocumentException {
        logger.info("callBack:{}",callBack);
        StringBuilder sb = new StringBuilder();
        BufferedReader in = request.getReader();
        String line;
        while ((line = in.readLine()) != null) {
            sb.append(line);
        }
        in.close();
        String xml = sb.toString(); //将xml变成字符串
        System.out.println(weiXinConfig);
        WXBizMsgCrypt wXBizMsgCrypt = new WXBizMsgCrypt(weiXinConfig.getToken(),weiXinConfig.getEncodingAESKey(),weiXinConfig.getAppId());
        logger.info("读取的XML为：{}",xml);
        if(!StringUtils.isBlank(callBack.getEchostr())){
            return wXBizMsgCrypt.mpVerifyUrl(callBack.getSignature(), callBack.getTimestamp(), callBack.getNonce(), callBack.getEchostr());
        }
        xml = wXBizMsgCrypt.mpDecryptMsg(callBack.getSignature(), callBack.getTimestamp(), callBack.getNonce(), xml);
        logger.info("解密后:{}",xml);
        Document doc = DocumentHelper.parseText(xml);
        Element rootElt = doc.getRootElement();
        String msgType = rootElt.elementText("MsgType");
        String content = rootElt.elementText("Content");
        String fromUserName = rootElt.elementText("FromUserName");
        String createTime = rootElt.elementText("CreateTime");
        String msgId = rootElt.elementText("MsgId");
        WeChatPostXML postXML = new WeChatPostXML(fromUserName, createTime, msgType, content, msgId);
        analysisMessageService.message(postXML);
        return "";
    }

    @RequestMapping("/test")
    public String test(WeChatPostXML dto){
        analysisMessageService.message(dto);
        return "ok";
    }

    @RequestMapping("/receptionCode")
    public String receptionCode(String openid, String code,HttpServletRequest request){
        logger.info("来服务了:{}",openid,code);
        Enumeration<String> headerNames = request.getHeaderNames();
        StringBuilder log = new StringBuilder("\n");
        while (headerNames.hasMoreElements()){
            String headerName = headerNames.nextElement();
            String header = request.getHeader(headerName);
            log.append(headerName).append("=").append(header).append("\n");
        }
        logger.info(log.toString());
        if(StringUtils.isBlank(openid)||StringUtils.isBlank(code)){
            return "error";
        }
        if(analysisMessageService.getHaoshuiXHXOpenId().equals(openid)){
            maoTaiService.setCode(code);
            analysisMessageService.haoshuiCustomSend(openid,"ok，开始监控");
        }
        return "success";
    }

    private GZH_mao_tai mt = new GZH_mao_tai();

    @RequestMapping("/myTest")
    public String myTest(HttpServletRequest request) throws IOException {
        Enumeration<String> headerNames = request.getHeaderNames();
        StringBuilder log = new StringBuilder("\n");
        while (headerNames.hasMoreElements()){
            String headerName = headerNames.nextElement();
            String header = request.getHeader(headerName);
            log.append(headerName).append("=").append(header).append("\n");
        }
        logger.info(log.toString());
        String ttt = mt.myTest("ttt", ",yToken");
        return ttt;
    }

}
