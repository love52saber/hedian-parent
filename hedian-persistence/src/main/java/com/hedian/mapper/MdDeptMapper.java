package com.hedian.mapper;

import com.hedian.entity.MdDept;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.hedian.entity.MdRes;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 单位和管理域对应关系表 Mapper 接口
 * </p>
 *
 * @author hedian123
 * @since 2018-08-27
 */
public interface MdDeptMapper extends BaseMapper<MdDept> {

    List<MdDept> findByMap(Map<String,Object> map);

}
