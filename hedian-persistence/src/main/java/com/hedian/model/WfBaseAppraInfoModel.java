package com.hedian.model;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.Date;

public class WfBaseAppraInfoModel {


    private Integer baseAppraId;

    private Integer baseAppraScore;
    private String kexinAppraName;
    private String kexinAppraPhone;
    private String currentBaseAppraName;
    private String baseAppraInfo;
    @JSONField(format="yyyy-MM-dd HH:mm:ss")
    private Date baseAppraTime;

    public Integer getBaseAppraId() {
        return baseAppraId;
    }

    public void setBaseAppraId(Integer baseAppraId) {
        this.baseAppraId = baseAppraId;
    }

    public Integer getBaseAppraScore() {
        return baseAppraScore;
    }

    public void setBaseAppraScore(Integer baseAppraScore) {
        this.baseAppraScore = baseAppraScore;
    }

    public String getKexinAppraName() {
        return kexinAppraName;
    }

    public void setKexinAppraName(String kexinAppraName) {
        this.kexinAppraName = kexinAppraName;
    }

    public String getKexinAppraPhone() {
        return kexinAppraPhone;
    }

    public void setKexinAppraPhone(String kexinAppraPhone) {
        this.kexinAppraPhone = kexinAppraPhone;
    }

    public String getBaseAppraInfo() {
        return baseAppraInfo;
    }

    public void setBaseAppraInfo(String baseAppraInfo) {
        this.baseAppraInfo = baseAppraInfo;
    }

    public Date getBaseAppraTime() {
        return baseAppraTime;
    }

    public void setBaseAppraTime(Date baseAppraTime) {
        this.baseAppraTime = baseAppraTime;
    }

    public String getCurrentBaseAppraName() {
        return currentBaseAppraName;
    }

    public void setCurrentBaseAppraName(String currentBaseAppraName) {
        this.currentBaseAppraName = currentBaseAppraName;
    }

    @Override
    public String toString() {
        return "WfBaseAppraInfoModel{" +
                "baseAppraId=" + baseAppraId +
                ", baseAppraScore=" + baseAppraScore +
                ", kexinAppraName='" + kexinAppraName + '\'' +
                ", kexinAppraPhone='" + kexinAppraPhone + '\'' +
                ", currentBaseAppraName='" + currentBaseAppraName + '\'' +
                ", baseAppraInfo='" + baseAppraInfo + '\'' +
                ", baseAppraTime=" + baseAppraTime +
                '}';
    }
}
