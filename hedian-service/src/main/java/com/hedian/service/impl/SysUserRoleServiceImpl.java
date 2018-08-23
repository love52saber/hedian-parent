package com.hedian.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.hedian.entity.SysUser;
import com.hedian.entity.SysUserRole;
import com.hedian.mapper.SysUserRoleMapper;
import com.hedian.service.ISysUserRoleService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.hedian.util.ComUtil;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 用户与角色对应关系 服务实现类
 * </p>
 *
 * @author hedian123
 * @since 2018-08-20
 */
@Service
public class SysUserRoleServiceImpl extends ServiceImpl<SysUserRoleMapper, SysUserRole> implements ISysUserRoleService {

    @Override
    public SysUserRole selectByUserId(Long userId) {
        EntityWrapper<SysUserRole> ew = new EntityWrapper<>();
        ew.where("user_id={0}", userId);
        List<SysUserRole> userToRoleList = this.selectList(ew);
        return ComUtil.isEmpty(userToRoleList)? null: userToRoleList.get(0);
    }
}
