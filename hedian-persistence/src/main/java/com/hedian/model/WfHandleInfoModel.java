package com.hedian.model;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.Date;

public class WfHandleInfoModel {


    private Integer handleId;

    private Boolean handleType;
    private String confirmPhone;
    private String confirmName;
    private String currentHandleName;
    private String handleFile;
    private String handleInfo;
    private String handleMethod;
    @JSONField(format="yyyy-MM-dd HH:mm:ss")
    private Date handleTime;

    public Integer getHandleId() {
        return handleId;
    }

    public void setHandleId(Integer handleId) {
        this.handleId = handleId;
    }

    public String getConfirmPhone() {
        return confirmPhone;
    }

    public void setConfirmPhone(String confirmPhone) {
        this.confirmPhone = confirmPhone;
    }

    public String getConfirmName() {
        return confirmName;
    }

    public void setConfirmName(String confirmName) {
        this.confirmName = confirmName;
    }

    public String getHandleFile() {
        return handleFile;
    }

    public void setHandleFile(String handleFile) {
        this.handleFile = handleFile;
    }

    public String getHandleInfo() {
        return handleInfo;
    }

    public void setHandleInfo(String handleInfo) {
        this.handleInfo = handleInfo;
    }

    public String getHandleMethod() {
        return handleMethod;
    }

    public void setHandleMethod(String handleMethod) {
        this.handleMethod = handleMethod;
    }

    public Date getHandleTime() {
        return handleTime;
    }

    public void setHandleTime(Date handleTime) {
        this.handleTime = handleTime;
    }

    public Boolean getHandleType() {
        return handleType;
    }

    public void setHandleType(Boolean handleType) {
        this.handleType = handleType;
    }

    public String getCurrentHandleName() {
        return currentHandleName;
    }

    public void setCurrentHandleName(String currentHandleName) {
        this.currentHandleName = currentHandleName;
    }

    @Override
    public String toString() {
        return "WfHandleInfoModel{" +
                "handleId=" + handleId +
                ", handleType=" + handleType +
                ", confirmPhone='" + confirmPhone + '\'' +
                ", confirmName='" + confirmName + '\'' +
                ", currentHandleName='" + currentHandleName + '\'' +
                ", handleFile='" + handleFile + '\'' +
                ", handleInfo='" + handleInfo + '\'' +
                ", handleMethod='" + handleMethod + '\'' +
                ", handleTime=" + handleTime +
                '}';
    }
}
