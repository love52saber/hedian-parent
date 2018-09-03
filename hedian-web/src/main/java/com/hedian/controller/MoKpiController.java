package com.hedian.controller;


import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.hedian.annotation.Pass;
import com.hedian.base.PublicResult;
import com.hedian.base.PublicResultConstant;
import com.hedian.entity.MoKpi;
import com.hedian.entity.ResSubtype;
import com.hedian.service.IMoKpiService;
import com.hedian.util.ComUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
    @Pass
    public PublicResult getAllMoKpi(@RequestParam(name = "resStype", defaultValue = "", required = false) String resStype) {
        List<MoKpi> moKpiList = moKpiService.selectMokpiByStype(resStype);
        return new PublicResult(PublicResultConstant.SUCCESS, moKpiList);
    }


}

