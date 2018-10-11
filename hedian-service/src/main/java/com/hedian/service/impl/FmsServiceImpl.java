package com.hedian.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.hedian.base.BusinessException;
import com.hedian.entity.*;
import com.hedian.mapper.FmsMapper;
import com.hedian.model.FmsModel;
import com.hedian.service.*;
import com.hedian.util.ComUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 故障维修策略 服务实现类
 * </p>
 *
 * @author hedian123
 * @since 2018-09-17
 */
@Service
public class FmsServiceImpl extends ServiceImpl<FmsMapper, Fms> implements IFmsService {

    @Autowired
    private FmsMapper fmsMapper;
    @Autowired
    private IFmsMdService fmsMdService;
    @Autowired
    private IFmsResService fmsResService;
    @Autowired
    private IFmsAbnormalService fmsAbnormalService;
    @Autowired
    private IFmsAbnormalTypeService fmsAbnormalTypeService;

    @Override
    public Page<FmsModel> selectPageByCondition(Page<FmsModel> page, String fmsName, String deptName, String userName, Integer dispatchflag, String grpName, Integer fmsStatus) {
        return page.setRecords(fmsMapper.selectPageByCondition(page, fmsName, deptName, userName, dispatchflag, grpName, fmsStatus));
    }

    @Override
    public boolean addAllNodes(Fms fms) throws Exception {
        boolean result = this.insert(fms);
        if (!result) {
            throw new BusinessException("插入故障维护策略失败");
        }
        //插入管理域用户信息
        result = fmsMdService.saveAll(fms.getFmsId(), fms.getMdIds());
        if (!result) {
            throw new BusinessException("插入管理域用户信息失败");
        }
        result = fmsResService.saveAll(fms.getFmsId(), fms.getResIds());
        if (!result) {
            throw new BusinessException("插入信息失败");
        }
        result = fmsAbnormalService.saveAll(fms.getFmsId(), fms.getAbnormalIds());
        if (!result) {
            throw new BusinessException("插入管理域设备信息失败");
        }
        result = fmsAbnormalTypeService.saveAll(fms.getFmsId(), fms.getAbnormalTypeIds());
        if (!result) {
            throw new BusinessException("插入管理域设备信息失败");
        }
        return result;
    }

    @Override
    public boolean updateFms(Fms fms) throws Exception {
        Fms fmsCopy = this.selectById(fms.getFmsId());
        if (ComUtil.isEmpty(fmsCopy)) {
            return false;
        }
        boolean result = this.updateById(fms);
        if (!result) {
            throw new BusinessException("更新故障维护策略失败");
        }
        result = fmsMdService.delete(new EntityWrapper<FmsMd>().eq("fms_id", fms.getFmsId()));
        if (!result) {
            throw new BusinessException("删除管理域信息失败");
        }
        //插入管理域用户信息
        result = fmsMdService.saveAll(fms.getFmsId(), fms.getMdIds());
        if (!result) {
            throw new BusinessException("插入管理域信息失败");
        }
        result = fmsAbnormalService.delete(new EntityWrapper<FmsAbnormal>().eq("fms_id", fms.getFmsId()));
        if (!result) {
            throw new BusinessException("删除故障信息失败");
        }
        result = fmsAbnormalService.saveAll(fms.getFmsId(), fms.getAbnormalIds());
        if (!result) {
            throw new BusinessException("更新故障信息失败");
        }
        result = fmsAbnormalTypeService.delete(new EntityWrapper<FmsAbnormalType>().eq("fms_id", fms.getFmsId()));
        if (!result) {
            throw new BusinessException("删除故障类型信息失败");
        }
        result = fmsAbnormalTypeService.saveAll(fms.getFmsId(), fms.getAbnormalTypeIds());
        if (!result) {
            throw new BusinessException("更新故障类型信息失败");
        }
        result = fmsResService.delete(new EntityWrapper<FmsRes>().eq("fms_id", fms.getFmsId()));
        if (!result) {
            throw new BusinessException("删除设备信息失败");
        }
        result = fmsResService.saveAll(fms.getFmsId(), fms.getResIds());
        if (!result) {
            throw new BusinessException("更新设备信息失败");
        }
        return result;
    }
}
