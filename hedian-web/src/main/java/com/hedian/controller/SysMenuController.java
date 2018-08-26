package com.hedian.controller;


import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.hedian.base.Constant;
import com.hedian.base.PageResult;
import com.hedian.base.PublicResult;
import com.hedian.base.PublicResultConstant;
import com.hedian.entity.Menu;
import com.hedian.entity.SysMenu;
import com.hedian.entity.User;
import com.hedian.service.IMenuService;
import com.hedian.service.ISysMenuService;
import com.hedian.util.ComUtil;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * 菜单管理 前端控制器
 * </p>
 *
 * @author hedian123
 * @since 2018-08-20
 */
@RestController
@RequestMapping("/api/sysMenu")
@Api(description = "系统菜单")
public class SysMenuController {

    @Autowired
    private ISysMenuService menuService;


    @GetMapping(value = "/all")
    public PublicResult findAll() {
        List<SysMenu> menuList = menuService.selectList(null);
        List<SysMenu> retMenuList = menuService.treeMenuList(0L, menuList);
        return new PublicResult(PublicResultConstant.SUCCESS, retMenuList);
    }

}

