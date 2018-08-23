package com.hedian.entity;

import java.io.Serializable;

import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;

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
    private Integer useflag;
    @TableField("user_id_create")
    private Long userIdCreate;
    @TableField("gmt_create")
    private Date gmtCreate;
    @TableField("user_id_mod")
    private Long userIdMod;
    @TableField("gmt_modified")
    private Date gmtModified;

    /**
     * stype下的指标
     */
    @TableField(exist = false)
    private MoKpi moKpi;


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
