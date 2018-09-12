package com.hedian.mapper;

import com.baomidou.mybatisplus.plugins.Page;
import com.hedian.entity.MoThreshold;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.hedian.model.MoThresholdModel;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 监控阈值配置 Mapper 接口
 * </p>
 *
 * @author hedian123
 * @since 2018-08-17
 */
public interface MoThresholdMapper extends BaseMapper<MoThreshold> {

    List<MoThresholdModel> selectPageMoThreshold(Page<MoThresholdModel> page, @Param("moKpiName") String moKpiName, @Param("moAbnormalName") String moAbnormalName,
                                             @Param("resStypeName") String resStypeName, @Param("resMtypeName") String resMtypeName);

}
