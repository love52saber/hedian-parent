package com.hedian.service;

import com.hedian.entity.MoKpi;
import com.baomidou.mybatisplus.service.IService;

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
}
