package com.hedian.entity;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableLogic;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.FieldFill;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * <p>
 * 监控阈值配置
 * </p>
 *
 * @author hedian123
 * @since 2018-08-17
 */
@TableName("tbl_mo_threshold")
public class MoThreshold extends Model<MoThreshold> {

    private static final long serialVersionUID = 1L;

    @TableId("mo_th_id")
    private Integer moThId;
    /**
     * 阈值类型： 1 上下限监控，2  百分比监控(区间)，3  基准值百分比上下限监控 ，4 基准值百分比监控（区间），对于上下限 如果有配置上限或者下限则按照大于上限小于下限为异常，若只配置了上限或者下限则按照有配置的比较, 5 判断获取到到的值是否等于mo_th_value
     */
    @TableField("mo_th_type")
    private Integer moThType;
    /**
     * 基准值
     */
    @TableField("mo_th_base")
    private BigDecimal moThBase;
    /**
     * 上限
     */
    @TableField("mo_th_up")
    private BigDecimal moThUp;
    /**
     * 下限
     */
    @TableField("mo_th_down")
    private BigDecimal moThDown;
    /**
     * 上下限包含标记 ：1 包含上下限，0 不包含上下限 默认0
     */
    @TableField("mo_th_inupdown")
    private Integer moThInupdown;
    /**
     * 做等于比较时和该值进行比较
     */
    @TableField("mo_th_value")
    private String moThValue;
    /**
     * 当同一个对象的同一监控指标 匹配出多个故障时 按照该优先级展示优先级高的故障，该数值越小优先级越高
     */
    @TableField("mo_th_priority")
    private Integer moThPriority;
    @TableField("res_stype_id")
    private Integer resStypeId;

    @TableField("mo_kpi_id")
    private Integer moKpiId;
    @TableField("mo_abnormal_id")
    private Integer moAbnormalId;
    private Integer showorder;
    @TableLogic
    @TableField(value = "useflag", fill = FieldFill.INSERT)
    private Integer useflag;
    @TableField(value = "user_id_create", fill = FieldFill.INSERT)
    private Long userIdCreate;
    @TableField(value = "gmt_create", fill = FieldFill.INSERT)
    private Date gmtCreate;
    @TableField(value = "user_id_mod", fill = FieldFill.INSERT_UPDATE)
    private Long userIdMod;
    @TableField(value = "gmt_modified", fill = FieldFill.INSERT_UPDATE)
    private Date gmtModified;


    public Integer getMoThId() {
        return moThId;
    }

    public void setMoThId(Integer moThId) {
        this.moThId = moThId;
    }

    public Integer getMoThType() {
        return moThType;
    }

    public void setMoThType(Integer moThType) {
        this.moThType = moThType;
    }

    public BigDecimal getMoThBase() {
        return moThBase;
    }

    public void setMoThBase(BigDecimal moThBase) {
        this.moThBase = moThBase;
    }

    public BigDecimal getMoThUp() {
        return moThUp;
    }

    public void setMoThUp(BigDecimal moThUp) {
        this.moThUp = moThUp;
    }

    public BigDecimal getMoThDown() {
        return moThDown;
    }

    public void setMoThDown(BigDecimal moThDown) {
        this.moThDown = moThDown;
    }

    public Integer getMoThInupdown() {
        return moThInupdown;
    }

    public void setMoThInupdown(Integer moThInupdown) {
        this.moThInupdown = moThInupdown;
    }

    public String getMoThValue() {
        return moThValue;
    }

    public void setMoThValue(String moThValue) {
        this.moThValue = moThValue;
    }

    public Integer getMoThPriority() {
        return moThPriority;
    }

    public void setMoThPriority(Integer moThPriority) {
        this.moThPriority = moThPriority;
    }

    public Integer getResStypeId() {
        return resStypeId;
    }

    public void setResStypeId(Integer resStypeId) {
        this.resStypeId = resStypeId;
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

    public Integer getShoworder() {
        return showorder;
    }

    public void setShoworder(Integer showorder) {
        this.showorder = showorder;
    }

    public Integer getUseflag() {
        return useflag;
    }

    public void setUseflag(Integer useflag) {
        this.useflag = useflag;
    }

    public Long getUserIdCreate() {
        return userIdCreate;
    }

    public void setUserIdCreate(Long userIdCreate) {
        this.userIdCreate = userIdCreate;
    }

    public Date getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    public Long getUserIdMod() {
        return userIdMod;
    }

    public void setUserIdMod(Long userIdMod) {
        this.userIdMod = userIdMod;
    }

    public Date getGmtModified() {
        return gmtModified;
    }

    public void setGmtModified(Date gmtModified) {
        this.gmtModified = gmtModified;
    }

    @Override
    protected Serializable pkVal() {
        return this.moThId;
    }

    @Override
    public String toString() {
        return "MoThreshold{" +
                "moThId=" + moThId +
                ", moThType=" + moThType +
                ", moThBase=" + moThBase +
                ", moThUp=" + moThUp +
                ", moThDown=" + moThDown +
                ", moThInupdown=" + moThInupdown +
                ", moThValue=" + moThValue +
                ", moThPriority=" + moThPriority +
                ", resStypeId=" + resStypeId +
                ", moKpiId=" + moKpiId +
                ", moAbnormalId=" + moAbnormalId +
                ", showorder=" + showorder +
                ", useflag=" + useflag +
                ", userIdCreate=" + userIdCreate +
                ", gmtCreate=" + gmtCreate +
                ", userIdMod=" + userIdMod +
                ", gmtModified=" + gmtModified +
                "}";
    }
}
