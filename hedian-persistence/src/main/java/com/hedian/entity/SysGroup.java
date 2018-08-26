package com.hedian.entity;

import java.io.Serializable;

import java.util.Date;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
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
    @TableField("user_id_create")
    private Long userIdCreate;
    @TableField("gmt_create")
    private Date gmtCreate;
    @TableField("gmt_modified")
    private Date gmtModified;
    /**
     * 使用标记： 1 使用， 0 不使用
     */
    private Integer useflag;
    @TableField("user_id_mod")
    private Long userIdMod;
    /**
     * 删除标记：0 不能删除， 1可以删除， 默认 1
     */
    private Integer delflag;

    @TableField(exist = false)
    private List<Long> roleIds;

    @TableField(exist = false)
    private List<Long> userIds;

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
