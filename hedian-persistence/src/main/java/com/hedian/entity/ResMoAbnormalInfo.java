package com.hedian.entity;

import java.io.Serializable;

import com.baomidou.mybatisplus.enums.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;

/**
 * <p>
 * 资源监控异常信息
 * </p>
 *
 * @author hedian123
 * @since 2018-08-17
 */
@TableName("tbl_res_mo_abnormal_info")
public class ResMoAbnormalInfo extends Model<ResMoAbnormalInfo> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "res_abnormal_id", type = IdType.AUTO)
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
     * 异常状态：1 异常 0 异常恢复
     */
    @TableField("res_abnormalstatus")
    private Integer resAbnormalstatus;


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

    public Integer getResAbnormalstatus() {
        return resAbnormalstatus;
    }

    public void setResAbnormalstatus(Integer resAbnormalstatus) {
        this.resAbnormalstatus = resAbnormalstatus;
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
        ", resAbnormaldesc=" + resAbnormaldesc +
        ", resAbnomaltime=" + resAbnomaltime +
        ", resRecoverytime=" + resRecoverytime +
        ", resAbnormalvalue=" + resAbnormalvalue +
        ", resRevoveryvalue=" + resRevoveryvalue +
        ", resAbnormalstatus=" + resAbnormalstatus +
        "}";
    }
}
