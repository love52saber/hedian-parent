package com.hedian.controller;


import com.hedian.annotation.ValidationParam;
import com.hedian.base.PublicResult;
import com.hedian.base.PublicResultConstant;
import com.hedian.entity.WfBusiness;
import com.hedian.service.IWfBusinessService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author gjyang
 * @since 2018-12-11
 */
@RestController
@Api(description = "工单管理")
@RequestMapping("/api/wfBusiness")
public class WfBusinessController {

    @Autowired
    private IWfBusinessService iWfBusinessService;

    @GetMapping("/associatedBusiness/{businessId}")
    @ApiOperation("获取关联工单")
    @ApiImplicitParam(name = "businessId",value = "工单id",required = true,dataType = "Long",paramType = "path")
    public PublicResult findAssociatedBusinessList(@ValidationParam @PathVariable("businessId")Long businessId) throws Exception {
        List<WfBusiness> associatedBusinessListExceptSelf = iWfBusinessService.getAssociatedBusinessListExceptSelf(businessId);
        return new PublicResult(PublicResultConstant.SUCCESS, associatedBusinessListExceptSelf);
    }
	
}
