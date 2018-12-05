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
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

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
                                    @RequestParam(name = "msTitle", defaultValue = "", required = false) String msTitle,
                                    @RequestParam(name = "msType", defaultValue = "", required = false) Integer msType,
                                    @RequestParam(name = "msStatus", defaultValue = "", required = false) Integer msStatus,
                                    @RequestParam(name = "currentTime", defaultValue = "", required = false) boolean currentTime) {
        EntityWrapper ew = new EntityWrapper();
        if (!ComUtil.isEmpty(msTitle)) {
            ew.like("ms_name", msTitle);
        }
        if (!ComUtil.isEmpty(msType)) {
            ew.eq("ms_type", msType);
        }
        if (!ComUtil.isEmpty(msStatus)) {
            ew.eq("ms_status", msStatus);
        }
        if (currentTime) {
            String currentDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
            ew.le("begin_time", currentDate).and().ge("end_time", currentDate);
        }
        Page<MaintainStrategy> msPage = maintainStrategyService.selectPage(new Page<>(pageIndex, pageSize), ew);
        msPage.getRecords().stream().forEach(md -> {
            List<MsRes> msResList = msResService.selectList(new EntityWrapper<MsRes>().eq("ms_id", md.getMsId()));
            md.setResIds(msResList.stream().map(MsRes::getResId).collect(Collectors.toList()));
        });
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
     * 添加维护策略
     *
     * @param
     * @return
     */
    @PostMapping
    @RequiresPermissions("maintainStrategy:add")
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
    @RequiresPermissions("maintainStrategy:update")
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
    @RequiresPermissions("maintainStrategy:delete")
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

