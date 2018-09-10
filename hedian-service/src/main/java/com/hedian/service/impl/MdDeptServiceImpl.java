package com.hedian.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.hedian.entity.MdDept;
import com.hedian.mapper.MdDeptMapper;
import com.hedian.service.IMdDeptService;
import com.hedian.util.ComUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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

    @Override
    public boolean saveAll(Integer mdId, List<Long> deptIds) {
        boolean result = true;
        if (!ComUtil.isEmpty(deptIds)) {
            List<MdDept> mdDepts = new ArrayList<>();
            deptIds.stream().forEach(deptId -> {
                mdDepts.add(new MdDept(mdId, deptId));
            });
            result = this.insertBatch(mdDepts);
        }
        return result;
    }
}
