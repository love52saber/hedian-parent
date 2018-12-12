package com.hedian.service.impl;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.hedian.base.BusinessException;
import com.hedian.entity.WoSla;
import com.hedian.mapper.WoSlaMapper;
import com.hedian.service.IWoSlaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 工单考核sla定义表 服务实现类
 * </p>
 *
 * @author hedian123
 * @since 2018-11-27
 */
@Service
public class WoSlaServiceImpl extends ServiceImpl<WoSlaMapper, WoSla> implements IWoSlaService {


    @Autowired
    private WoSlaMapper woSlaMapper;
    @Autowired
    private IWoSlaService iWoSlaService;



    @Override
    public boolean addSla(WoSla woSla) throws Exception {
        boolean result = iWoSlaService.insert(woSla);
        if(!result){
            throw new BusinessException("插入sla数据失败");
        }
        return result;
    }


    @Override
    public Page<WoSla> selectwoSlaPageByCondition(Page<WoSla> page, String woSlaName, Integer resAbnormallevelId,
                                           String procDefId, String woSlaStatus, Integer flag, String nowTime,String woSlaDesc) {
        return page.setRecords(woSlaMapper.selectwoSlaPageByCondition(page,woSlaName,resAbnormallevelId,procDefId,woSlaStatus,flag,nowTime,woSlaDesc));
    }


//    @Override
//    public boolean delBatchByIds(List<Integer> appraiseridList) throws BusinessException {
//        for (Integer appraiserid : appraiseridList) {
//            if (!delById(appraiserid)) {
//                throw new BusinessException("评价人删除失败");
//            }
//        }
//        return true;
//    }

    @Override
    public boolean deleteBatchByIds(List<Integer> woslaidList) throws BusinessException {
        for (Integer woslaid: woslaidList) {
            if (!deleteById(woslaid)){
                throw new BusinessException("woslaid删除失败");
            }
        }
        return true;
    }


}
