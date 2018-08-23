package com.hedian.service;

import com.hedian.entity.ResMoAbnormalInfo;
import com.baomidou.mybatisplus.service.IService;

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
}
