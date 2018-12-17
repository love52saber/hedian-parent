package com.hedian.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;
import com.hedian.base.BusinessException;
import com.hedian.entity.Fms;
import com.hedian.model.FmsModel;

import java.util.List;

/**
 * <p>
 * 故障维修策略 服务类
 * </p>
 *
 * @author hedian123
 * @since 2018-09-17
 */
public interface IFmsService extends IService<Fms> {

    Page<FmsModel> selectPageByCondition(Page<FmsModel> page, String fmsName, String deptName, String userName,
                                         Integer dispatchflag, String grpName, Integer fmsStatus);

    boolean addAllNodes(Fms fms) throws Exception;

    boolean updateFms(Fms fms) throws Exception;

    List<Fms> selectByCondition(Integer resId, Integer abnormalTypeId, Integer moAbnormalId, Integer mdId) throws Exception;
}
