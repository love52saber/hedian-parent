package com.hedian.controller;


import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.hedian.annotation.ValidationParam;
import com.hedian.base.PageResult;
import com.hedian.base.PublicResult;
import com.hedian.base.PublicResultConstant;
import com.hedian.entity.MaintainStrategy;
import com.hedian.entity.MsRes;
import com.hedian.service.IMaintainStrategyService;
import com.hedian.service.IMsResService;
import com.hedian.util.ComUtil;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 维护期策略 前端控制器
 * </p>
 *
 * @author hedian123
 * @since 2018-09-17
 */
@RestController
@RequestMapping("/api/maintainStrategy")
@Api(description = "维护期策略管理")
public class MaintainStrategyController {

    @Autowired
    private IMaintainStrategyService maintainStrategyService;
    @Autowired
    private IMsResService msResService;

    /**
     * 维护策略列表
     */
    @GetMapping("/pageList")
    public PublicResult getPageList(@RequestParam(name = "pageIndex", defaultValue = "1", required = false) Integer pageIndex,
                                    @RequestParam(name = "pageSize", defaultValue = "10", required = false) Integer pageSize,
                                    //info-->管理域名
                                    @RequestParam(name = "info", defaultValue = "", required = false) String info) {
        EntityWrapper ew = new EntityWrapper();

        Page<MaintainStrategy> msPage = maintainStrategyService.selectPage(new Page<>(pageIndex, pageSize), ew);
        return new PublicResult(PublicResultConstant.SUCCESS, new PageResult<>(msPage.getTotal(), pageIndex, pageSize, msPage.getRecords()));
    }

    /**
     * 获取所有维护策略
     */
    @GetMapping("/all")
    public PublicResult getAll() {
        List<MaintainStrategy> mdList = maintainStrategyService.selectList(new EntityWrapper<MaintainStrategy>());
        return new PublicResult(PublicResultConstant.SUCCESS, mdList);
    }

    /**
     * 获取维护期策略详情
     */
    @GetMapping(value = "/{msId}")
    public PublicResult getById(@PathVariable("msId") Integer msId) {
        MaintainStrategy maintainStrategy = maintainStrategyService.selectById(msId);
        if (ComUtil.isEmpty(maintainStrategy)) {
            return new PublicResult(PublicResultConstant.ERROR, null);
        }
        Map<String, Object> result = new HashMap<>();
        result.put("ms", maintainStrategy);
        //设备信息
        result.put("resList", msResService.selectList(new EntityWrapper<MsRes>().eq("ms_id", msId)));
        return new PublicResult<>(PublicResultConstant.SUCCESS, result);
    }


    /**
     * 添加维护策略
     *
     * @param
     * @return
     */
    @PostMapping
    public PublicResult<String> addMs(@ValidationParam("msName,msType,beginTime,endTime,msStatus")
                                      @RequestBody JSONObject requestJson) throws Exception {
        //可直接转为java对象,简化操作,不用再set一个个属性
        MaintainStrategy ms = requestJson.toJavaObject(MaintainStrategy.class);
        boolean result = maintainStrategyService.addAllNodes(ms);
        return result ? new PublicResult<>(PublicResultConstant.SUCCESS, null) : new PublicResult<>(PublicResultConstant.ERROR, null);
    }

    /**
     * 修改维护策略
     */
    @PutMapping
    public PublicResult<String> updateMs(@ValidationParam("msId,msName,msType,beginTime,endTime,msStatus")
                                         @RequestBody JSONObject requestJson) throws Exception {
        MaintainStrategy ms = requestJson.toJavaObject(MaintainStrategy.class);
        boolean result = maintainStrategyService.updateMs(ms);
        return result ? new PublicResult<>(PublicResultConstant.SUCCESS, null) : new PublicResult<>(PublicResultConstant.ERROR, null);
    }

    /**
     * 删除维护策略
     */
    @DeleteMapping(value = "/{msId}")
    public PublicResult deleteMs(@PathVariable("msId") Integer msId) {
        if (ComUtil.isEmpty(maintainStrategyService.selectById(msId))) {
            return new PublicResult<>(PublicResultConstant.ERROR, null);
        }
        if (ComUtil.isEmpty(msResService.selectCount(new EntityWrapper<MsRes>().eq("ms_id", msId)))) {
            return new PublicResult<>("维护期策略下面有设备，不能删除", null);
        }
        boolean result = maintainStrategyService.deleteById(msId);
        return result ? new PublicResult<>(PublicResultConstant.SUCCESS, null) : new PublicResult<>(PublicResultConstant.ERROR, null);
    }


}

