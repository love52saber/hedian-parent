package com.hedian.controller;


import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.hedian.annotation.Pass;
import com.hedian.base.PublicResult;
import com.hedian.base.PublicResultConstant;
import com.hedian.entity.WoSla;
import com.hedian.service.IWoSlaService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 工单考核sla定义表 前端控制器
 * </p>
 *
 * @author hedian123
 * @since 2018-10-18
 */
@RestController
@RequestMapping("/api/woSla")
@Api(description = "工单考核sla定义")
public class WoSlaController {

    @Value("${workflow.workorderkey}")
    private String workOrderKey;
    @Autowired
    private IWoSlaService woSlaService;

    /**
     * 获取工单流程考核定义
     */
    @GetMapping("/wosla")
    public PublicResult getSingleWoSla() {
        WoSla woSla = woSlaService.selectOne(new EntityWrapper<WoSla>().eq("proc_def_id", workOrderKey));
        return new PublicResult(PublicResultConstant.SUCCESS, woSla);
    }


}

