package com.hedian.controller;


import com.baomidou.mybatisplus.plugins.Page;
import com.hedian.base.PageResult;
import com.hedian.base.PublicResult;
import com.hedian.base.PublicResultConstant;
import com.hedian.model.ResMoAbnormalInfoModel;
import com.hedian.service.IResMoAbnormalInfoHService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 资源监控异常信息 前端控制器
 * </p>
 *
 * @author hedian123
 * @since 2018-09-17
 */
@RestController
@RequestMapping("/api/resMoAbnormalInfoH")
@Api(description = "历史告警管理")
public class ResMoAbnormalInfoHController {


    @Autowired
    private IResMoAbnormalInfoHService resMoAbnormalInfoHService;

    /**
     * 获取所有的历史告警
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
            @ApiImplicitParam(name = "resAlias", value = "告警设备别名", dataType = "String", paramType = "query")
    })
    public PublicResult getAllMoThread(@RequestParam(name = "pageIndex", defaultValue = "1", required = false) Integer pageIndex,
                                       @RequestParam(name = "pageSize", defaultValue = "10", required = false) Integer pageSize,
                                       @RequestParam(name = "beginTime", defaultValue = "", required = false) String beginTime,
                                       @RequestParam(name = "endTime", defaultValue = "", required = false) String endTime,
                                       @RequestParam(name = "conStatus", defaultValue = "", required = false) String conStatus,
                                       @RequestParam(name = "abnormalLevel", defaultValue = "", required = false) String abnormalLevel,
                                       @RequestParam(name = "abnormalType", defaultValue = "", required = false) String abnormalType,
                                       @RequestParam(name = "abnormalName", defaultValue = "", required = false) String abnormalName,
                                       @RequestParam(name = "mokpiName", defaultValue = "", required = false) String mokpiName,
                                       @RequestParam(name = "resName", defaultValue = "", required = false) String resName,
                                       @RequestParam(name = "resAlias", defaultValue = "", required = false) String resAlias) {
        Page<ResMoAbnormalInfoModel> moThresholdModelPage = resMoAbnormalInfoHService.selectPageByCondition(new Page<>(pageIndex, pageSize), beginTime, endTime, conStatus, abnormalLevel, abnormalType, abnormalName, mokpiName, resName, resAlias);
        return new PublicResult(PublicResultConstant.SUCCESS, new PageResult<>(moThresholdModelPage.getTotal(), pageIndex, pageSize, moThresholdModelPage.getRecords()));
    }

}

