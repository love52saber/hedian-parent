package com.hedian.entity;

import java.io.Serializable;

import java.util.Date;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableLogic;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.FieldFill;
import com.baomidou.mybatisplus.enums.IdType;

import java.io.Serializable;
import java.util.List;

/**
 * <p>
 * 维修工单评价人管理
 * </p>
 *
 * @author hedian123
 * @since 2018-11-05
 */
@TableName("tbl_repair_order_appraiser")
public class RepairOrderAppraiser extends Model<RepairOrderAppraiser> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "appraiserid", type = IdType.AUTO)
    private Integer appraiserid;
    /**
     * 评价人类型： 1 基层评价人  2 科信评价人 3 默认评价人
     */
    private Integer appraisertype;
    /**
     * 评价人规则名称
     */
    private String apprasiername;
    /**
     * 评价用户组
     */
    @TableField("grp_id")
    private Integer grpId;
    @TableField(value = "user_id_create",fill = FieldFill.INSERT)
    private Long userIdCreate;
    @TableField(value = "gmt_create",fill = FieldFill.INSERT)
    private Date gmtCreate;
    @TableField(value = "user_id_mod",fill = FieldFill.UPDATE)
    private Long userIdMod;
    @TableField(value = "gmt_modified",fill = FieldFill.UPDATE)
    private Date gmtModified;
    @TableLogic
    @TableField(fill = FieldFill.INSERT)
    private Integer useflag;
    private Integer delflag;

    @TableField(exist = false)
    //用户组名称
    private String grpName;
    @TableField(exist = false)
    //关联域集合
    private List<Md> mdList;
    @TableField(exist = false)
    //关联域id数组
    private Integer[] mdIds;

    public Integer[] getMdIds() {
        return mdIds;
    }

    public void setMdIds(Integer[] mdIds) {
        this.mdIds = mdIds;
    }

    public List<Md> getMdList() {
        return mdList;
    }

    public void setMdList(List<Md> mdList) {
        this.mdList = mdList;
    }

    public String getGrpName() {
        return grpName;
    }

    public void setGrpName(String grpName) {
        this.grpName = grpName;
    }

    public Integer getAppraiserid() {
        return appraiserid;
    }

    public void setAppraiserid(Integer appraiserid) {
        this.appraiserid = appraiserid;
    }

    public Integer getAppraisertype() {
        return appraisertype;
    }

    public void setAppraisertype(Integer appraisertype) {
        this.appraisertype = appraisertype;
    }

    public String getApprasiername() {
        return apprasiername;
    }

    public void setApprasiername(String apprasiername) {
        this.apprasiername = apprasiername;
    }

    public Integer getGrpId() {
        return grpId;
    }

    public void setGrpId(Integer grpId) {
        this.grpId = grpId;
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

    public Integer getUseflag() {
        return useflag;
    }

    public void setUseflag(Integer useflag) {
        this.useflag = useflag;
    }

    public Integer getDelflag() {
        return delflag;
    }

    public void setDelflag(Integer delflag) {
        this.delflag = delflag;
    }

    @Override
    protected Serializable pkVal() {
        return this.appraiserid;
    }

    @Override
    public String toString() {
        return "RepairOrderAppraiser{" +
                "appraiserid=" + appraiserid +
                ", appraisertype=" + appraisertype +
                ", apprasiername=" + apprasiername +
                ", grpId=" + grpId +
                ", userIdCreate=" + userIdCreate +
                ", gmtCreate=" + gmtCreate +
                ", userIdMod=" + userIdMod +
                ", gmtModified=" + gmtModified +
                ", useflag=" + useflag +
                ", delflag=" + delflag +
                "}";
    }
}
