package com.hedian.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.hedian.base.BusinessException;
import com.hedian.base.Constant;
import com.hedian.entity.WfBusiness;
import com.hedian.mapper.WfBusinessMapper;
import com.hedian.service.IWfBusinessService;
import com.hedian.util.ComUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author hedian123
 * @since 2018-10-18
 */
@Service
public class WfBusinessServiceImpl extends ServiceImpl<WfBusinessMapper, WfBusiness> implements IWfBusinessService {

    @Autowired
    private WfBusinessMapper wfBusinessMapper;

    @Override
    public List<WfBusiness> getAssociatedBusinessListExceptSelf(Long businessId) throws Exception {

        WfBusiness currentWfBusiness = this.selectById(businessId);
        if (ComUtil.isEmpty(currentWfBusiness)) {
            throw new BusinessException("查询不到这条工单");
        }
        //获取最顶层工单
        WfBusiness toppestBusiness = this.getToppestBusiness(currentWfBusiness);
        //获取整个工单树的工单
        List<WfBusiness> associatedBusinessList = this.getAllSubBusiness(toppestBusiness);
        //排除当前工单
        associatedBusinessList.removeIf(wfBusiness -> {
            return wfBusiness.getBusinessId().equals(businessId);
        });
        return associatedBusinessList;
    }

    /**
     * 获取最顶层工单
     *
     * @param currentWfBusiness
     * @return
     */
    public WfBusiness getToppestBusiness(WfBusiness currentWfBusiness) {
        Long parentBusinessId = currentWfBusiness.getParentBusinessId();
        if (parentBusinessId.equals(Constant.TOPPEST_BUSINESS_PARENT_ID)) {
            return currentWfBusiness;
        } else {
            WfBusiness parentBusiness = this.selectById(parentBusinessId);
            return getToppestBusiness(parentBusiness);
        }
    }

    /**
     * 获取指定工单下的子工单list(包括自身)
     *
     * @param originalBusiness 指定的起始工单
     * @return
     */
    private List<WfBusiness> getAllSubBusiness(WfBusiness originalBusiness) {
        List<WfBusiness> associatedBusinessList = new ArrayList<>();
        this.addAllSubBusiness(originalBusiness, associatedBusinessList, true);
        return associatedBusinessList;
    }

    /**
     * 递归添加指定工单下的子工单树(包括自身)到指定list
     *
     * @param currentBusiness       当前工单
     * @param specifiedBusinessList 存储相关工单的list
     * @param isOriginalFlag        是否为起始节点
     * @return
     */
    private List<WfBusiness> addAllSubBusiness(WfBusiness currentBusiness,
                                                                 List<WfBusiness> specifiedBusinessList,
                                                                 boolean isOriginalFlag) {
        if (isOriginalFlag) {
            specifiedBusinessList.add(currentBusiness);
        }
        List<WfBusiness> subBusinessList = this.selectList(new EntityWrapper<WfBusiness>().eq("parent_business_id",
                currentBusiness.getBusinessId()));
        specifiedBusinessList.addAll(subBusinessList);
        for (WfBusiness subBusiness : subBusinessList) {
            addAllSubBusiness(subBusiness, specifiedBusinessList, false);
        }
        return specifiedBusinessList;
    }


}
