package com.hedian.controller;


import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.plugins.Page;
import com.hedian.annotation.CurrentUser;
import com.hedian.annotation.ValidationParam;
import com.hedian.base.PageResult;
import com.hedian.base.PublicResult;
import com.hedian.base.PublicResultConstant;
import com.hedian.entity.MoAbnormalDef;
import com.hedian.entity.ResMoAbnormalInfo;
import com.hedian.entity.SysUser;
import com.hedian.model.AbnormalLevelModel;
import com.hedian.model.AlarmInfoModel;
import com.hedian.model.ResMoAbnormalInfoModel;
import com.hedian.service.IResMoAbnormalInfoService;
import com.hedian.utils.HdywUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

/**
 * <p>
 * 资源监控异常信息 前端控制器
 * </p>
 *
 * @author hedian123
 * @since 2018-08-17
 */
@RestController
@RequestMapping("/api/resMoAbnormalInfo")
@Api(description = "资源监控异常信息管理")
public class ResMoAbnormalInfoController {

    @Autowired
    private IResMoAbnormalInfoService resMoAbnormalInfoService;


    /**
     * 获取所有的告警
     */
    @GetMapping("/pageList")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageIndex", value = "第几页", dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "pageSize", value = "每页多少条", dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "beginTime", value = "开始时间", dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "endTime", value = "结束时间", dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "conStatus", value = "确认状态", dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "abnormalLevel", value = "级别", dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "abnormalType", value = "告警类型", dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "abnormalName", value = "告警名称", dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "mokpiName", value = "告警对象名称", dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "resName", value = "告警设备名称", dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "resId", value = "设备Id", dataType = "int", paramType = "query"),
            @ApiImplicitParam(name = "resAlias", value = "告警设备别名", dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "resAbnormalId", value = "告警ID", dataType = "String", paramType = "query")
    })
    public PublicResult getPageList(@RequestParam(name = "pageIndex", defaultValue = "1", required = false) Integer pageIndex,
                                    @RequestParam(name = "pageSize", defaultValue = "10", required = false) Integer pageSize,
                                    @RequestParam(name = "beginTime", defaultValue = "", required = false) String beginTime,
                                    @RequestParam(name = "endTime", defaultValue = "", required = false) String endTime,
                                    @RequestParam(name = "conStatus", defaultValue = "", required = false) String conStatus,
                                    @RequestParam(name = "abnormalLevel", defaultValue = "", required = false) String abnormalLevel,
                                    @RequestParam(name = "abnormalType", defaultValue = "", required = false) String abnormalType,
                                    @RequestParam(name = "abnormalName", defaultValue = "", required = false) String abnormalName,
                                    @RequestParam(name = "mokpiName", defaultValue = "", required = false) String mokpiName,
                                    @RequestParam(name = "resName", defaultValue = "", required = false) String resName,
                                    @RequestParam(name = "resId", defaultValue = "", required = false) Integer resId,
                                    @RequestParam(name = "resAlias", defaultValue = "", required = false) String resAlias,
                                    @RequestParam(name = "isAutoOrder", defaultValue = "", required = false) boolean isAutoOrder,
                                    @RequestParam(name = "resAbnormalId", defaultValue = "", required = false) String resAbnormalId,
                                    @RequestParam(name = "resIds", defaultValue = "", required = false) List<Integer> resIds) {
        Page<ResMoAbnormalInfoModel> moThresholdModelPage = resMoAbnormalInfoService.selectPageByCondition(new Page<>(pageIndex, pageSize), beginTime, endTime, conStatus, abnormalLevel,
                abnormalType, abnormalName, mokpiName, resName, resId, resAlias, isAutoOrder, resAbnormalId, resIds);
        return new PublicResult(PublicResultConstant.SUCCESS, new PageResult<>(moThresholdModelPage.getTotal(), pageIndex, pageSize, moThresholdModelPage.getRecords()));
    }

    /**
     * 告警统计
     */
    @GetMapping(value = "/abnormalLevelCount")
    public PublicResult abnormalLevelCount() {
        List<AbnormalLevelModel> abnormalLevelModels = resMoAbnormalInfoService.selectAbnormalLevelCount();
        return new PublicResult<>(PublicResultConstant.SUCCESS, abnormalLevelModels);
    }


    /**
     * 删除告警
     */
    @DeleteMapping(value = "/{resAbnormalId}")
    public PublicResult deleteAbnormalInfo(@PathVariable("resAbnormalId") Long resAbnormalId, @CurrentUser SysUser user) throws Exception {

        boolean result = resMoAbnormalInfoService.deleteResAbnoraml(resAbnormalId, user);
        return result ? new PublicResult<>(PublicResultConstant.SUCCESS, null) : new PublicResult<>(PublicResultConstant.ERROR, null);
    }

    /**
     * 确认告警
     */
    @PutMapping(value = "/confirmAbnormal")
    public PublicResult confirmAbnormalInfo(@ValidationParam("resAbnormalId") @CurrentUser SysUser user,
                                            @RequestBody JSONObject requestJson) {
        ResMoAbnormalInfo resMoAbnormalInfo = requestJson.toJavaObject(ResMoAbnormalInfo.class);
        resMoAbnormalInfo.setConfirmStatus(2);
        resMoAbnormalInfo.setConfirmUserId(user.getUserId());
        resMoAbnormalInfo.setConfirmTime(new Date());
        boolean result = resMoAbnormalInfoService.updateById(resMoAbnormalInfo);
        return result ? new PublicResult<>(PublicResultConstant.SUCCESS, null) : new PublicResult<>(PublicResultConstant.ERROR, null);
    }

    /**
     * 清除告警
     */
    @PutMapping(value = "/cleanAbnormal")
    public PublicResult cleanAbnormalInfo(@ValidationParam("resAbnormalId") @CurrentUser SysUser user,
                                          @RequestBody JSONObject requestJson) throws Exception {
        boolean result = resMoAbnormalInfoService.cleanResAbnormal(requestJson, user);
        return result ? new PublicResult<>(PublicResultConstant.SUCCESS, null) : new PublicResult<>(PublicResultConstant.ERROR, null);
    }


    /**
     * 获得TOP故障统计
     *
     * @return
     */
    @GetMapping("/getTopAbnormal")
    public PublicResult getTopAbnormal(@CurrentUser SysUser sysUser) {
        Map<String, Object> map = new HashMap<String, Object>(16);
        List<Integer> resIds = HdywUtils.getResidsByUserid(sysUser);
        map.put("resIds", resIds);
        List<MoAbnormalDef> moAbnormalDefs = resMoAbnormalInfoService.getTopAbnormal(map);
        return new PublicResult(PublicResultConstant.SUCCESS, moAbnormalDefs);
    }

    @GetMapping("/selectAlarmByResid/{resId}")
    public PublicResult selectAlarmByResid(@PathVariable String resId) {
        List<AlarmInfoModel> alarmInfoModelList = resMoAbnormalInfoService.selectAlarmByResId(resId);
        return new PublicResult(PublicResultConstant.SUCCESS, alarmInfoModelList);
    }

}

