package com.hedian.model;

import java.io.Serializable;
import java.util.Date;

public class MokpiModel implements Serializable {


    private Integer moKpiId;
    /**
     * 监控指标名称
     */
    private String moKpiName;
    /**
     * 硬件上对于指标可能不是数字有可能是一个字符串，匹配硬件采集的数据通过这个key关联得到mo_kpi_id
     */
    private String moKpiKey;
    /**
     * 监控指标描述
     */
    private String moKpiDesc;
    /**
     * 指标中文单位i
     */
    private String unitCh;
    /**
     * 指标英文单位
     */
    private String unitEn;
    private Integer showorder;
    private Integer useflag;

    private Long userIdCreate;

    private Date gmtCreate;

    private Long userIdMod;

    private Date gmtModified;

    private Integer skId;

    private String resMtypeName;

    private Integer stypeFlag;

    private String resStypeName;

    private String resCurStypeName;

    public String getResCurStypeName() {
        return resCurStypeName;
    }

    public void setResCurStypeName(String resCurStypeName) {
        this.resCurStypeName = resCurStypeName;
    }

    public Integer getMoKpiId() {
        return moKpiId;
    }

    public void setMoKpiId(Integer moKpiId) {
        this.moKpiId = moKpiId;
    }

    public String getMoKpiName() {
        return moKpiName;
    }

    public void setMoKpiName(String moKpiName) {
        this.moKpiName = moKpiName;
    }

    public String getMoKpiKey() {
        return moKpiKey;
    }

    public void setMoKpiKey(String moKpiKey) {
        this.moKpiKey = moKpiKey;
    }

    public String getMoKpiDesc() {
        return moKpiDesc;
    }

    public void setMoKpiDesc(String moKpiDesc) {
        this.moKpiDesc = moKpiDesc;
    }

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

    public Integer getSkId() {
        return skId;
    }

    public void setSkId(Integer skId) {
        this.skId = skId;
    }

    public Integer getStypeFlag() {
        return stypeFlag;
    }

    public void setStypeFlag(Integer stypeFlag) {
        this.stypeFlag = stypeFlag;
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

    @Override
    public String toString() {
        return "MokpiModel{" +
                "moKpiId=" + moKpiId +
                ", moKpiName='" + moKpiName + '\'' +
                ", moKpiKey='" + moKpiKey + '\'' +
                ", moKpiDesc='" + moKpiDesc + '\'' +
                ", unitCh='" + unitCh + '\'' +
                ", unitEn='" + unitEn + '\'' +
                ", showorder=" + showorder +
                ", useflag=" + useflag +
                ", userIdCreate=" + userIdCreate +
                ", gmtCreate=" + gmtCreate +
                ", userIdMod=" + userIdMod +
                ", gmtModified=" + gmtModified +
                ", skId=" + skId +
                ", resMtypeName='" + resMtypeName + '\'' +
                ", stypeFlag=" + stypeFlag +
                ", resStypeName='" + resStypeName + '\'' +
                ", resCurStypeName='" + resCurStypeName + '\'' +
                '}';
    }
}
