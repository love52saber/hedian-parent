package com.hedian.service.impl;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.hedian.entity.MoAbnormalDef;
import com.hedian.mapper.MoAbnormalDefMapper;
import com.hedian.service.IMoAbnormalDefService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 监控异常定义 服务实现类
 * </p>
 *
 * @author hedian123
 * @since 2018-08-17
 */
@Service
public class MoAbnormalDefServiceImpl extends ServiceImpl<MoAbnormalDefMapper, MoAbnormalDef> implements IMoAbnormalDefService {

    @Autowired
    private MoAbnormalDefMapper moAbnormalDefMapper;

    @Override
    public Page<MoAbnormalDef> selectAbnormalDefPageList(Page<MoAbnormalDef> page, String abnormalCode, String abnormalName, String abnormalType, String abnormalLevel) {

        return page.setRecords(moAbnormalDefMapper.selectAbnormalDefPageList(page, abnormalCode, abnormalName, abnormalType, abnormalLevel));
    }
}
