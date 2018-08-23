package com.hedian.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.hedian.base.BusinessException;
import com.hedian.entity.*;
import com.hedian.mapper.SysRoleMapper;
import com.hedian.model.RoleModel;
import com.hedian.model.SysRoleModel;
import com.hedian.service.*;
import com.hedian.util.ComUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 角色 服务实现类
 * </p>
 *
 * @author hedian123
 * @since 2018-08-17
 */
@Service
public class SysRoleServiceImpl extends ServiceImpl<SysRoleMapper, SysRole> implements ISysRoleService {

    @Autowired
    private ISysMenuService menuService;

    @Autowired
    private ISysRoleMenuService roleToMenuService;

    @Autowired
    private SysRoleMapper sysRoleMapper;

    @Override
    public boolean addRoleAndPermission(SysRoleModel roleModel) throws Exception {
        SysRole role = new SysRole();
        BeanUtils.copyProperties(roleModel, role);
        boolean result = this.insert(role);
        if (!result) {
            throw new BusinessException("插入角色信息失败");
        }
        result = roleToMenuService.saveAll(role.getRoleId(), roleModel.getMenuCodes());
        return result;
    }

    @Override
    public Map<String, Object> getMenuByRoleCode(Long roleId) {
        Map<String, Object> retMap = new HashMap<>();
        List<SysMenu> menuList = menuService.findMenuByRoleId(roleId);
        List<SysMenu> retMenuList = menuService.treeMenuList(0L, menuList);
        retMap.put("menuList", retMenuList);
        return retMap;
    }

    @Override
    public boolean updateRoleInfo(SysRoleModel roleModel) throws Exception {
        SysRole role = this.selectById(roleModel.getRoleId());
        if (ComUtil.isEmpty(role)) {
            return false;
        }
        BeanUtils.copyProperties(roleModel, role);
        boolean result = this.updateById(role);
        if (!result) {
            throw new BusinessException("更新角色信息失败");
        }
        result = roleToMenuService.delete(new EntityWrapper<SysRoleMenu>().eq("role_id", roleModel.getRoleId()));
        if (!result) {
            throw new BusinessException("删除权限信息失败");
        }
        result = roleToMenuService.saveAll(role.getRoleId(), roleModel.getMenuCodes());
        if (!result) {
            throw new BusinessException("更新权限信息失败");
        }
        return result;

    }

    @Override
    public List<SysRole> getRolesByGrpId(Long grpId) {
        List<SysRole> sysRoles = sysRoleMapper.getRolesByGrpId(grpId);
        return sysRoles;
    }

}
