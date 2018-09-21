package com.hedian.service.impl;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.hedian.entity.MoAbnormalDef;
import com.hedian.entity.ResMoAbnormalInfo;
import com.hedian.mapper.ResMoAbnormalInfoMapper;
import com.hedian.model.AbnormalLevelModel;
import com.hedian.model.AlarmInfoModel;
import com.hedian.model.ResMoAbnormalInfoModel;
import com.hedian.service.IResMoAbnormalInfoService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 资源监控异常信息 服务实现类
 * </p>
 *
 * @author hedian123
 * @since 2018-08-17
 */
@Service
public class ResMoAbnormalInfoServiceImpl extends ServiceImpl<ResMoAbnormalInfoMapper, ResMoAbnormalInfo> implements IResMoAbnormalInfoService {

    @Resource
    private ResMoAbnormalInfoMapper resMoAbnormalInfoMapper;


    @Override
    public ResMoAbnormalInfo selectByResIdAndkpiId(Map<String, Integer> map) {
        return resMoAbnormalInfoMapper.selectByResIdAndkpiId(map);
    }

    @Override
    public List<AlarmInfoModel> selectAlarmByResId(String resId) {
        return resMoAbnormalInfoMapper.selectAlarmByResId(resId);
    }

    @Override
    public List<MoAbnormalDef> getTopAbnormal(Map<String, Object> map) {
        return resMoAbnormalInfoMapper.getTopAbnormal(map);
    }

    @Override
    public Page<ResMoAbnormalInfoModel> selectPageByCondition(Page<ResMoAbnormalInfoModel> page, String beginTime, String endTime, String conStatus, String abnormalLevel, String abnormalType,
                                                              String abnormalName, String mokpiName, String resName, String resAlias,boolean isAutoOrder) {
        return page.setRecords(resMoAbnormalInfoMapper.selectPageByCondition(page, beginTime, endTime, conStatus, abnormalLevel, abnormalType, abnormalName, mokpiName, resName, resAlias,isAutoOrder));
    }

    @Override
    public List<AbnormalLevelModel> selectAbnormalLevelCount() {
        return resMoAbnormalInfoMapper.selectAbnormalLevelCount();
    }
}
