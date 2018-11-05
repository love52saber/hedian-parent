package com.hedian.entity;

import java.io.Serializable;

import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 
 * </p>
 *
 * @author hedian123
 * @since 2018-11-01
 */
@TableName("tbl_wf_over_info")
public class WfOverInfo extends Model<WfOverInfo> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "over_id",type = IdType.AUTO)
    private Integer overId;
    @TableField("business_id")
    private Long businessId;
    @TableField("over_user_id")
    private Long overUserId;
    @TableField("over_info")
    private String overInfo;
    @TableField("over_type")
    private boolean overType;
    @TableField("over_file")
    private String overFile;
    @JSONField(format="yyyy-MM-dd HH:mm:ss")
    @TableField("over_time")
    private Date overTime;
    @TableField("handle_user_id")
    private Long handleUserId;
    @TableField("handle_phone")
    private String handlePhone;


    public Integer getOverId() {
        return overId;
    }

    public void setOverId(Integer overId) {
        this.overId = overId;
    }

    public Long getOverUserId() {
        return overUserId;
    }

    public void setOverUserId(Long overUserId) {
        this.overUserId = overUserId;
    }

    public String getOverInfo() {
        return overInfo;
    }

    public void setOverInfo(String overInfo) {
        this.overInfo = overInfo;
    }

    public String getOverFile() {
        return overFile;
    }

    public void setOverFile(String overFile) {
        this.overFile = overFile;
    }

    public Long getHandleUserId() {
        return handleUserId;
    }

    public void setHandleUserId(Long handleUserId) {
        this.handleUserId = handleUserId;
    }

    public String getHandlePhone() {
        return handlePhone;
    }

    public void setHandlePhone(String handlePhone) {
        this.handlePhone = handlePhone;
    }

    public Date getOverTime() {
        return overTime;
    }

    public void setOverTime(Date overTime) {
        this.overTime = overTime;
    }

    public boolean isOverType() {
        return overType;
    }

    public void setOverType(boolean overType) {
        this.overType = overType;
    }

    public Long getBusinessId() {
        return businessId;
    }

    public void setBusinessId(Long businessId) {
        this.businessId = businessId;
    }

    @Override
    protected Serializable pkVal() {
        return this.overId;
    }

    @Override
    public String toString() {
        return "WfOverInfo{" +
                "overId=" + overId +
                ", businessId=" + businessId +
                ", overUserId=" + overUserId +
                ", overInfo='" + overInfo + '\'' +
                ", overType=" + overType +
                ", overFile='" + overFile + '\'' +
                ", overTime=" + overTime +
                ", handleUserId=" + handleUserId +
                ", handlePhone='" + handlePhone + '\'' +
                '}';
    }
}
