package com.hedian.service.impl;

import com.hedian.entity.ResMoAbnormalInfo;
import com.hedian.mapper.ResMoAbnormalInfoMapper;
import com.hedian.service.IResMoAbnormalInfoService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;

/**
 * <p>
 * 资源监控异常信息 服务实现类
 * </p>
 *
 * @author hedian123
 * @since 2018-08-17
 */
@Service
public class ResMoAbnormalInfoServiceImpl extends ServiceImpl<ResMoAbnormalInfoMapper, ResMoAbnormalInfo> implements IResMoAbnormalInfoService {

    @Resource
    private ResMoAbnormalInfoMapper resMoAbnormalInfoMapper;


    @Override
    public ResMoAbnormalInfo selectByResIdAndkpiId(HashMap<String, Object> map) {
        return resMoAbnormalInfoMapper.selectByResIdAndkpiId(map);
    }
}
