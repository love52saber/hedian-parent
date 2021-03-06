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
 * 单位和管理域对应关系表
 * </p>
 *
 * @author hedian123
 * @since 2018-08-27
 */
@TableName("tbl_md_dept")
public class MdDept extends Model<MdDept> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    @TableField("dept_id")
    private Long deptId;
    @TableField("md_id")
    private Integer mdId;
    @TableLogic
    @TableField(fill = FieldFill.INSERT)
    private Integer useflag;

    public MdDept(Integer mdId,Long deptId) {
        this.deptId = deptId;
        this.mdId = mdId;
    }

    public MdDept() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Long getDeptId() {
        return deptId;
    }

    public void setDeptId(Long deptId) {
        this.deptId = deptId;
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
        return "MdDept{" +
        "id=" + id +
        ", deptId=" + deptId +
        ", mdId=" + mdId +
        ", useflag=" + useflag +
        "}";
    }
}
