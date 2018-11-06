package com.hedian.entity;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotations.TableLogic;
import com.baomidou.mybatisplus.enums.FieldFill;
import com.baomidou.mybatisplus.enums.FieldStrategy;
import com.baomidou.mybatisplus.enums.IdType;

import java.util.Date;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

/**
 * <p>
 * 部门管理
 * </p>
 *
 * @author hedian123
 * @since 2018-08-17
 */
@TableName("sys_dept")
public class SysDept extends Model<SysDept> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "dept_id", type = IdType.AUTO)
    private Long deptId;
    /**
     * 上级部门ID，一级部门为0
     */
    @TableField("parent_id")
    private Long parentId;
    /**
     * 类型：1默认，1 本单位，2 合作单位
     */
    @TableField("org_type")
    private Integer orgType;
    @TableField("org_code")
    private String orgCode;
    /**
     * 部门名称
     */
    private String name;
    @TableField("short_name")
    private String shortName;
    @TableField("org_desc")
    private String orgDesc;
    /**
     * 排序
     */
    @TableField("order_num")
    private Integer orderNum;
    /**
     * 是否可以删除标记：0 不能删除， 1可以删除， 默认 1
     */
    @TableField(value = "delflag", fill = FieldFill.INSERT)
    private Integer delflag;
    /**
     * 是否删除  0：已删除  1：正常
     */
    @TableField(value = "useflag", fill = FieldFill.INSERT)
    @TableLogic
    private Integer useflag;
    @TableField(value = "user_id_create", fill = FieldFill.INSERT)
    private Long userIdCreate;
    @TableField(value = "gmt_create", fill = FieldFill.INSERT)
    private Date gmtCreate;
    @TableField(value = "gmt_modified", fill = FieldFill.INSERT_UPDATE)
    private Date gmtModified;
    @TableField(value = "user_id_mod", fill = FieldFill.UPDATE)
    private Long userIdMod;

    @TableField(exist = false)
    private List<SysDept> children;

    @TableField(exist = false)
    private String parentName;

    public String getParentName() {
        return parentName;
    }

    public void setParentName(String parentName) {
        this.parentName = parentName;
    }

    public List<SysDept> getChildren() {
        return children;
    }

    public void setChildren(List<SysDept> children) {
        this.children = children;
    }

    public Long getDeptId() {
        return deptId;
    }

    public void setDeptId(Long deptId) {
        this.deptId = deptId;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public Integer getOrgType() {
        return orgType;
    }

    public void setOrgType(Integer orgType) {
        this.orgType = orgType;
    }

    public String getOrgCode() {
        return orgCode;
    }

    public void setOrgCode(String orgCode) {
        this.orgCode = orgCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    public String getOrgDesc() {
        return orgDesc;
    }

    public void setOrgDesc(String orgDesc) {
        this.orgDesc = orgDesc;
    }

    public Integer getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(Integer orderNum) {
        this.orderNum = orderNum;
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

    public Date getGmtModified() {
        return gmtModified;
    }

    public void setGmtModified(Date gmtModified) {
        this.gmtModified = gmtModified;
    }

    public Long getUserIdMod() {
        return userIdMod;
    }

    public void setUserIdMod(Long userIdMod) {
        this.userIdMod = userIdMod;
    }

    @Override
    protected Serializable pkVal() {
        return this.deptId;
    }

    @Override
    public String toString() {
        return "SysDept{" +
                "deptId=" + deptId +
                ", parentId=" + parentId +
                ", orgType=" + orgType +
                ", orgCode='" + orgCode + '\'' +
                ", name='" + name + '\'' +
                ", shortName='" + shortName + '\'' +
                ", orgDesc='" + orgDesc + '\'' +
                ", orderNum=" + orderNum +
                ", delflag=" + delflag +
                ", useflag=" + useflag +
                ", userIdCreate=" + userIdCreate +
                ", gmtCreate=" + gmtCreate +
                ", gmtModified=" + gmtModified +
                ", userIdMod=" + userIdMod +
                ", children=" + children +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SysDept sysDept = (SysDept) o;
        return Objects.equals(deptId, sysDept.deptId) &&
                Objects.equals(parentId, sysDept.parentId) &&
                Objects.equals(orgType, sysDept.orgType) &&
                Objects.equals(orgCode, sysDept.orgCode) &&
                Objects.equals(name, sysDept.name) &&
                Objects.equals(shortName, sysDept.shortName) &&
                Objects.equals(orgDesc, sysDept.orgDesc) &&
                Objects.equals(orderNum, sysDept.orderNum) &&
                Objects.equals(delflag, sysDept.delflag) &&
                Objects.equals(useflag, sysDept.useflag) &&
                Objects.equals(userIdCreate, sysDept.userIdCreate) &&
                Objects.equals(gmtCreate, sysDept.gmtCreate) &&
                Objects.equals(gmtModified, sysDept.gmtModified) &&
                Objects.equals(userIdMod, sysDept.userIdMod) &&
                Objects.equals(children, sysDept.children) &&
                Objects.equals(parentName, sysDept.parentName);
    }

    @Override
    public int hashCode() {

        return Objects.hash(deptId, parentId, orgType, orgCode, name, shortName, orgDesc, orderNum, delflag, useflag, userIdCreate, gmtCreate, gmtModified, userIdMod, children, parentName);
    }
}
