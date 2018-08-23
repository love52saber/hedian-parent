package com.hedian.entity;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;

/**
 * <p>
 * 系统参数配置表
 * </p>
 *
 * @author hedian123
 * @since 2018-08-17
 */
@TableName("tbl_sys_conf")
public class SysConf extends Model<SysConf> {

    private static final long serialVersionUID = 1L;

    private Integer id;
    /**
     * 配置项类型
     */
    @TableField("c_type")
    private Integer cType;
    /**
     * 配置项名称
     */
    private String parakey;
    /**
     * 配置值
     */
    private String paravalue;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getcType() {
        return cType;
    }

    public void setcType(Integer cType) {
        this.cType = cType;
    }

    public String getParakey() {
        return parakey;
    }

    public void setParakey(String parakey) {
        this.parakey = parakey;
    }

    public String getParavalue() {
        return paravalue;
    }

    public void setParavalue(String paravalue) {
        this.paravalue = paravalue;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "SysConf{" +
        "id=" + id +
        ", cType=" + cType +
        ", parakey=" + parakey +
        ", paravalue=" + paravalue +
        "}";
    }
}
