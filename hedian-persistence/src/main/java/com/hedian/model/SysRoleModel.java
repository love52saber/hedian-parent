package com.hedian.model;

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
public class SysRoleModel {

    private Long roleId;
    /**
     * 角色名称
     */
    private String roleName;
    /**
     * 角色标识
     */
    private String roleSign;
    /**
     * 备注
     */
    private String remark;
    /**
     * 创建用户id
     */
    private Long userIdCreate;
    /**
     * 创建时间
     */
    private Date gmtCreate;
    /**
     * 创建时间
     */
    private Date gmtModified;
    /**
     * 显示顺序
     */
    private Integer showorder;
    /**
     * 删除标记：0 不能删除， 1可以删除， 默认 1
     */
    private Integer delflag;

    private Long userIdMod;
    /**
     * 使用标记： 1 使用， 0 不使用
     */
    private Integer useflag;

    private List<Long> menuCodes;

    public SysRoleModel() {
    }

    public SysRoleModel(Long roleId, String roleName, String roleSign, String remark, Long userIdCreate, Date gmtCreate, Date gmtModified, Integer showorder, Integer delflag, Long userIdMod, Integer useflag, List<Long> menuCodes) {
        this.roleId = roleId;
        this.roleName = roleName;
        this.roleSign = roleSign;
        this.remark = remark;
        this.userIdCreate = userIdCreate;
        this.gmtCreate = gmtCreate;
        this.gmtModified = gmtModified;
        this.showorder = showorder;
        this.delflag = delflag;
        this.userIdMod = userIdMod;
        this.useflag = useflag;
        this.menuCodes = menuCodes;
    }

    @Override
    public String toString() {
        return "SysRoleModel{" +
                "roleId=" + roleId +
                ", roleName='" + roleName + '\'' +
                ", roleSign='" + roleSign + '\'' +
                ", remark='" + remark + '\'' +
                ", userIdCreate=" + userIdCreate +
                ", gmtCreate=" + gmtCreate +
                ", gmtModified=" + gmtModified +
                ", showorder=" + showorder +
                ", delflag=" + delflag +
                ", userIdMod=" + userIdMod +
                ", useflag=" + useflag +
                ", menuCodes=" + menuCodes +
                '}';
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getRoleSign() {
        return roleSign;
    }

    public void setRoleSign(String roleSign) {
        this.roleSign = roleSign;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
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

    public Long getUserIdMod() {
        return userIdMod;
    }

    public void setUserIdMod(Long userIdMod) {
        this.userIdMod = userIdMod;
    }

    public Integer getUseflag() {
        return useflag;
    }

    public void setUseflag(Integer useflag) {
        this.useflag = useflag;
    }

    public List<Long> getMenuCodes() {
        return menuCodes;
    }

    public void setMenuCodes(List<Long> menuCodes) {
        this.menuCodes = menuCodes;
    }
}
