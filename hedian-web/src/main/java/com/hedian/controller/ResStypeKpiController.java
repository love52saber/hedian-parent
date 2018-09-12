package com.hedian.controller;


import com.alibaba.fastjson.JSONObject;
import com.hedian.annotation.ValidationParam;
import com.hedian.base.BusinessException;
import com.hedian.base.PublicResult;
import com.hedian.base.PublicResultConstant;
import com.hedian.entity.ResStypeKpi;
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

    /**
     * 添加mokpi对象
     *
     * @param
     * @return
     */
    @PostMapping
    public PublicResult<String> addMokpiObject(@ValidationParam("moKpiIds,resStypeId,stypeFlag")
                                               @RequestBody JSONObject requestJson) throws Exception {
        //可直接转为java对象,简化操作,不用再set一个个属性
        boolean result = false;
        List<Integer> moKpiIds = requestJson.getJSONArray("moKpiIds").toJavaList(Integer.class);
        for (Integer moKpiId : moKpiIds) {
            ResStypeKpi resStypeKpi = new ResStypeKpi(requestJson.getInteger("resStypeId"), moKpiId, requestJson.getInteger("stypeFlag"));
            result = resStypeKpiService.insert(resStypeKpi);
            if (!result) {
                throw new BusinessException("添加mokpi对象失败");
            }
        }
        return result ? new PublicResult<>(PublicResultConstant.SUCCESS, null) : new PublicResult<>(PublicResultConstant.ERROR, null);
    }

    /**
     * 修改mokpid对象
     */
    @PutMapping
    public PublicResult<String> updateMokpiObject(@ValidationParam("skId,moKpiId,resStypeId,stypeFlag")
                                                  @RequestBody JSONObject requestJson) throws Exception {
        //先删除，在添加
        Integer skId = requestJson.getInteger("skId");
        boolean result = resStypeKpiService.deleteById(skId);
        if (result) {
            List<Integer> moKpiIds = requestJson.getJSONArray("moKpiIds").toJavaList(Integer.class);
            for (Integer moKpiId : moKpiIds) {
                ResStypeKpi resStypeKpi = new ResStypeKpi(requestJson.getInteger("resStypeId"), moKpiId, requestJson.getInteger("stypeFlag"));
                result = resStypeKpiService.insert(resStypeKpi);
                if (!result) {
                    throw new BusinessException("修改mokpi对象失败");
                }
            }
        }
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

