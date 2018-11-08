package com.hedian.model;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.Date;

public class WfOverInfoModel {

    private Integer overId;

    private Boolean overType;
    private String overFile;
    private String overInfo;
    private String handlePhone;
    private String currentOverName;
    private String handleName;
    @JSONField(format="yyyy-MM-dd HH:mm:ss")
    private Date overTime;


    public Integer getOverId() {
        return overId;
    }

    public void setOverId(Integer overId) {
        this.overId = overId;
    }

    public String getOverFile() {
        return overFile;
    }

    public void setOverFile(String overFile) {
        this.overFile = overFile;
    }

    public String getOverInfo() {
        return overInfo;
    }

    public void setOverInfo(String overInfo) {
        this.overInfo = overInfo;
    }

    public String getHandlePhone() {
        return handlePhone;
    }

    public void setHandlePhone(String handlePhone) {
        this.handlePhone = handlePhone;
    }

    public String getHandleName() {
        return handleName;
    }

    public void setHandleName(String handleName) {
        this.handleName = handleName;
    }

    public Date getOverTime() {
        return overTime;
    }

    public void setOverTime(Date overTime) {
        this.overTime = overTime;
    }

    public Boolean getOverType() {
        return overType;
    }

    public void setOverType(Boolean overType) {
        this.overType = overType;
    }

    public String getCurrentOverName() {
        return currentOverName;
    }

    public void setCurrentOverName(String currentOverName) {
        this.currentOverName = currentOverName;
    }

    @Override
    public String toString() {
        return "WfOverInfoModel{" +
                "overId=" + overId +
                ", overType=" + overType +
                ", overFile='" + overFile + '\'' +
                ", overInfo='" + overInfo + '\'' +
                ", handlePhone='" + handlePhone + '\'' +
                ", currentOverName='" + currentOverName + '\'' +
                ", handleName='" + handleName + '\'' +
                ", overTime=" + overTime +
                '}';
    }
}
