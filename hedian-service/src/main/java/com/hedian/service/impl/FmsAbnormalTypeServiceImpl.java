package com.hedian.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.hedian.entity.FmsAbnormalType;
import com.hedian.mapper.FmsAbnormalTypeMapper;
import com.hedian.service.IFmsAbnormalTypeService;
import com.hedian.util.ComUtil;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 维护策略关联的故障类型 服务实现类
 * </p>
 *
 * @author hedian123
 * @since 2018-09-17
 */
@Service
public class FmsAbnormalTypeServiceImpl extends ServiceImpl<FmsAbnormalTypeMapper, FmsAbnormalType> implements IFmsAbnormalTypeService {

    @Override
    public boolean saveAll(Integer fmsId, List<Integer> abnormalTypeIds) {
        boolean result = true;
        if (!ComUtil.isEmpty(abnormalTypeIds)) {
            List<FmsAbnormalType> fmsAbnormalTypes = new ArrayList<>();
            abnormalTypeIds.stream().forEach(abnormalTypeId -> {
                fmsAbnormalTypes.add(new FmsAbnormalType(fmsId, abnormalTypeId));
            });
            result = this.insertBatch(fmsAbnormalTypes);
        }
        return result;
    }
}
