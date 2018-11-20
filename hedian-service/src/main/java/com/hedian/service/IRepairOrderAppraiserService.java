package com.hedian.service;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.plugins.Page;
import com.hedian.base.BusinessException;
import com.hedian.entity.RepairOrderAppraiser;
import com.baomidou.mybatisplus.service.IService;

import java.util.List;

/**
 * <p>
 * 维修工单评价人管理 服务类
 * </p>
 *
 * @author hedian123
 * @since 2018-11-05
 */
public interface IRepairOrderAppraiserService extends IService<RepairOrderAppraiser> {

    boolean addAppraiser(JSONObject requestJson) throws BusinessException;

    boolean updateAppraiser(JSONObject requestJson) throws BusinessException;

    RepairOrderAppraiser findAppraiserById(Integer appraiserId);

    boolean delById(Integer appraiserid) throws BusinessException;

    boolean delBatchByIds(List<Integer> appraiseridList) throws BusinessException;

    Page<RepairOrderAppraiser> findPageByCondition(Page<RepairOrderAppraiser> page, Integer appraisertype, String apprasiername, String grpName);
}
