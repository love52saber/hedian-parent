package com.hedian.controller;


import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.hedian.annotation.CurrentUser;
import com.hedian.base.PageResult;
import com.hedian.base.PublicResult;
import com.hedian.base.PublicResultConstant;
import com.hedian.entity.*;
import com.hedian.model.AlarmInfoModel;
import com.hedian.service.IResMoAbnormalInfoService;
import com.hedian.util.ComUtil;
import com.hedian.utils.HdywUtils;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 资源监控异常信息 前端控制器
 * </p>
 *
 * @author hedian123
 * @since 2018-08-17
 */
@RestController
@RequestMapping("/api/resMoAbnormalInfo")
@Api(description = "资源监控异常信息管理")
public class ResMoAbnormalInfoController {

    @Autowired
    private IResMoAbnormalInfoService resMoAbnormalInfoService;


    /**
     * 故障信息列表
     */
    @GetMapping("/pageAlarmList")
    public PublicResult getPageAlarmList(@RequestParam(name = "pageIndex", defaultValue = "1", required = false) Integer pageIndex,
                                         @RequestParam(name = "pageSize", defaultValue = "10", required = false) Integer pageSize,
                                         @RequestParam(name = "resId", defaultValue = "", required = false) String resId) {
        Page<AlarmInfoModel> rolePage = resMoAbnormalInfoService.selectAlarmByResId(new Page<>(pageIndex, pageSize), resId);
        return new PublicResult(PublicResultConstant.SUCCESS, new PageResult<>(rolePage.getTotal(), pageIndex, pageSize, rolePage.getRecords()));
    }


    /**
     * 获得TOP故障统计
     *
     * @return
     */
    @GetMapping("/getTopAbnormal")
    public PublicResult getTopAbnormal(@CurrentUser SysUser sysUser) {
        Map<String, Object> map = new HashMap<String, Object>(16);
        List<Integer> resIds = HdywUtils.getResidsByUserid(sysUser);
        map.put("resIds", resIds);
        List<MoAbnormalDef> moAbnormalDefs = resMoAbnormalInfoService.getTopAbnormal(map);
        return new PublicResult(PublicResultConstant.SUCCESS, moAbnormalDefs);
    }


}

