package com.hedian.entity;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableLogic;
import com.baomidou.mybatisplus.annotations.TableName;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 资源监控异常信息
 * </p>
 *
 * @author hedian123
 * @since 2018-09-17
 */
@TableName("tbl_res_mo_abnormal_info")
public class ResMoAbnormalInfo extends Model<ResMoAbnormalInfo> {

    private static final long serialVersionUID = 1L;

    @TableId("res_abnormal_id")
    private Long resAbnormalId;
    @TableField("res_id")
    private Integer resId;
    @TableField("mo_th_id")
    private Integer moThId;
    @TableField("mo_kpi_id")
    private Integer moKpiId;
    @TableField("mo_abnormal_id")
    private Integer moAbnormalId;
    @TableField("res_abnormal_code")
    private Integer resAbnormalCode;
    @TableField("res_abnormal_name")
    private String resAbnormalName;
    @TableField("res_abnormallevel_id")
    private Integer resAbnormallevelId;
    @TableField("abnormal_type_id")
    private Integer abnormalTypeId;
    /**
     * 根据mo_abnormal_showtemplate转换得到的异常描述信息
     */
    @TableField("res_abnormaldesc")
    private String resAbnormaldesc;
    @TableField("res_abnomaltime")
    private Date resAbnomaltime;
    @TableField("res_recoverytime")
    private Date resRecoverytime;
    @TableField("res_abnormalvalue")
    private String resAbnormalvalue;
    @TableField("res_revoveryvalue")
    private String resRevoveryvalue;
    /**
     * 确认状态：1 未确认 2 已确认
     */
    @TableField("confirm_status")
    private Integer confirmStatus;
    /**
     * 异常状态：1 未清除  0 已清除 2 维护期清除
     */
    @TableField("res_abnormalstatus")
    private Integer resAbnormalstatus;
    /**
     * 确认类型：0 未确认 1 自动确认 2 手工确认
     */
    @TableField("confirm_type")
    private Integer confirmType;
    @TableField("confirm_user_id")
    private Long confirmUserId;
    @TableField("confirm_time")
    private Date confirmTime;
    @TableField("confirm_info")
    private String confirmInfo;
    /**
     * 清除类型：0 未清除 1 自动清除 2 手工清除
     */
    @TableField("clean_type")
    private Integer cleanType;
    @TableField("clean_user_id")
    private Long cleanUserId;
    @TableField("clean_time")
    private Date cleanTime;
    @TableField("clean_info")
    private String cleanInfo;
    /**
     * 派单类型：0 未派单 1 自动派单 2 手工派单
     */
    @TableField("dispatch_type")
    private Integer dispatchType;
    @TableField("dispatch_user_id")
    private Long dispatchUserId;
    @TableField("dispatch_time")
    private Date dispatchTime;
    /**
     * 派发的工单ID
     */
    @TableField("dispatch_ticket_id")
    private Long dispatchTicketId;
    /**
     * 自动派单匹配的策略ID
     */
    @TableField("fms_id")
    private Integer fmsId;
    /**
     * 使用标记： 1 正常 0删除
     */
    @TableLogic
    private Integer useflag;
    @TableField("del_user_id")
    private Long delUserId;
    private Date deltime;


    public Long getResAbnormalId() {
        return resAbnormalId;
    }

    public void setResAbnormalId(Long resAbnormalId) {
        this.resAbnormalId = resAbnormalId;
    }

    public Integer getResId() {
        return resId;
    }

    public void setResId(Integer resId) {
        this.resId = resId;
    }

    public Integer getMoThId() {
        return moThId;
    }

    public void setMoThId(Integer moThId) {
        this.moThId = moThId;
    }

    public Integer getMoKpiId() {
        return moKpiId;
    }

    public void setMoKpiId(Integer moKpiId) {
        this.moKpiId = moKpiId;
    }

    public Integer getMoAbnormalId() {
        return moAbnormalId;
    }

    public void setMoAbnormalId(Integer moAbnormalId) {
        this.moAbnormalId = moAbnormalId;
    }

    public Integer getResAbnormalCode() {
        return resAbnormalCode;
    }

    public void setResAbnormalCode(Integer resAbnormalCode) {
        this.resAbnormalCode = resAbnormalCode;
    }

    public String getResAbnormalName() {
        return resAbnormalName;
    }

    public void setResAbnormalName(String resAbnormalName) {
        this.resAbnormalName = resAbnormalName;
    }

    public Integer getResAbnormallevelId() {
        return resAbnormallevelId;
    }

    public void setResAbnormallevelId(Integer resAbnormallevelId) {
        this.resAbnormallevelId = resAbnormallevelId;
    }

    public Integer getAbnormalTypeId() {
        return abnormalTypeId;
    }

    public void setAbnormalTypeId(Integer abnormalTypeId) {
        this.abnormalTypeId = abnormalTypeId;
    }

    public String getResAbnormaldesc() {
        return resAbnormaldesc;
    }

    public void setResAbnormaldesc(String resAbnormaldesc) {
        this.resAbnormaldesc = resAbnormaldesc;
    }

    public Date getResAbnomaltime() {
        return resAbnomaltime;
    }

    public void setResAbnomaltime(Date resAbnomaltime) {
        this.resAbnomaltime = resAbnomaltime;
    }

    public Date getResRecoverytime() {
        return resRecoverytime;
    }

    public void setResRecoverytime(Date resRecoverytime) {
        this.resRecoverytime = resRecoverytime;
    }

    public String getResAbnormalvalue() {
        return resAbnormalvalue;
    }

    public void setResAbnormalvalue(String resAbnormalvalue) {
        this.resAbnormalvalue = resAbnormalvalue;
    }

    public String getResRevoveryvalue() {
        return resRevoveryvalue;
    }

    public void setResRevoveryvalue(String resRevoveryvalue) {
        this.resRevoveryvalue = resRevoveryvalue;
    }

    public Integer getConfirmStatus() {
        return confirmStatus;
    }

    public void setConfirmStatus(Integer confirmStatus) {
        this.confirmStatus = confirmStatus;
    }

    public Integer getResAbnormalstatus() {
        return resAbnormalstatus;
    }

    public void setResAbnormalstatus(Integer resAbnormalstatus) {
        this.resAbnormalstatus = resAbnormalstatus;
    }

    public Integer getConfirmType() {
        return confirmType;
    }

    public void setConfirmType(Integer confirmType) {
        this.confirmType = confirmType;
    }

    public Long getConfirmUserId() {
        return confirmUserId;
    }

    public void setConfirmUserId(Long confirmUserId) {
        this.confirmUserId = confirmUserId;
    }

    public Date getConfirmTime() {
        return confirmTime;
    }

    public void setConfirmTime(Date confirmTime) {
        this.confirmTime = confirmTime;
    }

    public String getConfirmInfo() {
        return confirmInfo;
    }

    public void setConfirmInfo(String confirmInfo) {
        this.confirmInfo = confirmInfo;
    }

    public Integer getCleanType() {
        return cleanType;
    }

    public void setCleanType(Integer cleanType) {
        this.cleanType = cleanType;
    }

    public Long getCleanUserId() {
        return cleanUserId;
    }

    public void setCleanUserId(Long cleanUserId) {
        this.cleanUserId = cleanUserId;
    }

    public Date getCleanTime() {
        return cleanTime;
    }

    public void setCleanTime(Date cleanTime) {
        this.cleanTime = cleanTime;
    }

    public String getCleanInfo() {
        return cleanInfo;
    }

    public void setCleanInfo(String cleanInfo) {
        this.cleanInfo = cleanInfo;
    }

    public Integer getDispatchType() {
        return dispatchType;
    }

    public void setDispatchType(Integer dispatchType) {
        this.dispatchType = dispatchType;
    }

    public Long getDispatchUserId() {
        return dispatchUserId;
    }

    public void setDispatchUserId(Long dispatchUserId) {
        this.dispatchUserId = dispatchUserId;
    }

    public Date getDispatchTime() {
        return dispatchTime;
    }

    public void setDispatchTime(Date dispatchTime) {
        this.dispatchTime = dispatchTime;
    }

    public Long getDispatchTicketId() {
        return dispatchTicketId;
    }

    public void setDispatchTicketId(Long dispatchTicketId) {
        this.dispatchTicketId = dispatchTicketId;
    }

    public Integer getFmsId() {
        return fmsId;
    }

    public void setFmsId(Integer fmsId) {
        this.fmsId = fmsId;
    }

    public Integer getUseflag() {
        return useflag;
    }

    public void setUseflag(Integer useflag) {
        this.useflag = useflag;
    }

    public Long getDelUserId() {
        return delUserId;
    }

    public void setDelUserId(Long delUserId) {
        this.delUserId = delUserId;
    }

    public Date getDeltime() {
        return deltime;
    }

    public void setDeltime(Date deltime) {
        this.deltime = deltime;
    }

    @Override
    protected Serializable pkVal() {
        return this.resAbnormalId;
    }

    @Override
    public String toString() {
        return "ResMoAbnormalInfo{" +
        "resAbnormalId=" + resAbnormalId +
        ", resId=" + resId +
        ", moThId=" + moThId +
        ", moKpiId=" + moKpiId +
        ", moAbnormalId=" + moAbnormalId +
        ", resAbnormalCode=" + resAbnormalCode +
        ", resAbnormalName=" + resAbnormalName +
        ", resAbnormallevelId=" + resAbnormallevelId +
        ", abnormalTypeId=" + abnormalTypeId +
        ", resAbnormaldesc=" + resAbnormaldesc +
        ", resAbnomaltime=" + resAbnomaltime +
        ", resRecoverytime=" + resRecoverytime +
        ", resAbnormalvalue=" + resAbnormalvalue +
        ", resRevoveryvalue=" + resRevoveryvalue +
        ", confirmStatus=" + confirmStatus +
        ", resAbnormalstatus=" + resAbnormalstatus +
        ", confirmType=" + confirmType +
        ", confirmUserId=" + confirmUserId +
        ", confirmTime=" + confirmTime +
        ", confirmInfo=" + confirmInfo +
        ", cleanType=" + cleanType +
        ", cleanUserId=" + cleanUserId +
        ", cleanTime=" + cleanTime +
        ", cleanInfo=" + cleanInfo +
        ", dispatchType=" + dispatchType +
        ", dispatchUserId=" + dispatchUserId +
        ", dispatchTime=" + dispatchTime +
        ", dispatchTicketId=" + dispatchTicketId +
        ", fmsId=" + fmsId +
        ", useflag=" + useflag +
        ", delUserId=" + delUserId +
        ", deltime=" + deltime +
        "}";
    }
}
