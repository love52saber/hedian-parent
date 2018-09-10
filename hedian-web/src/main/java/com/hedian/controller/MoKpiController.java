package com.hedian.controller;


import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.hedian.annotation.ValidationParam;
import com.hedian.base.PublicResult;
import com.hedian.base.PublicResultConstant;
import com.hedian.entity.MoKpi;
import com.hedian.service.IMoKpiService;
import com.hedian.util.ComUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 监控指标 前端控制器
 * </p>
 *
 * @author hedian123
 * @since 2018-08-17
 */
@RestController
@RequestMapping("/api/moKpi")
@Api(description = "监控指标管理")
public class MoKpiController {

    @Autowired
    private IMoKpiService moKpiService;

    /**
     * 获取mokpi
     */
    @GetMapping("/all")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "resStype", value = "子类型", dataType = "String", paramType = "query")
    })
    public PublicResult getAll(@RequestParam(name = "resStype", defaultValue = "", required = false) Integer resStype) {
        List<MoKpi> moKpiList = moKpiService.selectMokpiByStype(resStype);
        return new PublicResult(PublicResultConstant.SUCCESS, moKpiList);
    }

    /**
     * 获取所有mokpi
     */
    @GetMapping("/allMokpi")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "info", value = "名称", dataType = "String", paramType = "query")
    })
    public PublicResult getAllMokpi(@RequestParam(name = "info", defaultValue = "", required = false) Integer info) {
        EntityWrapper ew = new EntityWrapper();
        if (!ComUtil.isEmpty(info)) {
            ew.eq("mo_kpi_name", info);
        }
        List<MoKpi> moKpiList = moKpiService.selectList(ew);
        return new PublicResult(PublicResultConstant.SUCCESS, moKpiList);
    }

    /**
     * 添加mokpi
     *
     * @param
     * @return
     */
    @PostMapping
    public PublicResult<String> addMokpi(@ValidationParam("moKpiName,moKpiKey,unitCh,unitEn,showorder")
                                         @RequestBody JSONObject requestJson) throws Exception {
        //可直接转为java对象,简化操作,不用再set一个个属性
        MoKpi moKpi = requestJson.toJavaObject(MoKpi.class);
        boolean result = moKpiService.insert(moKpi);
        return result ? new PublicResult<>(PublicResultConstant.SUCCESS, null) : new PublicResult<>(PublicResultConstant.ERROR, null);
    }

    /**
     * 修改mokpi信息
     */
    @PutMapping
    public PublicResult<String> updateDept(@ValidationParam("moKpiName,moKpiKey,unitCh,unitEn,showorder,moKpiId")
                                           @RequestBody JSONObject requestJson) throws Exception {
        MoKpi moKpi = requestJson.toJavaObject(MoKpi.class);
        boolean result = moKpiService.updateById(moKpi);
        return !result ? new PublicResult<>(PublicResultConstant.ERROR, null) : new PublicResult<>(PublicResultConstant.SUCCESS, null);
    }

    /**
     * 删除mokpi
     */
    @DeleteMapping(value = "/{mokpiId}")
    public PublicResult deleteDept(@PathVariable("mokpiId") Long mokpiId) {
        if (ComUtil.isEmpty(moKpiService.selectById(mokpiId))) {
            return new PublicResult<>(PublicResultConstant.ERROR, null);
        }
        boolean result = moKpiService.deleteById(mokpiId);
        return result ? new PublicResult<>(PublicResultConstant.SUCCESS, null) : new PublicResult<>(PublicResultConstant.ERROR, null);
    }


}

