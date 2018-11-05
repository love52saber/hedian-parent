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
@TableName("tbl_wf_confirm_info")
public class WfConfirmInfo extends Model<WfConfirmInfo> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "confirm_id",type = IdType.AUTO)
    private Integer confirmId;
    @TableField("business_id")
    private Long businessId;
    @TableField("confirm_user_id")
    private Long confirmUserId;
    @TableField("confirm_info")
    private String confirmInfo;
    @JSONField(format="yyyy-MM-dd HH:mm:ss")
    @TableField("confirm_time")
    private Date confirmTime;
    @TableField("base_appra_user_id")
    private Long baseAppraUserId;
    @TableField("base_appra_phone")
    private String baseAppraPhone;


    public Integer getConfirmId() {
        return confirmId;
    }

    public void setConfirmId(Integer confirmId) {
        this.confirmId = confirmId;
    }

    public Long getConfirmUserId() {
        return confirmUserId;
    }

    public void setConfirmUserId(Long confirmUserId) {
        this.confirmUserId = confirmUserId;
    }

    public String getConfirmInfo() {
        return confirmInfo;
    }

    public void setConfirmInfo(String confirmInfo) {
        this.confirmInfo = confirmInfo;
    }

    public Long getBaseAppraUserId() {
        return baseAppraUserId;
    }

    public void setBaseAppraUserId(Long baseAppraUserId) {
        this.baseAppraUserId = baseAppraUserId;
    }

    public String getBaseAppraPhone() {
        return baseAppraPhone;
    }

    public void setBaseAppraPhone(String baseAppraPhone) {
        this.baseAppraPhone = baseAppraPhone;
    }

    public Long getBusinessId() {
        return businessId;
    }

    public void setBusinessId(Long businessId) {
        this.businessId = businessId;
    }

    public Date getConfirmTime() {
        return confirmTime;
    }

    public void setConfirmTime(Date confirmTime) {
        this.confirmTime = confirmTime;
    }

    @Override
    protected Serializable pkVal() {
        return this.confirmId;
    }

    @Override
    public String toString() {
        return "WfConfirmInfo{" +
                "confirmId=" + confirmId +
                ", businessId=" + businessId +
                ", confirmUserId=" + confirmUserId +
                ", confirmInfo='" + confirmInfo + '\'' +
                ", confirmTime=" + confirmTime +
                ", baseAppraUserId=" + baseAppraUserId +
                ", baseAppraPhone='" + baseAppraPhone + '\'' +
                '}';
    }
}
