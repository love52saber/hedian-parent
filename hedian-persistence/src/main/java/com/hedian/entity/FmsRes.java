package com.hedian.entity;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableLogic;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.FieldFill;
import com.baomidou.mybatisplus.enums.IdType;

import java.io.Serializable;

/**
 * <p>
 * 维护策略关联的资源表
 * </p>
 *
 * @author hedian123
 * @since 2018-09-17
 */
@TableName("tbl_fms_res")
public class FmsRes extends Model<FmsRes> {

    private static final long serialVersionUID = 1L;
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    @TableField("fms_id")
    private Integer fmsId;
    @TableField("res_id")
    private Integer resId;
    @TableLogic
    @TableField(value = "useflag", fill = FieldFill.INSERT)
    private Integer useflag;

    public FmsRes() {
    }

    public FmsRes(Integer fmsId, Integer resId) {
        this.fmsId = fmsId;
        this.resId = resId;
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

    public Integer getResId() {
        return resId;
    }

    public void setResId(Integer resId) {
        this.resId = resId;
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
        return "FmsRes{" +
                "id=" + id +
                ", fmsId=" + fmsId +
                ", resId=" + resId +
                ", useflag=" + useflag +
                "}";
    }
}
