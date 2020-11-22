package com.xiongmaohaixin.service;

import com.xiongmaohaixin.bean.WeChatPostXML;

/**
 * ClassName:IAnalysisMessageService
 * Package:com.xiongmaohaixin.service
 * Description:请为该功能做描述
 *
 * @Date:2020/11/17 10:44
 * @Author:XHX
 */
public interface IAnalysisMessageService {

    void message(WeChatPostXML xml);

    void customSend(String text);

    String getAccessToken();

    String getHaoshuiXHXOpenId();

    void haoshuiCustomSend(String openId,String content);

    void haoshuiCustomSend(String content);

}
