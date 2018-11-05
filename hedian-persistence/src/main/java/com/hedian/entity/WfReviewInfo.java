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
 * @since 2018-10-18
 */
@TableName("tbl_wf_review_info")
public class WfReviewInfo extends Model<WfReviewInfo> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "review_id",type = IdType.AUTO)
    private Integer reviewId;
    @TableField("business_id")
    private Long businessId;
    @TableField("dept_id")
    private Integer deptId;
    @TableField("review_user_id")
    private Long reviewUserId;
    @TableField("review_desc")
    private String reviewDesc;
    @JSONField(format="yyyy-MM-dd HH:mm:ss")
    @TableField("review_time")
    private Date reviewTime;
    @TableField("dis_user_id")
    private Long disUserId;
    @TableField("dis_phone")
    private String disPhone;


    public Integer getReviewId() {
        return reviewId;
    }

    public void setReviewId(Integer reviewId) {
        this.reviewId = reviewId;
    }

    public Long getBusinessId() {
        return businessId;
    }

    public void setBusinessId(Long businessId) {
        this.businessId = businessId;
    }

    public Integer getDeptId() {
        return deptId;
    }

    public void setDeptId(Integer deptId) {
        this.deptId = deptId;
    }

    public Long getReviewUserId() {
        return reviewUserId;
    }

    public void setReviewUserId(Long reviewUserId) {
        this.reviewUserId = reviewUserId;
    }

    public Long getDisUserId() {
        return disUserId;
    }

    public void setDisUserId(Long disUserId) {
        this.disUserId = disUserId;
    }

    public String getDisPhone() {
        return disPhone;
    }

    public void setDisPhone(String disPhone) {
        this.disPhone = disPhone;
    }

    public String getReviewDesc() {
        return reviewDesc;
    }

    public void setReviewDesc(String reviewDesc) {
        this.reviewDesc = reviewDesc;
    }

    public Date getReviewTime() {
        return reviewTime;
    }

    public void setReviewTime(Date reviewTime) {
        this.reviewTime = reviewTime;
    }

    @Override
    protected Serializable pkVal() {
        return this.reviewId;
    }

    @Override
    public String toString() {
        return "WfReviewInfo{" +
                "reviewId=" + reviewId +
                ", businessId=" + businessId +
                ", deptId=" + deptId +
                ", reviewUserId=" + reviewUserId +
                ", reviewDesc='" + reviewDesc + '\'' +
                ", reviewTime=" + reviewTime +
                ", disUserId=" + disUserId +
                ", disPhone='" + disPhone + '\'' +
                '}';
    }
}
