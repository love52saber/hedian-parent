package com.hedian.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * <p>
 * 监控阈值配置
 * </p>
 *
 * @author hedian123
 * @since 2018-08-17
 */
public class MoThresholdModel {


    private Integer moThId;
    /**
     * 阈值类型： 1 上下限监控，2  百分比监控(区间)，3  基准值百分比上下限监控 ，4 基准值百分比监控（区间），对于上下限 如果有配置上限或者下限则按照大于上限小于下限为异常，若只配置了上限或者下限则按照有配置的比较, 5 判断获取到到的值是否等于mo_th_value
     */
    private Integer moThType;
    /**
     * 基准值
     */
    private BigDecimal moThBase;
    /**
     * 上限
     */
    private BigDecimal moThUp;
    /**
     * 下限
     */
    private BigDecimal moThDown;
    /**
     * 上下限包含标记 ：1 包含上下限，0 不包含上下限 默认0
     */
    private Integer moThInupdown;
    /**
     * 做等于比较时和该值进行比较
     */
    private String moThValue;
    /**
     * 当同一个对象的同一监控指标 匹配出多个故障时 按照该优先级展示优先级高的故障，该数值越小优先级越高
     */
    private Integer moThPriority;
    private Integer resStypeId;

    private Integer moKpiId;

    private Integer moAbnormalId;
    private Integer showorder;
    private Integer useflag;

    private Long userIdCreate;

    private Date gmtCreate;

    private Long userIdMod;

    private Date gmtModified;

    private String resMtypeName;

    private String resStypeName;

    private String moAbnormalName;

    private String moKpiName;
    private String unitCh;
    private String unitEn;

    public String getUnitCh() {
        return unitCh;
    }

    public void setUnitCh(String unitCh) {
        this.unitCh = unitCh;
    }

    public String getUnitEn() {
        return unitEn;
    }

    public void setUnitEn(String unitEn) {
        this.unitEn = unitEn;
    }

    public String getResMtypeName() {
        return resMtypeName;
    }

    public void setResMtypeName(String resMtypeName) {
        this.resMtypeName = resMtypeName;
    }

    public String getResStypeName() {
        return resStypeName;
    }

    public void setResStypeName(String resStypeName) {
        this.resStypeName = resStypeName;
    }

    public String getMoAbnormalName() {
        return moAbnormalName;
    }

    public void setMoAbnormalName(String moAbnormalName) {
        this.moAbnormalName = moAbnormalName;
    }

    public String getMoKpiName() {
        return moKpiName;
    }

    public void setMoKpiName(String moKpiName) {
        this.moKpiName = moKpiName;
    }

    public Integer getMoThId() {
        return moThId;
    }

    public void setMoThId(Integer moThId) {
        this.moThId = moThId;
    }

    public Integer getMoThType() {
        return moThType;
    }

    public void setMoThType(Integer moThType) {
        this.moThType = moThType;
    }

    public BigDecimal getMoThBase() {
        return moThBase;
    }

    public void setMoThBase(BigDecimal moThBase) {
        this.moThBase = moThBase;
    }

    public BigDecimal getMoThUp() {
        return moThUp;
    }

    public void setMoThUp(BigDecimal moThUp) {
        this.moThUp = moThUp;
    }

    public BigDecimal getMoThDown() {
        return moThDown;
    }

    public void setMoThDown(BigDecimal moThDown) {
        this.moThDown = moThDown;
    }

    public Integer getMoThInupdown() {
        return moThInupdown;
    }

    public void setMoThInupdown(Integer moThInupdown) {
        this.moThInupdown = moThInupdown;
    }

    public String getMoThValue() {
        return moThValue;
    }

    public void setMoThValue(String moThValue) {
        this.moThValue = moThValue;
    }

    public Integer getMoThPriority() {
        return moThPriority;
    }

    public void setMoThPriority(Integer moThPriority) {
        this.moThPriority = moThPriority;
    }

    public Integer getResStypeId() {
        return resStypeId;
    }

    public void setResStypeId(Integer resStypeId) {
        this.resStypeId = resStypeId;
    }
    public Integer getMoKpiId() {
        return moKpiId;
    }

    public void setMoKpiId(Integer moKpiId) {
        this.moKpiId = moKpiId;
    }

    public Integer getMoAbnormalId() {
        return moAbnormalId;
    }

    public void setMoAbnormalId(Integer moAbnormalId) {
        this.moAbnormalId = moAbnormalId;
    }

    public Integer getShoworder() {
        return showorder;
    }

    public void setShoworder(Integer showorder) {
        this.showorder = showorder;
    }

    public Integer getUseflag() {
        return useflag;
    }

    public void setUseflag(Integer useflag) {
        this.useflag = useflag;
    }

    public Long getUserIdCreate() {
        return userIdCreate;
    }

    public void setUserIdCreate(Long userIdCreate) {
        this.userIdCreate = userIdCreate;
    }

    public Date getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    public Long getUserIdMod() {
        return userIdMod;
    }

    public void setUserIdMod(Long userIdMod) {
        this.userIdMod = userIdMod;
    }

    public Date getGmtModified() {
        return gmtModified;
    }

    public void setGmtModified(Date gmtModified) {
        this.gmtModified = gmtModified;
    }


    @Override
    public String toString() {
        return "MoThresholdModel{" +
                "moThId=" + moThId +
                ", moThType=" + moThType +
                ", moThBase=" + moThBase +
                ", moThUp=" + moThUp +
                ", moThDown=" + moThDown +
                ", moThInupdown=" + moThInupdown +
                ", moThValue='" + moThValue + '\'' +
                ", moThPriority=" + moThPriority +
                ", resStypeId=" + resStypeId +
                ", moKpiId=" + moKpiId +
                ", moAbnormalId=" + moAbnormalId +
                ", showorder=" + showorder +
                ", useflag=" + useflag +
                ", userIdCreate=" + userIdCreate +
                ", gmtCreate=" + gmtCreate +
                ", userIdMod=" + userIdMod +
                ", gmtModified=" + gmtModified +
                ", resMtypeName='" + resMtypeName + '\'' +
                ", resStypeName='" + resStypeName + '\'' +
                ", moAbnormalName='" + moAbnormalName + '\'' +
                ", moKpiName='" + moKpiName + '\'' +
                '}';
    }
}
