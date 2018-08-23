package com.hedian.service;

import com.hedian.entity.Role;
import com.baomidou.mybatisplus.service.IService;
import com.hedian.model.RoleModel;

import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author why
 * @since 2018-05-03
 */
public interface IRoleService extends IService<Role> {

    /**
     * 新增角色以及角色权限信息
     * @param roleModel 角色以及角色权限信息
     * @return 新增结果 true/false
     */
    boolean addRoleAndPermission(RoleModel roleModel)throws Exception;

    /**
     * 更新色以及角色权限信息
     * @param roleModel
     * @return
     * @throws Exception
     */
    boolean updateRoleInfo(RoleModel roleModel)throws Exception;

    /**
     * 通过角色编号获取菜单列表
     * @param roleCode
     * @return
     */
    Map<String,Object> getMenuByRoleCode(String roleCode);

}
