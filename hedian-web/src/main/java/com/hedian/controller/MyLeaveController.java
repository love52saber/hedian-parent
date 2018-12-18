package com.hedian.controller;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.hedian.annotation.CurrentUser;
import com.hedian.annotation.ValidationParam;
import com.hedian.base.BusinessException;
import com.hedian.base.PublicResult;
import com.hedian.base.PublicResultConstant;
import com.hedian.entity.SysUser;
import com.hedian.entity.WfLeave;
import com.hedian.entity.WfLeaveCapitalAudit;
import com.hedian.service.IWfLeaveService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author gjyang
 * @since 2018-12-11
 */
@RestController
@Api(description = "请假流程")
@RequestMapping("/api/leave")
public class MyLeaveController {

    @Autowired
    private IWfLeaveService iWfLeaveService;

    @GetMapping("/pageList")
    @ApiOperation("获取请假列表")
    public PublicResult findPageList(@ValidationParam @PathVariable("businessId")Long businessId) throws Exception {
        return null;
    }

    @PostMapping("save")
    @ApiOperation("保存请假条")
    @ApiImplicitParam(name = "requestJson",value = "{\"leaveUserId\":请假人id,\"startTime\":\"开始时间\"," +
        "\"endTime\":\"结束时间\",\"reason\":\"申请理由\"}",paramType = "String",dataType = "body")
    public PublicResult saveLeave(@RequestBody JSONObject requestJson) {
        WfLeave wfLeave = JSON.toJavaObject(requestJson, WfLeave.class);
        return iWfLeaveService.insertOrUpdate(wfLeave)?new PublicResult(PublicResultConstant.SUCCESS,null):
                new PublicResult(PublicResultConstant.ERROR,null);
    }

    @PostMapping("leaveApplication")
    @ApiOperation("申请请假")
    @ApiImplicitParam(name = "requestJson",value = "{\"startTime\":\"开始时间\"," +
            "\"endTime\":\"结束时间\",\"reason\":\"申请理由\",\"applyTime\":\"申请时间\",\"capitalAuditUserId\":\"申请人用户id\"}",
            paramType = "String",dataType = "body")
    public PublicResult startLeave(@RequestBody JSONObject requestJson) throws BusinessException {
        WfLeave wfLeave = JSON.toJavaObject(requestJson, WfLeave.class);
        long capitalAuditUserId = Long.parseLong(requestJson.getString("capitalAuditUserId"));
        return iWfLeaveService.startLeave(wfLeave,capitalAuditUserId)?new PublicResult(PublicResultConstant.SUCCESS,null):
                new PublicResult(PublicResultConstant.ERROR,null);
    }

    @PostMapping("capitalAudit")
    @ApiOperation("组长审批")
    @ApiImplicitParam(name = "requestJson",value = "{\"leaveId\":\"请假单id\",\"leaveCapitalAuditStatus\":\"请假单审核状态\"}",
            paramType = "String",dataType = "body")
    public PublicResult capitalAudit(@RequestBody JSONObject requestJson, @CurrentUser SysUser currentUser) throws BusinessException {
        WfLeaveCapitalAudit wfLeaveCapitalAudit = JSON.toJavaObject(requestJson, WfLeaveCapitalAudit.class);
        return iWfLeaveService.capitalAudit(wfLeaveCapitalAudit,currentUser)?new PublicResult(PublicResultConstant.SUCCESS,null):
                new PublicResult(PublicResultConstant.ERROR,null);
    }

    @PutMapping("leaveApplication")
    @ApiOperation("重新申请请假")
    @ApiImplicitParam(name = "requestJson",value = "{\"startTime\":\"开始时间\"," +
            "\"endTime\":\"结束时间\",\"reason\":\"申请理由\",\"capitalAuditUserId\":\"申请人用户id\"}",
            paramType = "String",dataType = "body")
    public PublicResult reApplyLeave(@RequestBody JSONObject requestJson) throws BusinessException {
        WfLeave wfLeave = JSON.toJavaObject(requestJson, WfLeave.class);
        long capitalAuditUserId = Long.parseLong(requestJson.getString("capitalAuditUserId"));
        return iWfLeaveService.startLeave(wfLeave,capitalAuditUserId)?new PublicResult(PublicResultConstant.SUCCESS,null):
                new PublicResult(PublicResultConstant.ERROR,null);
    }

}
