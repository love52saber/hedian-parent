package com.hedian.service;

import com.hedian.entity.SysGrpRole;
import com.baomidou.mybatisplus.service.IService;
import com.hedian.entity.SysRole;

import java.util.List;

/**
 * <p>
 * 用户组权限表 服务类
 * </p>
 *
 * @author hedian123
 * @since 2018-08-20
 */
public interface ISysGrpRoleService extends IService<SysGrpRole> {


    boolean saveAll(Integer grpId, List<Long> menuCodes);
}
