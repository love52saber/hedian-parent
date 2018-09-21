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
 * 维护期与资源关系表
 * </p>
 *
 * @author hedian123
 * @since 2018-09-17
 */
@TableName("tbl_ms_res")
public class MsRes extends Model<MsRes> {

    private static final long serialVersionUID = 1L;
    @TableId(type= IdType.AUTO)
    private Integer id;
    @TableField("ms_id")
    private Integer msId;
    @TableField("res_id")
    private Integer resId;
    @TableLogic
    @TableField(value = "useflag", fill = FieldFill.INSERT)
    private Integer useflag;

    public MsRes(Integer msId, Integer resId) {
        this.msId = msId;
        this.resId = resId;
    }

    public MsRes() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getMsId() {
        return msId;
    }

    public void setMsId(Integer msId) {
        this.msId = msId;
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
        return "MsRes{" +
                "id=" + id +
                ", msId=" + msId +
                ", resId=" + resId +
                ", useflag=" + useflag +
                "}";
    }
}
