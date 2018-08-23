package com.hedian.service.impl;

import com.hedian.entity.ResStypeKpi;
import com.hedian.mapper.ResMoAbnormalInfoMapper;
import com.hedian.mapper.ResStypeKpiMapper;
import com.hedian.service.IResStypeKpiService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 资源子类型监控的指标 服务实现类
 * </p>
 *
 * @author hedian123
 * @since 2018-08-17
 */
@Service
public class ResStypeKpiServiceImpl extends ServiceImpl<ResStypeKpiMapper, ResStypeKpi> implements IResStypeKpiService {

    @Resource
    private ResStypeKpiMapper resStypeKpiMapper;
    @Override
    public List<ResStypeKpi> selectByResStypeId(Integer resStypeId) {
        return resStypeKpiMapper.selectByResStypeId(resStypeId);
    }
}
