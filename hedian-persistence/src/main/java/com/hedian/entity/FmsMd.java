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
 * 维护策略关联的管理域表
 * </p>
 *
 * @author hedian123
 * @since 2018-09-17
 */
@TableName("tbl_fms_md")
public class FmsMd extends Model<FmsMd> {

    private static final long serialVersionUID = 1L;
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    @TableField("fms_id")
    private Integer fmsId;
    @TableField("md_id")
    private Integer mdId;
    @TableLogic
    @TableField(value = "useflag", fill = FieldFill.INSERT)
    private Integer useflag;

    public FmsMd() {
    }

    public FmsMd(Integer fmsId, Integer mdId) {
        this.fmsId = fmsId;
        this.mdId = mdId;
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

    public Integer getMdId() {
        return mdId;
    }

    public void setMdId(Integer mdId) {
        this.mdId = mdId;
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
        return "FmsMd{" +
                "id=" + id +
                ", fmsId=" + fmsId +
                ", mdId=" + mdId +
                ", useflag=" + useflag +
                "}";
    }
}
