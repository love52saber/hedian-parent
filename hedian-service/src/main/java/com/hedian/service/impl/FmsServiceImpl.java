package com.hedian.service.impl;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.hedian.entity.Fms;
import com.hedian.mapper.FmsMapper;
import com.hedian.model.FmsModel;
import com.hedian.service.IFmsService;
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

    @Override
    public Page<FmsModel> selectPageByConfidition(Page<FmsModel> page, String fmsName, String deptName, String userName, Integer dispatchflag, String grpName, Integer fmsStatus) {
        return page.setRecords(fmsMapper.selectPageByConfidition(page, fmsName, deptName, userName, dispatchflag, grpName, fmsStatus));
    }
}
