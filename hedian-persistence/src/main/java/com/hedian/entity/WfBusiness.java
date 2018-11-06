package com.hedian.entity;

import java.io.Serializable;

import com.baomidou.mybatisplus.enums.FieldFill;
import com.baomidou.mybatisplus.enums.IdType;
import java.util.Date;
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
@TableName("tbl_wf_business")
public class WfBusiness extends Model<WfBusiness> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "business_id", type = IdType.AUTO)
    private Long businessId;
    /**
     * 流程id
     */
    @TableField("wf_id")
    private String wfId;
    /**
     * 工单标题
     */
    @TableField("wf_title")
    private String wfTitle;
    /**
     * 工单类型
     */
    @TableField("wf_type")
    private Integer wfType;
    /**
     * 工单考核id
     */
    @TableField("wo_sla_id")
    private Integer woSlaId;
    @TableField("user_id")
    private Long userId;
    private String telephone;
    @TableField(value = "gmt_create",fill = FieldFill.INSERT)
    private Date gmtCreate;
    /**
     * 告警等级
     */
    @TableField("res_abnormaldesc")
    private String resAbnormaldesc;
    private String url;
    @TableField("res_abnormal_id")
    private Integer resAbnormalId;
    @TableField("abnormal_type_id")
    private Integer abnormalTypeId;
    @TableField("res_abnormallevel_id")
    private Integer resAbnormallevelId;
    @TableField("res_id")
    private Integer resId;
    @TableField("res_stype_id")
    private Integer resStypeId;
    @TableField("res_mtype_id")
    private Integer resMtypeId;
    /**
     * 流程状态
     */
    @TableField("wf_status")
    private Boolean wfStatus;
    @TableField(value = "useflag",fill = FieldFill.INSERT)
    private Integer useflag;
    /**
     * 当前节点
     */
    @TableField("current_step")
    private Integer currentStep;
    /**
     * 处理人
     */
    @TableField("current_user")
    private Long currentUser;
    /**
     * 评价最终得分
     */
    @TableField("wo_eval_score")
    private Integer woEvalScore;


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

    public Integer getWoSlaId() {
        return woSlaId;
    }

    public void setWoSlaId(Integer woSlaId) {
        this.woSlaId = woSlaId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
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

    public Integer getResAbnormalId() {
        return resAbnormalId;
    }

    public void setResAbnormalId(Integer resAbnormalId) {
        this.resAbnormalId = resAbnormalId;
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

    public Integer getResId() {
        return resId;
    }

    public void setResId(Integer resId) {
        this.resId = resId;
    }

    public Integer getResStypeId() {
        return resStypeId;
    }

    public void setResStypeId(Integer resStypeId) {
        this.resStypeId = resStypeId;
    }

    public Integer getResMtypeId() {
        return resMtypeId;
    }

    public void setResMtypeId(Integer resMtypeId) {
        this.resMtypeId = resMtypeId;
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

    public Long getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(Long currentUser) {
        this.currentUser = currentUser;
    }

    public Integer getWoEvalScore() {
        return woEvalScore;
    }

    public void setWoEvalScore(Integer woEvalScore) {
        this.woEvalScore = woEvalScore;
    }

    @Override
    protected Serializable pkVal() {
        return this.businessId;
    }

    @Override
    public String toString() {
        return "WfBusiness{" +
                "businessId=" + businessId +
                ", wfId='" + wfId + '\'' +
                ", wfTitle='" + wfTitle + '\'' +
                ", wfType=" + wfType +
                ", woSlaId=" + woSlaId +
                ", userId=" + userId +
                ", telephone='" + telephone + '\'' +
                ", gmtCreate=" + gmtCreate +
                ", resAbnormaldesc='" + resAbnormaldesc + '\'' +
                ", url='" + url + '\'' +
                ", resAbnormalId=" + resAbnormalId +
                ", abnormalTypeId=" + abnormalTypeId +
                ", resAbnormallevelId=" + resAbnormallevelId +
                ", resId=" + resId +
                ", resStypeId=" + resStypeId +
                ", resMtypeId=" + resMtypeId +
                ", wfStatus=" + wfStatus +
                ", useflag=" + useflag +
                ", currentStep=" + currentStep +
                ", currentUser=" + currentUser +
                ", woEvalScore='" + woEvalScore + '\'' +
                '}';
    }
}
