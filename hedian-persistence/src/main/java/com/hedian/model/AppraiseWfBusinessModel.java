package com.hedian.model;

import com.alibaba.fastjson.annotation.JSONField;
import com.hedian.entity.ResBase;

import java.math.BigDecimal;
import java.util.Date;

public class AppraiseWfBusinessModel {


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

    private Integer abnormalTypeId;

    private Integer resAbnormallevelId;

    private String resAbnormallevelName;

    private String resStypeName;

    private String resMtypeName;

    /**
     * 故障类型
     */
    private  String abnormalTypeName;
 /**
     * 流程状态
     */

    private Boolean wfStatus;

    private Integer useflag;
    /**
     * 当前节点
     */

    private Integer currentStep;
    /**
     * 处理人ID
     */

    private Long currentUser;
    /**
     * 评价最终得分
     */
    private Integer woEvalScore;

    private String deptName;

    private WfBaseAppraInfoModel wfBaseAppraInfoModel;

    private WfKexinAppraInfoModel wfKexinAppraInfoModel;

    public String getAbnormalTypeName() {
        return abnormalTypeName;
    }

    public void setAbnormalTypeName(String abnormalTypeName) {
        this.abnormalTypeName = abnormalTypeName;
    }

    public WfBaseAppraInfoModel getWfBaseAppraInfoModel() {
        return wfBaseAppraInfoModel;
    }

    public void setWfBaseAppraInfoModel(WfBaseAppraInfoModel wfBaseAppraInfoModel) {
        this.wfBaseAppraInfoModel = wfBaseAppraInfoModel;
    }

    public WfKexinAppraInfoModel getWfKexinAppraInfoModel() {
        return wfKexinAppraInfoModel;
    }

    public void setWfKexinAppraInfoModel(WfKexinAppraInfoModel wfKexinAppraInfoModel) {
        this.wfKexinAppraInfoModel = wfKexinAppraInfoModel;
    }

    public Integer getAbnormalTypeId() {
        return abnormalTypeId;
    }

    public void setAbnormalTypeId(Integer abnormalTypeId) {
        this.abnormalTypeId = abnormalTypeId;
    }

    public Integer getResAbnormallevelId() {
        return resAbnormallevelId;
    }

    public void setResAbnormallevelId(Integer resAbnormallevelId) {
        this.resAbnormallevelId = resAbnormallevelId;
    }

    public Long getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(Long currentUser) {
        this.currentUser = currentUser;
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

    public String getResAbnormallevelName() {
        return resAbnormallevelName;
    }

    public void setResAbnormallevelName(String resAbnormallevelName) {
        this.resAbnormallevelName = resAbnormallevelName;
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

    public Boolean getWfStatus() {
        return wfStatus;
    }

    public void setWfStatus(Boolean wfStatus) {
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

    public Integer getWoEvalScore() {
        return woEvalScore;
    }

    public void setWoEvalScore(Integer woEvalScore) {
        this.woEvalScore = woEvalScore;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }
}
