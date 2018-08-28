package com.hedian.mapper;

import com.hedian.entity.MdRes;
import com.baomidou.mybatisplus.mapper.BaseMapper;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 维护域与资源关系 Mapper 接口
 * </p>
 *
 * @author hedian123
 * @since 2018-08-27
 */
public interface MdResMapper extends BaseMapper<MdRes> {

    List<MdRes> findByMap(Map<String,Object> map);

}
