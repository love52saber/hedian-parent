package com.hedian.controller;


import com.hedian.base.PublicResult;
import com.hedian.base.PublicResultConstant;
import com.hedian.entity.ResBase;
import com.hedian.service.IResBaseService;
import com.hedian.utils.HdywUtils;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * <p>
 * 资源基础信息表 前端控制器
 * </p>
 *
 * @author hedian123
 * @since 2018-08-17
 */
@RestController
@RequestMapping("/api/resBase")
@Api(description = "资源基础信息管理")
public class ResBaseController {

    @Autowired
    private IResBaseService resBaseService;

    @GetMapping("/getResBaseInfos/{resId}")
    public PublicResult<Map<String, Object>> getTerminalById(@PathVariable("resId") String resId) throws Exception {
        ResBase resBase = resBaseService.selectById(resId);
        Map<String, ResBase> mainMap = new LinkedHashMap<>(16);
        Map<String, String> tblMqttDevList = HdywUtils.getCollectionData(resBase.getResSerialnumber());
        HdywUtils.getMainMap(mainMap, resBase.getResSerialnumber(), resBase, tblMqttDevList, 0);
        return new PublicResult(PublicResultConstant.SUCCESS, mainMap);
    }

}

