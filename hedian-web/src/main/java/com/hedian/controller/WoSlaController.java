package com.hedian.controller;


import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.hedian.annotation.Pass;
import com.hedian.annotation.ValidationParam;
import com.hedian.base.PublicResult;
import com.hedian.base.PublicResultConstant;
import com.hedian.entity.ResAbnormallevel;
import com.hedian.entity.WoSla;
import com.hedian.service.IResAbnormallevelService;
import com.hedian.service.IWoSlaService;
import com.hedian.util.ComUtil;
import com.hedian.util.DateTimeUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;

import javax.xml.crypto.Data;
import java.util.Date;
import java.util.List;

import static com.hedian.util.DateTimeUtil.FMT_yyyyMMddHHmmss;

/**
 * <p>
 * 工单考核sla定义表 前端控制器
 * </p>
 *
 * @author hedian123
 * @since 2018-11-27
 */
@RestController
@RequestMapping("/api/woSla")
@Api(description = "工单考核sla定义")
public class WoSlaController {
    @Autowired
    private IWoSlaService iWoSlaService;


    /**
     * 获取所有sla
     */
    @GetMapping(value = "/pageList")
    @ApiImplicitParams({//下面是显示的  对功能没影响
            @ApiImplicitParam(name="pageIndex", value = "第几页", dataType = "String", paramType = "query"),
            @ApiImplicitParam(name="pageSize", value = "每页多少条", dataType = "String", paramType = "query"),
            @ApiImplicitParam(name="woSlaName",value="sla名称",dataType = "String",paramType = "query"),
            @ApiImplicitParam(name="resAbnormallevelId",value="告警等级",dataType = "String",paramType = "query"),
            @ApiImplicitParam(name="procDefId",value="流程id",dataType = "String",paramType = "query"),
            @ApiImplicitParam(name="woSlaStatus",value="启用状态",dataType = "String",paramType = "query"),
            @ApiImplicitParam(name="flag",value="有效期内：1，不在有效期：0",dataType = "String",paramType = "query"),
            @ApiImplicitParam(name="woSlaDesc",value="Sla描述",dataType = "String",paramType = "query")
    })

    public PublicResult getSla(@RequestParam(name = "pageIndex", defaultValue = "1", required = false) Integer pageIndex,
                               @RequestParam(name = "pageSize", defaultValue = "10", required = false) Integer pageSize,
                               @RequestParam(name = "woSlaName", defaultValue = "", required = false) String woSlaName,
                               @RequestParam(name = "resAbnormallevelId", defaultValue = "", required = false) Integer resAbnormallevelId,
                               @RequestParam(name = "procDefId", defaultValue = "", required = false) String procDefId,
                               @RequestParam(name = "woSlaStatus", defaultValue = "", required = false) String woSlaStatus,
                               @RequestParam(name = "flag", defaultValue = "", required = false) Integer flag,
                               @RequestParam(name = "woSlaDesc", defaultValue = "", required = false) String woSlaDesc)
    {
        String nowTime = DateTimeUtil.formatDateTimetoString(new Date(),FMT_yyyyMMddHHmmss);
        Page<WoSla> woSlaPage = iWoSlaService.selectwoSlaPageByCondition(new Page<>(pageIndex, pageSize),woSlaName,resAbnormallevelId,procDefId,woSlaStatus,flag,nowTime,woSlaDesc);
        return new PublicResult(PublicResultConstant.SUCCESS,woSlaPage);
    }


    /**
     * 添加sla数据
     * @param requestJson
     * @return
     * @throws Exception
     */
    @PostMapping(value = "/save")
    public PublicResult save(@ValidationParam("woSlaName,procDefId,actIdIn,actIdOut,hopetime," +
                                              "deadtime,begintime,endtime,woSlaStatus")
                             @RequestBody JSONObject requestJson)throws Exception{
        //可直接转为java对象,简化操作,不用再set一个个属性
        WoSla woSla = requestJson.toJavaObject(WoSla.class);
        boolean result = iWoSlaService.addSla(woSla);
        return result ? new PublicResult(PublicResultConstant.SUCCESS, null) : new PublicResult(PublicResultConstant.ERROR,null);
    }

    /**
     * 根据id修改数据
     * @param requestJson
     * @return
     */
    @PutMapping(value = "/update")
    public PublicResult updateSla(@ValidationParam("woSlaName,procDefId,actIdIn,actIdOut,hopetime," +
                                                   "deadtime,begintime,endtime,woSlaStatus")
                                      @RequestBody JSONObject requestJson){
        WoSla woSla = requestJson.toJavaObject(WoSla.class);
        boolean result = iWoSlaService.updateById(woSla);
        return result ? new PublicResult(PublicResultConstant.SUCCESS,result):new PublicResult(PublicResultConstant.ERROR,null);
    }


//    /**
//     * 根据id删除sla数据
//     * @param woSlaId
//     * @return
//     */
//    @DeleteMapping(value="/{woSlaId}")
//    public PublicResult delete(@PathVariable("woSlaId") Integer woSlaId){
//         boolean result = iWoSlaService.deleteById(woSlaId);
//         return result ? new PublicResult(PublicResultConstant.SUCCESS,null):new PublicResult(PublicResultConstant.ERROR,null);
//      }


//    @DeleteMapping("")
//    @ApiOperation("根据appraiserid数组删除评价人")
//    @ApiImplicitParams({
//            @ApiImplicitParam(name = "jsonObject", value = "{\"appraiserids\":评价人id数组}", required = true, dataType = "string", paramType = "body")
//    })
//    public PublicResult delBatchById(@ValidationParam("appraiserids") @RequestBody JSONObject jsonObject) throws Exception {
//        JSONArray appraiserids = jsonObject.getJSONArray("appraiserids");
//        List<Integer> appraiseridList = appraiserids.toJavaList(Integer.class);
//        boolean result = iRepairOrderAppraiserService.delBatchByIds(appraiseridList);
//        return result ? new PublicResult(PublicResultConstant.SUCCESS, null) : new PublicResult(PublicResultConstant.ERROR, null);
//    }


    /**
     * 根据id批量删除数据
     * @param jsonObject
     * @return
     * @throws Exception
     */
    @DeleteMapping("")
    @ApiImplicitParams({
            @ApiImplicitParam(name="jsonObject", value = "{\"woSlaIds\":工单考核id数组}",required = true,dataType = "string",paramType = "body")
    })
    public PublicResult deleteBatchById(@ValidationParam("woSlaIds") @RequestBody JSONObject jsonObject) throws Exception{
        JSONArray woSlaIds = jsonObject.getJSONArray("woSlaIds");
        List<Integer> woslaidList = woSlaIds.toJavaList(Integer.class);
        boolean result = iWoSlaService.deleteBatchByIds(woslaidList);
        return result ? new PublicResult(PublicResultConstant.SUCCESS,null) : new PublicResult(PublicResultConstant.ERROR,null);
    }
}

