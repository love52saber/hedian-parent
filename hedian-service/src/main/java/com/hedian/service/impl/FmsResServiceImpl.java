package com.hedian.service.impl;

import com.hedian.entity.FmsRes;
import com.hedian.mapper.FmsResMapper;
import com.hedian.service.IFmsResService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.hedian.util.ComUtil;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 维护策略关联的资源表 服务实现类
 * </p>
 *
 * @author hedian123
 * @since 2018-09-17
 */
@Service
public class FmsResServiceImpl extends ServiceImpl<FmsResMapper, FmsRes> implements IFmsResService {

    @Override
    public boolean saveAll(Integer fmsId, List<Integer> resIds) {
        boolean result = true;
        if (!ComUtil.isEmpty(resIds)) {
            List<FmsRes> fmsResList = new ArrayList<>();
            resIds.stream().forEach(resId -> {
                fmsResList.add(new FmsRes(fmsId, resId));
            });
            result = this.insertBatch(fmsResList);
        }
        return result;
    }
}
