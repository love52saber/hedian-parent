package com.hedian.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.hedian.entity.MoAbnormalDef;
import com.hedian.entity.ResMoAbnormalInfo;
import com.baomidou.mybatisplus.service.IService;
import com.hedian.model.AlarmInfoModel;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 资源监控异常信息 服务类
 * </p>
 *
 * @author hedian123
 * @since 2018-08-17
 */
public interface IResMoAbnormalInfoService extends IService<ResMoAbnormalInfo> {

    ResMoAbnormalInfo selectByResIdAndkpiId(Map<String,Integer> map);

    List<AlarmInfoModel> selectAlarmByResId(String resId);

    List<MoAbnormalDef> getTopAbnormal(Map<String,Object> map);
}
