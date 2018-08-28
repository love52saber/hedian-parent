package com.hedian.service;

import com.hedian.entity.MdRes;
import com.baomidou.mybatisplus.service.IService;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 维护域与资源关系 服务类
 * </p>
 *
 * @author hedian123
 * @since 2018-08-27
 */
public interface IMdResService extends IService<MdRes> {

    List<MdRes> findByMap(Map<String,Object> map);

}
