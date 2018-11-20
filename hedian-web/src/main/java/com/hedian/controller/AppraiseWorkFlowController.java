package com.hedian.controller;


import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.plugins.Page;
import com.hedian.annotation.Pass;
import com.hedian.annotation.ValidationParam;
import com.hedian.base.BusinessException;
import com.hedian.base.PageResult;
import com.hedian.base.PublicResult;
import com.hedian.base.PublicResultConstant;
import com.hedian.entity.WfBaseAppraInfo;
import com.hedian.entity.WfBusiness;
import com.hedian.entity.WfKexinAppraInfo;
import com.hedian.model.AppraiseWfBusinessModel;
import com.hedian.model.WfBaseAppraInfoModel;
import com.hedian.model.WfBusinessModel;
import com.hedian.service.IRuntimeService;
import com.hedian.service.ISysFileService;
import com.hedian.service.IWfBaseAppraInfoService;
import com.hedian.service.IWfKexinAppraInfoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.expression.spel.ast.NullLiteral;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

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

    @Autowired
    private IWfBaseAppraInfoService iWfBaseAppraInfoService;


    /**
     * 获取所有wfBusinessModel
     */
    @GetMapping("/pageList")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageIndex", value = "第几页", dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "pageSize", value = "每页多少条", dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "wfTitle", value = "工单标题", dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "wfId",value="工单编号",dataType = "String",paramType = "query"),
            @ApiImplicitParam(name = "baseUserName", value = "基层评价人", dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "kexinUserName", value = "科信评价人", dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "baseAppraBeginTime", value = "基层评价开始时间", dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "baseAppraEndTime", value = "基层评价结束时间", dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "kexinAppraBeginTime", value = "科信评价开始时间", dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "kexinAppraEndTime", value = "科信评价结束时间", dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "baseAppraScore", value = "基层评分", dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "kexinAppraScore", value = "科信评分", dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "defFlag", value = "0：大于，1：小于，2：等于 3：大于等于 4：小于等于", dataType = "String", paramType = "query"),

    })
    @Pass
    public PublicResult getAllWF(@RequestParam(name = "pageIndex", defaultValue = "1", required = false) Integer pageIndex,
                                 @RequestParam(name = "pageSize", defaultValue = "10", required = false) Integer pageSize,
                                 @RequestParam(name = "wfTitle", defaultValue = "", required = false) String wfTitle,
                                 @RequestParam(name = "wfId", defaultValue = "", required = false) String wfId,
                                 @RequestParam(name = "kexinUserName", defaultValue = "", required = false) String kexinUserName,
                                 @RequestParam(name = "baseUserName", defaultValue = "", required = false) String baseUserName,
                                 @RequestParam(name = "defFlag", defaultValue = "", required = false) Integer defFlag,
                                 @RequestParam(name = "baseAppraBeginTime", defaultValue = "", required = false) String baseAppraBeginTime,
                                 @RequestParam(name = "baseAppraScore", defaultValue = "", required = false) Integer baseAppraScore,
                                 @RequestParam(name = "kexinAppraScore", defaultValue = "", required = false) Integer kexinAppraScore,
                                 @RequestParam(name = "baseAppraEndTime", defaultValue = "", required = false) String baseAppraEndTime,
                                 @RequestParam(name = "kexinAppraEndTime", defaultValue = "", required = false) String kexinAppraEndTime,
                                 @RequestParam(name = "kexinAppraBeginTime", defaultValue = "", required = false) String kexinAppraBeginTime){
        Page<AppraiseWfBusinessModel> appraiseWfBusinessModelPage = runtimeService.selectAppraisePageByCondition(new Page<>(pageIndex, pageSize), wfTitle,
           wfId,kexinUserName,baseUserName,baseAppraBeginTime, baseAppraEndTime,kexinAppraBeginTime,kexinAppraEndTime,baseAppraScore,kexinAppraScore,defFlag);
        return new PublicResult(PublicResultConstant.SUCCESS, new PageResult<>(appraiseWfBusinessModelPage.getTotal(), pageIndex, pageSize, appraiseWfBusinessModelPage.getRecords()));

    }


    /**
     * 更新工单信息
     */
    @PutMapping("/updateAppra")
    @ApiImplicitParams({
            @ApiImplicitParam(name="requestJson",value ="{\\\"businessId\\\":\\\"XXX\\\"," +
                    "\\\"baseAppraId\\\":\\\"基层评价id\\\"," +
                    "\\\"kexinAppraId\\\":\\\"科信评价id\\\"" +
                    "\\\"baseAppraScore\\\":\\\"基层评价分\\\" ," +
                    "\\\"kexinAppraScore\\\":\\\"科信评价分\\\"，" +
                    "\\\"baseAppraInfo\\\":\\\"基层评价信息\\\"，" +
                    "\\\"kexinAppraInfo\\\":\\\"科信评价信息\\\" " +
                    "}",dataType = "String",paramType = "body",required = true),



    })
    @Pass
    public PublicResult updateBaseAppra(@ValidationParam("businessId,baseAppraScore,kexinAppraScore") @RequestBody JSONObject requestJson ) throws Exception{
        boolean result = iWfBaseAppraInfoService.save(requestJson);
        return  result? new PublicResult<>(PublicResultConstant.SUCCESS,null):new PublicResult<>(PublicResultConstant.SUCCESS.ERROR,null);
    }


}
