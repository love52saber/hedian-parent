package com.hedian.service.impl;

import com.hedian.entity.MdUser;
import com.hedian.entity.MsRes;
import com.hedian.mapper.MsResMapper;
import com.hedian.service.IMsResService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.hedian.util.ComUtil;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 维护期与资源关系表 服务实现类
 * </p>
 *
 * @author hedian123
 * @since 2018-09-17
 */
@Service
public class MsResServiceImpl extends ServiceImpl<MsResMapper, MsRes> implements IMsResService {

    @Override
    public boolean insertAll(Integer msId, List<Integer> resIds) {
        boolean result = false;
        if (!ComUtil.isEmpty(resIds)) {
            List<MsRes> msResList = new ArrayList<>();
            resIds.stream().forEach(resId -> {
                msResList.add(new MsRes(msId, resId));
            });
            result = this.insertBatch(msResList);
        }
        return result;
    }
}
