package com.hedian.mapper;

import com.hedian.entity.ResAbnormallevel;
import com.baomidou.mybatisplus.mapper.BaseMapper;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 资源异常级别 Mapper 接口
 * </p>
 *
 * @author hedian123
 * @since 2018-08-17
 */
public interface ResAbnormallevelMapper extends BaseMapper<ResAbnormallevel> {

    List<ResAbnormallevel> getCountByLevelMap(Map<String,Object> map);
}
