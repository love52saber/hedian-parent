package com.hedian.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.hedian.base.BusinessException;
import com.hedian.entity.RepairOrderAppraiser;
import com.hedian.entity.RepairOrderAppraiserMd;
import com.hedian.mapper.RepairOrderAppraiserMapper;
import com.hedian.mapper.RepairOrderAppraiserMdMapper;
import com.hedian.service.IRepairOrderAppraiserMdService;
import com.hedian.service.IRepairOrderAppraiserService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.hedian.util.ComUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 维修工单评价人管理 服务实现类
 * </p>
 *
 * @author hedian123
 * @since 2018-11-05
 */
@Service
public class RepairOrderAppraiserServiceImpl extends ServiceImpl<RepairOrderAppraiserMapper, RepairOrderAppraiser> implements IRepairOrderAppraiserService {

    @Autowired
    private IRepairOrderAppraiserMdService iRepairOrderAppraiserMdService;
    @Autowired
    private IRepairOrderAppraiserService iRepairOrderAppraiserService;
    @Autowired
    private RepairOrderAppraiserMapper repairOrderAppraiserMapper;
    @Autowired
    private RepairOrderAppraiserMdMapper repairOrderAppraiserMdMapper;

    @Override
    public boolean addAppraiser(JSONObject requestJson) throws BusinessException {
        RepairOrderAppraiser repairOrderAppraiser = requestJson.toJavaObject(RepairOrderAppraiser.class);
        boolean saveAppraiserResult = this.insert(repairOrderAppraiser);
        //修改维修工单评价人关联维护域表
        ArrayList<RepairOrderAppraiserMd> repairOrderAppraiserMdList = new ArrayList<>();
        RepairOrderAppraiserMd repairOrderAppraiserMd;
        Integer[] mdIds = repairOrderAppraiser.getMdIds();
        if (!ComUtil.isEmpty(mdIds)) {
            for (Integer mdId : mdIds) {
                repairOrderAppraiserMd = new RepairOrderAppraiserMd();
                repairOrderAppraiserMd.setAppraiserid(repairOrderAppraiser.getAppraiserid());
                repairOrderAppraiserMd.setMdId(mdId);
                repairOrderAppraiserMdList.add(repairOrderAppraiserMd);
            }
            boolean saveAppraiserMdResult = iRepairOrderAppraiserMdService.insertBatch(repairOrderAppraiserMdList);
            if (!saveAppraiserMdResult) {
                throw new BusinessException("添加失败");
            }
        }
        return saveAppraiserResult;
    }

    @Override
    public boolean updateAppraiser(JSONObject requestJson) throws BusinessException {
        RepairOrderAppraiser repairOrderAppraiser = requestJson.toJavaObject(RepairOrderAppraiser.class);
        boolean saveAppraiserResult = this.updateById(repairOrderAppraiser);
        //修改维修工单评价人对应维护域表,先删再添加
        List<RepairOrderAppraiserMd> existAppraiserMds =
                iRepairOrderAppraiserMdService.selectList(new EntityWrapper<RepairOrderAppraiserMd>().eq("appraiserid", repairOrderAppraiser.getAppraiserid()));
        if (!ComUtil.isEmpty(existAppraiserMds)) {
            existAppraiserMds.stream().forEach(repairOrderAppraiserMd -> {
                repairOrderAppraiserMd.setUseflag(0);
            });
            if (!iRepairOrderAppraiserMdService.updateBatchById(existAppraiserMds)) {
                throw new BusinessException("修改失败");
            }
        }
        Integer[] mdIds = repairOrderAppraiser.getMdIds();
        ArrayList<RepairOrderAppraiserMd> repairOrderAppraiserMdList = new ArrayList<>();
        if (!ComUtil.isEmpty(mdIds)) {
            for (Integer mdId : repairOrderAppraiser.getMdIds()) {
                RepairOrderAppraiserMd repairOrderAppraiserMd = new RepairOrderAppraiserMd();
                repairOrderAppraiserMd.setAppraiserid(repairOrderAppraiser.getAppraiserid());
                repairOrderAppraiserMd.setMdId(mdId);
                repairOrderAppraiserMdList.add(repairOrderAppraiserMd);
            }
            boolean saveAppraiserMdResult = iRepairOrderAppraiserMdService.insertBatch(repairOrderAppraiserMdList);
            if (saveAppraiserMdResult) {
                throw new BusinessException("修改失败");
            }
        }
        return saveAppraiserResult;
    }

    @Override
    public RepairOrderAppraiser findAppraiserById(Integer appraiserId) {
        return repairOrderAppraiserMapper.findAppraiserById(appraiserId);
    }

    @Override
    public boolean delById(Integer appraiserid) throws BusinessException {
        //删除评价人
        boolean delAppraiserResult = iRepairOrderAppraiserService.deleteById(appraiserid);
        //删除评价人域关联表
        List<RepairOrderAppraiserMd> existAppraiserMdList = iRepairOrderAppraiserMdService.selectList(new EntityWrapper<RepairOrderAppraiserMd>().eq("appraiserid", appraiserid));
        if (!ComUtil.isEmpty(existAppraiserMdList)) {
            existAppraiserMdList.stream().forEach(repairOrderAppraiserMd -> {
                repairOrderAppraiserMd.setUseflag(0);
            });
            boolean deleteAppraiserMdResult = iRepairOrderAppraiserMdService.updateBatchById(existAppraiserMdList);
            if (!deleteAppraiserMdResult) {
                throw new BusinessException("相关域删除失败");
            }
        }
        return delAppraiserResult;
    }

    @Override
    public boolean delBatchByIds(List<Integer> appraiseridList) throws BusinessException {
        for (Integer appraiserid : appraiseridList) {
            if (!delById(appraiserid)) {
                throw new BusinessException("评价人删除失败");
            }
        }
        return true;
    }

    @Override
    public Page<RepairOrderAppraiser> findPageByCondition(Page<RepairOrderAppraiser> page, Integer appraisertype, String apprasiername, String grpName) {
        List<RepairOrderAppraiser> repairOrderAppraiserList = repairOrderAppraiserMapper.findPageByCondition(page,appraisertype,apprasiername,grpName);
        page.setRecords(repairOrderAppraiserList);
        return page;
    }


}
