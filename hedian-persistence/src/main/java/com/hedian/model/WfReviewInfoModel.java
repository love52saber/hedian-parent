package com.hedian.model;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.Date;

public class WfReviewInfoModel {


    private Integer reviewId;
    private String disName;
    private String disPhone;
    private String currentReviewName;
    private String reviewDesc;
    @JSONField(format="yyyy-MM-dd HH:mm:ss")
    private Date reviewTime;

    public Integer getReviewId() {
        return reviewId;
    }

    public void setReviewId(Integer reviewId) {
        this.reviewId = reviewId;
    }

    public String getDisName() {
        return disName;
    }

    public void setDisName(String disName) {
        this.disName = disName;
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

    public String getCurrentReviewName() {
        return currentReviewName;
    }

    public void setCurrentReviewName(String currentReviewName) {
        this.currentReviewName = currentReviewName;
    }

    @Override
    public String toString() {
        return "WfReviewInfoModel{" +
                "reviewId=" + reviewId +
                ", disName='" + disName + '\'' +
                ", disPhone='" + disPhone + '\'' +
                ", currentReviewName='" + currentReviewName + '\'' +
                ", reviewDesc='" + reviewDesc + '\'' +
                ", reviewTime=" + reviewTime +
                '}';
    }
}
