package com.hedian.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.hedian.entity.*;
import com.hedian.mapper.SysRoleMapper;
import com.hedian.mapper.SysUserMapper;
import com.hedian.service.ISysFileService;
import com.hedian.service.ISysMenuService;
import com.hedian.service.ISysUserRoleService;
import com.hedian.service.ISysUserService;
import com.hedian.util.ComUtil;
import com.hedian.util.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author hedian123
 * @since 2018-08-17
 */
@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements ISysUserService {

    @Autowired
    private ISysUserRoleService sysUserRoleService;

    @Autowired
    private SysRoleMapper sysRoleMapper;

    @Autowired
    private SysUserMapper sysUserMapper;

    @Autowired
    private ISysMenuService sysMenuService;

    @Autowired
    private ISysFileService sysFileService;

    @Override
    public boolean register(SysUser user, JSONArray roleIds, String url) {
        user.setGmtCreate(new Date());
        boolean result = this.insert(user);
        if (result) {
            result = sysFileService.insert(new SysFile(0, url));
        }
        if (result) {
            if (!ComUtil.isEmpty(roleIds)) {
                List<SysUserRole> sysUserRoles = new ArrayList<>();
                for (Object roleId : roleIds) {
                    SysUserRole sysUserRole = new SysUserRole(user.getUserId(), (Long) roleId, 1);
                    sysUserRoles.add(sysUserRole);
                }
                result = sysUserRoleService.insertBatch(sysUserRoles);
            }
        }
        return result;
    }

    @Override
    public boolean updateInfo(SysUser userUpdate, JSONArray roleIds, String url) {
        userUpdate.setGmtCreate(new Date());
        boolean result = this.updateById(userUpdate);
        if (result) {
            result = sysFileService.updateById(new SysFile(userUpdate.getPicId(), url));
        }
        if (result) {
            sysUserRoleService.delete(new EntityWrapper<SysUserRole>().eq("user_id", userUpdate.getUserId()));
            List<SysUserRole> sysUserRoles = new ArrayList<>();
            for (Object roleId : roleIds) {
                SysUserRole sysUserRole = new SysUserRole(userUpdate.getUserId(), (Long) roleId, 1);
                sysUserRoles.add(sysUserRole);
            }
            result = sysUserRoleService.insertBatch(sysUserRoles);
        }
        return result;
    }

    @Override
    public Map<String, Object> getUserInfoAndRoles(Long userId) throws Exception {
        Map<String, Object> result = new HashMap<>();
        SysUser sysUser = this.selectById(userId);
        result.put("user", sysUser);
        List<SysRole> sysRoles = sysRoleMapper.findRoleByUserId(userId);
        result.put("roles", sysRoles);
        return result;
    }

    @Override
    public List<SysUser> getUsersByGrpId(Long grpId) {
        List<SysUser> sysUserList = sysUserMapper.getUsersByGrpId(grpId);
        return sysUserList;
    }

    @Override
    public SysUser getUserByUserName(String userName) {
        EntityWrapper<SysUser> ew = new EntityWrapper<>();
        ew.where("username={0}", userName);
        return this.selectOne(ew);
    }

    @Override
    public Map<String, Object> getLoginUserAndMenuInfo(SysUser user) {
        Map<String, Object> result = new HashMap<>();
        result.put("token", JWTUtil.sign(user.getUsername(), user.getPassword()));
        user.setPassword(null);
        result.put("user", user);
        //根据用户主键查询启用的菜单权限
        List<SysMenu> menuList = sysMenuService.findMenuByUserId(user.getUserId());
        List<SysMenu> retMenuList = sysMenuService.treeMenuList(0L, menuList);
        result.put("menuList", retMenuList);
        return result;
    }
}
