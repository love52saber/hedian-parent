package com.hedian.service;

import com.hedian.base.BusinessException;
import com.hedian.entity.MaintainStrategy;
import com.baomidou.mybatisplus.service.IService;

/**
 * <p>
 * 维护期策略 服务类
 * </p>
 *
 * @author hedian123
 * @since 2018-09-17
 */
public interface IMaintainStrategyService extends IService<MaintainStrategy> {

    boolean addAllNodes(MaintainStrategy ms) throws Exception;

    boolean updateMs(MaintainStrategy ms) throws Exception;
}
