package com.hedian.service;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.plugins.Page;
import com.hedian.base.BusinessException;
import com.hedian.model.AppraiseWfBusinessModel;
import com.hedian.model.WfBusinessModel;

public interface IRuntimeService {


    Page<WfBusinessModel> selectPageByConditionResBase(Page<WfBusinessModel> page, Integer wfType, String wfTitle, String resAbnormallevelName, String resName,
                                                       String userName, Integer wfStatus, String currentUserName, String beginTime, String endTime, Integer currentUser,
                                                       Integer userId, Integer handleId);

    /**
     * 开启流程
     *
     * @param requestJson
     * @return
     */
    String startWorkFlow(JSONObject requestJson) throws Exception;

    /**
     * 保存流程审批
     *
     * @param requestJson
     * @return
     */
    String saveWorkFlow(JSONObject requestJson) throws BusinessException;

    String handleWorkFlow(JSONObject requestJson) throws BusinessException;

    String rejectWorkFlow(JSONObject requestJson) throws BusinessException;

    String deleteWorkFlow(Long businessId) throws BusinessException;

    /**
     * 流程评价管理
     *
     * @param page
     * @param wfTitle
     * @param resName
     * @param userName
     * @param beginTime
     * @param endTime
     * @return
     */
    Page<AppraiseWfBusinessModel> selectAppraisePageByCondition(Page<AppraiseWfBusinessModel> page, String wfTitle, String resName,
                                                                String userName, String beginTime, String endTime);

}

