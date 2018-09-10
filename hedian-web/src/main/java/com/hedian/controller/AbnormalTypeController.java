package com.hedian.controller;


import com.hedian.base.PublicResult;
import com.hedian.base.PublicResultConstant;
import com.hedian.entity.AbnormalType;
import com.hedian.service.IAbnormalTypeService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * 异常类型 前端控制器
 * </p>
 *
 * @author hedian123
 * @since 2018-09-04
 */
@RestController
@RequestMapping("/api/abnormalType")
@Api(description = "故障类型管理")
public class AbnormalTypeController {

    @Autowired
    private IAbnormalTypeService iAbnormalTypeService;

    /**
     * 故障类型列表
     *
     * @return
     */
    @GetMapping("/all")
    public PublicResult getAll() {
        List<AbnormalType> abnormalTypeList = iAbnormalTypeService.selectList(null);
        return new PublicResult(PublicResultConstant.SUCCESS, abnormalTypeList);
    }


}

