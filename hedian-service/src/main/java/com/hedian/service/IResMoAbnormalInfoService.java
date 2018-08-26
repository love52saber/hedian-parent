package com.hedian.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.hedian.entity.ResMoAbnormalInfo;
import com.baomidou.mybatisplus.service.IService;
import com.hedian.model.AlarmInfoModel;

import java.util.HashMap;

/**
 * <p>
 * 资源监控异常信息 服务类
 * </p>
 *
 * @author hedian123
 * @since 2018-08-17
 */
public interface IResMoAbnormalInfoService extends IService<ResMoAbnormalInfo> {

    ResMoAbnormalInfo selectByResIdAndkpiId(HashMap<String,Object> map);

    Page<AlarmInfoModel> selectAlarmByResId(Page<AlarmInfoModel> alarmInfoModelPage, String resId);

}
