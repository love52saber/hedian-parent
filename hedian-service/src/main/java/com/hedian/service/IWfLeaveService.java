package com.hedian.service;

import com.hedian.base.BusinessException;
import com.hedian.entity.WfLeave;
import com.baomidou.mybatisplus.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author gjyang
 * @since 2018-12-15
 */
public interface IWfLeaveService extends IService<WfLeave> {

    boolean startLeave(WfLeave wfLeave) throws BusinessException;

    boolean capitalAudit(WfLeave wfLeave);
}
