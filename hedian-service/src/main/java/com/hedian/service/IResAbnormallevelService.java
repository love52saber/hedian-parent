package com.hedian.service;

import com.hedian.entity.ResAbnormallevel;
import com.baomidou.mybatisplus.service.IService;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 资源异常级别 服务类
 * </p>
 *
 * @author hedian123
 * @since 2018-08-17
 */
public interface IResAbnormallevelService extends IService<ResAbnormallevel> {

    List<ResAbnormallevel> getCountByLevelMap(Map<String,Object> map);
}
