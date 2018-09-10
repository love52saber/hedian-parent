package com.hedian.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.hedian.entity.MdRes;
import com.hedian.mapper.MdResMapper;
import com.hedian.service.IMdResService;
import com.hedian.util.ComUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 维护域与资源关系 服务实现类
 * </p>
 *
 * @author hedian123
 * @since 2018-08-27
 */
@Service
public class MdResServiceImpl extends ServiceImpl<MdResMapper, MdRes> implements IMdResService {

    @Autowired
    private MdResMapper mdResMapper;

    @Override
    public List<MdRes> findByMap(Map<String, Object> map) {
        return mdResMapper.findByMap(map);
    }

    @Override
    public boolean saveAll(Integer mdId, List<Integer> resIds) {
        boolean result = true;
        if (!ComUtil.isEmpty(resIds)) {
            List<MdRes> mdResList = new ArrayList<>();
            resIds.stream().forEach(resId -> {
                mdResList.add(new MdRes(mdId, resId));
            });
            result = this.insertBatch(mdResList);
        }

        return result;
    }
}
