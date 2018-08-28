package com.hedian.service.impl;

import com.hedian.entity.ResAbnormallevel;
import com.hedian.mapper.ResAbnormallevelMapper;
import com.hedian.service.IResAbnormallevelService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 资源异常级别 服务实现类
 * </p>
 *
 * @author hedian123
 * @since 2018-08-17
 */
@Service
public class ResAbnormallevelServiceImpl extends ServiceImpl<ResAbnormallevelMapper, ResAbnormallevel> implements IResAbnormallevelService {


    @Autowired
    private ResAbnormallevelMapper resAbnormallevelMapper;
    @Override
    public List<ResAbnormallevel> getCountByLevelMap(Map<String, Object> map) {
        return resAbnormallevelMapper.getCountByLevelMap(map);
    }
}
