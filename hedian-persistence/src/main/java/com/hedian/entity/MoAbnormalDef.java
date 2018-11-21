package com.hedian.entity;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

import java.io.Serializable;
import java.util.Comparator;
import java.util.Date;
import java.util.Objects;

/**
 * <p>
 * 监控异常定义
 * </p>
 *
 * @author hedian123
 * @since 2018-08-17
 */
@TableName("tbl_mo_abnormal_def")
public class MoAbnormalDef extends Model<MoAbnormalDef>{

    private static final long serialVersionUID = 1L;

    @TableId("mo_abnormal_id")
    private Integer moAbnormalId;
    /**
     * 异常码
     */
    @TableField("mo_abnormalcode")
    private Integer moAbnormalcode;
    @TableField("mo_abnormal_name")
    private String moAbnormalName;
    @TableField("res_abnormallevel_id")
    private Integer resAbnormallevelId;
    @TableField("abnormal_type_id")
    private Integer abnormalTypeId;
    @TableField("mo_abnormal_desc")
    private String moAbnormalDesc;
    @TableField("mo_abnormal_showtemplate")
    private String moAbnormalShowtemplate;
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

    /**
     * 统计值
     */
    @TableField(exist = false)
    private Integer countNum;

    @TableField(exist = false)
    private AbnormalType abnormalType;
    @TableField(exist = false)
    private ResAbnormallevel resAbnormallevel;

    public AbnormalType getAbnormalType() {
        return abnormalType;
    }

    public void setAbnormalType(AbnormalType abnormalType) {
        this.abnormalType = abnormalType;
    }

    public ResAbnormallevel getResAbnormallevel() {
        return resAbnormallevel;
    }

    public void setResAbnormallevel(ResAbnormallevel resAbnormallevel) {
        this.resAbnormallevel = resAbnormallevel;
    }

    public Integer getAbnormalTypeId() {
        return abnormalTypeId;
    }

    public void setAbnormalTypeId(Integer abnormalTypeId) {
        this.abnormalTypeId = abnormalTypeId;
    }

    public Integer getCountNum() {
        return countNum;
    }

    public void setCountNum(Integer countNum) {
        this.countNum = countNum;
    }

    public Integer getMoAbnormalId() {
        return moAbnormalId;
    }

    public void setMoAbnormalId(Integer moAbnormalId) {
        this.moAbnormalId = moAbnormalId;
    }

    public Integer getMoAbnormalcode() {
        return moAbnormalcode;
    }

    public void setMoAbnormalcode(Integer moAbnormalcode) {
        this.moAbnormalcode = moAbnormalcode;
    }

    public String getMoAbnormalName() {
        return moAbnormalName;
    }

    public void setMoAbnormalName(String moAbnormalName) {
        this.moAbnormalName = moAbnormalName;
    }

    public Integer getResAbnormallevelId() {
        return resAbnormallevelId;
    }

    public void setResAbnormallevelId(Integer resAbnormallevelId) {
        this.resAbnormallevelId = resAbnormallevelId;
    }

    public String getMoAbnormalDesc() {
        return moAbnormalDesc;
    }

    public void setMoAbnormalDesc(String moAbnormalDesc) {
        this.moAbnormalDesc = moAbnormalDesc;
    }

    public String getMoAbnormalShowtemplate() {
        return moAbnormalShowtemplate;
    }

    public void setMoAbnormalShowtemplate(String moAbnormalShowtemplate) {
        this.moAbnormalShowtemplate = moAbnormalShowtemplate;
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
        return this.moAbnormalId;
    }

    @Override
    public String toString() {
        return "MoAbnormalDef{" +
                "moAbnormalId=" + moAbnormalId +
                ", moAbnormalcode=" + moAbnormalcode +
                ", moAbnormalName='" + moAbnormalName + '\'' +
                ", resAbnormallevelId=" + resAbnormallevelId +
                ", abnormalTypeId=" + abnormalTypeId +
                ", moAbnormalDesc='" + moAbnormalDesc + '\'' +
                ", moAbnormalShowtemplate='" + moAbnormalShowtemplate + '\'' +
                ", showorder=" + showorder +
                ", useflag=" + useflag +
                ", userIdCreate=" + userIdCreate +
                ", gmtCreate=" + gmtCreate +
                ", userIdMod=" + userIdMod +
                ", gmtModified=" + gmtModified +
                ", countNum=" + countNum +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        MoAbnormalDef that = (MoAbnormalDef) o;
        return Objects.equals(moAbnormalId, that.moAbnormalId) &&
                Objects.equals(moAbnormalcode, that.moAbnormalcode) &&
                Objects.equals(moAbnormalName, that.moAbnormalName) &&
                Objects.equals(resAbnormallevelId, that.resAbnormallevelId) &&
                Objects.equals(abnormalTypeId, that.abnormalTypeId) &&
                Objects.equals(moAbnormalDesc, that.moAbnormalDesc) &&
                Objects.equals(moAbnormalShowtemplate, that.moAbnormalShowtemplate) &&
                Objects.equals(showorder, that.showorder) &&
                Objects.equals(useflag, that.useflag) &&
                Objects.equals(userIdCreate, that.userIdCreate) &&
                Objects.equals(gmtCreate, that.gmtCreate) &&
                Objects.equals(userIdMod, that.userIdMod) &&
                Objects.equals(gmtModified, that.gmtModified) &&
                Objects.equals(countNum, that.countNum) &&
                Objects.equals(abnormalType, that.abnormalType) &&
                Objects.equals(resAbnormallevel, that.resAbnormallevel);
    }

    @Override
    public int hashCode() {

        return Objects.hash(moAbnormalId, moAbnormalcode, moAbnormalName, resAbnormallevelId, abnormalTypeId, moAbnormalDesc, moAbnormalShowtemplate, showorder, useflag, userIdCreate, gmtCreate, userIdMod, gmtModified, countNum, abnormalType, resAbnormallevel);
    }

}
