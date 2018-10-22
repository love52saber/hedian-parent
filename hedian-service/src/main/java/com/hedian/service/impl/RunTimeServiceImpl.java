package com.hedian.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.plugins.Page;
import com.hedian.base.BusinessException;
import com.hedian.entity.WfBusiness;
import com.hedian.mapper.WfBusinessMapper;
import com.hedian.model.WfBusinessModel;
import com.hedian.service.IRuntimeService;
import com.hedian.service.IWfBusinessService;
import com.hedian.util.ComUtil;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.activiti.engine.task.TaskQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
public class RunTimeServiceImpl implements IRuntimeService {

    @Value("${workflow.workorderkey}")
    private String workOrderKey;
    @Value("${workflow.uservariable}")
    private String uservariable;

    @Autowired
    private RuntimeService runtimeService;

    @Autowired
    private TaskService taskService;

    @Autowired
    private IWfBusinessService wfBusinessService;

    @Autowired
    private WfBusinessMapper wfBusinessMapper;


    @Override
    public Page<WfBusinessModel> selectPageByConditionResBase(Page<WfBusinessModel> page,Integer wfType, String wfTitle, Integer resAbnormallevelId,
                                                              String userName, String currentUserName, String beginTime, String endTime) {
        List<WfBusinessModel> wfBusinessModels = wfBusinessMapper.selectPageByCondition(page,wfType,wfTitle,resAbnormallevelId,userName,currentUserName,
                beginTime,endTime);
        wfBusinessModels.stream().forEach(wfBusinessModel -> {
            TaskQuery taskQuery = taskService.createTaskQuery();
            taskQuery.processInstanceId(wfBusinessModel.getWfId());
            taskQuery.taskAssignee(String.valueOf(wfBusinessModel.getCurrentUser()));
            Task task = taskQuery.singleResult();
            if(!ComUtil.isEmpty(task)){
                wfBusinessModel.setTaskId(task.getId());
            }
        });
        return page.setRecords(wfBusinessModels);
    }

    @Override
    public String startWorkFlow(JSONObject requestJson) throws Exception {
        //业务数据导入
        Map<String, Object> variables = new HashMap<>();
        variables.put(uservariable, requestJson.getLongValue(uservariable));
        ProcessInstance processInstance = runtimeService.startProcessInstanceByKey(workOrderKey, variables);
        WfBusiness wfBusiness = requestJson.toJavaObject(WfBusiness.class);
        wfBusiness.setWfId(processInstance.getProcessInstanceId());
        wfBusiness.setWfType(1);
        boolean result = wfBusinessService.insert(wfBusiness);
        if(!result){
            throw new BusinessException("插入信息失败");
        }
        //查询代办任务
        TaskQuery taskQuery = taskService.createTaskQuery();
        taskQuery.processInstanceId(processInstance.getProcessInstanceId());
        taskQuery.taskAssignee(String.valueOf(requestJson.getLongValue(uservariable)));
        Task task = taskQuery.singleResult();
        if (!ComUtil.isEmpty(task)) {
            variables.clear();
            variables.put(uservariable, requestJson.getLongValue("currentUserId"));
            taskService.complete(task.getId(), variables);
        }
        return processInstance.getProcessInstanceId();
    }

    @Override
    public String saveWorkFlow(JSONObject requestJson) throws BusinessException {
        Map<String, Object> variables = new HashMap<>();
        variables.put(uservariable, requestJson.getLongValue(uservariable));
        ProcessInstance processInstance = runtimeService.startProcessInstanceByKey(workOrderKey, variables);
        WfBusiness wfBusiness = requestJson.toJavaObject(WfBusiness.class);
        wfBusiness.setWfId(processInstance.getProcessInstanceId());
        wfBusiness.setWfType(1);
        //设置当前节点人
        wfBusiness.setCurrentUser(wfBusiness.getUserId());
        boolean result = wfBusinessService.insert(wfBusiness);
        if(!result){
            throw new BusinessException("插入信息失败");
        }
        return processInstance.getProcessInstanceId();
    }
}
