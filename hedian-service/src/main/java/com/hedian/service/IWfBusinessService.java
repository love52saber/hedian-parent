package com.hedian.service;

import com.baomidou.mybatisplus.service.IService;
import com.hedian.entity.WfBusiness;

import java.util.List;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author hedian123
 * @since 2018-10-18
 */
public interface IWfBusinessService extends IService<WfBusiness> {

    public List<WfBusiness> getAssociatedBusinessListExceptSelf(Long businessId) throws Exception;

}
