package com.hedian.mapper;

import com.hedian.entity.ResBase;
import com.baomidou.mybatisplus.mapper.BaseMapper;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 资源基础信息表 Mapper 接口
 * </p>
 *
 * @author hedian123
 * @since 2018-08-17
 */
public interface ResBaseMapper extends BaseMapper<ResBase> {

    List<ResBase> findByMap(Map<String, Object> map);

    List<ResBase> getTopRes(Map<String,Object> map);

}
