package com.hedian.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.hedian.base.BusinessException;
import com.hedian.entity.WfLeave;
import com.hedian.mapper.WfLeaveMapper;
import com.hedian.service.IWfLeaveService;
import com.hedian.util.ComUtil;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.runtime.ProcessInstance;
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

    @Override
    public boolean startLeave(WfLeave wfLeave) throws BusinessException {
        //更新或新增leave数据
        if (this.insertOrUpdate(wfLeave)) {
            throw new BusinessException("请假单信息更新失败");
        }
        //开启工作流,使用leave_id作为business_id关联
        String businessKey = wfLeave.getLeaveId().toString();
        Map<String, Object> map = new HashMap<>();
        map.put("leaveUserId", wfLeave.getLeaveUserId());
        ProcessInstance leaveProcessInstance = runtimeService.startProcessInstanceByKey("myLeave", businessKey, map);
        return ComUtil.isEmpty(leaveProcessInstance)?false:true;
    }

    @Override
    public boolean capitalAudit(WfLeave wfLeave) {

        return false;
    }
}
