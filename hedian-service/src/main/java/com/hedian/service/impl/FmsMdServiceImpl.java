package com.hedian.service.impl;

import com.hedian.entity.FmsMd;
import com.hedian.mapper.FmsMdMapper;
import com.hedian.service.IFmsMdService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.hedian.util.ComUtil;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 维护策略关联的管理域表 服务实现类
 * </p>
 *
 * @author hedian123
 * @since 2018-09-17
 */
@Service
public class FmsMdServiceImpl extends ServiceImpl<FmsMdMapper, FmsMd> implements IFmsMdService {

    @Override
    public boolean saveAll(Integer fmsId, List<Integer> mdIds) {
        boolean result = true;
        if (!ComUtil.isEmpty(mdIds)) {
            List<FmsMd> fmsMds = new ArrayList<>();
            mdIds.stream().forEach(mdId -> {
                fmsMds.add(new FmsMd(fmsId, mdId));
            });
            result = this.insertBatch(fmsMds);
        }
        return result;
    }
}
