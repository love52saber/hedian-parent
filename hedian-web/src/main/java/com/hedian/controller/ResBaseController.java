package com.hedian.controller;


import com.hedian.annotation.CurrentUser;
import com.hedian.base.PageResult;
import com.hedian.base.PublicResult;
import com.hedian.base.PublicResultConstant;
import com.hedian.entity.ResBase;
import com.hedian.entity.SysUser;
import com.hedian.service.IResBaseService;
import com.hedian.util.ComUtil;
import com.hedian.utils.HdywUtils;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
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

    /**
     * 获取最新故障
     */
    @GetMapping("/getTopAlarm")
    public PublicResult getTopAlarm(@CurrentUser SysUser sysUser) {
        Map<String, Object> map = new HashMap<>(16);
        List<Integer> resIds = HdywUtils.getResidsByUserid(sysUser);
        map.put("resIds", resIds);
        List<ResBase> resBaseAlarms = resBaseService.findByMap(map);
        return new PublicResult(PublicResultConstant.SUCCESS, resBaseAlarms);

    }


    /**
     * 获得TOP故障设备统计
     *
     * @return
     */
    @GetMapping("/getTopRes")
    public PublicResult getTopRes(@CurrentUser SysUser sysUser) {
        Map<String, Object> map = new HashMap<String, Object>(16);
        List<Integer> resIds = HdywUtils.getResidsByUserid(sysUser);
        map.put("resIds", resIds);
        List<ResBase> resBaseList = resBaseService.getTopRes(map);
        return new PublicResult(PublicResultConstant.SUCCESS, resBaseList);
    }

}

