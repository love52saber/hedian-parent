package com.hedian.entity;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableLogic;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.FieldFill;
import com.baomidou.mybatisplus.enums.IdType;

import java.io.Serializable;

/**
 * <p>
 * 维护策略关联的故障类型
 * </p>
 *
 * @author hedian123
 * @since 2018-09-17
 */
@TableName("tbl_fms_abnormal_type")
public class FmsAbnormalType extends Model<FmsAbnormalType> {

    private static final long serialVersionUID = 1L;
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    @TableField("fms_id")
    private Integer fmsId;
    @TableField("abnormal_type_id")
    private Integer abnormalTypeId;
    @TableLogic
    @TableField(value = "useflag", fill = FieldFill.INSERT)
    private Integer useflag;

    public FmsAbnormalType(Integer fmsId, Integer abnormalTypeId) {
        this.fmsId = fmsId;
        this.abnormalTypeId = abnormalTypeId;
    }

    public FmsAbnormalType() {
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

    public Integer getAbnormalTypeId() {
        return abnormalTypeId;
    }

    public void setAbnormalTypeId(Integer abnormalTypeId) {
        this.abnormalTypeId = abnormalTypeId;
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
        return "FmsAbnormalType{" +
                "id=" + id +
                ", fmsId=" + fmsId +
                ", abnormalTypeId=" + abnormalTypeId +
                ", useflag=" + useflag +
                "}";
    }
}
