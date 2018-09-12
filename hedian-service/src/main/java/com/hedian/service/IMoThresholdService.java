package com.hedian.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.hedian.entity.MoThreshold;
import com.baomidou.mybatisplus.service.IService;
import com.hedian.model.MoThresholdModel;

/**
 * <p>
 * 监控阈值配置 服务类
 * </p>
 *
 * @author hedian123
 * @since 2018-08-17
 */
public interface IMoThresholdService extends IService<MoThreshold> {

    Page<MoThresholdModel> selectPageMoThreshold(Page<MoThresholdModel> page, String moKpiName, String moAbnormalName,
                                                 String resStypeName, String resMtypeName);

}
