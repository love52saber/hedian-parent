package com.hedian.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.hedian.base.BusinessException;
import com.hedian.base.Constant;
import com.hedian.base.PublicResult;
import com.hedian.base.PublicResultConstant;
import com.hedian.entity.*;
import com.hedian.mapper.SysRoleMapper;
import com.hedian.mapper.SysUserMapper;
import com.hedian.service.*;
import com.hedian.util.ComUtil;
import com.hedian.util.JWTUtil;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.text.SimpleDateFormat;
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

    @Override
    public Map<String, Object> login(SysUser user) throws Exception {
        String userName = user.getUsername();
        String password = user.getPassword();
        user = this.getUserByUserName(userName);
        if (ComUtil.isEmpty(user)) {
            throw new BusinessException("该用户名不存在");
        }
        if (user.getStatus().equals(0)) {
            throw new BusinessException("该用户已被禁用请联系管理员");
        }
        //locktype和lockflag不为空
        if (!ComUtil.isEmpty(user.getLocktype()) && !ComUtil.isEmpty(user.getLockflag())) {
            if (user.getLockflag().equals(1) && user.getLocktype().equals(1)) {
                if (user.getUnlocktime().getTime() > System.currentTimeMillis()) {
                    throw new BusinessException("用户已锁定请联系管理员");
                }
            }
            if (user.getLockflag().equals(1) && user.getLocktype().equals(2)) {
                throw new BusinessException("用户已锁定请联系管理员");
            }
        }

        if (!BCrypt.checkpw(password, user.getPassword())) {
            //管理员不锁定
            if (userName.equals(Constant.RoleType.ADMIN)) {
                throw new BusinessException("密码错误");
            }
            if (!ComUtil.isEmpty(user.getLastwrongTime()) && user.getLastwrongTime().getTime() < System.currentTimeMillis() + (30 * 60 * 1000)) {
                user.setWrongTimes((ComUtil.isEmpty(user.getWrongTimes())) ? 1 : user.getWrongTimes() + 1);
            } else {
                user.setWrongTimes(1);
            }
            user.setLastwrongTime(new Date());
            if (user.getWrongTimes().equals(5)) {
                user.setLockreason(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()) + "密码输入错误次数超过5次");
                user.setLocktype(1);
                user.setLockflag(1);
                user.setUnlocktime(new Date(System.currentTimeMillis() + (30 * 60 * 1000)));
                user.setLastwrongTime(null);
                user.setWrongTimes(null);
            }
            this.updateAllColumnById(user);
            String msg = !ComUtil.isEmpty(user.getWrongTimes()) ? "用户名或密码错误，剩余" + (5 - user.getWrongTimes()) + "次后该用户将会被锁定30分钟" : "用户已锁定请联系管理员";
            throw new BusinessException(msg);
        }
        Map<String, Object> result = this.getLoginUserAndMenuInfo(user);
        //用户被锁定 登录完清空消息
        if (!ComUtil.isEmpty(user.getLockflag()) && user.getLockflag() != 0) {
            user.setUnlocktime(null);
            user.setLocktype(null);
            user.setLockflag(0);
            user.setLockreason(null);
            user.setLastwrongTime(null);
            this.updateAllColumnById(user);
        }
        return result;
    }
}
