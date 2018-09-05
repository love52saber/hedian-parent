package com.hedian.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.hedian.entity.MoAbnormalDef;
import com.baomidou.mybatisplus.service.IService;

/**
 * <p>
 * 监控异常定义 服务类
 * </p>
 *
 * @author hedian123
 * @since 2018-08-17
 */
public interface IMoAbnormalDefService extends IService<MoAbnormalDef> {

    Page<MoAbnormalDef> selectAbnormalDefPageList(Page<MoAbnormalDef> page, String abnormalCode, String abnormalName,
                                                  String abnormalType, String abnormalLevel);


}
