package com.hedian.entity;

import java.io.Serializable;

import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 异常类型
 * </p>
 *
 * @author hedian123
 * @since 2018-09-04
 */
@TableName("tbl_abnormal_type")
public class AbnormalType extends Model<AbnormalType> {

    private static final long serialVersionUID = 1L;

    @TableId("abnormal_type_id")
    private Integer abnormalTypeId;
    @TableField("abnormal_type_name")
    private String abnormalTypeName;
    @TableField("abnormal_type_desc")
    private String abnormalTypeDesc;
    private Integer delflag;
    private Integer useflag;
    @TableField("user_id_create")
    private Long userIdCreate;
    @TableField("gmt_create")
    private Date gmtCreate;
    @TableField("user_id_mod")
    private Long userIdMod;
    @TableField("gmt_modified")
    private Date gmtModified;


    public Integer getAbnormalTypeId() {
        return abnormalTypeId;
    }

    public void setAbnormalTypeId(Integer abnormalTypeId) {
        this.abnormalTypeId = abnormalTypeId;
    }

    public String getAbnormalTypeName() {
        return abnormalTypeName;
    }

    public void setAbnormalTypeName(String abnormalTypeName) {
        this.abnormalTypeName = abnormalTypeName;
    }

    public String getAbnormalTypeDesc() {
        return abnormalTypeDesc;
    }

    public void setAbnormalTypeDesc(String abnormalTypeDesc) {
        this.abnormalTypeDesc = abnormalTypeDesc;
    }

    public Integer getDelflag() {
        return delflag;
    }

    public void setDelflag(Integer delflag) {
        this.delflag = delflag;
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
        return this.abnormalTypeId;
    }

    @Override
    public String toString() {
        return "AbnormalType{" +
        "abnormalTypeId=" + abnormalTypeId +
        ", abnormalTypeName=" + abnormalTypeName +
        ", abnormalTypeDesc=" + abnormalTypeDesc +
        ", delflag=" + delflag +
        ", useflag=" + useflag +
        ", userIdCreate=" + userIdCreate +
        ", gmtCreate=" + gmtCreate +
        ", userIdMod=" + userIdMod +
        ", gmtModified=" + gmtModified +
        "}";
    }
}
