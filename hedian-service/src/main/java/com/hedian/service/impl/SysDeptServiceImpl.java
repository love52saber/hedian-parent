package com.hedian.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.hedian.entity.SysDept;
import com.hedian.mapper.SysDeptMapper;
import com.hedian.service.ISysDeptService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 部门管理 服务实现类
 * </p>
 *
 * @author hedian123
 * @since 2018-08-17
 */
@Service
public class SysDeptServiceImpl extends ServiceImpl<SysDeptMapper, SysDept> implements ISysDeptService {


    @Override
    public List<SysDept> treeDeptList(Long pId, List<SysDept> list) {
        List<SysDept> IteratorMenuList = new ArrayList<>();
        for (SysDept m : list) {
            if (m.getParentId().equals(pId)) {
                //TODO 待优化
                if(!pId.equals(0L)){
                    m.setParentName(this.selectOne(new EntityWrapper<SysDept>().eq("dept_id",m.getParentId())).getName());
                }
                List<SysDept> childMenuList = treeDeptList(m.getDeptId(), list);
                m.setChildren(childMenuList);
                IteratorMenuList.add(m);
            }
        }
        return IteratorMenuList;
    }
}
