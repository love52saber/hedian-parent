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
 * 维护期策略
 * </p>
 *
 * @author hedian123
 * @since 2018-09-17
 */
@TableName("tbl_maintain_strategy")
public class MaintainStrategy extends Model<MaintainStrategy> {

    private static final long serialVersionUID = 1L;

    @TableId("ms_id")
    private Integer msId;
    @TableField("ms_name")
    private String msName;
    /**
     * 维护期类型： 1 新建 2 维修 3 扩建 4 割接 5 其他
     */
    @TableField("ms_type")
    private Integer msType;
    @TableField("begin_time")
    private Date beginTime;
    @TableField("end_time")
    private Date endTime;
    /**
     * 状态：1 启用 2 停用
     */
    @TableField("ms_status")
    private Integer msStatus;
    @TableField("ms_desc")
    private String msDesc;
    private Integer useflag;
    @TableField("user_id_create")
    private Long userIdCreate;
    @TableField("gmt_create")
    private Date gmtCreate;
    @TableField("user_id_mod")
    private Long userIdMod;
    @TableField("gmt_modified")
    private Date gmtModified;


    public Integer getMsId() {
        return msId;
    }

    public void setMsId(Integer msId) {
        this.msId = msId;
    }

    public String getMsName() {
        return msName;
    }

    public void setMsName(String msName) {
        this.msName = msName;
    }

    public Integer getMsType() {
        return msType;
    }

    public void setMsType(Integer msType) {
        this.msType = msType;
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

    public Integer getMsStatus() {
        return msStatus;
    }

    public void setMsStatus(Integer msStatus) {
        this.msStatus = msStatus;
    }

    public String getMsDesc() {
        return msDesc;
    }

    public void setMsDesc(String msDesc) {
        this.msDesc = msDesc;
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
        return this.msId;
    }

    @Override
    public String toString() {
        return "MaintainStrategy{" +
        "msId=" + msId +
        ", msName=" + msName +
        ", msType=" + msType +
        ", beginTime=" + beginTime +
        ", endTime=" + endTime +
        ", msStatus=" + msStatus +
        ", msDesc=" + msDesc +
        ", useflag=" + useflag +
        ", userIdCreate=" + userIdCreate +
        ", gmtCreate=" + gmtCreate +
        ", userIdMod=" + userIdMod +
        ", gmtModified=" + gmtModified +
        "}";
    }
}
