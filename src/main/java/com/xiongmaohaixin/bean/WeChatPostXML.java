package com.xiongmaohaixin.bean;

/**
 * ClassName:WeChatPostXML
 * Package:com.xiongmaohaixin.bean
 * Description:请为该功能做描述
 *
 * @Date:2020/11/17 10:38
 * @Author:XHX
 */
public class WeChatPostXML {
    private String fromUserName;
    private Long createTime;
    private String msgType;
    private String content;
    private Long msgId;

    public WeChatPostXML(String fromUserName, String createTime, String msgType, String content, String msgId) {
        this.fromUserName = fromUserName;
        this.createTime = Long.valueOf(createTime);
        this.msgType = msgType;
        this.content = content;
        this.msgId = Long.valueOf(msgId);
    }

    public WeChatPostXML() {
    }

    public String getFromUserName() {
        return fromUserName;
    }

    public void setFromUserName(String fromUserName) {
        this.fromUserName = fromUserName;
    }

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    public String getMsgType() {
        return msgType;
    }

    public void setMsgType(String msgType) {
        this.msgType = msgType;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Long getMsgId() {
        return msgId;
    }

    public void setMsgId(Long msgId) {
        this.msgId = msgId;
    }

    @Override
    public String toString() {
        return "WeChatPostXML [" +
                "fromUserName=" + fromUserName +
                ", createTime=" + createTime +
                ", msgType=" + msgType +
                ", content=" + content +
                ", msgId=" + msgId +
                ']';
    }
}
