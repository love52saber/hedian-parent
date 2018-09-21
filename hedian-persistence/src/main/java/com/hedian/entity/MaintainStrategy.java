package com.hedian.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableLogic;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.FieldFill;
import com.baomidou.mybatisplus.enums.IdType;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

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

    @TableId(value = "ms_id", type = IdType.AUTO)
    private Integer msId;
    @TableField("ms_name")
    private String msName;
    /**
     * 维护期类型： 1 新建 2 维修 3 扩建 4 割接 5 其他
     */
    @TableField("ms_type")
    private Integer msType;

    @TableField("begin_time")
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date beginTime;

    @TableField("end_time")
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date endTime;
    /**
     * 状态：1 启用 2 停用
     */
    @TableField("ms_status")
    private Integer msStatus;
    @TableField("ms_desc")
    private String msDesc;
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
    private List<Integer> resIds;

    public List<Integer> getResIds() {
        return resIds;
    }

    public void setResIds(List<Integer> resIds) {
        this.resIds = resIds;
    }

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
