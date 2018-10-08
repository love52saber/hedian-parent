package com.hedian.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.hedian.entity.FmsAbnormal;
import com.hedian.mapper.FmsAbnormalMapper;
import com.hedian.service.IFmsAbnormalService;
import com.hedian.util.ComUtil;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 维护策略关联的故障表 服务实现类
 * </p>
 *
 * @author hedian123
 * @since 2018-09-17
 */
@Service
public class FmsAbnormalServiceImpl extends ServiceImpl<FmsAbnormalMapper, FmsAbnormal> implements IFmsAbnormalService {

    @Override
    public boolean saveAll(Integer fmsId, List<Integer> abnormalIds) {
        boolean result = true;
        if (!ComUtil.isEmpty(abnormalIds)) {
            List<FmsAbnormal> fmsAbnormals = new ArrayList<>();
            abnormalIds.stream().forEach(abnormalId -> {
                fmsAbnormals.add(new FmsAbnormal(fmsId, abnormalId));
            });
            result = this.insertBatch(fmsAbnormals);
        }
        return result;
    }
}
