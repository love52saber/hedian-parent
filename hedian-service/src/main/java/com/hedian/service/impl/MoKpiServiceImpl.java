package com.hedian.service.impl;

import com.hedian.entity.MoKpi;
import com.hedian.entity.ResSubtype;
import com.hedian.mapper.MoKpiMapper;
import com.hedian.service.IMoKpiService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.hedian.service.IResSubtypeService;
import com.hedian.util.ComUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 监控指标 服务实现类
 * </p>
 *
 * @author hedian123
 * @since 2018-08-17
 */
@Service
public class MoKpiServiceImpl extends ServiceImpl<MoKpiMapper, MoKpi> implements IMoKpiService {

    @Autowired
    private IResSubtypeService resSubtypeService;
    @Autowired
    private MoKpiMapper moKpiMapper;

    @Override
    public List<MoKpi> selectMokpiByStype(String resStype) {
        List<Integer> stypeIds = getAllResStypeIds(resStype);
        List<MoKpi> moKpiList = moKpiMapper.selectMoKpiByStype(stypeIds);
        return moKpiList;
    }


    private List<Integer> getAllResStypeIds(String resStype) {
        List<Integer> resStypeIds = new ArrayList<>();
        ResSubtype resSubtype = resSubtypeService.selectById(resStype);
        if (!ComUtil.isEmpty(resSubtype)) {
            resStypeIds.add(resSubtype.getResStypeId());
            if (!resSubtype.getParentId().equals(0)) {
                ResSubtype resSubtypeParent = resSubtypeService.selectById(resSubtype.getParentId());
                getAllResStypeIds(String.valueOf(resSubtypeParent.getParentId()));
                resStypeIds.add(resSubtype.getResStypeId());
            }
        }
        return resStypeIds;
    }
}
