package com.hedian.entity;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableLogic;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.FieldFill;
import com.baomidou.mybatisplus.enums.IdType;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

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

    @TableId(value = "mo_kpi_id", type = IdType.AUTO)
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
    @TableField(value = "showorder", fill = FieldFill.INSERT)
    private Integer showorder;
    @TableLogic
    @TableField(value = "useflag", fill = FieldFill.INSERT)
    private Integer useflag;
    @TableField(value = "user_id_create", fill = FieldFill.INSERT)
    private Long userIdCreate;
    @TableField(value = "gmt_create", fill = FieldFill.INSERT)
    private Date gmtCreate;
    @TableField(value = "user_id_mod", fill = FieldFill.INSERT_UPDATE)
    private Long userIdMod;
    @TableField(value = "gmt_modified", fill = FieldFill.INSERT_UPDATE)
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MoKpi moKpi = (MoKpi) o;
        return Objects.equals(moKpiId, moKpi.moKpiId) &&
                Objects.equals(moKpiName, moKpi.moKpiName) &&
                Objects.equals(moKpiKey, moKpi.moKpiKey) &&
                Objects.equals(moKpiDesc, moKpi.moKpiDesc) &&
                Objects.equals(unitCh, moKpi.unitCh) &&
                Objects.equals(unitEn, moKpi.unitEn) &&
                Objects.equals(showorder, moKpi.showorder) &&
                Objects.equals(useflag, moKpi.useflag) &&
                Objects.equals(userIdCreate, moKpi.userIdCreate) &&
                Objects.equals(gmtCreate, moKpi.gmtCreate) &&
                Objects.equals(userIdMod, moKpi.userIdMod) &&
                Objects.equals(gmtModified, moKpi.gmtModified) &&
                Objects.equals(dataValue, moKpi.dataValue);
    }

    @Override
    public int hashCode() {

        return Objects.hash(moKpiId, moKpiName, moKpiKey, moKpiDesc, unitCh, unitEn, showorder, useflag, userIdCreate, gmtCreate, userIdMod, gmtModified, dataValue);
    }
}
