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
@TableName("tbl_wf_kexin_appra_info")
public class WfKexinAppraInfo extends Model<WfKexinAppraInfo> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "kexin_appra_id", type = IdType.AUTO)
    private Integer kexinAppraId;
    @TableField("business_id")
    private Long businessId;
    @TableField("kexin_appra_user_id")
    private Long kexinAppraUserId;
    @TableField("kexin_appra_info")
    private String kexinAppraInfo;
    @JSONField(format="yyyy-MM-dd HH:mm:ss")
    @TableField("kexin_appra_time")
    private Date kexinAppraTime;
    @TableField("kexin_appra_scora")
    private Integer kexinAppraScora;


    public Integer getKexinAppraId() {
        return kexinAppraId;
    }

    public void setKexinAppraId(Integer kexinAppraId) {
        this.kexinAppraId = kexinAppraId;
    }

    public Long getKexinAppraUserId() {
        return kexinAppraUserId;
    }

    public void setKexinAppraUserId(Long kexinAppraUserId) {
        this.kexinAppraUserId = kexinAppraUserId;
    }

    public String getKexinAppraInfo() {
        return kexinAppraInfo;
    }

    public void setKexinAppraInfo(String kexinAppraInfo) {
        this.kexinAppraInfo = kexinAppraInfo;
    }

    public Integer getKexinAppraScora() {
        return kexinAppraScora;
    }

    public void setKexinAppraScora(Integer kexinAppraScora) {
        this.kexinAppraScora = kexinAppraScora;
    }

    public Date getKexinAppraTime() {
        return kexinAppraTime;
    }

    public void setKexinAppraTime(Date kexinAppraTime) {
        this.kexinAppraTime = kexinAppraTime;
    }

    public Long getBusinessId() {
        return businessId;
    }

    public void setBusinessId(Long businessId) {
        this.businessId = businessId;
    }

    @Override
    protected Serializable pkVal() {
        return this.kexinAppraId;
    }

    @Override
    public String toString() {
        return "WfKexinAppraInfo{" +
                "kexinAppraId=" + kexinAppraId +
                ", businessId=" + businessId +
                ", kexinAppraUserId=" + kexinAppraUserId +
                ", kexinAppraInfo='" + kexinAppraInfo + '\'' +
                ", kexinAppraTime=" + kexinAppraTime +
                ", kexinAppraScora=" + kexinAppraScora +
                '}';
    }
}
