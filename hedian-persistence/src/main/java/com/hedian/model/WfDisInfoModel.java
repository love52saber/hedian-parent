package com.hedian.model;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.Date;

public class WfDisInfoModel {


    private Integer disId;
    private String overName;
    private String overPhone;
    private String currentDisName;
    private String disInfo;
    @JSONField(format="yyyy-MM-dd HH:mm:ss")
    private Date disTime;

    public Integer getDisId() {
        return disId;
    }

    public void setDisId(Integer disId) {
        this.disId = disId;
    }

    public String getOverName() {
        return overName;
    }

    public void setOverName(String overName) {
        this.overName = overName;
    }

    public String getOverPhone() {
        return overPhone;
    }

    public void setOverPhone(String overPhone) {
        this.overPhone = overPhone;
    }

    public String getDisInfo() {
        return disInfo;
    }

    public void setDisInfo(String disInfo) {
        this.disInfo = disInfo;
    }

    public Date getDisTime() {
        return disTime;
    }

    public void setDisTime(Date disTime) {
        this.disTime = disTime;
    }

    public String getCurrentDisName() {
        return currentDisName;
    }

    public void setCurrentDisName(String currentDisName) {
        this.currentDisName = currentDisName;
    }

    @Override
    public String toString() {
        return "WfDisInfoModel{" +
                "disId=" + disId +
                ", overName='" + overName + '\'' +
                ", overPhone='" + overPhone + '\'' +
                ", currentDisName='" + currentDisName + '\'' +
                ", disInfo='" + disInfo + '\'' +
                ", disTime=" + disTime +
                '}';
    }
}
