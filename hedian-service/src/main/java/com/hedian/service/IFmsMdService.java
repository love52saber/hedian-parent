package com.hedian.service;

import com.hedian.entity.FmsMd;
import com.baomidou.mybatisplus.service.IService;

import java.util.List;

/**
 * <p>
 * 维护策略关联的管理域表 服务类
 * </p>
 *
 * @author hedian123
 * @since 2018-09-17
 */
public interface IFmsMdService extends IService<FmsMd> {

    boolean saveAll(Integer fmsId, List<Integer> mdIds);
}
