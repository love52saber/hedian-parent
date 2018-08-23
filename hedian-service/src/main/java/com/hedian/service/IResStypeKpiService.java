package com.hedian.service;

import com.hedian.entity.ResStypeKpi;
import com.baomidou.mybatisplus.service.IService;

import java.util.List;

/**
 * <p>
 * 资源子类型监控的指标 服务类
 * </p>
 *
 * @author hedian123
 * @since 2018-08-17
 */
public interface IResStypeKpiService extends IService<ResStypeKpi> {

    List<ResStypeKpi> selectByResStypeId(Integer resStypeId);
}
