package com.hedian.service;

import com.hedian.entity.FmsAbnormal;
import com.baomidou.mybatisplus.service.IService;

import java.util.List;

/**
 * <p>
 * 维护策略关联的故障表 服务类
 * </p>
 *
 * @author hedian123
 * @since 2018-09-17
 */
public interface IFmsAbnormalService extends IService<FmsAbnormal> {

    boolean saveAll(Integer fmsId, List<Integer> abnormalIds);
}
