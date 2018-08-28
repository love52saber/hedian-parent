package com.hedian.service;

import com.hedian.entity.ResStatus;
import com.baomidou.mybatisplus.service.IService;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 资源状态定义 服务类
 * </p>
 *
 * @author hedian123
 * @since 2018-08-17
 */
public interface IResStatusService extends IService<ResStatus> {

    List<ResStatus> getCountByStatusMap(Map<String,Object> map);
}
