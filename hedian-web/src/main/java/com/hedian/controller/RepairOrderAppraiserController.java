package com.hedian.controller;


import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.plugins.Page;
import com.hedian.annotation.CurrentUser;
import com.hedian.annotation.Pass;
import com.hedian.annotation.ValidationParam;
import com.hedian.base.BusinessException;
import com.hedian.base.PageResult;
import com.hedian.base.PublicResult;
import com.hedian.base.PublicResultConstant;
import com.hedian.entity.RepairOrderAppraiser;
import com.hedian.entity.SysUser;
import com.hedian.service.IRepairOrderAppraiserMdService;
import com.hedian.service.IRepairOrderAppraiserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 维修工单评价人管理 前端控制器
 * </p>
 *
 * @author hedian123
 * @since 2018-11-05
 */
@RestController
@RequestMapping("/api/repairOrderAppraiser")
@Api(description = "维修工单评价人管理")
public class RepairOrderAppraiserController {

    @Autowired
    private IRepairOrderAppraiserService iRepairOrderAppraiserService;
    @Autowired
    private IRepairOrderAppraiserMdService iRepairOrderAppraiserMdService;

    @PostMapping("")
    @ApiOperation("添加评价人")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "requestJson", value = "{\"appraisertype\":评价人类型,\"apprasiername\":\"评价人规则名称\"," +
                    "\"grpId\":评价用户组id,\"mdIds\":[管理域id数组]}", required = true, dataType = "string", paramType = "body")
    })
    public PublicResult addAppraiser(@ValidationParam("appraisertype,apprasiername,grpId") @RequestBody JSONObject requestJson) throws BusinessException {
        boolean result = iRepairOrderAppraiserService.addAppraiser(requestJson);
        return result ? new PublicResult(PublicResultConstant.SUCCESS, null) : new PublicResult(PublicResultConstant.ERROR, null);
    }

    @PutMapping("")
    @ApiOperation("修改评价人")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "requestJson", value = "{\"appraiserid\":评价人规则id,\"appraisertype\":评价人类型," +
                    "\"apprasiername\":\"评价人规则名称\",\"grpId\":评价用户组id,\"mdIds\":[管理域id数组]}", required = true, dataType = "string", paramType = "body")
    })
    public PublicResult updateAppraiser(@ValidationParam("appraiserid,appraisertype,apprasiername,grpId") @RequestBody JSONObject requestJson) throws Exception {
        boolean result = iRepairOrderAppraiserService.updateAppraiser(requestJson);
        return result ? new PublicResult(PublicResultConstant.SUCCESS, null) : new PublicResult(PublicResultConstant.ERROR, null);
    }

    @GetMapping("/pageList")
    @ApiOperation("分页查询评价人")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageIndex", value = "当前页数", required = true, dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "pageSize", value = "每页显示页数", required = true, dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "appraisertype", value = "评价人类型", required = false, dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "apprasiername", value = "评价人规则名称", required = false, dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "grpName", value = "组名称", required = false, dataType = "string", paramType = "query")
    })
    public PublicResult pageAppraiser(@RequestParam(name = "pageIndex", defaultValue = "1", required = true) Integer pageIndex,
                                      @RequestParam(name = "pageSize", defaultValue = "10", required = true) Integer pageSize,
                                      @RequestParam(name = "appraisertype", required = false) Integer appraisertype,
                                      @RequestParam(name = "apprasiername", required = false) String apprasiername,
                                      @RequestParam(name = "grpName", required = false) String grpName) throws Exception {
        Page<RepairOrderAppraiser> page = new Page<>(pageIndex, pageSize);
        page = iRepairOrderAppraiserService.findPageByCondition(page, appraisertype, apprasiername, grpName);
        return new PublicResult(PublicResultConstant.SUCCESS, new PageResult<RepairOrderAppraiser>(page.getTotal(), page.getCurrent(), page.getSize(), page.getRecords()));
    }

    @GetMapping("/{appraiserid}")
    @ApiOperation("根据appraiserId查询评价人及相关管理域")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "appraiserid", value = "评价人id", required = true, dataType = "string", paramType = "path")
    })
    public PublicResult getAppraiserById(@PathVariable("appraiserid") @ValidationParam("appraiserid") Integer appraiserid) throws Exception {
        RepairOrderAppraiser repairOrderAppraiser = iRepairOrderAppraiserService.findAppraiserById(appraiserid);
        return repairOrderAppraiser != null ? new PublicResult(PublicResultConstant.SUCCESS, repairOrderAppraiser) : new PublicResult(PublicResultConstant.ERROR, null);
    }

    @DeleteMapping("")
    @ApiOperation("根据appraiserid数组删除评价人")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "jsonObject", value = "{\"appraiserids\":评价人id数组}", required = true, dataType = "string", paramType = "body")
    })
    public PublicResult delBatchById(@ValidationParam("appraiserids") @RequestBody JSONObject jsonObject) throws Exception {
        JSONArray appraiserids = jsonObject.getJSONArray("appraiserids");
        List<Integer> appraiseridList = appraiserids.toJavaList(Integer.class);
        boolean result = iRepairOrderAppraiserService.delBatchByIds(appraiseridList);
        return result ? new PublicResult(PublicResultConstant.SUCCESS, null) : new PublicResult(PublicResultConstant.ERROR, null);
    }

}

