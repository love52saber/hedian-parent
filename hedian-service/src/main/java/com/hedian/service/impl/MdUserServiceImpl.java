package com.hedian.service.impl;

import com.hedian.entity.MdUser;
import com.hedian.mapper.MdUserMapper;
import com.hedian.service.IMdUserService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

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

}
