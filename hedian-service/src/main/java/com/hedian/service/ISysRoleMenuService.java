package com.hedian.service;

import com.hedian.entity.SysRoleMenu;
import com.baomidou.mybatisplus.service.IService;

import java.util.List;

/**
 * <p>
 * 角色与菜单对应关系 服务类
 * </p>
 *
 * @author hedian123
 * @since 2018-08-20
 */
public interface ISysRoleMenuService extends IService<SysRoleMenu> {
    /**
     * 根据角色、权限集合录入数据
     * @param roleCode 角色ID
     * @param menuCodes 权限集合
     * @return 结果 true/false
     */
    boolean saveAll(Long roleCode, List<Long> menuCodes);
}
