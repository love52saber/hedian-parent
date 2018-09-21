package com.hedian.model;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.Date;

/**
 * <p>
 * 资源监控异常信息
 * </p>
 *
 * @author hedian123
 * @since 2018-09-17
 */
public class ResMoAbnormalInfoModel {

    private static final long serialVersionUID = 1L;

    private Long resAbnormalId;

    private Integer resId;

    private Integer moThId;

    private Integer moKpiId;

    private Integer moAbnormalId;

    private Integer resAbnormalCode;

    private String resAbnormalName;

    private Integer resAbnormallevelId;

    private Integer abnormalTypeId;
    /**
     * 根据mo_abnormal_showtemplate转换得到的异常描述信息
     */
    private String resAbnormaldesc;
    @JSONField(format="yyyy-MM-dd HH:mm:ss")
    private Date resAbnomaltime;
    @JSONField(format="yyyy-MM-dd HH:mm:ss")
    private Date resRecoverytime;
    private String resAbnormalvalue;
    private String resRevoveryvalue;
    /**
     * 确认状态：1 未确认 2 已确认
     */
    private Integer confirmStatus;
    /**
     * 异常状态：1 未清除  0 已清除 2 维护期清除
     */
    private Integer resAbnormalstatus;
    /**
     * 确认类型：0 未确认 1 自动确认 2 手工确认
     */
    private Integer confirmType;
    private Long confirmUserId;
    @JSONField(format="yyyy-MM-dd HH:mm:ss")
    private Date confirmTime;
    private String confirmInfo;
    /**
     * 清除类型：0 未清除 1 自动清除 2 手工清除
     */
    private Integer cleanType;
    private Long cleanUserId;
    @JSONField(format="yyyy-MM-dd HH:mm:ss")
    private Date cleanTime;
    private String cleanInfo;
    /**
     * 派单类型：0 未派单 1 自动派单 2 手工派单
     */
    private Integer dispatchType;
    private Long dispatchUserId;
    @JSONField(format="yyyy-MM-dd HH:mm:ss")
    private Date dispatchTime;
    /**
     * 派发的工单ID
     */
    private Long dispatchTicketId;
    /**
     * 自动派单匹配的策略ID
     */
    private Integer fmsId;
    /**
     * 使用标记： 1 正常 0删除
     */
    private Integer useflag;
    private Long delUserId;
    private Date deltime;

    private String abnormalTypeName;
    private String resAbnormallevelName;
    private String resName;
    private String resAlias;
    private String mokpiName;
    private String confirmUserName;
    private String dispatchUserName;
    private String cleanUserName;
    private String delUserName;

    public String getConfirmUserName() {
        return confirmUserName;
    }

    public void setConfirmUserName(String confirmUserName) {
        this.confirmUserName = confirmUserName;
    }

    public String getDispatchUserName() {
        return dispatchUserName;
    }

    public void setDispatchUserName(String dispatchUserName) {
        this.dispatchUserName = dispatchUserName;
    }

    public String getCleanUserName() {
        return cleanUserName;
    }

    public void setCleanUserName(String cleanUserName) {
        this.cleanUserName = cleanUserName;
    }

    public String getDelUserName() {
        return delUserName;
    }

    public void setDelUserName(String delUserName) {
        this.delUserName = delUserName;
    }

    public String getAbnormalTypeName() {
        return abnormalTypeName;
    }

    public void setAbnormalTypeName(String abnormalTypeName) {
        this.abnormalTypeName = abnormalTypeName;
    }

    public String getResAbnormallevelName() {
        return resAbnormallevelName;
    }

    public void setResAbnormallevelName(String resAbnormallevelName) {
        this.resAbnormallevelName = resAbnormallevelName;
    }

    public String getResName() {
        return resName;
    }

    public void setResName(String resName) {
        this.resName = resName;
    }

    public String getResAlias() {
        return resAlias;
    }

    public void setResAlias(String resAlias) {
        this.resAlias = resAlias;
    }

    public String getMokpiName() {
        return mokpiName;
    }

    public void setMokpiName(String mokpiName) {
        this.mokpiName = mokpiName;
    }

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


}
