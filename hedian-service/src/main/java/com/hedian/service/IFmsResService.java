package com.hedian.service;

import com.hedian.entity.FmsRes;
import com.baomidou.mybatisplus.service.IService;

import java.util.List;

/**
 * <p>
 * 维护策略关联的资源表 服务类
 * </p>
 *
 * @author hedian123
 * @since 2018-09-17
 */
public interface IFmsResService extends IService<FmsRes> {

    boolean saveAll(Integer fmsId, List<Integer> resIds);
}
