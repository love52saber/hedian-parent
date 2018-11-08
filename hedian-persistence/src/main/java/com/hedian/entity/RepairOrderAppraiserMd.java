package com.hedian.entity;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotations.TableLogic;
import com.baomidou.mybatisplus.enums.FieldFill;
import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 维修工单评价人对应维护域
 * </p>
 *
 * @author hedian123
 * @since 2018-11-06
 */
@TableName("tbl_repair_order_appraiser_md")
public class RepairOrderAppraiserMd extends Model<RepairOrderAppraiserMd> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    private Integer appraiserid;
    @TableField("md_id")
    private Integer mdId;
    @TableLogic
    @TableField(fill = FieldFill.INSERT)
    private Integer useflag;
    private Integer delflag;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getAppraiserid() {
        return appraiserid;
    }

    public void setAppraiserid(Integer appraiserid) {
        this.appraiserid = appraiserid;
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

    public Integer getDelflag() {
        return delflag;
    }

    public void setDelflag(Integer delflag) {
        this.delflag = delflag;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "RepairOrderAppraiserMd{" +
        "id=" + id +
        ", appraiserid=" + appraiserid +
        ", mdId=" + mdId +
        ", useflag=" + useflag +
        ", delflag=" + delflag +
        "}";
    }
}
