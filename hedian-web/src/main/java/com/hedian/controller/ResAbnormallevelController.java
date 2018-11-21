package com.hedian.controller;


import com.hedian.annotation.CurrentUser;
import com.hedian.base.PublicResult;
import com.hedian.base.PublicResultConstant;
import com.hedian.entity.ResAbnormallevel;
import com.hedian.entity.SysUser;
import com.hedian.service.IResAbnormallevelService;
import com.hedian.util.ComUtil;
import com.hedian.utils.HdywUtils;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 资源异常级别 前端控制器
 * </p>
 *
 * @author hedian123
 * @since 2018-08-17
 */
@RestController
@RequestMapping("/api/resAbnormallevel")
@Api(description = "资源告警等级管理")
public class ResAbnormallevelController {

    @Autowired
    private IResAbnormallevelService resAbnormallevelService;

    /**
     * 根据用户下资产  故障等级统计
     *
     * @return
     */
    @GetMapping("/getCountByLevel")
    public PublicResult getCountByLevel(@CurrentUser SysUser sysUser) {
        Map<String, Object> map = new HashMap<>(16);
        List<Integer> redIds = HdywUtils.getResidsByUserid(sysUser);
        if (!ComUtil.isEmpty(redIds)) {
            map.put("resIds", redIds);
        } else {
            map.put("resIds", null);
        }

        List<ResAbnormallevel> resAbnormallevels = resAbnormallevelService.getCountByLevelMap(map);
        return new PublicResult(PublicResultConstant.SUCCESS, resAbnormallevels);
    }


    /**
     * 故障等级列表
     *
     * @return
     */
    @GetMapping("/all")
    public PublicResult getAll() {
        List<ResAbnormallevel> resAbnormallevels = resAbnormallevelService.selectList(null);
        return new PublicResult(PublicResultConstant.SUCCESS, resAbnormallevels);
    }

}

