package com.hedian.controller;


import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.hedian.base.PublicResult;
import com.hedian.base.PublicResultConstant;
import com.hedian.entity.ResMaintype;
import com.hedian.service.IResMaintypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * 资源主类型 前端控制器
 * </p>
 *
 * @author hedian123
 * @since 2018-08-17
 */
@RestController
@RequestMapping("/api/resMaintype")
public class ResMaintypeController {

    @Autowired
    private IResMaintypeService resMaintypeService;

    /**
     * 获取所有主类型
     */
    @GetMapping("/all")
    public PublicResult getAllMtype() {
        List<ResMaintype> maintypeList = resMaintypeService.selectList(new EntityWrapper<ResMaintype>());
        return new PublicResult(PublicResultConstant.SUCCESS, maintypeList);
    }


}

