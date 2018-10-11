package com.hedian.model;

import java.io.Serializable;

public class NoticeModel implements Serializable {

    /**
     * 1:故障告警
     * 2:消息通知
     * 3:
     */
    private Integer type;

    private Object noticeInfo;

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Object getNoticeInfo() {
        return noticeInfo;
    }

    public void setNoticeInfo(Object noticeInfo) {
        this.noticeInfo = noticeInfo;
    }

    @Override
    public String toString() {
        return "NoticeModel{" +
                "type=" + type +
                ", noticeInfo=" + noticeInfo +
                '}';
    }
}
