package com.hedian.controller;


import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.hedian.annotation.Pass;
import com.hedian.base.PublicResult;
import com.hedian.base.PublicResultConstant;
import com.hedian.entity.ResSubtype;
import com.hedian.service.IResSubtypeService;
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
 * 资源子类型 前端控制器
 * </p>
 *
 * @author hedian123
 * @since 2018-08-17
 */
@RestController
@RequestMapping("/api/resSubtype")
@Api(description = "子类型资源管理")
public class ResSubtypeController {

    @Autowired
    private IResSubtypeService resSubtypeService;

    /**
     * 获取所有子类型  树形结构
     */
    @GetMapping("/treeAll")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "resMtype", value = "主类型", dataType = "String", paramType = "query")
    })
    public PublicResult getTreeAllSubtype(@RequestParam(name = "resMtype", defaultValue = "", required = false) Integer resMtype) {
        EntityWrapper ew = new EntityWrapper<ResSubtype>();
        if (!ComUtil.isEmpty(resMtype)) {
            ew.eq("res_mtype_id", resMtype);
        }
        List<ResSubtype> resSubtypeList = resSubtypeService.selectList(ew);
        List<ResSubtype> resTreeSubtypeList = resSubtypeService.treeSubtypeList(0, resSubtypeList);
        return new PublicResult(PublicResultConstant.SUCCESS, resTreeSubtypeList);
    }
}

