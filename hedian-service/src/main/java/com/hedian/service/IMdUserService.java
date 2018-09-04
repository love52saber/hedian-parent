package com.hedian.service;

import com.hedian.entity.MdUser;
import com.baomidou.mybatisplus.service.IService;

import java.util.List;

/**
 * <p>
 * 维护域和维护人员关系 服务类
 * </p>
 *
 * @author hedian123
 * @since 2018-08-27
 */
public interface IMdUserService extends IService<MdUser> {

    boolean saveAll(Integer mdId, List<Integer> userIds);
}
