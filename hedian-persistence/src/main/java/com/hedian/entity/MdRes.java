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
 * 维护域与资源关系
 * </p>
 *
 * @author hedian123
 * @since 2018-08-27
 */
@TableName("tbl_md_res")
public class MdRes extends Model<MdRes> {

    private static final long serialVersionUID = 1L;
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    @TableField("md_id")
    private Integer mdId;
    @TableField("res_id")
    private Integer resId;
    @TableLogic
    @TableField(fill = FieldFill.INSERT)
    private Integer useflag;


    public MdRes(Integer mdId, Integer resId) {
        this.mdId = mdId;
        this.resId = resId;
    }

    public MdRes() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getMdId() {
        return mdId;
    }

    public void setMdId(Integer mdId) {
        this.mdId = mdId;
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
        return "MdRes{" +
        "id=" + id +
        ", mdId=" + mdId +
        ", resId=" + resId +
        ", useflag=" + useflag +
        "}";
    }
}
