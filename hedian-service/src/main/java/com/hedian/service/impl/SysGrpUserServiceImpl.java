package com.hedian.service.impl;

import com.hedian.entity.SysGrpUser;
import com.hedian.entity.SysUser;
import com.hedian.mapper.SysGrpUserMapper;
import com.hedian.service.ISysGrpUserService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.hedian.util.ComUtil;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 用户组包含用户表 服务实现类
 * </p>
 *
 * @author hedian123
 * @since 2018-08-20
 */
@Service
public class SysGrpUserServiceImpl extends ServiceImpl<SysGrpUserMapper, SysGrpUser> implements ISysGrpUserService {


    @Override
    public boolean saveAll(Integer grpId, List<Long> userIds) {
        boolean result = true;
        if (!ComUtil.isEmpty(userIds)) {
            List<SysGrpUser> userIdList = new ArrayList<>();
            for (Long userId : userIds) {
                userIdList.add(new SysGrpUser(grpId, userId, 1));
            }
            result = this.insertBatch(userIdList);
        }
        return result;
    }
}
