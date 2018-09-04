package com.hedian.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.hedian.base.BusinessException;
import com.hedian.entity.SysGroup;
import com.hedian.entity.SysGrpRole;
import com.hedian.entity.SysGrpUser;
import com.hedian.mapper.SysGroupMapper;
import com.hedian.service.ISysGroupService;
import com.hedian.service.ISysGrpRoleService;
import com.hedian.service.ISysGrpUserService;
import com.hedian.util.ComUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户组 服务实现类
 * </p>
 *
 * @author hedian123
 * @since 2018-08-17
 */
@Service
public class SysGroupServiceImpl extends ServiceImpl<SysGroupMapper, SysGroup> implements ISysGroupService {


    @Autowired
    private ISysGrpRoleService sysGrpRoleService;
    @Autowired
    private ISysGrpUserService sysGrpUserService;

    @Override
    public boolean addRoleAndUsers(SysGroup sysGroup) throws Exception {
        boolean result = this.insert(sysGroup);
        if (!result) {
            throw new BusinessException("插入用户组信息失败");
        }
        //插入用户组权限信息
        result = sysGrpRoleService.saveAll(sysGroup.getGrpId(), sysGroup.getRoleIds());
        if (!result) {
            throw new BusinessException("插入用户组权限信息失败");
        }
        //插入用户组用户信息
        result = sysGrpUserService.saveAll(sysGroup.getGrpId(), sysGroup.getUserIds());
        if (!result) {
            throw new BusinessException("插入用户组用户信息失败");
        }
        return result;
    }

    @Override
    public boolean updateGroupInfo(SysGroup sysGroup) throws Exception {
        SysGroup group = this.selectById(sysGroup.getGrpId());
        if (ComUtil.isEmpty(group)) {
            return false;
        }
        boolean result = this.updateById(sysGroup);
        if (!result) {
            throw new BusinessException("更新用户组信息失败");
        }
        result = sysGrpRoleService.delete(new EntityWrapper<SysGrpRole>().eq("grp_id", sysGroup.getGrpId()));
        if (!result) {
            throw new BusinessException("删除权限信息失败");
        }
        result = sysGrpRoleService.saveAll(sysGroup.getGrpId(), sysGroup.getRoleIds());
        if (!result) {
            throw new BusinessException("更新权限信息失败");
        }
        result = sysGrpUserService.delete(new EntityWrapper<SysGrpUser>().eq("grp_id", sysGroup.getGrpId()));
        if (!result) {
            throw new BusinessException("删除用户组用户信息失败");
        }
        result = sysGrpUserService.saveAll(sysGroup.getGrpId(), sysGroup.getUserIds());
        if (!result) {
            throw new BusinessException("更新用户组用户信息失败");
        }
        return result;
    }
}
