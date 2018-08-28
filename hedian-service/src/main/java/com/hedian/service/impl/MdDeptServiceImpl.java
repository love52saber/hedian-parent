package com.hedian.service.impl;

import com.hedian.entity.MdDept;
import com.hedian.mapper.MdDeptMapper;
import com.hedian.service.IMdDeptService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 单位和管理域对应关系表 服务实现类
 * </p>
 *
 * @author hedian123
 * @since 2018-08-27
 */
@Service
public class MdDeptServiceImpl extends ServiceImpl<MdDeptMapper, MdDept> implements IMdDeptService {

    @Autowired
    private MdDeptMapper mdDeptMapper;

    @Override
    public List<MdDept> findByMap(Map<String, Object> map) {
        return mdDeptMapper.findByMap(map);
    }
}
