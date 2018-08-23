package com.hedian.entity;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;

/**
 * <p>
 * 资源子类型
 * </p>
 *
 * @author hedian123
 * @since 2018-08-17
 */
@TableName("tbl_res_subtype")
public class ResSubtype extends Model<ResSubtype> {

    private static final long serialVersionUID = 1L;

    @TableId("res_stype_id")
    private Integer resStypeId;
    @TableField("res_mtype_id")
    private Integer resMtypeId;
    @TableField("parent_id")
    private Integer parentId;
    @TableField("res_stype_name")
    private String resStypeName;
    @TableField("res_stype_desc")
    private String resStypeDesc;
    private Integer showorder;
    private Integer delflag;
    private Integer useflag;


    public Integer getResStypeId() {
        return resStypeId;
    }

    public void setResStypeId(Integer resStypeId) {
        this.resStypeId = resStypeId;
    }

    public Integer getResMtypeId() {
        return resMtypeId;
    }

    public void setResMtypeId(Integer resMtypeId) {
        this.resMtypeId = resMtypeId;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public String getResStypeName() {
        return resStypeName;
    }

    public void setResStypeName(String resStypeName) {
        this.resStypeName = resStypeName;
    }

    public String getResStypeDesc() {
        return resStypeDesc;
    }

    public void setResStypeDesc(String resStypeDesc) {
        this.resStypeDesc = resStypeDesc;
    }

    public Integer getShoworder() {
        return showorder;
    }

    public void setShoworder(Integer showorder) {
        this.showorder = showorder;
    }

    public Integer getDelflag() {
        return delflag;
    }

    public void setDelflag(Integer delflag) {
        this.delflag = delflag;
    }

    public Integer getUseflag() {
        return useflag;
    }

    public void setUseflag(Integer useflag) {
        this.useflag = useflag;
    }

    @Override
    protected Serializable pkVal() {
        return this.resStypeId;
    }

    @Override
    public String toString() {
        return "ResSubtype{" +
        "resStypeId=" + resStypeId +
        ", resMtypeId=" + resMtypeId +
        ", parentId=" + parentId +
        ", resStypeName=" + resStypeName +
        ", resStypeDesc=" + resStypeDesc +
        ", showorder=" + showorder +
        ", delflag=" + delflag +
        ", useflag=" + useflag +
        "}";
    }
}
