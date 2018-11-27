package com.hedian.controller;


import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.plugins.Page;
import com.hedian.annotation.ValidationParam;
import com.hedian.base.BusinessException;
import com.hedian.base.PageResult;
import com.hedian.base.PublicResult;
import com.hedian.base.PublicResultConstant;
import com.hedian.entity.MoThreshold;
import com.hedian.model.MoThresholdModel;
import com.hedian.service.IMoThresholdService;
import com.hedian.util.ComUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

/**
 * <p>
 * 监控阈值配置 前端控制器
 * </p>
 *
 * @author hedian123
 * @since 2018-08-17
 */
@RestController
@RequestMapping("/api/moThreshold")
@Api(description = "监控阈值配置管理")
public class MoThresholdController {

    @Autowired
    private IMoThresholdService moThresholdService;

    /**
     * 获取所有的moThreshold
     */
    @GetMapping("/pageList")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageIndex", value = "第几页", dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "pageSize", value = "每页多少条", dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "moKpiName", value = "指标名称", dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "moAbnormalName", value = "告警名称", dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "resStypeName", value = "资源子类型", dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "resMtypeName", value = "资源主类型", dataType = "String", paramType = "query")
    })
    public PublicResult getAllMoThread(@RequestParam(name = "pageIndex", defaultValue = "1", required = false) Integer pageIndex,
                                       @RequestParam(name = "pageSize", defaultValue = "10", required = false) Integer pageSize,
                                       @RequestParam(name = "moKpiName", defaultValue = "", required = false) String moKpiName,
                                       @RequestParam(name = "moAbnormalName", defaultValue = "", required = false) String moAbnormalName,
                                       @RequestParam(name = "resStypeName", defaultValue = "", required = false) String resStypeName,
                                       @RequestParam(name = "resMtypeName", defaultValue = "", required = false) String resMtypeName) {
        Page<MoThresholdModel> moThresholdModelPage = moThresholdService.selectPageMoThreshold(new Page<>(pageIndex, pageSize), moKpiName, moAbnormalName, resStypeName, resMtypeName);
        return new PublicResult(PublicResultConstant.SUCCESS, new PageResult<>(moThresholdModelPage.getTotal(), pageIndex, pageSize, moThresholdModelPage.getRecords()));
    }


    /**
     * 添加moThreshold
     *
     * @param
     * @return
     */
    @PostMapping
    public PublicResult<String> addMokpi(@ValidationParam("moThType,resStypeId,moKpiId,moAbnormalId")
                                         @RequestBody JSONObject requestJson) throws Exception {
        //可直接转为java对象,简化操作,不用再set一个个属性
        MoThreshold moThreshold = requestJson.toJavaObject(MoThreshold.class);
        this.checkParam(moThreshold);
        boolean result = moThresholdService.insert(moThreshold);
        return result ? new PublicResult<>(PublicResultConstant.SUCCESS, null) : new PublicResult<>(PublicResultConstant.ERROR, null);
    }

    /**
     * 新增或删除阈值参数校验
     * @param moThreshold
     */
    private void checkParam(MoThreshold moThreshold) throws BusinessException {
        Integer moThType = moThreshold.getMoThType();
        BigDecimal moThUp = moThreshold.getMoThUp();
        BigDecimal moThDown = moThreshold.getMoThDown();
        BigDecimal moThBase = moThreshold.getMoThBase();
        String moThValue = moThreshold.getMoThValue();
        switch (moThType) {
            case 3:
                if (ComUtil.isEmpty(moThBase)) {
                    throw new BusinessException("基准值不能为空");
                }
            case 1:
                if (ComUtil.isEmpty(moThUp) && ComUtil.isEmpty(moThDown)) {
                    throw new BusinessException("上下限不能同时为空");
                }
                if (!ComUtil.isEmpty(moThUp) && !ComUtil.isEmpty(moThDown)) {
                    throw new BusinessException("上下限不能同时存在");
                }
                break;
            case 4:
                if (ComUtil.isEmpty(moThBase)) {
                    throw new BusinessException("基准值不能为空");
                }
            case 2:
                if (ComUtil.isEmpty(moThUp) || ComUtil.isEmpty(moThDown)) {
                    throw new BusinessException("上限或下限为空");
                }
                break;
            case 5:
                if (ComUtil.isEmpty(moThValue)) {
                    throw new BusinessException("对比值不能为空");
                }
        }
    }

    /**
     * 修改moThreshold信息
     */
    @PutMapping
    public PublicResult<String> updateMokpi(@ValidationParam("moThType,moThId,resStypeId,moKpiId,moAbnormalId,showorder")
                                            @RequestBody JSONObject requestJson) throws Exception {
        MoThreshold moThreshold = requestJson.toJavaObject(MoThreshold.class);
        this.checkParam(moThreshold);
        boolean result = moThresholdService.updateById(moThreshold);
        return !result ? new PublicResult<>(PublicResultConstant.ERROR, null) : new PublicResult<>(PublicResultConstant.SUCCESS, null);
    }

    /**
     * 删除moThreshold
     */
    @DeleteMapping(value = "/{moThId}")
    public PublicResult deleteMokpi(@PathVariable("moThId") Integer moThId) {
        if (ComUtil.isEmpty(moThresholdService.selectById(moThId))) {
            return new PublicResult<>(PublicResultConstant.ERROR, null);
        }
        boolean result = moThresholdService.deleteById(moThId);
        return result ? new PublicResult<>(PublicResultConstant.SUCCESS, null) : new PublicResult<>(PublicResultConstant.ERROR, null);
    }


}

