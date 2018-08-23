package com.hedian.entity;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;

/**
 * <p>
 * 资源主类型
 * </p>
 *
 * @author hedian123
 * @since 2018-08-17
 */
@TableName("tbl_res_maintype")
public class ResMaintype extends Model<ResMaintype> {

    private static final long serialVersionUID = 1L;

    @TableId("res_mtype_id")
    private Integer resMtypeId;
    /**
     * 资源主类型名称
     */
    @TableField("res_mtype_name")
    private String resMtypeName;
    /**
     * 资源主类型描述
     */
    @TableField("res_mtype_desc")
    private String resMtypeDesc;
    private Integer showorder;
    private Integer delflag;
    private Integer useflag;


    public Integer getResMtypeId() {
        return resMtypeId;
    }

    public void setResMtypeId(Integer resMtypeId) {
        this.resMtypeId = resMtypeId;
    }

    public String getResMtypeName() {
        return resMtypeName;
    }

    public void setResMtypeName(String resMtypeName) {
        this.resMtypeName = resMtypeName;
    }

    public String getResMtypeDesc() {
        return resMtypeDesc;
    }

    public void setResMtypeDesc(String resMtypeDesc) {
        this.resMtypeDesc = resMtypeDesc;
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
        return this.resMtypeId;
    }

    @Override
    public String toString() {
        return "ResMaintype{" +
        "resMtypeId=" + resMtypeId +
        ", resMtypeName=" + resMtypeName +
        ", resMtypeDesc=" + resMtypeDesc +
        ", showorder=" + showorder +
        ", delflag=" + delflag +
        ", useflag=" + useflag +
        "}";
    }
}
