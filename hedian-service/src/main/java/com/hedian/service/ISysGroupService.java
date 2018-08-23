package com.hedian.service;

import com.baomidou.mybatisplus.service.IService;
import com.hedian.entity.SysGroup;
import com.hedian.model.SysGroupModel;

/**
 * <p>
 * 用户组 服务类
 * </p>
 *
 * @author hedian123
 * @since 2018-08-17
 */
public interface ISysGroupService extends IService<SysGroup> {

    boolean addRoleAndUsers(SysGroupModel groupModel) throws Exception;

    boolean updateGroupInfo(SysGroupModel groupModel) throws Exception;
}
