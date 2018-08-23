package com.hedian.service;

import com.hedian.entity.SysUserRole;
import com.baomidou.mybatisplus.service.IService;

import java.util.Map;

/**
 * <p>
 * 用户与角色对应关系 服务类
 * </p>
 *
 * @author hedian123
 * @since 2018-08-20
 */
public interface ISysUserRoleService extends IService<SysUserRole> {


    SysUserRole selectByUserId(Long userId);
}
