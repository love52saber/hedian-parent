package com.hedian.entity;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableLogic;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.FieldFill;
import com.baomidou.mybatisplus.enums.IdType;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 资源子类型监控的指标
 * </p>
 *
 * @author hedian123
 * @since 2018-08-17
 */
@TableName("tbl_res_stype_kpi")
public class ResStypeKpi extends Model<ResStypeKpi> {

    private static final long serialVersionUID = 1L;
    @TableId(type = IdType.AUTO)
    private Integer id;
    @TableField("res_stype_id")
    private Integer resStypeId;
    @TableField("mo_kpi_id")
    private Integer moKpiId;
    /**
     * 子类型标记：1 该规则应用于该类型下所有子类型， 2 该规则只应用于该类型本身
     */
    @TableField("stype_flag")
    private Integer stypeFlag;
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

    /**
     * stype下的指标
     */
    @TableField(exist = false)
    private MoKpi moKpi;

    public ResStypeKpi(Integer resStypeId, Integer moKpiId, Integer stypeFlag) {
        this.resStypeId = resStypeId;
        this.moKpiId = moKpiId;
        this.stypeFlag = stypeFlag;
    }

    public ResStypeKpi() {
    }

    public MoKpi getMoKpi() {
        return moKpi;
    }

    public void setMoKpi(MoKpi moKpi) {
        this.moKpi = moKpi;
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public Integer getStypeFlag() {
        return stypeFlag;
    }

    public void setStypeFlag(Integer stypeFlag) {
        this.stypeFlag = stypeFlag;
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
        return this.id;
    }

    @Override
    public String toString() {
        return "ResStypeKpi{" +
                "id=" + id +
                ", resStypeId=" + resStypeId +
                ", moKpiId=" + moKpiId +
                ", stypeFlag=" + stypeFlag +
                ", useflag=" + useflag +
                ", userIdCreate=" + userIdCreate +
                ", gmtCreate=" + gmtCreate +
                ", userIdMod=" + userIdMod +
                ", gmtModified=" + gmtModified +
                "}";
    }
}
