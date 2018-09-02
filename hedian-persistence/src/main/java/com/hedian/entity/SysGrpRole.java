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
 * 用户组权限表
 * </p>
 *
 * @author hedian123
 * @since 2018-08-20
 */
@TableName("sys_grp_role")
public class SysGrpRole extends Model<SysGrpRole> {

    private static final long serialVersionUID = 1L;
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    @TableField("grp_id")
    private Integer grpId;
    @TableField("role_id")
    private Long roleId;
    @TableLogic
    @TableField(fill = FieldFill.INSERT)
    private Integer useflag;

    public SysGrpRole() {
    }

    public SysGrpRole(Integer grpId, Long roleId) {
        this.grpId = grpId;
        this.roleId = roleId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getGrpId() {
        return grpId;
    }

    public void setGrpId(Integer grpId) {
        this.grpId = grpId;
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
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
        return "SysGrpRole{" +
        "id=" + id +
        ", grpId=" + grpId +
        ", roleId=" + roleId +
        ", useflag=" + useflag +
        "}";
    }
}
