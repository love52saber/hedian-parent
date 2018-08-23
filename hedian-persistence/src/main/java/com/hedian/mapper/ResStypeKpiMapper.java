package com.hedian.mapper;

import com.hedian.entity.ResStypeKpi;
import com.baomidou.mybatisplus.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 * 资源子类型监控的指标 Mapper 接口
 * </p>
 *
 * @author hedian123
 * @since 2018-08-17
 */
public interface ResStypeKpiMapper extends BaseMapper<ResStypeKpi> {

    List<ResStypeKpi> selectByResStypeId(Integer resStypeId);
}
