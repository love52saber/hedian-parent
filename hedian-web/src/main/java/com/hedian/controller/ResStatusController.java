package com.hedian.controller;


import com.hedian.annotation.CurrentUser;
import com.hedian.base.PublicResult;
import com.hedian.base.PublicResultConstant;
import com.hedian.entity.ResStatus;
import com.hedian.entity.SysUser;
import com.hedian.service.IResStatusService;
import com.hedian.util.ComUtil;
import com.hedian.utils.HdywUtils;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 资源状态定义 前端控制器
 * </p>
 *
 * @author hedian123
 * @since 2018-08-17
 */
@RestController
@RequestMapping("/api/resStatus")
@Api(description = "资源状态信息管理")
public class ResStatusController {

    @Autowired
    private IResStatusService iResStatusService;

    /**
     * 统计该用户下的资产状态
     *
     * @return
     */
    @GetMapping("/getCountByStatus")
    public PublicResult getCountByStatus(@CurrentUser SysUser sysUser) {
        Map<String, Object> map = new HashMap<>(16);
        List<Integer> resIds = HdywUtils.getResidsByUserid(sysUser);
        map.put("resIds", resIds);
        List<ResStatus> resStatusList = iResStatusService.getCountByStatusMap(map);
        return new PublicResult(PublicResultConstant.SUCCESS, resStatusList);
    }

}

