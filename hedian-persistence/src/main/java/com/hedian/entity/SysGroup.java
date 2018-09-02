package com.hedian.entity;

import java.io.Serializable;

import java.util.Date;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableLogic;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.FieldFill;
import com.baomidou.mybatisplus.enums.IdType;

import java.io.Serializable;
import java.util.List;

/**
 * <p>
 * 用户组
 * </p>
 *
 * @author hedian123
 * @since 2018-08-22
 */
@TableName("sys_group")
public class SysGroup extends Model<SysGroup> {

    private static final long serialVersionUID = 1L;

    /**
     * 用户在ID
     */
    @TableId(value = "grp_id", type = IdType.AUTO)
    private Integer grpId;
    /**
     * 用户组类型：1 普通用户组 2 工单处理组
     */
    @TableField("grp_type")
    private Integer grpType;
    /**
     * 用户组名称
     */
    @TableField("grp_name")
    private String grpName;
    /**
     * 用户组描述
     */
    @TableField("grp_desc")
    private String grpDesc;

    @TableField("dept_id")
    private String deptId;

    @TableField(value = "user_id_create",fill = FieldFill.INSERT)
    private Long userIdCreate;
    @TableField(value = "gmt_create", fill = FieldFill.INSERT)
    private Date gmtCreate;
    @TableField(value = "gmt_modified",fill = FieldFill.INSERT_UPDATE)
    private Date gmtModified;
    /**
     * 使用标记： 1 使用， 0 不使用
     */
    @TableLogic
    @TableField(fill = FieldFill.INSERT)
    private Integer useflag;
    @TableField(value = "user_id_mod", fill = FieldFill.UPDATE)
    private Long userIdMod;
    /**
     * 删除标记：0 不能删除， 1可以删除， 默认 1
     */
    @TableField(fill = FieldFill.INSERT)
    private Integer delflag;

    @TableField(exist = false)
    private List<Long> roleIds;

    @TableField(exist = false)
    private List<Long> userIds;

    @TableField(exist = false)
    private SysDept sysDept;

    public SysDept getSysDept() {
        return sysDept;
    }

    public void setSysDept(SysDept sysDept) {
        this.sysDept = sysDept;
    }

    public String getDeptId() {
        return deptId;
    }

    public void setDeptId(String deptId) {
        this.deptId = deptId;
    }

    public List<Long> getRoleIds() {
        return roleIds;
    }

    public void setRoleIds(List<Long> roleIds) {
        this.roleIds = roleIds;
    }

    public List<Long> getUserIds() {
        return userIds;
    }

    public void setUserIds(List<Long> userIds) {
        this.userIds = userIds;
    }

    public Integer getGrpId() {
        return grpId;
    }

    public void setGrpId(Integer grpId) {
        this.grpId = grpId;
    }

    public Integer getGrpType() {
        return grpType;
    }

    public void setGrpType(Integer grpType) {
        this.grpType = grpType;
    }

    public String getGrpName() {
        return grpName;
    }

    public void setGrpName(String grpName) {
        this.grpName = grpName;
    }

    public String getGrpDesc() {
        return grpDesc;
    }

    public void setGrpDesc(String grpDesc) {
        this.grpDesc = grpDesc;
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

    public Integer getUseflag() {
        return useflag;
    }

    public void setUseflag(Integer useflag) {
        this.useflag = useflag;
    }

    public Long getUserIdMod() {
        return userIdMod;
    }

    public void setUserIdMod(Long userIdMod) {
        this.userIdMod = userIdMod;
    }

    public Integer getDelflag() {
        return delflag;
    }

    public void setDelflag(Integer delflag) {
        this.delflag = delflag;
    }

    @Override
    protected Serializable pkVal() {
        return this.grpId;
    }

    @Override
    public String toString() {
        return "SysGroup{" +
                "grpId=" + grpId +
                ", grpType=" + grpType +
                ", grpName='" + grpName + '\'' +
                ", grpDesc='" + grpDesc + '\'' +
                ", userIdCreate=" + userIdCreate +
                ", gmtCreate=" + gmtCreate +
                ", gmtModified=" + gmtModified +
                ", useflag=" + useflag +
                ", userIdMod=" + userIdMod +
                ", delflag=" + delflag +
                ", roleIds=" + roleIds +
                ", userIds=" + userIds +
                '}';
    }
}
