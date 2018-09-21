package com.hedian.controller;


import com.baomidou.mybatisplus.plugins.Page;
import com.hedian.base.PageResult;
import com.hedian.base.PublicResult;
import com.hedian.base.PublicResultConstant;
import com.hedian.model.FmsModel;
import com.hedian.service.IFmsService;
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

    /**
     * 维护策略管理列表
     */
    @GetMapping("/pageList")
    public PublicResult getPageList(@RequestParam(name = "pageIndex", defaultValue = "1", required = false) Integer pageIndex,
                                    @RequestParam(name = "pageSize", defaultValue = "10", required = false) Integer pageSize,
                                    @RequestParam(name = "fmsName", defaultValue = "", required = false) String fmsName,
                                    @RequestParam(name = "deptName", defaultValue = "", required = false) String deptName,
                                    @RequestParam(name = "userName", defaultValue = "", required = false) String userName,
                                    @RequestParam(name = "dispatchflag", defaultValue = "", required = false) Integer dispatchflag,
                                    @RequestParam(name = "grpName", defaultValue = "", required = false) String grpName,
                                    @RequestParam(name = "fmsStatus", defaultValue = "", required = false) Integer fmsStatus) {
        Page<FmsModel> fmsModelPage = fmsService.selectPageByCondition(new Page<>(pageIndex, pageSize), fmsName, deptName, userName, dispatchflag, grpName, fmsStatus);
        return new PublicResult(PublicResultConstant.SUCCESS, new PageResult<>(fmsModelPage.getTotal(), pageIndex, pageSize, fmsModelPage.getRecords()));
    }

}

