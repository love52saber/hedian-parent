package com.hedian.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.hedian.entity.MdUser;
import com.hedian.mapper.MdUserMapper;
import com.hedian.service.IMdUserService;
import com.hedian.util.ComUtil;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 维护域和维护人员关系 服务实现类
 * </p>
 *
 * @author hedian123
 * @since 2018-08-27
 */
@Service
public class MdUserServiceImpl extends ServiceImpl<MdUserMapper, MdUser> implements IMdUserService {

    @Override
    public boolean saveAll(Integer mdId, List<Integer> userIds) {
        boolean result = true;
        if (!ComUtil.isEmpty(userIds)) {
            List<MdUser> mdUsers = new ArrayList<>();
            userIds.stream().forEach(userId -> {
                mdUsers.add(new MdUser(mdId, userId, 1));
            });
            result = this.insertBatch(mdUsers);
        }
        return result;
    }
}
