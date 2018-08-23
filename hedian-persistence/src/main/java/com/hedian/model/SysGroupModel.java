package com.hedian.model;

import com.baomidou.mybatisplus.annotations.TableField;

import java.util.Date;
import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author hedian
 * @since 2018-06-30
 */
public class SysGroupModel {

    private Integer grpId;

    /**
     * 用户组类型：1 普通用户组 2 工单处理组
     */
    private Integer grpType;
    /**
     * 用户组名称
     */
    private String grpName;
    /**
     * 用户组描述
     */
    private String grpDesc;

    private Long userIdCreate;

    private Date gmtCreate;

    private Date gmtModified;
    /**
     * 使用标记： 1 使用， 0 不使用
     */
    private Integer useflag;

    private Long userIdMod;
    /**
     * 删除标记：0 不能删除， 1可以删除， 默认 1
     */
    private Integer delflag;

    private List<Long> roleIds;

    private List<Long> userIds;

    public SysGroupModel() {
    }

    public SysGroupModel(Integer grpType, String grpName, String grpDesc, Long userIdCreate, Date gmtCreate, Date gmtModified, Integer useflag, Long userIdMod, Integer delflag, List<Long> roleIds, List<Long> userIds) {
        this.grpType = grpType;
        this.grpName = grpName;
        this.grpDesc = grpDesc;
        this.userIdCreate = userIdCreate;
        this.gmtCreate = gmtCreate;
        this.gmtModified = gmtModified;
        this.useflag = useflag;
        this.userIdMod = userIdMod;
        this.delflag = delflag;
        this.roleIds = roleIds;
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

    @Override
    public String toString() {
        return "SysGroupModel{" +
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
