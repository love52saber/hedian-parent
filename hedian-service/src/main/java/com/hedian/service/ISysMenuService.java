package com.hedian.service;

import com.hedian.entity.Menu;
import com.hedian.entity.SysMenu;
import com.baomidou.mybatisplus.service.IService;

import java.util.List;

/**
 * <p>
 * 菜单管理 服务类
 * </p>
 *
 * @author hedian123
 * @since 2018-08-20
 */
public interface ISysMenuService extends IService<SysMenu> {
    /**
     * 获取菜单树形结构
     * @param pId
     * @param list
     * @return
     */
    List<SysMenu> treeMenuList(Long pId, List<SysMenu> list);

    /**
     * 根据角色查询菜单
     * @param roleId 角色主键
     * @return
     */
    List<SysMenu> findMenuByRoleId(Long roleId);


    /**
     * 根据用户查所有权限
     * @param userId
     * @return
     */
    List<SysMenu> findMenuByUserId(Long userId);
}
