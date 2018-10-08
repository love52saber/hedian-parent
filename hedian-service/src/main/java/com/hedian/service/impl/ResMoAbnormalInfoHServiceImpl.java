package com.hedian.service.impl;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.hedian.entity.ResMoAbnormalInfoH;
import com.hedian.mapper.ResMoAbnormalInfoHMapper;
import com.hedian.model.ResMoAbnormalInfoModel;
import com.hedian.service.IResMoAbnormalInfoHService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 资源监控异常信息 服务实现类
 * </p>
 *
 * @author hedian123
 * @since 2018-09-17
 */
@Service
public class ResMoAbnormalInfoHServiceImpl extends ServiceImpl<ResMoAbnormalInfoHMapper, ResMoAbnormalInfoH> implements IResMoAbnormalInfoHService {


    @Autowired
    private ResMoAbnormalInfoHMapper resMoAbnormalInfoHMapper;

    @Override
    public Page<ResMoAbnormalInfoModel> selectPageByCondition(Page<ResMoAbnormalInfoModel> page, String beginTime, String endTime,String abnormalLevel, String abnormalType, String abnormalName,
                                                              String mokpiName, String resName, String resAlias,boolean isAutoOrder,boolean useflag) {
        return page.setRecords(resMoAbnormalInfoHMapper.selectPageByCondition(page, beginTime, endTime, abnormalLevel, abnormalType, abnormalName,
                mokpiName, resName, resAlias, isAutoOrder, useflag));
    }
}
