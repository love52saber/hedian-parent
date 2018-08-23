package com.hedian.service;

import com.baomidou.mybatisplus.service.IService;
import com.hedian.entity.SysGrpUser;

import java.util.List;

/**
 * <p>
 * 用户组包含用户表 服务类
 * </p>
 *
 * @author hedian123
 * @since 2018-08-20
 */
public interface ISysGrpUserService extends IService<SysGrpUser> {


    boolean saveAll(Integer grpId, List<Long> userIds);
}
