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
        List<WfBusiness> associatedBusinessList = new ArrayList<>();
        WfBusiness currentWfBusiness = this.selectById(businessId);
        if (ComUtil.isEmpty(currentWfBusiness)) {
            throw new BusinessException("查询不到这条工单");
        }
        //获取最顶层工单id
        Long originalBusinessId = this.getOriginalBusinessId(currentWfBusiness);
        //获取整个工单树
        associatedBusinessList.add(this.selectById(originalBusinessId));
        associatedBusinessList = this.getAssociatedBusinessList(originalBusinessId, associatedBusinessList);
        //排除当前工单
        associatedBusinessList.removeIf(wfBusiness -> {
            return wfBusiness.getBusinessId().equals(businessId);
        });
        return associatedBusinessList;
    }

    /**
     * 获取最顶层工单id
     *
     * @param currentWfBusiness
     * @return
     */
    public Long getOriginalBusinessId(WfBusiness currentWfBusiness) {
        Long originalBusinessId = null;
        Long currentBusinessBusinessId = currentWfBusiness.getBusinessId();
        if (currentWfBusiness.getParentBusinessId().equals(Constant.IS_ORIGINAL_BUSINESS_ID)) {
            originalBusinessId = currentBusinessBusinessId;
        } else {
            WfBusiness parentBusiness = this.selectById(currentWfBusiness.getParentBusinessId());
            originalBusinessId = getOriginalBusinessId(parentBusiness);
        }
        return originalBusinessId;
    }

    /**
     * 获取整个相关的工单树
     *
     * @param originalBusinessId
     * @param associatedBusinessList
     * @return
     */
    private List<WfBusiness> getAssociatedBusinessList(Long originalBusinessId, List<WfBusiness> associatedBusinessList) {
        List<WfBusiness> subBusinessList = this.selectList(new EntityWrapper<WfBusiness>().eq("parent_business_id", originalBusinessId));
        associatedBusinessList.addAll(subBusinessList);
        for (WfBusiness wfBusiness : subBusinessList) {
            getAssociatedBusinessList(wfBusiness.getBusinessId(), associatedBusinessList);
        }
        return associatedBusinessList;
    }


}
