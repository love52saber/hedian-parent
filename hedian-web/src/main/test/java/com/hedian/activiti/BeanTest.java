package com.hedian.activiti;

import org.activiti.engine.HistoryService;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.history.HistoricTaskInstance;
import org.activiti.engine.history.HistoricTaskInstanceQuery;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.activiti.engine.task.TaskQuery;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BeanTest {
    @Autowired
    private RepositoryService repositoryService;
    @Autowired
    private RuntimeService runtimeService;
    @Autowired
    private TaskService taskService;
    @Autowired
    private HistoryService historyService;

    @Test
    public void testActivit() throws Exception {
        long count = repositoryService.createDeploymentQuery().count();
        System.out.println(count);
    }

    @Test
    public void startInstance() throws Exception {
        Map<String, Object> map = new LinkedHashMap<String, Object>();
        map.put("userId", "why");
        ProcessInstance service_work = runtimeService.startProcessInstanceByKey("Service_Work", map);
        System.out.println(service_work);
    }


    @Test
    public void queryTask() throws Exception {
        // 流程定义key（流程定义的标识 ）
        String processDefinitionKey = "Service_Work";// 采购流程 标识
        // 任务 负责人
        String assignee = "1";
        // 创建查询对象
        TaskQuery taskQuery = taskService.createTaskQuery();

        // 设置查询条件
//        taskQuery.taskCandidateOrAssigned(assignee);

        // 指定 流程定义key，只查询某个流程的任务
        taskQuery.processInstanceId("40041");
        taskQuery.taskAssignee(assignee);

        // 获取查询列表
        Task task = taskQuery.singleResult();


        System.out.println("任务 id：" + task.getId());
        System.out.println("任务负责人：" + task.getAssignee());
        System.out.println("任务名称：" + task.getName());

//        Map<String, Object> map = new LinkedHashMap<String, Object>();
//
//        map.put("Reviewer", "1,2,3");
//        taskService.complete("7508", map);
    }

    @Test
    public void queryHistory() throws Exception {
        HistoricTaskInstanceQuery historicTaskInstanceQuery = historyService.createHistoricTaskInstanceQuery();
        historicTaskInstanceQuery.processInstanceId("5001");
        List<HistoricTaskInstance> list = historicTaskInstanceQuery.list();
        for (HistoricTaskInstance historicDetail : list) {
            System.out.println(historicDetail);
        }

    }


}
