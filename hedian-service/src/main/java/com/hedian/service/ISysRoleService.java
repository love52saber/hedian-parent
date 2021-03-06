package com.hedian.service;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.plugins.Page;
import com.hedian.entity.SysRole;
import com.baomidou.mybatisplus.service.IService;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 角色 服务类
 * </p>
 *
 * @author hedian123
 * @since 2018-08-17
 */
public interface ISysRoleService extends IService<SysRole> {

    /**
     * 通过角色编号获取菜单列表
     * @param roleId
     * @return
     */
    Map<String,Object> getMenuByRoleCode(Long roleId);

    /**
     * 新增角色以及角色权限信息
     * @param requestJson 角色以及角色权限信息
     * @return 新增结果 true/false
     */
    boolean addRoleAndPermission(JSONObject requestJson)throws Exception;

    /**
     * 更新色以及角色权限信息
     * @param requestJson
     * @return
     * @throws Exception
     */
    boolean updateRoleInfo(JSONObject requestJson) throws Exception;

    /**
     * 根据groupId查询角色
     * @param grpId
     * @return
     */
    List<SysRole> getRolesByGrpId(Long grpId);


    Page<SysRole> selectPageByConditionRole(Page<SysRole> sysRolePage, String info);
}
