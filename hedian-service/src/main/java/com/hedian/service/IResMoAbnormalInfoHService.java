package com.hedian.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;
import com.hedian.entity.ResMoAbnormalInfoH;
import com.hedian.model.ResMoAbnormalInfoModel;

/**
 * <p>
 * 资源监控异常信息 服务类
 * </p>
 *
 * @author hedian123
 * @since 2018-09-17
 */
public interface IResMoAbnormalInfoHService extends IService<ResMoAbnormalInfoH> {

    Page<ResMoAbnormalInfoModel> selectPageByCondition(Page<ResMoAbnormalInfoModel> page, String beginTime, String endTime, String conStatus,
                                                       String abnormalLevel, String abnormalType, String abnormalName,
                                                       String mokpiName, String resName, String resAlias);

}
