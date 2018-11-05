package com.hedian.entity;

import java.io.Serializable;

import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableId;
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
@TableName("tbl_wf_base_appra_info")
public class WfBaseAppraInfo extends Model<WfBaseAppraInfo> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "base_appra_id", type = IdType.AUTO)
    private Integer baseAppraId;
    @TableField("business_id")
    private Long businessId;
    @TableField("base_appra_user_id")
    private Long baseAppraUserId;
    @TableField("base_appra_score")
    private Integer baseAppraScore;
    @TableField("base_appra_info")
    private String baseAppraInfo;
    @JSONField(format="yyyy-MM-dd HH:mm:ss")
    @TableField("base_appra_time")
    private Date baseAppraTime;
    @TableField("kexin_appra_user_id")
    private Long kexinAppraUserId;
    @TableField("kexin_appra_phone")
    private String kexinAppraPhone;


    public Integer getBaseAppraId() {
        return baseAppraId;
    }

    public void setBaseAppraId(Integer baseAppraId) {
        this.baseAppraId = baseAppraId;
    }

    public Long getBaseAppraUserId() {
        return baseAppraUserId;
    }

    public void setBaseAppraUserId(Long baseAppraUserId) {
        this.baseAppraUserId = baseAppraUserId;
    }

    public Integer getBaseAppraScore() {
        return baseAppraScore;
    }

    public void setBaseAppraScore(Integer baseAppraScore) {
        this.baseAppraScore = baseAppraScore;
    }

    public String getBaseAppraInfo() {
        return baseAppraInfo;
    }

    public void setBaseAppraInfo(String baseAppraInfo) {
        this.baseAppraInfo = baseAppraInfo;
    }

    public Long getKexinAppraUserId() {
        return kexinAppraUserId;
    }

    public void setKexinAppraUserId(Long kexinAppraUserId) {
        this.kexinAppraUserId = kexinAppraUserId;
    }

    public String getKexinAppraPhone() {
        return kexinAppraPhone;
    }

    public void setKexinAppraPhone(String kexinAppraPhone) {
        this.kexinAppraPhone = kexinAppraPhone;
    }

    public Date getBaseAppraTime() {
        return baseAppraTime;
    }

    public void setBaseAppraTime(Date baseAppraTime) {
        this.baseAppraTime = baseAppraTime;
    }

    public Long getBusinessId() {
        return businessId;
    }

    public void setBusinessId(Long businessId) {
        this.businessId = businessId;
    }

    @Override
    protected Serializable pkVal() {
        return this.baseAppraId;
    }

    @Override
    public String toString() {
        return "WfBaseAppraInfo{" +
                "baseAppraId=" + baseAppraId +
                ", businessId=" + businessId +
                ", baseAppraUserId=" + baseAppraUserId +
                ", baseAppraScore=" + baseAppraScore +
                ", baseAppraInfo='" + baseAppraInfo + '\'' +
                ", baseAppraTime=" + baseAppraTime +
                ", kexinAppraUserId=" + kexinAppraUserId +
                ", kexinAppraPhone='" + kexinAppraPhone + '\'' +
                '}';
    }
}
