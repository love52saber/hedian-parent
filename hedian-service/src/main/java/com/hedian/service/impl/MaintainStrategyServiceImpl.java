package com.hedian.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.hedian.base.BusinessException;
import com.hedian.entity.MaintainStrategy;
import com.hedian.entity.MsRes;
import com.hedian.mapper.MaintainStrategyMapper;
import com.hedian.service.IMaintainStrategyService;
import com.hedian.service.IMsResService;
import com.hedian.util.ComUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 维护期策略 服务实现类
 * </p>
 *
 * @author hedian123
 * @since 2018-09-17
 */
@Service
public class MaintainStrategyServiceImpl extends ServiceImpl<MaintainStrategyMapper, MaintainStrategy> implements IMaintainStrategyService {

    @Autowired
    private IMsResService msResService;

    @Override
    public boolean addAllNodes(MaintainStrategy ms) throws Exception {
        boolean result = this.insert(ms);
        if (!result) {
            throw new BusinessException("插入维护期策略失败");
        }
        result = msResService.insertAll(ms.getMsId(), ms.getResIds());
        if (!result) {
            throw new BusinessException("插入维护期设备失败");
        }
        return result;
    }

    @Override
    public boolean updateMs(MaintainStrategy ms) throws Exception {
        MaintainStrategy msCopy = this.selectById(ms.getMsId());
        if (ComUtil.isEmpty(msCopy)) {
            return false;
        }
        boolean result = this.updateById(ms);
        if (!result) {
            throw new BusinessException("更新维护期策略失败");
        }
        result = msResService.delete(new EntityWrapper<MsRes>().eq("ms_id", ms.getMsId()));
        if (!result) {
            throw new BusinessException("删除设备信息失败");
        }
        result = msResService.insertAll(ms.getMsId(), ms.getResIds());
        if (!result) {
            throw new BusinessException("更新设备信息失败");
        }
        return result;
    }
}
