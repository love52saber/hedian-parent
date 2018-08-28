package com.hedian.service.impl;

import com.hedian.entity.ResBase;
import com.hedian.mapper.ResBaseMapper;
import com.hedian.service.IResBaseService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 资源基础信息表 服务实现类
 * </p>
 *
 * @author hedian123
 * @since 2018-08-17
 */
@Service
public class ResBaseServiceImpl extends ServiceImpl<ResBaseMapper, ResBase> implements IResBaseService {

    @Autowired
    private ResBaseMapper resBaseMapper;

    @Override
    public List<ResBase> findByMap(Map<String, Object> map) {
        return resBaseMapper.findByMap(map);
    }

    @Override
    public List<ResBase> getTopRes(Map<String, Object> map) {
        return resBaseMapper.getTopRes(map);
    }
}
