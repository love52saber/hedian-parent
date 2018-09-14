package com.hedian.controller;


import com.alibaba.fastjson.JSONObject;
import com.hedian.annotation.ValidationParam;
import com.hedian.base.PublicResult;
import com.hedian.base.PublicResultConstant;
import com.hedian.entity.MoKpi;
import com.hedian.entity.ResStypeKpi;
import com.hedian.service.IMoKpiService;
import com.hedian.service.IResStypeKpiService;
import com.hedian.util.ComUtil;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 资源子类型监控的指标 前端控制器
 * </p>
 *
 * @author hedian123
 * @since 2018-08-17
 */
@RestController
@RequestMapping("/api/resStypeKpi")
@Api(description = "子类型监控指标管理")
public class ResStypeKpiController {

    @Autowired
    private IResStypeKpiService resStypeKpiService;

    @Autowired
    private IMoKpiService moKpiService;

    /**
     * 添加mokpi对象
     *
     * @param
     * @return
     */
    @PostMapping
    public PublicResult<String> addMokpiObject(@ValidationParam("moKpiId,resStypeId,stypeFlag")
                                               @RequestBody JSONObject requestJson) throws Exception {
        //可直接转为java对象,简化操作,不用再set一个个属性
        ResStypeKpi resStypeKpi = requestJson.toJavaObject(ResStypeKpi.class);
        List<MoKpi> moKpiList = moKpiService.selectMokpiByStype(resStypeKpi.getResStypeId());
        if (!ComUtil.isEmpty(moKpiList)) {
            for (MoKpi moKpi : moKpiList) {
                if (moKpi.getMoKpiId().equals(resStypeKpi.getMoKpiId())) {
                    return new PublicResult<>("已有当前规则，不能重复添加", null);
                }
            }
        }
        boolean result = resStypeKpiService.insert(resStypeKpi);
        return result ? new PublicResult<>(PublicResultConstant.SUCCESS, null) : new PublicResult<>(PublicResultConstant.ERROR, null);
    }

    /**
     * 修改mokpid对象
     */
    @PutMapping
    public PublicResult<String> updateMokpiObject(@ValidationParam("skId,stypeFlag")
                                                  @RequestBody JSONObject requestJson) throws Exception {
        //先删除，在添加
        ResStypeKpi resStypeKpi = requestJson.toJavaObject(ResStypeKpi.class);
        resStypeKpi.setId(requestJson.getInteger("skId"));
        boolean result = resStypeKpiService.updateById(resStypeKpi);
        return !result ? new PublicResult<>(PublicResultConstant.ERROR, null) : new PublicResult<>(PublicResultConstant.SUCCESS, null);
    }

    /**
     * 删除mokpi对象
     */
    @DeleteMapping(value = "/{skId}")
    public PublicResult deletemoKpiObject(@PathVariable("skId") Integer skId) {
        if (ComUtil.isEmpty(resStypeKpiService.selectById(skId))) {
            return new PublicResult<>(PublicResultConstant.ERROR, null);
        }
        boolean result = resStypeKpiService.deleteById(skId);
        return result ? new PublicResult<>(PublicResultConstant.SUCCESS, null) : new PublicResult<>(PublicResultConstant.ERROR, null);
    }


}

