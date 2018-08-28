package com.hedian.service.impl;

import com.hedian.entity.MdRes;
import com.hedian.mapper.MdResMapper;
import com.hedian.service.IMdResService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 维护域与资源关系 服务实现类
 * </p>
 *
 * @author hedian123
 * @since 2018-08-27
 */
@Service
public class MdResServiceImpl extends ServiceImpl<MdResMapper, MdRes> implements IMdResService {

    @Autowired
    private MdResMapper mdResMapper;
    @Override
    public List<MdRes> findByMap(Map<String, Object> map) {
        return mdResMapper.findByMap(map);
    }
}
