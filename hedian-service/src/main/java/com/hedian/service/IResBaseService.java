package com.hedian.service;

import com.hedian.entity.ResBase;
import com.baomidou.mybatisplus.service.IService;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 资源基础信息表 服务类
 * </p>
 *
 * @author hedian123
 * @since 2018-08-17
 */
public interface IResBaseService extends IService<ResBase> {

    List<ResBase> findByMap(Map<String, Object> map);

    List<ResBase> getTopRes(Map<String,Object> map);
}
