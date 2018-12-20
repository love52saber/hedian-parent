package com.hedian.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.hedian.base.BusinessException;
import com.hedian.entity.WoSla;
import com.hedian.mapper.WoSlaMapper;
import com.hedian.service.IWoSlaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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

    @Value("${workflow.workorderkey}")
    private String workOrderKey;

    @Override
    public boolean addSla(WoSla woSla) throws Exception {
        boolean result = iWoSlaService.insert(woSla);
        if(!result){
            throw new BusinessException("插入sla数据失败");
        }
        return result;
    }


    @Override
    public Page<WoSla> selectwoSlaPageByCondition(Page<WoSla> page, Integer woSlaId, String woSlaName, Integer resAbnormallevelId,
                                                  String procDefId, String woSlaStatus, Integer flag, String woSlaDesc) {
        return page.setRecords(woSlaMapper.selectwoSlaPageByCondition(page,woSlaId,woSlaName,resAbnormallevelId,procDefId,woSlaStatus,flag,woSlaDesc));
    }

    @Override
    public boolean deleteBatchByIds(List<Integer> woslaidList) throws BusinessException {

        List<WoSla> woSlaList = new ArrayList<>();
        woslaidList.stream().forEach(id -> {
            WoSla woSla = new WoSla();
            woSla.setWoSlaId(id);
            woSla.setUseflag(0);
            woSlaList.add(woSla);
        });
        return this.updateBatchById(woSlaList);
    }

    @Override
    public WoSla getSingleWoSla() {
        return iWoSlaService.selectOne(new EntityWrapper<WoSla>().eq("proc_def_id", workOrderKey));
    }

}
