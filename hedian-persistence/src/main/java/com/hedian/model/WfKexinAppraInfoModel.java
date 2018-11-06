package com.hedian.model;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.Date;

public class WfKexinAppraInfoModel {


    private Integer kexinAppraId;

    private Integer kexinAppraScora;
    private String kexinAppraInfo;
    private String kexinAppraName;
    @JSONField(format="yyyy-MM-dd HH:mm:ss")
    private Date kexinAppraTime;

    public Integer getKexinAppraId() {
        return kexinAppraId;
    }

    public void setKexinAppraId(Integer kexinAppraId) {
        this.kexinAppraId = kexinAppraId;
    }

    public Integer getKexinAppraScora() {
        return kexinAppraScora;
    }

    public void setKexinAppraScora(Integer kexinAppraScora) {
        this.kexinAppraScora = kexinAppraScora;
    }

    public String getKexinAppraInfo() {
        return kexinAppraInfo;
    }

    public void setKexinAppraInfo(String kexinAppraInfo) {
        this.kexinAppraInfo = kexinAppraInfo;
    }

    public Date getKexinAppraTime() {
        return kexinAppraTime;
    }

    public void setKexinAppraTime(Date kexinAppraTime) {
        this.kexinAppraTime = kexinAppraTime;
    }

    public String getKexinAppraName() {
        return kexinAppraName;
    }

    public void setKexinAppraName(String kexinAppraName) {
        this.kexinAppraName = kexinAppraName;
    }

    @Override
    public String toString() {
        return "WfKexinAppraInfoModel{" +
                "kexinAppraId=" + kexinAppraId +
                ", kexinAppraScora=" + kexinAppraScora +
                ", kexinAppraInfo='" + kexinAppraInfo + '\'' +
                ", kexinAppraName='" + kexinAppraName + '\'' +
                ", kexinAppraTime=" + kexinAppraTime +
                '}';
    }
}
