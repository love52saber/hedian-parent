package com.hedian.controller;


import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.plugins.Page;
import com.hedian.annotation.Pass;
import com.hedian.annotation.ValidationParam;
import com.hedian.base.BusinessException;
import com.hedian.base.PageResult;
import com.hedian.base.PublicResult;
import com.hedian.base.PublicResultConstant;
import com.hedian.model.AppraiseWfBusinessModel;
import com.hedian.model.WfBusinessModel;
import com.hedian.service.IRuntimeService;
import com.hedian.service.ISysFileService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;

/**
 * <p>
 * 初始化流程
 * </p>
 *
 * @author hedian123
 * @since 2018-09-17
 */
@RestController
@RequestMapping("/api/workflow/appraise")
@Api(description = "流程评价管理")
public class AppraiseWorkFlowController {

    @Autowired
    private IRuntimeService runtimeService;



    /**
     * 获取所有wfBusinessModel
     */
    @GetMapping("/pageList")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageIndex", value = "第几页", dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "pageSize", value = "每页多少条", dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "wfTitle", value = "工单标题", dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "currentUserName", value = "处理人", dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "beginTime", value = "创建开始日期", dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "endTime", value = "创建结束日期", dataType = "String", paramType = "query"),
    })
    @Pass
    public PublicResult getAllWF(@RequestParam(name = "pageIndex", defaultValue = "1", required = false) Integer pageIndex,
                                 @RequestParam(name = "pageSize", defaultValue = "10", required = false) Integer pageSize,
                                 @RequestParam(name = "wfTitle", defaultValue = "", required = false) String wfTitle,
                                 @RequestParam(name = "resName", defaultValue = "", required = false) String resName,
                                 @RequestParam(name = "userName", defaultValue = "", required = false) String userName,
                                 @RequestParam(name = "beginTime", defaultValue = "", required = false) String beginTime,
                                 @RequestParam(name = "endTime", defaultValue = "", required = false) String endTime) {
        Page<AppraiseWfBusinessModel> appraiseWfBusinessModelPage = runtimeService.selectAppraisePageByCondition(new Page<>(pageIndex, pageSize), wfTitle,
                resName,userName,beginTime, endTime);
        return new PublicResult(PublicResultConstant.SUCCESS, new PageResult<>(appraiseWfBusinessModelPage.getTotal(), pageIndex, pageSize, appraiseWfBusinessModelPage.getRecords()));
    }


}
