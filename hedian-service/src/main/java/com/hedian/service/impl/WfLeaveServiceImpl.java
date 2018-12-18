package com.hedian.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.hedian.base.BusinessException;
import com.hedian.entity.SysUser;
import com.hedian.entity.WfLeave;
import com.hedian.entity.WfLeaveCapitalAudit;
import com.hedian.mapper.WfLeaveMapper;
import com.hedian.service.IWfLeaveCapitalAuditService;
import com.hedian.service.IWfLeaveService;
import com.hedian.util.ComUtil;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author gjyang
 * @since 2018-12-15
 */
@Service
public class WfLeaveServiceImpl extends ServiceImpl<WfLeaveMapper, WfLeave> implements IWfLeaveService {

    @Autowired
    private RuntimeService runtimeService;
    @Autowired
    private TaskService taskService;
    @Autowired
    private IWfLeaveCapitalAuditService iWfLeaveCapitalAuditService;

    @Override
    public boolean startLeave(WfLeave wfLeave,long capitalAuditUserId) throws BusinessException {
        //更新或新增leave数据
        if (this.insertOrUpdate(wfLeave)) {
            throw new BusinessException("请假单信息更新失败");
        }
        //开启工作流,使用leave_id作为business_id关联
        String businessKey = wfLeave.getLeaveId().toString();
        Map<String, Object> map = new HashMap<>();
        map.put("leaveUserId", wfLeave.getLeaveUserId());
        map.put("capitalAuditUserId", capitalAuditUserId);
        ProcessInstance leaveProcessInstance = runtimeService.startProcessInstanceByKey("myLeave", businessKey, map);
        return ComUtil.isEmpty(leaveProcessInstance)?false:true;
    }

    @Override
    public boolean capitalAudit(WfLeaveCapitalAudit wfLeaveCapitalAudit, SysUser currentUser) {
        //工作流操作
        Task task = taskService.createTaskQuery().processInstanceBusinessKey(wfLeaveCapitalAudit.getLeaveId().toString())
                .taskAssignee(currentUser.getUserId().toString()).singleResult();
        Map<String, Object> map = new HashMap<>();
        map.put("capitalAuditResult",wfLeaveCapitalAudit.getLeaveCapitalAuditStatus());
        taskService.complete(task.getId(),map);
        //数据库操作
        return iWfLeaveCapitalAuditService.insert(wfLeaveCapitalAudit);
    }
}
