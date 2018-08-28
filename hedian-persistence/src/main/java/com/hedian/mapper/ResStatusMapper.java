package com.hedian.mapper;

import com.hedian.entity.ResStatus;
import com.baomidou.mybatisplus.mapper.BaseMapper;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 资源状态定义 Mapper 接口
 * </p>
 *
 * @author hedian123
 * @since 2018-08-17
 */
public interface ResStatusMapper extends BaseMapper<ResStatus> {


    List<ResStatus> getCountByStatusMap(Map<String,Object> map);
}
