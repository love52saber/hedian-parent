package com.hedian.controller;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.hedian.annotation.ValidationParam;
import com.hedian.base.PublicResult;
import com.hedian.base.PublicResultConstant;
import com.hedian.entity.WfLeave;
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

    @PostMapping("")
    @ApiOperation("保存请假条")
    @ApiImplicitParam(name = "requestJson",value = "{\"leave_user_id\":请假人id,\"start_time\":\"开始时间\"," +
            "\"end_time\":\"结束时间\",\"reason\":\"申请理由\",\"apply_time\":\"申请时间\"}",paramType = "String",dataType = "body")
    public PublicResult saveLeave(@RequestBody JSONObject requestJson) {
        WfLeave wfLeave = JSON.toJavaObject(requestJson, WfLeave.class);
        return iWfLeaveService.insert(wfLeave)?new PublicResult(PublicResultConstant.SUCCESS,null):
                new PublicResult(PublicResultConstant.ERROR,null);
    }




}
