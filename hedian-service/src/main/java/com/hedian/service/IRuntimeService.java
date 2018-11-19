package com.hedian.service;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.plugins.Page;
import com.hedian.base.BusinessException;
import com.hedian.model.AppraiseWfBusinessModel;
import com.hedian.model.WfBusinessModel;

import java.util.List;

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
     * @param wfId
     * @param kexinUserName
     * @param baseUserName
     * @param baseAppraBeginTime
     * @param baseAppraEndTime
     * @param kexinAppraBeginTime
     * @param kexinAppraEndTIime
     * @param baseAppraScore
     * @param kexinAppraScore
     * @param defFlag
     * @return
     */
    Page<AppraiseWfBusinessModel> selectAppraisePageByCondition(Page<AppraiseWfBusinessModel> page, String wfTitle, String wfId,String kexinUserName,
                                                                String baseUserName,String baseAppraBeginTime,String baseAppraEndTime,String kexinAppraBeginTime,
                                                                String kexinAppraEndTIime,Integer baseAppraScore,Integer kexinAppraScore,Integer defFlag);




}

