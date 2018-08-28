package com.hedian.service.impl;

import com.hedian.entity.ResStatus;
import com.hedian.mapper.ResStatusMapper;
import com.hedian.service.IResStatusService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 资源状态定义 服务实现类
 * </p>
 *
 * @author hedian123
 * @since 2018-08-17
 */
@Service
public class ResStatusServiceImpl extends ServiceImpl<ResStatusMapper, ResStatus> implements IResStatusService {


    @Autowired
    private ResStatusMapper resStatusMapper;

    @Override
    public List<ResStatus> getCountByStatusMap(Map<String, Object> map) {
        return resStatusMapper.getCountByStatusMap(map);
    }
}
