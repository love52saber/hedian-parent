package com.hedian.entity;

import java.io.Serializable;

import java.math.BigDecimal;
import java.util.Date;

import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.FieldFill;
import com.baomidou.mybatisplus.enums.IdType;

import javax.print.DocFlavor;
import java.io.Serializable;

/**
 * <p>
 * 工单考核sla定义表
 * </p>
 *
 * @author hedian123
 * @since 2018-11-27
 */
@TableName("tbl_wo_sla")
public class WoSla extends Model<WoSla> {

    private static final long serialVersionUID = 1L;

    @TableId(value="wo_sla_id",type = IdType.AUTO)
    private Integer woSlaId;
    @TableField("wo_sla_name")
    private String woSlaName;
    @TableField("wo_sla_desc")
    private String woSlaDesc;
    /**
     * 非必选，可以按照告警等级设置不同的sla，也可以不设置，获取sla时优先按照告警等级获取，如果没有告警等级则再获取没有配置等级的。
     */
    @TableField("res_abnormallevel_id")
    private Integer resAbnormallevelId;
    /**
     * 流程ID
     */
    @TableField("proc_def_id")
    private String procDefId;
    /**
     * 入节点ID
     */
    @TableField("act_id_in")
    private String actIdIn;
    /**
     * 出节点ID
     */
    @TableField("act_id_out")
    private String actIdOut;
    /**
     * 期望时长：小时为单位存储
     */
    private BigDecimal hopetime;
    /**
     * 最晚时长：小时为单位存储
     */
    private BigDecimal deadtime;
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date begintime;
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date endtime;
    /**
     * 启用状态：1 启用 0 禁用
     */
    @TableField("wo_sla_status")
    private Integer woSlaStatus;
    /**
     * 创建工单标记： 1 是 创建工单时获取该配置作为期望时长和最晚时长的配置，如果一个工单配置了多个则几个配置的时长叠加，2 否
     */
    private Integer createflag;
    private Integer delflag;
    private Integer useflag;
    @TableField(value = "user_id_create",fill = FieldFill.INSERT)
    private Long userIdCreate;
    @TableField(value = "gmt_create",fill = FieldFill.INSERT)
    private Date gmtCreate;
    @TableField(value = "user_id_mod",fill = FieldFill.UPDATE)
    private Long userIdMod;
    @TableField(value = "gmt_modified",fill = FieldFill.UPDATE)
    private Date gmtModified;

    @TableField(exist = false)
    private String resAbnormallevelName;

    public String getResAbnormallevelName() {
        return resAbnormallevelName;
    }

    public void setResAbnormallevelName(String resAbnormallevelName) {
        this.resAbnormallevelName = resAbnormallevelName;
    }

    public Integer getWoSlaId() {
        return woSlaId;
    }

    public void setWoSlaId(Integer woSlaId) {
        this.woSlaId = woSlaId;
    }

    public String getWoSlaName() {
        return woSlaName;
    }

    public void setWoSlaName(String woSlaName) {
        this.woSlaName = woSlaName;
    }

    public String getWoSlaDesc() {
        return woSlaDesc;
    }

    public void setWoSlaDesc(String woSlaDesc) {
        this.woSlaDesc = woSlaDesc;
    }

    public Integer getResAbnormallevelId() {
        return resAbnormallevelId;
    }

    public void setResAbnormallevelId(Integer resAbnormallevelId) {
        this.resAbnormallevelId = resAbnormallevelId;
    }

    public String getProcDefId() {
        return procDefId;
    }

    public void setProcDefId(String procDefId) {
        this.procDefId = procDefId;
    }

    public String getActIdIn() {
        return actIdIn;
    }

    public void setActIdIn(String actIdIn) {
        this.actIdIn = actIdIn;
    }

    public String getActIdOut() {
        return actIdOut;
    }

    public void setActIdOut(String actIdOut) {
        this.actIdOut = actIdOut;
    }

    public BigDecimal getHopetime() {
        return hopetime;
    }

    public void setHopetime(BigDecimal hopetime) {
        this.hopetime = hopetime;
    }

    public BigDecimal getDeadtime() {
        return deadtime;
    }

    public void setDeadtime(BigDecimal deadtime) {
        this.deadtime = deadtime;
    }

    public Date getBegintime() {
        return begintime;
    }

    public void setBegintime(Date begintime) {
        this.begintime = begintime;
    }

    public Date getEndtime() {
        return endtime;
    }

    public void setEndtime(Date endtime) {
        this.endtime = endtime;
    }

    public Integer getWoSlaStatus() {
        return woSlaStatus;
    }

    public void setWoSlaStatus(Integer woSlaStatus) {
        this.woSlaStatus = woSlaStatus;
    }

    public Integer getCreateflag() {
        return createflag;
    }

    public void setCreateflag(Integer createflag) {
        this.createflag = createflag;
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
        return this.woSlaId;
    }

    @Override
    public String toString() {
        return "WoSla{" +
        "woSlaId=" + woSlaId +
        ", woSlaName=" + woSlaName +
        ", woSlaDesc=" + woSlaDesc +
        ", resAbnormallevelId=" + resAbnormallevelId +
        ", procDefId=" + procDefId +
        ", actIdIn=" + actIdIn +
        ", actIdOut=" + actIdOut +
        ", hopetime=" + hopetime +
        ", deadtime=" + deadtime +
        ", begintime=" + begintime +
        ", endtime=" + endtime +
        ", woSlaStatus=" + woSlaStatus +
        ", createflag=" + createflag +
        ", delflag=" + delflag +
        ", useflag=" + useflag +
        ", userIdCreate=" + userIdCreate +
        ", gmtCreate=" + gmtCreate +
        ", userIdMod=" + userIdMod +
        ", gmtModified=" + gmtModified +
        "}";
    }
}
