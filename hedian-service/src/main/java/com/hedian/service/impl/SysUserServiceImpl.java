package com.hedian.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.hedian.base.BusinessException;
import com.hedian.entity.*;
import com.hedian.mapper.SysRoleMapper;
import com.hedian.mapper.SysUserMapper;
import com.hedian.service.*;
import com.hedian.util.ComUtil;
import com.hedian.util.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    @Autowired
    private ISysConfService sysConfService;

    @Override
    public boolean register(SysUser user, List<Long> roleIds, String url) throws BusinessException {
        if (!ComUtil.isEmpty(url)) {
            SysFile sysFile = new SysFile(0, url);
            boolean result = sysFileService.insert(sysFile);
            if(!result){
                throw new BusinessException("插入信息失败");
            }
            user.setPicId(sysFile.getId());
        }

        boolean result = this.insert(user);
        if (!result) {
            throw new BusinessException("插入信息失败");
        }
        if (!ComUtil.isEmpty(roleIds)) {
            List<SysUserRole> sysUserRoles = new ArrayList<>();
            roleIds.stream().forEach(roleId -> {
                sysUserRoles.add(new SysUserRole(user.getUserId(), roleId));
            });
            result = sysUserRoleService.insertBatch(sysUserRoles);
        }
        return result;
    }

    @Override
    public boolean updateInfo(SysUser userUpdate, List<Long> roleIds, String url) throws BusinessException {
        if (!ComUtil.isEmpty(url)) {
            SysFile sysFile = new SysFile(0, url);
            boolean result = sysFileService.insert(sysFile);
            if (!result) {
                throw new BusinessException("插入信息失败");
            }
            userUpdate.setPicId(sysFile.getId());
        }
        boolean result = this.updateById(userUpdate);
        if (!result) {
            throw new BusinessException("插入信息失败");
        }
        if (!ComUtil.isEmpty(roleIds)) {
            sysUserRoleService.delete(new EntityWrapper<SysUserRole>().eq("user_id", userUpdate.getUserId()));
            List<SysUserRole> sysUserRoles = new ArrayList<>();
            roleIds.stream().forEach(roleId -> {
                sysUserRoles.add(new SysUserRole(userUpdate.getUserId(), roleId));
            });
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
        SysFile sysFile = sysFileService.selectById(user.getPicId());
        user.setSysFile(sysFile);
        result.put("user", user);
        //根据用户主键查询启用的菜单权限
        List<SysMenu> menuList = sysMenuService.findMenuByUserId(user.getUserId());
        List<SysMenu> retMenuList = sysMenuService.treeMenuList(0L, menuList);
        result.put("menuList", retMenuList);
        return result;
    }

    @Override
    public List<SysUser> selectUserList(Long grpId) {
        List<SysUser> userList = sysUserMapper.selectUserList(grpId);
        return userList;
    }

    @Override
    public List<SysUser> getWfUsers(Integer stepType, Long deptId) throws BusinessException {

        List<SysUser> userList = null;
        switch (stepType){
            case 1:
                stepType = 1000;
                break;
            case 2:
                stepType = 2000;
                break;
            case 3:
                stepType = 3000;
                break;
            case 4:
                stepType = 4000;
                break;
            case 5:
                stepType = 5000;
                break;
            case 6:
                stepType = 6000;
                break;
            default:
                throw new BusinessException("stepType类型不正确!stepType＝" + stepType);
        }

        SysConf sysConf = sysConfService.selectOne(new EntityWrapper<SysConf>().where("c_type={0} and paraKey={1}", stepType, deptId));
        if(!ComUtil.isEmpty(sysConf)){
            userList = this.selectUserList(Long.valueOf(sysConf.getParavalue()));
        }
        return userList;
    }
}
