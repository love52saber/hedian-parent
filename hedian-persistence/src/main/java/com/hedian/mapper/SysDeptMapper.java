package com.hedian.mapper;

import com.hedian.entity.SysDept;
import com.baomidou.mybatisplus.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 * 部门管理 Mapper 接口
 * </p>
 *
 * @author hedian123
 * @since 2018-08-17
 */
public interface SysDeptMapper extends BaseMapper<SysDept> {

    /**
     * 获取本部门下的子部门
     * @param deptId
     * @return
     */
    List<SysDept> getChildList(Long deptId);

    List<SysDept> getParentList(Long deptId);
}
