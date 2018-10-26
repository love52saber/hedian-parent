package com.hedian.controller;


import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.plugins.Page;
import com.hedian.annotation.CurrentUser;
import com.hedian.annotation.Pass;
import com.hedian.base.PageResult;
import com.hedian.base.PublicResult;
import com.hedian.base.PublicResultConstant;
import com.hedian.entity.SysUser;
import com.hedian.model.WfBusinessModel;
import com.hedian.service.IRuntimeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 初始化流程
 * </p>
 *
 * @author hedian123
 * @since 2018-09-17
 */
@RestController
@RequestMapping("/api/workflow")
@Api(description = "流程管理")
public class InitializeWorkFlowController {

    @Autowired
    private IRuntimeService runtimeService;


    /**
     * 获取所有wfBusinessModel
     */
    @GetMapping("/pageList")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageIndex", value = "第几页", dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "pageSize", value = "每页多少条", dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "wfType", value = "工单类型", dataType = "Integer", paramType = "query"),
            @ApiImplicitParam(name = "wfTitle", value = "工单标题", dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "resAbnormallevelId", value = "告警等级", dataType = "Integer", paramType = "query"),
            @ApiImplicitParam(name = "userName", value = "创建人", dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "currentUserName", value = "处理人", dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "beginTime", value = "创建开始日期", dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "endTime", value = "创建结束日期", dataType = "String", paramType = "query")
    })
    public PublicResult getAllWF(@RequestParam(name = "pageIndex", defaultValue = "1", required = false) Integer pageIndex,
                                 @RequestParam(name = "pageSize", defaultValue = "10", required = false) Integer pageSize,
                                 @RequestParam(name = "wfType", defaultValue = "", required = false) Integer wfType,
                                 @RequestParam(name = "wfTitle", defaultValue = "", required = false) String wfTitle,
                                 @RequestParam(name = "resAbnormallevelId", defaultValue = "", required = false) Integer resAbnormallevelId,
                                 @RequestParam(name = "userName", defaultValue = "", required = false) String userName,
                                 @RequestParam(name = "currentUserName", defaultValue = "", required = false) String currentUserName,
                                 @RequestParam(name = "beginTime", defaultValue = "", required = false) String beginTime,
                                 @RequestParam(name = "endTime", defaultValue = "", required = false) String endTime) {
        Page<WfBusinessModel> wfBusinessModelPage = runtimeService.selectPageByConditionResBase(new Page<>(pageIndex, pageSize), wfType, wfTitle,
                resAbnormallevelId, userName, currentUserName, beginTime, endTime);
        return new PublicResult(PublicResultConstant.SUCCESS, new PageResult<>(wfBusinessModelPage.getTotal(), pageIndex, pageSize, wfBusinessModelPage.getRecords()));
    }

    /**
     * 保存流程审批
     */
    @PostMapping(value = "saveWorkFlow")
    public PublicResult<String> saveWorkFlow(@RequestBody JSONObject requestJson) throws Exception {
        String instanceId = runtimeService.saveWorkFlow(requestJson);
        return new PublicResult<>(PublicResultConstant.SUCCESS, instanceId);
    }

    /**
     * 提交流程审批
     */
    @PostMapping(value = "startWorkFlow")
    public PublicResult<String> startWorkFlow(@RequestBody JSONObject requestJson) throws Exception {
        String instanceId = runtimeService.startWorkFlow(requestJson);
        return new PublicResult<>(PublicResultConstant.SUCCESS, instanceId);
    }
}