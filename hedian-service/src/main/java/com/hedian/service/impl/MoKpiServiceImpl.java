package com.hedian.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.hedian.entity.MoKpi;
import com.hedian.entity.ResSubtype;
import com.hedian.mapper.MoKpiMapper;
import com.hedian.model.MokpiModel;
import com.hedian.service.IMoKpiService;
import com.hedian.service.IResSubtypeService;
import com.hedian.util.ComUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    public List<MoKpi> selectMokpiByStype(Integer resStype) {
        List<Integer> stypeIds = this.getAllResStypeIds(resStype);
        stypeIds.add(resStype);
        List<MoKpi> moKpiList = moKpiMapper.selectMoKpiByStype(stypeIds);
        return moKpiList;
    }

    @Override
    public List<Integer> getAllResStypeIds(Integer resStype) {
        List<Integer> resStypeIds = new ArrayList<>();
        ResSubtype resSubtype = resSubtypeService.selectById(resStype);
        if (!ComUtil.isEmpty(resSubtype)) {
            if (!resSubtype.getParentId().equals(0)) {
                ResSubtype resSubtypeParent = resSubtypeService.selectById(resSubtype.getParentId());
                resStypeIds.add(resSubtypeParent.getResStypeId());
                getAllResStypeIds(resSubtypeParent.getParentId());

            }
        }
        return resStypeIds;
    }

    @Override
    public List<MokpiModel> selectMokpiObject(Integer resStype) {
        Map<String, Object> params = new HashMap<>();
        params.put("stypeId", resStype);
        //查询本节点下的mokpi
        List<MokpiModel> mokpiModels = moKpiMapper.selectMokpiObject(params);
        List<Integer> stypeIds = this.getAllResStypeIds(resStype);
        if (!ComUtil.isEmpty(stypeIds)) {
            params.clear();
            params.put("stypeFlag", 1);
            params.put("stypePIds", stypeIds);
            List<MokpiModel> mokpiModelParents = moKpiMapper.selectMokpiObject(params);
            if (!ComUtil.isEmpty(mokpiModelParents)) {
                mokpiModelParents.stream().forEach(mokpiModel -> {
                    if(!ComUtil.isEmpty(mokpiModels)){
                        mokpiModel.setResCurStypeName(mokpiModels.get(0).getResStypeName());
                    }else{
                        mokpiModel.setResCurStypeName(resSubtypeService.selectById(resStype).getResStypeName());
                    }

                });
                mokpiModels.addAll(mokpiModelParents);
            }
        }
        return mokpiModels;
    }
}
