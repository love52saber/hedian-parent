package com.hedian.service;

import com.hedian.entity.MsRes;
import com.baomidou.mybatisplus.service.IService;

import java.util.List;

/**
 * <p>
 * 维护期与资源关系表 服务类
 * </p>
 *
 * @author hedian123
 * @since 2018-09-17
 */
public interface IMsResService extends IService<MsRes> {

    boolean insertAll(Integer msId, List<Integer> resIds);
}
