package com.hedian.service;

import com.baomidou.mybatisplus.service.IService;
import com.hedian.entity.MoKpi;
import com.hedian.model.MokpiModel;

import java.util.List;

/**
 * <p>
 * 监控指标 服务类
 * </p>
 *
 * @author hedian123
 * @since 2018-08-17
 */
public interface IMoKpiService extends IService<MoKpi> {

    List<MoKpi> selectMokpiByStype(Integer resStype);

    /**
     * 只在service用的公共方法
     * <p>
     * 递归查询当前资源子类型的父节点
     *
     * @param resStype
     * @return
     */
    List<Integer> getAllResStypeIds(Integer resStype);

    /**
     * 获取本节点及所有父节点的所有mokpi
     *
     * @return
     */
    List<MokpiModel> selectMokpiObject(Integer resStype);
}
