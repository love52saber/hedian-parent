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
    private Integer reviewUserId;
    @TableField("review_desc")
    private String reviewDesc;
    private Integer disUserId;
    private String disPhone;


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

    public Integer getReviewUserId() {
        return reviewUserId;
    }

    public void setReviewUserId(Integer reviewUserId) {
        this.reviewUserId = reviewUserId;
    }

    public Integer getDisUserId() {
        return disUserId;
    }

    public void setDisUserId(Integer disUserId) {
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
                ", disUserId=" + disUserId +
                ", disPhone='" + disPhone + '\'' +
                '}';
    }
}
