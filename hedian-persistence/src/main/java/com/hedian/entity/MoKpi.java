package com.hedian.entity;

import java.io.Serializable;

import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;

/**
 * <p>
 * 监控指标
 * </p>
 *
 * @author hedian123
 * @since 2018-08-17
 */
@TableName("tbl_mo_kpi")
public class MoKpi extends Model<MoKpi> {

    private static final long serialVersionUID = 1L;

    @TableId("mo_kpi_id")
    private Integer moKpiId;
    /**
     * 监控指标名称
     */
    @TableField("mo_kpi_name")
    private String moKpiName;
    /**
     * 硬件上对于指标可能不是数字有可能是一个字符串，匹配硬件采集的数据通过这个key关联得到mo_kpi_id
     */
    @TableField("mo_kpi_key")
    private String moKpiKey;
    /**
     * 监控指标描述
     */
    @TableField("mo_kpi_desc")
    private String moKpiDesc;
    /**
     * 指标中文单位i
     */
    @TableField("unit_ch")
    private String unitCh;
    /**
     * 指标英文单位
     */
    @TableField("unit_en")
    private String unitEn;
    private Integer showorder;
    private Integer useflag;
    @TableField("user_id_create")
    private Long userIdCreate;
    @TableField("gmt_create")
    private Date gmtCreate;
    @TableField("user_id_mod")
    private Long userIdMod;
    @TableField("gmt_modified")
    private Date gmtModified;

    @TableField(exist = false)
    private String dataValue;

    public String getDataValue() {
        return dataValue;
    }

    public void setDataValue(String dataValue) {
        this.dataValue = dataValue;
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

    @Override
    protected Serializable pkVal() {
        return this.moKpiId;
    }

    @Override
    public String toString() {
        return "MoKpi{" +
        "moKpiId=" + moKpiId +
        ", moKpiName=" + moKpiName +
        ", moKpiKey=" + moKpiKey +
        ", moKpiDesc=" + moKpiDesc +
        ", unitCh=" + unitCh +
        ", unitEn=" + unitEn +
        ", showorder=" + showorder +
        ", useflag=" + useflag +
        ", userIdCreate=" + userIdCreate +
        ", gmtCreate=" + gmtCreate +
        ", userIdMod=" + userIdMod +
        ", gmtModified=" + gmtModified +
        "}";
    }
}
