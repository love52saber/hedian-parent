package com.hedian.controller;


import com.baomidou.mybatisplus.plugins.Page;
import com.hedian.base.PageResult;
import com.hedian.base.PublicResult;
import com.hedian.base.PublicResultConstant;
import com.hedian.model.FmsModel;
import com.hedian.service.IFmsMdService;
import com.hedian.service.IFmsResService;
import com.hedian.service.IFmsService;
import com.hedian.service.IMsResService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 故障维修策略 前端控制器
 * </p>
 *
 * @author hedian123
 * @since 2018-09-17
 */
@RestController
@RequestMapping("/api/fms")
public class FmsController {
    @Autowired
    private IFmsService fmsService;
    @Autowired
    private IFmsMdService fmsMdService;
    @Autowired
    private IFmsResService fmsResService;


    /**
     * 维护策略管理列表
     */
    @GetMapping("/pageList")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageIndex", value = "第几页", dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "pageSize", value = "每页多少条", dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "fmsName", value = "名稱", dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "deptName", value = "维护单位", dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "userName", value = "维护人", dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "dispatchflag", value = "自动派单", dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "fmsStatus", value = "状态", dataType = "String", paramType = "query")
    })
    public PublicResult getPageList(@RequestParam(name = "pageIndex", defaultValue = "1", required = false) Integer pageIndex,
                                    @RequestParam(name = "pageSize", defaultValue = "10", required = false) Integer pageSize,
                                    @RequestParam(name = "fmsName", defaultValue = "", required = false) String fmsName,
                                    @RequestParam(name = "deptName", defaultValue = "", required = false) String deptName,
                                    @RequestParam(name = "userName", defaultValue = "", required = false) String userName,
                                    @RequestParam(name = "dispatchflag", defaultValue = "", required = false) boolean dispatchflag,
                                    @RequestParam(name = "grpName", defaultValue = "", required = false) String grpName,
                                    @RequestParam(name = "fmsStatus", defaultValue = "", required = false) Integer fmsStatus) {
        Page<FmsModel> fmsModelPage = fmsService.selectPageByCondition(new Page<>(pageIndex, pageSize), fmsName, deptName, userName, dispatchflag, grpName, fmsStatus);

        return new PublicResult(PublicResultConstant.SUCCESS, new PageResult<>(fmsModelPage.getTotal(), pageIndex, pageSize, fmsModelPage.getRecords()));
    }

}

