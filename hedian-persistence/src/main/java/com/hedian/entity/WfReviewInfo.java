package com.hedian.entity;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

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

    @TableId("review_id")
    private Integer reviewId;
    @TableField("business_id")
    private Integer businessId;
    private Integer deptId;
    private Integer userId;
    private String phone;
    @TableField("review_desc")
    private String reviewDesc;


    public Integer getReviewId() {
        return reviewId;
    }

    public void setReviewId(Integer reviewId) {
        this.reviewId = reviewId;
    }

    public Integer getBusinessId() {
        return businessId;
    }

    public void setBusinessId(Integer businessId) {
        this.businessId = businessId;
    }

    public Integer getDeptId() {
        return deptId;
    }

    public void setDeptId(Integer deptId) {
        this.deptId = deptId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getReviewDesc() {
        return reviewDesc;
    }

    public void setReviewDesc(String reviewDesc) {
        this.reviewDesc = reviewDesc;
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
                ", userId=" + userId +
                ", phone=" + phone +
                ", reviewDesc='" + reviewDesc + '\'' +
                '}';
    }
}
