package com.hedian.entity;

import java.io.Serializable;

import java.util.Date;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableLogic;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.FieldFill;

import java.io.Serializable;
import java.util.List;

/**
 * <p>
 * 故障维修策略
 * </p>
 *
 * @author hedian123
 * @since 2018-09-17
 */
@TableName("tbl_fms")
public class Fms extends Model<Fms> {

    private static final long serialVersionUID = 1L;

    @TableId("fms_id")
    private Integer fmsId;
    /**
     * 维护策略名称
     */
    @TableField("fms_name")
    private String fmsName;
    /**
     * 维护策略描述
     */
    @TableField("fms_desc")
    private String fmsDesc;
    /**
     * 该维护策略所属单位，必填
     */
    @TableField("dept_id")
    private Long deptId;
    /**
     * 该维护策略维护人员，选填
     */
    @TableField("user_id")
    private Long userId;
    /**
     * 自动派单标记：0 不自动派单 1 自动派单
     */
    private Integer dispatchflag;
    /**
     * 自动或手动派单时，处理该工单的用户组
     */
    @TableField("grp_id")
    private Integer grpId;
    /**
     * 维护策略状态：1 启用 0 禁用
     */
    @TableField("fms_status")
    private Integer fmsStatus;
    /**
     * 策略生效开始时间
     */
    @TableField("begin_time")
    private Date beginTime;
    /**
     * 策略生效结束时间
     */
    @TableField("end_time")
    private Date endTime;
    /**
     * 显示顺序，从小到大，如果匹配到多条则显示在前面的那条生效
     */
    private Integer showorder;
    @TableLogic
    @TableField(value = "useflag", fill = FieldFill.INSERT)
    private Integer useflag;
    private Integer delflag;
    @TableField(value = "user_id_create", fill = FieldFill.INSERT)
    private Long userIdCreate;
    @TableField(value = "gmt_create", fill = FieldFill.INSERT)
    private Date gmtCreate;
    @TableField(value = "user_id_mod", fill = FieldFill.INSERT_UPDATE)
    private Long userIdMod;
    @TableField(value = "gmt_modified", fill = FieldFill.INSERT_UPDATE)
    private Date gmtModified;

    @TableField(exist = false)
    private List<Integer> abnormalIds;
    @TableField(exist = false)
    private List<Integer> abnormalTypeIds;
    @TableField(exist = false)
    private List<Integer> mdIds;
    @TableField(exist = false)
    private List<Integer> resIds;

    public List<Integer> getAbnormalIds() {
        return abnormalIds;
    }

    public void setAbnormalIds(List<Integer> abnormalIds) {
        this.abnormalIds = abnormalIds;
    }

    public List<Integer> getAbnormalTypeIds() {
        return abnormalTypeIds;
    }

    public void setAbnormalTypeIds(List<Integer> abnormalTypeIds) {
        this.abnormalTypeIds = abnormalTypeIds;
    }

    public List<Integer> getMdIds() {
        return mdIds;
    }

    public void setMdIds(List<Integer> mdIds) {
        this.mdIds = mdIds;
    }

    public List<Integer> getResIds() {
        return resIds;
    }

    public void setResIds(List<Integer> resIds) {
        this.resIds = resIds;
    }

    public Integer getFmsId() {
        return fmsId;
    }

    public void setFmsId(Integer fmsId) {
        this.fmsId = fmsId;
    }

    public String getFmsName() {
        return fmsName;
    }

    public void setFmsName(String fmsName) {
        this.fmsName = fmsName;
    }

    public String getFmsDesc() {
        return fmsDesc;
    }

    public void setFmsDesc(String fmsDesc) {
        this.fmsDesc = fmsDesc;
    }

    public Long getDeptId() {
        return deptId;
    }

    public void setDeptId(Long deptId) {
        this.deptId = deptId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Integer getDispatchflag() {
        return dispatchflag;
    }

    public void setDispatchflag(Integer dispatchflag) {
        this.dispatchflag = dispatchflag;
    }

    public Integer getGrpId() {
        return grpId;
    }

    public void setGrpId(Integer grpId) {
        this.grpId = grpId;
    }

    public Integer getFmsStatus() {
        return fmsStatus;
    }

    public void setFmsStatus(Integer fmsStatus) {
        this.fmsStatus = fmsStatus;
    }

    public Date getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(Date beginTime) {
        this.beginTime = beginTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
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

    public Integer getDelflag() {
        return delflag;
    }

    public void setDelflag(Integer delflag) {
        this.delflag = delflag;
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
        return this.fmsId;
    }

    @Override
    public String toString() {
        return "Fms{" +
                "fmsId=" + fmsId +
                ", fmsName=" + fmsName +
                ", fmsDesc=" + fmsDesc +
                ", deptId=" + deptId +
                ", userId=" + userId +
                ", dispatchflag=" + dispatchflag +
                ", grpId=" + grpId +
                ", fmsStatus=" + fmsStatus +
                ", beginTime=" + beginTime +
                ", endTime=" + endTime +
                ", showorder=" + showorder +
                ", useflag=" + useflag +
                ", delflag=" + delflag +
                ", userIdCreate=" + userIdCreate +
                ", gmtCreate=" + gmtCreate +
                ", userIdMod=" + userIdMod +
                ", gmtModified=" + gmtModified +
                "}";
    }
}
