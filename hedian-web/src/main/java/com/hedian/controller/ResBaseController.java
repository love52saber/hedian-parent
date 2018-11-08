package com.hedian.controller;


import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.hedian.annotation.CurrentUser;
import com.hedian.annotation.Pass;
import com.hedian.base.PageResult;
import com.hedian.base.PublicResult;
import com.hedian.base.PublicResultConstant;
import com.hedian.entity.MdRes;
import com.hedian.entity.ResBase;
import com.hedian.entity.SysDept;
import com.hedian.entity.SysUser;
import com.hedian.model.Tree;
import com.hedian.service.IResBaseService;
import com.hedian.util.ComUtil;
import com.hedian.utils.HdywUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * <p>
 * 资源基础信息表 前端控制器
 * </p>
 *
 * @author hedian123
 * @since 2018-08-17
 */
@RestController
@RequestMapping("/api/resBase")
@Api(description = "资源基础信息管理")
public class ResBaseController {

    @Autowired
    private IResBaseService resBaseService;


    /**
     * 资源设备列表
     */
    @GetMapping("/pageList")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageIndex", value = "第几页"
                    , dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "pageSize", value = "每页多少条"
                    , dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "resName", value = "设备名字"
                    , dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "resStype", value = "设备子类型"
                    , dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "resIPV4", value = "设备IP地址"
                    , dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "resSerialNum", value = "设备序列号"
                    , dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "resAddress", value = "设备地址"
                    , dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "resMtype", value = "设备主类型"
                    , dataType = "String", paramType = "query")
    })
    public PublicResult getPageList(@RequestParam(name = "pageIndex", defaultValue = "1", required = false) Integer pageIndex,
                                    @RequestParam(name = "pageSize", defaultValue = "10", required = false) Integer pageSize,
                                    @RequestParam(name = "resName", defaultValue = "", required = false) String resName,
                                    @RequestParam(name = "resStype", defaultValue = "", required = false) Integer resStype,
                                    @RequestParam(name = "resIpv4", defaultValue = "", required = false) String resIpv4,
                                    @RequestParam(name = "resSerialNum", defaultValue = "", required = false) String resSerialNum,
                                    @RequestParam(name = "resAddress", defaultValue = "", required = false) String resAddress,
                                    @RequestParam(name = "resMtype", defaultValue = "", required = false) Integer resMtype) {

        //自定义分页关联查询列表
        Page<ResBase> resBasePage = resBaseService.selectPageByConditionResBase(new Page<>(pageIndex, pageSize), resName, resStype,
                resIpv4, resSerialNum, resAddress, resMtype);
        return new PublicResult(PublicResultConstant.SUCCESS, new PageResult<>(resBasePage.getTotal(), pageIndex, pageSize, resBasePage.getRecords()));

    }


    /**
     * 根据设备查询管理域id
     */
    @GetMapping("/allResIds")
    public PublicResult allResIds(@RequestParam(value = "userId", defaultValue = "", required = false) String userId) {
        List<Integer> resIdList = resBaseService.selectByUserId(userId);
        return new PublicResult(PublicResultConstant.SUCCESS, resIdList);
    }


    @GetMapping("/getResBaseInfos/{resId}")
    public PublicResult<Map<String, Object>> getTerminalById(@PathVariable("resId") String resId) throws Exception {
        ResBase resBase = resBaseService.selectById(resId);
        Map<String, ResBase> mainMap = new LinkedHashMap<>(16);
        Map<String, String> tblMqttDevList = HdywUtils.getCollectionData(resBase.getResSerialnumber());
        HdywUtils.getMainMap(mainMap, resBase.getResSerialnumber(), resBase, tblMqttDevList, 0);
        return new PublicResult(PublicResultConstant.SUCCESS, mainMap);
    }

    /**
     * 获取最新故障
     */
    @GetMapping("/getTopAlarm")
    @Pass
    public PublicResult getTopAlarm(@CurrentUser SysUser sysUser) {
        Map<String, Object> map = new HashMap<>(16);
        List<Integer> resIds = HdywUtils.getResidsByUserid(sysUser);
        map.put("resIds", resIds);
        map.put("resStatus", 1);
        map.put("resStatu", 3);
        List<ResBase> resBaseAlarms = resBaseService.findByMap(map);
        if(!ComUtil.isEmpty(resBaseAlarms)&& resBaseAlarms.size()>5){
            resBaseAlarms = resBaseAlarms.subList(0,5);
        }
        return new PublicResult(PublicResultConstant.SUCCESS, resBaseAlarms);
    }


    /**
     * 获得TOP故障设备统计
     *
     * @return
     */
    @GetMapping("/getTopRes")
    public PublicResult getTopRes(@CurrentUser SysUser sysUser) {
        Map<String, Object> map = new HashMap<String, Object>(16);
        List<Integer> resIds = HdywUtils.getResidsByUserid(sysUser);
        map.put("resIds", resIds);
        List<ResBase> resBaseList = resBaseService.getTopRes(map);
        return new PublicResult(PublicResultConstant.SUCCESS, resBaseList);
    }


    @GetMapping("/getResTree")
    @Pass
    public PublicResult getResTree(@CurrentUser SysUser sysUser) {
        Tree<SysDept> tree = resBaseService.genResTreeByUser(sysUser);
        return new PublicResult(PublicResultConstant.SUCCESS, tree);
    }


}

