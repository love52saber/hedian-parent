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
@TableName("tbl_wf_dis_info")
public class WfDisInfo extends Model<WfDisInfo> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "dis_id",type = IdType.AUTO)
    private Integer disId;
    @TableField("business_id")
    private Long businessId;
    @TableField("dis_user_id")
    private Long disUserId;
    @TableField("dis_info")
    private String disInfo;
    @JSONField(format="yyyy-MM-dd HH:mm:ss")
    @TableField("dis_time")
    private Date disTime;
    @TableField("over_user_id")
    private Long overUserId;
    @TableField("over_phone")
    private String overPhone;


    public Integer getDisId() {
        return disId;
    }

    public void setDisId(Integer disId) {
        this.disId = disId;
    }

    public Long getDisUserId() {
        return disUserId;
    }

    public void setDisUserId(Long disUserId) {
        this.disUserId = disUserId;
    }

    public String getDisInfo() {
        return disInfo;
    }

    public void setDisInfo(String disInfo) {
        this.disInfo = disInfo;
    }

    public Long getOverUserId() {
        return overUserId;
    }

    public void setOverUserId(Long overUserId) {
        this.overUserId = overUserId;
    }

    public String getOverPhone() {
        return overPhone;
    }

    public void setOverPhone(String overPhone) {
        this.overPhone = overPhone;
    }

    public Date getDisTime() {
        return disTime;
    }

    public void setDisTime(Date disTime) {
        this.disTime = disTime;
    }

    public Long getBusinessId() {
        return businessId;
    }

    public void setBusinessId(Long businessId) {
        this.businessId = businessId;
    }

    @Override
    protected Serializable pkVal() {
        return this.disId;
    }

    @Override
    public String toString() {
        return "WfDisInfo{" +
                "disId=" + disId +
                ", businessId=" + businessId +
                ", disUserId=" + disUserId +
                ", disInfo='" + disInfo + '\'' +
                ", disTime=" + disTime +
                ", overUserId=" + overUserId +
                ", overPhone='" + overPhone + '\'' +
                '}';
    }
}
