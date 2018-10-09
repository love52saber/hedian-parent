package com.hedian.controller;


import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.hedian.annotation.ValidationParam;
import com.hedian.base.PageResult;
import com.hedian.base.PublicResult;
import com.hedian.base.PublicResultConstant;
import com.hedian.entity.*;
import com.hedian.model.FmsModel;
import com.hedian.service.*;
import com.hedian.util.ComUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 故障维修策略 前端控制器
 * </p>
 *
 * @author hedian123
 * @since 2018-09-17
 */
@RestController
@RequestMapping("/api/fms")
@Api(description = "故障维修策略管理")
public class FmsController {
    @Autowired
    private IFmsService fmsService;
    @Autowired
    private IFmsMdService fmsMdService;
    @Autowired
    private IFmsResService fmsResService;
    @Autowired
    private IFmsAbnormalService fmsAbnormalService;
    @Autowired
    private IFmsAbnormalTypeService fmsAbnormalTypeService;


    /**
     * 故障维护策略列表
     */
    @GetMapping("/pageList")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageIndex", value = "第几页", dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "pageSize", value = "每页多少条", dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "fmsName", value = "名称", dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "deptName", value = "维护单位", dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "userName", value = "维护人", dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "fmsStatus", value = "状态", dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "dispatchflag", value = "自动派单", dataType = "String", paramType = "query")
    })
    public PublicResult getPageList(@RequestParam(name = "pageIndex", defaultValue = "1", required = false) Integer pageIndex,
                                    @RequestParam(name = "pageSize", defaultValue = "10", required = false) Integer pageSize,
                                    @RequestParam(name = "fmsName", defaultValue = "", required = false) String fmsName,
                                    @RequestParam(name = "deptName", defaultValue = "", required = false) String deptName,
                                    @RequestParam(name = "userName", defaultValue = "", required = false) String userName,
                                    @RequestParam(name = "grpName", defaultValue = "", required = false) String grpName,
                                    @RequestParam(name = "fmsStatus", defaultValue = "", required = false) Integer fmsStatus,
                                    @RequestParam(name = "dispatchflag", defaultValue = "", required = false) Integer dispatchflag) {
        Page<FmsModel> fmsModelPage = fmsService.selectPageByCondition(new Page<>(pageIndex, pageSize), fmsName, deptName, userName, dispatchflag, grpName, fmsStatus);
        fmsModelPage.getRecords().stream().forEach(fmsModel -> {
            List<FmsAbnormal> fmsAbnormals = fmsAbnormalService.selectList(new EntityWrapper<FmsAbnormal>().eq("fms_id", fmsModel.getFmsId()));
            fmsModel.setAbnormalIds(fmsAbnormals.stream().map(FmsAbnormal::getMoAbnormalId).collect(Collectors.toList()));
            List<FmsAbnormalType> fmsAbnormalTypes = fmsAbnormalTypeService.selectList(new EntityWrapper<FmsAbnormalType>().eq("fms_id", fmsModel.getFmsId()));
            fmsModel.setAbnormalTypeIds(fmsAbnormalTypes.stream().map(FmsAbnormalType::getAbnormalTypeId).collect(Collectors.toList()));
            List<FmsMd> fmsMds = fmsMdService.selectList(new EntityWrapper<FmsMd>().eq("fms_id", fmsModel.getFmsId()));
            fmsModel.setMdIds(fmsMds.stream().map(FmsMd::getMdId).collect(Collectors.toList()));
            List<FmsRes> fmsResList = fmsResService.selectList(new EntityWrapper<FmsRes>().eq("fms_id", fmsModel.getFmsId()));
            fmsModel.setResIds(fmsResList.stream().map(FmsRes::getResId).collect(Collectors.toList()));
        });
        return new PublicResult(PublicResultConstant.SUCCESS, new PageResult<>(fmsModelPage.getTotal(), pageIndex, pageSize, fmsModelPage.getRecords()));
    }

    /**
     * 添加故障维护策略
     *
     * @param
     * @return/
     */
    @PostMapping
    public PublicResult<String> addFms(@ValidationParam("fmsName,dispatchflag,grpId,fmsStatus,beginTime,endTime")
                                       @RequestBody JSONObject requestJson) throws Exception {
        //可直接转为java对象,简化操作,不用再set一个个属性
        Fms fms = requestJson.toJavaObject(Fms.class);
        boolean result = fmsService.addAllNodes(fms);
        return result ? new PublicResult<>(PublicResultConstant.SUCCESS, null) : new PublicResult<>(PublicResultConstant.ERROR, null);
    }

    /**
     * 修改故障维护策略
     */
    @PutMapping
    public PublicResult<String> updateMs(@ValidationParam("msId,msName,msType,beginTime,endTime,msStatus")
                                         @RequestBody JSONObject requestJson) throws Exception {
        //可直接转为java对象,简化操作,不用再set一个个属性
        Fms fms = requestJson.toJavaObject(Fms.class);
        boolean result = fmsService.updateFms(fms);
        return result ? new PublicResult<>(PublicResultConstant.SUCCESS, null) : new PublicResult<>(PublicResultConstant.ERROR, null);
    }

    /**
     * 删除故障维护策略
     */
    @DeleteMapping(value = "/{fmsId}")
    public PublicResult deleteMs(@PathVariable("fmsId") Integer fmsId) {
        if (ComUtil.isEmpty(fmsService.selectById(fmsId))) {
            return new PublicResult<>(PublicResultConstant.ERROR, null);
        }
        boolean result = fmsService.deleteById(fmsId);
        return result ? new PublicResult<>(PublicResultConstant.SUCCESS, null) : new PublicResult<>(PublicResultConstant.ERROR, null);
    }


}

