package com.hedian.model;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.Date;

public class WfConfirmInfoModel {


    private Integer confirmId;

    private String baseAppraName;
    private String baseAppraPhone;
    private String currentConfirmName;
    private String confirmInfo;
    @JSONField(format="yyyy-MM-dd HH:mm:ss")
    private Date confirmTime;

    public Integer getConfirmId() {
        return confirmId;
    }

    public void setConfirmId(Integer confirmId) {
        this.confirmId = confirmId;
    }

    public String getBaseAppraName() {
        return baseAppraName;
    }

    public void setBaseAppraName(String baseAppraName) {
        this.baseAppraName = baseAppraName;
    }

    public String getBaseAppraPhone() {
        return baseAppraPhone;
    }

    public void setBaseAppraPhone(String baseAppraPhone) {
        this.baseAppraPhone = baseAppraPhone;
    }

    public String getConfirmInfo() {
        return confirmInfo;
    }

    public void setConfirmInfo(String confirmInfo) {
        this.confirmInfo = confirmInfo;
    }

    public Date getConfirmTime() {
        return confirmTime;
    }

    public void setConfirmTime(Date confirmTime) {
        this.confirmTime = confirmTime;
    }

    public String getCurrentConfirmName() {
        return currentConfirmName;
    }

    public void setCurrentConfirmName(String currentConfirmName) {
        this.currentConfirmName = currentConfirmName;
    }

    @Override
    public String toString() {
        return "WfConfirmInfoModel{" +
                "confirmId=" + confirmId +
                ", baseAppraName='" + baseAppraName + '\'' +
                ", baseAppraPhone='" + baseAppraPhone + '\'' +
                ", currentConfirmName='" + currentConfirmName + '\'' +
                ", confirmInfo='" + confirmInfo + '\'' +
                ", confirmTime=" + confirmTime +
                '}';
    }
}
