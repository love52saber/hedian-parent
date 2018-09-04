package com.hedian.service;

import com.hedian.entity.MdDept;
import com.baomidou.mybatisplus.service.IService;
import com.hedian.entity.MdRes;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 单位和管理域对应关系表 服务类
 * </p>
 *
 * @author hedian123
 * @since 2018-08-27
 */
public interface IMdDeptService extends IService<MdDept> {

    List<MdDept> findByMap(Map<String,Object> map);

    boolean saveAll(Integer mdId, List<Long> deptIds);
}
