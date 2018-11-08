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
@TableName("tbl_wf_handle_info")
public class WfHandleInfo extends Model<WfHandleInfo> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "handle_id",type = IdType.AUTO)
    private Integer handleId;
    @TableField("business_id")
    private Long businessId;
    @TableField("handle_user_id")
    private Long handleUserId;
    @TableField("handle_info")
    private String handleInfo;
    @TableField("handle_method")
    private String handleMethod;
    @TableField("handle_type")
    private Boolean handleType;
    @TableField("handle_file")
    private String handleFile;
    @JSONField(format="yyyy-MM-dd HH:mm:ss")
    @TableField("handle_time")
    private Date handleTime;
    @TableField("confirm_user_id")
    private Long confirmUserId;
    @TableField("confirm_phone")
    private String confirmPhone;


    public Integer getHandleId() {
        return handleId;
    }

    public void setHandleId(Integer handleId) {
        this.handleId = handleId;
    }

    public Long getHandleUserId() {
        return handleUserId;
    }

    public void setHandleUserId(Long handleUserId) {
        this.handleUserId = handleUserId;
    }

    public String getHandleInfo() {
        return handleInfo;
    }

    public void setHandleInfo(String handleInfo) {
        this.handleInfo = handleInfo;
    }

    public Boolean getHandleType() {
        return handleType;
    }

    public void setHandleType(Boolean handleType) {
        this.handleType = handleType;
    }

    public String getHandleFile() {
        return handleFile;
    }

    public void setHandleFile(String handleFile) {
        this.handleFile = handleFile;
    }

    public Long getConfirmUserId() {
        return confirmUserId;
    }

    public void setConfirmUserId(Long confirmUserId) {
        this.confirmUserId = confirmUserId;
    }

    public String getConfirmPhone() {
        return confirmPhone;
    }

    public void setConfirmPhone(String confirmPhone) {
        this.confirmPhone = confirmPhone;
    }

    public String getHandleMethod() {
        return handleMethod;
    }

    public void setHandleMethod(String handleMethod) {
        this.handleMethod = handleMethod;
    }

    public Date getHandleTime() {
        return handleTime;
    }

    public void setHandleTime(Date handleTime) {
        this.handleTime = handleTime;
    }

    public Long getBusinessId() {
        return businessId;
    }

    public void setBusinessId(Long businessId) {
        this.businessId = businessId;
    }

    @Override
    protected Serializable pkVal() {
        return this.handleId;
    }

    @Override
    public String toString() {
        return "WfHandleInfo{" +
                "handleId=" + handleId +
                ", businessId=" + businessId +
                ", handleUserId=" + handleUserId +
                ", handleInfo='" + handleInfo + '\'' +
                ", handleMethod='" + handleMethod + '\'' +
                ", handleType=" + handleType +
                ", handleFile='" + handleFile + '\'' +
                ", handleTime=" + handleTime +
                ", confirmUserId=" + confirmUserId +
                ", confirmPhone='" + confirmPhone + '\'' +
                '}';
    }
}
