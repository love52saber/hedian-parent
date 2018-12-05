package com.hedian.controller;


import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.hedian.annotation.ValidationParam;
import com.hedian.base.PageResult;
import com.hedian.base.PublicResult;
import com.hedian.base.PublicResultConstant;
import com.hedian.entity.MoKpi;
import com.hedian.model.MokpiModel;
import com.hedian.service.IMoKpiService;
import com.hedian.util.ComUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import org.apache.shiro.authz.annotation.RequiresPermissions;
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
    @GetMapping("/pageList")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageIndex", value = "第几页", dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "pageSize", value = "每页多少条", dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "info", value = "名称", dataType = "String", paramType = "query")
    })
    public PublicResult getAllMokpi(@RequestParam(name = "pageIndex", defaultValue = "1", required = false) Integer pageIndex,
                                    @RequestParam(name = "pageSize", defaultValue = "10", required = false) Integer pageSize,
                                    @RequestParam(name = "info", defaultValue = "", required = false) String info) {
        EntityWrapper ew = new EntityWrapper();
        if (!ComUtil.isEmpty(info)) {
            ew.like("mo_kpi_name", info);
        }
        Page<MoKpi> moKpiPage = moKpiService.selectPage(new Page<>(pageIndex, pageSize), ew);
        return new PublicResult(PublicResultConstant.SUCCESS, new PageResult<>(moKpiPage.getTotal(), pageIndex, pageSize, moKpiPage.getRecords()));
    }

    /**
     * 获取所有mokpi
     */
    @GetMapping("/allMokpiObject")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "resStype", value = "子类型", dataType = "String", paramType = "query")
    })
    public PublicResult allMokpiObject(@RequestParam(name = "resStype", defaultValue = "", required = false) Integer resStype) {
        List<MokpiModel> moKpiList = moKpiService.selectMokpiObject(resStype);
        return new PublicResult(PublicResultConstant.SUCCESS, moKpiList);
    }

    /**
     * 添加mokpi
     *
     * @param
     * @return
     */
    @PostMapping
    @RequiresPermissions("mokpi:add")
    public PublicResult<String> addMokpi(@ValidationParam("moKpiName,moKpiKey")
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
    @RequiresPermissions("mokpi:update")
    public PublicResult<String> updateMokpi(@ValidationParam("moKpiName,moKpiKey,showorder,moKpiId")
                                           @RequestBody JSONObject requestJson) throws Exception {
        MoKpi moKpi = requestJson.toJavaObject(MoKpi.class);
        boolean result = moKpiService.updateById(moKpi);
        return !result ? new PublicResult<>(PublicResultConstant.ERROR, null) : new PublicResult<>(PublicResultConstant.SUCCESS, null);
    }

    /**
     * 删除mokpi
     */
    @DeleteMapping(value = "/{mokpiId}")
    @RequiresPermissions("mokpi:delete")
    public PublicResult deleteMokpi(@PathVariable("mokpiId") Integer mokpiId) {
        if (ComUtil.isEmpty(moKpiService.selectById(mokpiId))) {
            return new PublicResult<>(PublicResultConstant.ERROR, null);
        }
        boolean result = moKpiService.deleteById(mokpiId);
        return result ? new PublicResult<>(PublicResultConstant.SUCCESS, null) : new PublicResult<>(PublicResultConstant.ERROR, null);
    }


}

