package com.hedian.service;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;
import com.hedian.entity.MoAbnormalDef;
import com.hedian.entity.ResMoAbnormalInfo;
import com.hedian.model.AbnormalLevelModel;
import com.hedian.model.AlarmInfoModel;
import com.hedian.model.ResMoAbnormalInfoModel;

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

    ResMoAbnormalInfo selectByResIdAndkpiId(Map<String, Integer> map);

    List<AlarmInfoModel> selectAlarmByResId(String resId);

    List<MoAbnormalDef> getTopAbnormal(Map<String, Object> map);

    Page<ResMoAbnormalInfoModel> selectPageByCondition(Page<ResMoAbnormalInfoModel> page, String beginTime, String endTime, String conStatus,
                                                       String abnormalLevel, String abnormalType, String abnormalName,
                                                       String mokpiName, String resName, String resAlias, boolean isAutoOrder);

    List<AbnormalLevelModel> selectAbnormalLevelCount();

    boolean deleteResAbnoraml(Long resAbnormalId) throws Exception;

    /**
     * 清除告警信息
     *
     * @param requestJson
     * @return
     */
    boolean cleanResAbnormal(JSONObject requestJson) throws Exception;
}
