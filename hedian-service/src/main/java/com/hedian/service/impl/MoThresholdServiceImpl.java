package com.hedian.service.impl;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.hedian.entity.MoThreshold;
import com.hedian.mapper.MoThresholdMapper;
import com.hedian.model.MoThresholdModel;
import com.hedian.service.IMoThresholdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 监控阈值配置 服务实现类
 * </p>
 *
 * @author hedian123
 * @since 2018-08-17
 */
@Service
public class MoThresholdServiceImpl extends ServiceImpl<MoThresholdMapper, MoThreshold> implements IMoThresholdService {


    @Autowired
    private MoThresholdMapper moThresholdMapper;

    @Override
    public Page<MoThresholdModel> selectPageMoThreshold(Page<MoThresholdModel> page, String moKpiName, String moAbnormalName, String resStypeName, String resMtypeName) {
        return page.setRecords(moThresholdMapper.selectPageMoThreshold(page, moKpiName, moAbnormalName, resStypeName, resMtypeName));
    }
}
