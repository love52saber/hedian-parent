package com.hedian.model;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author hedian
 * @since 2018-06-30
 */
public class SysRoleModel{

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
     * 显示顺序
     */
    private Integer showorder;

    private List<Long> menuIds;

    public SysRoleModel() {
    }

    public SysRoleModel(Long roleId, String roleName, String roleSign, String remark, Integer showorder, List<Long> menuIds) {
        this.roleId = roleId;
        this.roleName = roleName;
        this.roleSign = roleSign;
        this.remark = remark;
        this.showorder = showorder;
        this.menuIds = menuIds;
    }

    @Override
    public String toString() {
        return "SysRoleModel{" +
                "roleId=" + roleId +
                ", roleName='" + roleName + '\'' +
                ", roleSign='" + roleSign + '\'' +
                ", remark='" + remark + '\'' +
                ", showorder=" + showorder +
                ", menuIds=" + menuIds +
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


    public Integer getShoworder() {
        return showorder;
    }

    public void setShoworder(Integer showorder) {
        this.showorder = showorder;
    }

    public List<Long> getMenuIds() {
        return menuIds;
    }

    public void setMenuIds(List<Long> menuIds) {
        this.menuIds = menuIds;
    }
}
