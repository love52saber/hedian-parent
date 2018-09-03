package com.hedian.service.impl;

import com.hedian.entity.ResSubtype;
import com.hedian.mapper.ResSubtypeMapper;
import com.hedian.service.IResSubtypeService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 资源子类型 服务实现类
 * </p>
 *
 * @author hedian123
 * @since 2018-08-17
 */
@Service
public class ResSubtypeServiceImpl extends ServiceImpl<ResSubtypeMapper, ResSubtype> implements IResSubtypeService {

    @Override
    public List<ResSubtype> treeSubtypeList(Integer pId, List<ResSubtype> resSubtypeList) {
        List<ResSubtype> iteratorSubtypeList = new ArrayList<>();
        for (ResSubtype st : resSubtypeList) {
            if (st.getParentId().equals(pId)) {
                List<ResSubtype> childResSubList = treeSubtypeList(st.getResStypeId(), resSubtypeList);
                st.setChildren(childResSubList);
                iteratorSubtypeList.add(st);
            }
        }
        return iteratorSubtypeList;

    }
}
