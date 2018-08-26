package com.hedian.model;

import java.util.Date;

/**
 * 告警信息
 */
public class AlarmInfoModel {

    /**
     * 告警设备id
     */
    private Integer resId;
    /**
     * 告警等级
     */
    private String alarmLevel;
    /**
     * 告警颜色
     */
    private String alarmColor;
    /**
     * 故障码
     */
    private String alarmCode;
    /**
     * 故障名称
     */
    private String alarmName;
    /**
     * 告警对象
     */
    private String alarmObject;
    /**
     * 告警对象类型
     */
    private String alarmObjectType;
    /**
     * 告警对象别名
     */
    private String alarmObjectAlias;
    /**
     * 告警时间
     */
    private Date alarmTime;
    /**
     * 告警详情
     */
    private String alarmDesc;

    public Integer getResId() {
        return resId;
    }

    public void setResId(Integer resId) {
        this.resId = resId;
    }

    public String getAlarmLevel() {
        return alarmLevel;
    }

    public void setAlarmLevel(String alarmLevel) {
        this.alarmLevel = alarmLevel;
    }

    public String getAlarmColor() {
        return alarmColor;
    }

    public void setAlarmColor(String alarmColor) {
        this.alarmColor = alarmColor;
    }

    public String getAlarmCode() {
        return alarmCode;
    }

    public void setAlarmCode(String alarmCode) {
        this.alarmCode = alarmCode;
    }

    public String getAlarmName() {
        return alarmName;
    }

    public void setAlarmName(String alarmName) {
        this.alarmName = alarmName;
    }

    public String getAlarmObject() {
        return alarmObject;
    }

    public void setAlarmObject(String alarmObject) {
        this.alarmObject = alarmObject;
    }

    public String getAlarmObjectType() {
        return alarmObjectType;
    }

    public void setAlarmObjectType(String alarmObjectType) {
        this.alarmObjectType = alarmObjectType;
    }

    public String getAlarmObjectAlias() {
        return alarmObjectAlias;
    }

    public void setAlarmObjectAlias(String alarmObjectAlias) {
        this.alarmObjectAlias = alarmObjectAlias;
    }

    public Date getAlarmTime() {
        return alarmTime;
    }

    public void setAlarmTime(Date alarmTime) {
        this.alarmTime = alarmTime;
    }

    public String getAlarmDesc() {
        return alarmDesc;
    }

    public void setAlarmDesc(String alarmDesc) {
        this.alarmDesc = alarmDesc;
    }

    @Override
    public String toString() {
        return "AlarmInfo{" +
                "resId=" + resId +
                ", alarmLevel='" + alarmLevel + '\'' +
                ", alarmColor='" + alarmColor + '\'' +
                ", alarmCode='" + alarmCode + '\'' +
                ", alarmName='" + alarmName + '\'' +
                ", alarmObject='" + alarmObject + '\'' +
                ", alarmObjectType='" + alarmObjectType + '\'' +
                ", alarmObjectAlias='" + alarmObjectAlias + '\'' +
                ", alarmTime=" + alarmTime +
                ", alarmDesc='" + alarmDesc + '\'' +
                '}';
    }
}
