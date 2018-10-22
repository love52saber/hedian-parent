package com.hedian.service;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.plugins.Page;
import com.hedian.base.BusinessException;
import com.hedian.model.WfBusinessModel;

public interface IRuntimeService {


    Page<WfBusinessModel> selectPageByConditionResBase(Page<WfBusinessModel> page, Integer wfType, String wfTitle, Integer resAbnormallevelId,
                                                       String userName, String currentUserName, String beginTime, String endTime);

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
}
