package com.hedian.controller;


import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.hedian.base.PublicResult;
import com.hedian.base.PublicResultConstant;
import com.hedian.entity.MdUser;
import com.hedian.service.IMdUserService;
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
@RequestMapping("/api/mdUser")
@Api(description = "管理域用户管理")
public class MdUserController {

    @Autowired
    private IMdUserService mdUserService;



    /**
     * 根据用户管理域id
     */
    @GetMapping("/allMdIds")
    public PublicResult getAllMdIds(@RequestParam(value = "userId", defaultValue = "", required = false) String userId) {
        List<MdUser> mdList = mdUserService.selectList(new EntityWrapper<MdUser>().eq("user_id",userId));
        List<Integer> collect = null;
        if(!ComUtil.isEmpty(mdList)){
            collect = mdList.stream().map(MdUser::getMdId).collect(Collectors.toList());
        }
        return new PublicResult(PublicResultConstant.SUCCESS, collect);
    }




}

