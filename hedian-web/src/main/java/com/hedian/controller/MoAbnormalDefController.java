package com.hedian.controller;


import com.baomidou.mybatisplus.plugins.Page;
import com.hedian.base.PageResult;
import com.hedian.base.PublicResult;
import com.hedian.base.PublicResultConstant;
import com.hedian.entity.MoAbnormalDef;
import com.hedian.service.IMoAbnormalDefService;
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
 * 监控异常定义 前端控制器
 * </p>
 *
 * @author hedian123
 * @since 2018-08-17
 */
@RestController
@RequestMapping("/api/tblMoAbnormalDef")
@Api(description = "监控异常定义管理")
public class MoAbnormalDefController {

    @Autowired
    private IMoAbnormalDefService moAbnormalDefService;

    /**
     * 资源设备列表
     */
    @GetMapping("/pageList")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageIndex", value = "第几页"
                    , dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "pageSize", value = "每页多少条"
                    , dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "abnormalCode", value = "故障编码"
                    , dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "abnormalName", value = "故障名称"
                    , dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "abnormalType", value = "故障类型"
                    , dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "abnormalLevel", value = "故障等级"
                    , dataType = "String", paramType = "query")
    })
    public PublicResult getPageList(@RequestParam(name = "pageIndex", defaultValue = "1", required = false) Integer pageIndex,
                                    @RequestParam(name = "pageSize", defaultValue = "10", required = false) Integer pageSize,
                                    @RequestParam(name = "abnormalCode", defaultValue = "", required = false) String abnormalCode,
                                    @RequestParam(name = "abnormalName", defaultValue = "", required = false) String abnormalName,
                                    @RequestParam(name = "abnormalType", defaultValue = "", required = false) String abnormalType,
                                    @RequestParam(name = "abnormalLevel", defaultValue = "", required = false) String abnormalLevel) {

        //自定义分页关联查询列表
        Page<MoAbnormalDef> resBasePage = moAbnormalDefService.selectAbnormalDefPageList(new Page<>(pageIndex, pageSize), abnormalCode, abnormalName,
                abnormalType, abnormalLevel);
        return new PublicResult(PublicResultConstant.SUCCESS, new PageResult<>(resBasePage.getTotal(), pageIndex, pageSize, resBasePage.getRecords()));

    }

}

