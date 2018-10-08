package com.hedian.service;

import com.hedian.entity.FmsAbnormalType;
import com.baomidou.mybatisplus.service.IService;

import java.util.List;

/**
 * <p>
 * 维护策略关联的故障类型 服务类
 * </p>
 *
 * @author hedian123
 * @since 2018-09-17
 */
public interface IFmsAbnormalTypeService extends IService<FmsAbnormalType> {

    boolean saveAll(Integer fmsId, List<Integer> abnormalTypeIds);
}
