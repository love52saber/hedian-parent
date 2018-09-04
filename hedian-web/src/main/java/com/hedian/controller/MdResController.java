package com.hedian.controller;


import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.hedian.base.PublicResult;
import com.hedian.base.PublicResultConstant;
import com.hedian.entity.MdRes;
import com.hedian.service.IMdResService;
import com.hedian.util.ComUtil;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 管理域 前端控制器
 * </p>
 *
 * @author hedian123
 * @since 2018-08-27
 */
@RestController
@RequestMapping("/api/mdRes")
@Api(description = "管理域设备管理")
public class MdResController {

    @Autowired
    private IMdResService mdResService;


    /**
     * 根据设备查询管理域id
     */
    @GetMapping("/allMdIds")
    public PublicResult getAllMdIds(@RequestParam(value = "resId", defaultValue = "", required = false) String resId) {
        List<MdRes> mdList = mdResService.selectList(new EntityWrapper<MdRes>().eq("res_id",resId));
        List<Integer> collect = null;
        if(!ComUtil.isEmpty(mdList)){
            collect = mdList.stream().map(MdRes::getMdId).collect(Collectors.toList());
        }
        return new PublicResult(PublicResultConstant.SUCCESS, collect);
    }

}

