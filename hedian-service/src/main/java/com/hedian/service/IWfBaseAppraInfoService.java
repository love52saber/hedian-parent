package com.hedian.service;

import com.alibaba.fastjson.JSONObject;
import com.hedian.base.BusinessException;
import com.hedian.entity.WfBaseAppraInfo;
import com.baomidou.mybatisplus.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author hedian123
 * @since 2018-11-01
 */
public interface IWfBaseAppraInfoService extends IService<WfBaseAppraInfo> {

     boolean save(JSONObject requestJson) throws BusinessException;


}
