package com.hedian.service;

import com.baomidou.mybatisplus.service.IService;
import com.hedian.entity.SysDept;

import java.util.List;

/**
 * <p>
 * 部门管理 服务类
 * </p>
 *
 * @author hedian123
 * @since 2018-08-17
 */
public interface ISysDeptService extends IService<SysDept> {


    /**
     * 获取部门树
     * @param pId
     * @param list
     * @return
     */
     List<SysDept> treeDeptList(Long pId, List<SysDept> list);

    /**
     * 获取本部门下的子部门
     * @param deptId
     * @return
     */
    List<SysDept> getChildList(Long deptId);
}
