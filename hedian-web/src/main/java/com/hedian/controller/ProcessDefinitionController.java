package com.hedian.controller;


import com.hedian.base.PublicResult;
import com.hedian.base.PublicResultConstant;
import com.hedian.entity.ProcessDefinition;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import org.activiti.bpmn.model.BpmnModel;
import org.activiti.bpmn.model.FlowElement;
import org.activiti.bpmn.model.Process;
import org.activiti.bpmn.model.UserTask;
import org.activiti.engine.RepositoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * 流程定义
 */
@RestController
@RequestMapping("/api/processDefinetion")
@Api(description = "流程定义")
public class ProcessDefinitionController {

    @Autowired
    private RepositoryService repositoryService;

    /**
     * 获取所有的流程定义列表
     */
    @GetMapping("/list")
    public PublicResult getProcessDefinetionList() {
        List<org.activiti.engine.repository.ProcessDefinition> processDefinitions = repositoryService.createProcessDefinitionQuery()
                .orderByProcessDefinitionVersion()
                .asc()
                .list();
        List<ProcessDefinition> processDefinitions1=new ArrayList<ProcessDefinition>();
        if(processDefinitions!=null &&processDefinitions.size()>0){
            processDefinitions.stream().forEach(processDefinition -> {
                processDefinitions1.add(new ProcessDefinition(processDefinition.getId(), processDefinition.getName()));
            });
        }
        return new PublicResult<>(PublicResultConstant.SUCCESS, processDefinitions1);


    }

    /**
     * 获取所有的节点
     */
    @ApiImplicitParams({
            @ApiImplicitParam(name = "processDefinitionId", value ="流程定义Id", dataType = "String", paramType = "query"),
    })
    @GetMapping("/getNodeList")
    public PublicResult getProcessNodeList(@RequestParam(name = "processDefinitionId", defaultValue = "", required = false) String processDefinitionId) {
        Collection<FlowElement> flowElements1=new ArrayList<>();
        //获取bpmn对象
        BpmnModel bpmnModel = repositoryService.getBpmnModel(processDefinitionId);
        if(bpmnModel!=null){
            Collection<FlowElement> flowElements = bpmnModel.getMainProcess().getFlowElements();
            flowElements.stream().forEach(flowElement -> {
              if(flowElement instanceof UserTask){
                  flowElements1.add(flowElement);
              }
          });
        }
        return new PublicResult<>(PublicResultConstant.SUCCESS,flowElements1);
    }

}