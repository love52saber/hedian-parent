package com.hedian.model;

import com.alibaba.fastjson.annotation.JSONField;
import com.hedian.entity.ResBase;
import com.hedian.entity.WfReviewInfo;

import java.math.BigDecimal;
import java.util.Date;

public class WfBusinessModel {


    private Long businessId;
    /**
     * 流程id
     */
    private String wfId;
    /**
     * 工单标题
     */
    private String wfTitle;
    /**
     * 工单类型
     */
    private Integer wfType;

    /**
     * 期望时长：小时为单位存储
     */
    private BigDecimal hopetime;
    /**
     * 最晚时长：小时为单位存储
     */
    private BigDecimal deadtime;

    private String username;

    private String telephone;

    @JSONField(format="yyyy-MM-dd HH:mm:ss")
    private Date gmtCreate;
    /**
     * 告警等级
     */
    private String resAbnormaldesc;

    private String url;

    private Long resAbnormalId;

    private String resAbnormalName;

    @JSONField(format="yyyy-MM-dd HH:mm:ss")
    private Date resAbnomaltime;

    private String abnormalTypeName;

    private String resAbnormallevelName;

    private String resStypeName;

    private String resMtypeName;
    /**
     * 流程状态
     */

    private Integer wfStatus;

    private Integer useflag;
    /**
     * 当前节点
     */

    private Integer currentStep;
    /**
     * 处理人
     */

    private String currentUserName;
    /**
     * 处理人ID
     */

    private Long currentUser;
    /**
     * 评价最终得分
     */
    private Integer woEvalScore;

    private ResBase resBase;

    private WfReviewInfoModel wfReviewInfoModel;


    private String taskId;

    public Long getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(Long currentUser) {
        this.currentUser = currentUser;
    }

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public Long getBusinessId() {
        return businessId;
    }

    public void setBusinessId(Long businessId) {
        this.businessId = businessId;
    }

    public String getWfId() {
        return wfId;
    }

    public void setWfId(String wfId) {
        this.wfId = wfId;
    }

    public String getWfTitle() {
        return wfTitle;
    }

    public void setWfTitle(String wfTitle) {
        this.wfTitle = wfTitle;
    }

    public Integer getWfType() {
        return wfType;
    }

    public void setWfType(Integer wfType) {
        this.wfType = wfType;
    }

    public BigDecimal getHopetime() {
        return hopetime;
    }

    public void setHopetime(BigDecimal hopetime) {
        this.hopetime = hopetime;
    }

    public BigDecimal getDeadtime() {
        return deadtime;
    }

    public void setDeadtime(BigDecimal deadtime) {
        this.deadtime = deadtime;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public Date getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    public String getResAbnormaldesc() {
        return resAbnormaldesc;
    }

    public void setResAbnormaldesc(String resAbnormaldesc) {
        this.resAbnormaldesc = resAbnormaldesc;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Long getResAbnormalId() {
        return resAbnormalId;
    }

    public void setResAbnormalId(Long resAbnormalId) {
        this.resAbnormalId = resAbnormalId;
    }

    public String getResAbnormalName() {
        return resAbnormalName;
    }

    public void setResAbnormalName(String resAbnormalName) {
        this.resAbnormalName = resAbnormalName;
    }

    public Date getResAbnomaltime() {
        return resAbnomaltime;
    }

    public void setResAbnomaltime(Date resAbnomaltime) {
        this.resAbnomaltime = resAbnomaltime;
    }

    public String getResAbnormallevelName() {
        return resAbnormallevelName;
    }

    public void setResAbnormallevelName(String resAbnormallevelName) {
        this.resAbnormallevelName = resAbnormallevelName;
    }

    public String getAbnormalTypeName() {
        return abnormalTypeName;
    }

    public void setAbnormalTypeName(String abnormalTypeName) {
        this.abnormalTypeName = abnormalTypeName;
    }

    public String getResStypeName() {
        return resStypeName;
    }

    public void setResStypeName(String resStypeName) {
        this.resStypeName = resStypeName;
    }

    public String getResMtypeName() {
        return resMtypeName;
    }

    public void setResMtypeName(String resMtypeName) {
        this.resMtypeName = resMtypeName;
    }

    public Integer getWfStatus() {
        return wfStatus;
    }

    public void setWfStatus(Integer wfStatus) {
        this.wfStatus = wfStatus;
    }

    public Integer getUseflag() {
        return useflag;
    }

    public void setUseflag(Integer useflag) {
        this.useflag = useflag;
    }

    public Integer getCurrentStep() {
        return currentStep;
    }

    public void setCurrentStep(Integer currentStep) {
        this.currentStep = currentStep;
    }

    public String getCurrentUserName() {
        return currentUserName;
    }

    public void setCurrentUserName(String currentUserName) {
        this.currentUserName = currentUserName;
    }

    public Integer getWoEvalScore() {
        return woEvalScore;
    }

    public void setWoEvalScore(Integer woEvalScore) {
        this.woEvalScore = woEvalScore;
    }

    public ResBase getResBase() {
        return resBase;
    }

    public void setResBase(ResBase resBase) {
        this.resBase = resBase;
    }

    public WfReviewInfoModel getWfReviewInfoModel() {
        return wfReviewInfoModel;
    }

    public void setWfReviewInfoModel(WfReviewInfoModel wfReviewInfoModel) {
        this.wfReviewInfoModel = wfReviewInfoModel;
    }



}
