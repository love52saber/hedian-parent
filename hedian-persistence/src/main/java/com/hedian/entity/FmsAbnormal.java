package com.hedian.entity;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.FieldFill;

import java.io.Serializable;

/**
 * <p>
 * 维护策略关联的故障表
 * </p>
 *
 * @author hedian123
 * @since 2018-09-17
 */
@TableName("tbl_fms_abnormal")
public class FmsAbnormal extends Model<FmsAbnormal> {

    private static final long serialVersionUID = 1L;

    private Integer id;
    @TableField("fms_id")
    private Integer fmsId;
    @TableField("mo_abnormal_id")
    private Integer moAbnormalId;
    @TableField(value = "useflag", fill = FieldFill.INSERT)
    private Integer useflag;

    public FmsAbnormal() {
    }

    public FmsAbnormal(Integer fmsId, Integer moAbnormalId) {
        this.fmsId = fmsId;
        this.moAbnormalId = moAbnormalId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getFmsId() {
        return fmsId;
    }

    public void setFmsId(Integer fmsId) {
        this.fmsId = fmsId;
    }

    public Integer getMoAbnormalId() {
        return moAbnormalId;
    }

    public void setMoAbnormalId(Integer moAbnormalId) {
        this.moAbnormalId = moAbnormalId;
    }

    public Integer getUseflag() {
        return useflag;
    }

    public void setUseflag(Integer useflag) {
        this.useflag = useflag;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "FmsAbnormal{" +
                "id=" + id +
                ", fmsId=" + fmsId +
                ", moAbnormalId=" + moAbnormalId +
                ", useflag=" + useflag +
                "}";
    }
}
