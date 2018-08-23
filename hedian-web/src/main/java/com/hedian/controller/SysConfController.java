package com.hedian.controller;


import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.hedian.base.PublicResult;
import com.hedian.base.PublicResultConstant;
import com.hedian.entity.SysConf;
import com.hedian.service.ISysConfService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 系统参数配置表 前端控制器
 * </p>
 *
 * @author hedian123
 * @since 2018-08-17
 */
@RestController
@RequestMapping("/api/tblSysConf")
@Api(value = "系统配置")
public class SysConfController {

    @Autowired
    private ISysConfService sysConfService;

    /**
     * 根据c_type和parakey获取配置值
     */
    @GetMapping("/{cType}/{paraKey}")
    public PublicResult getTreeAllDept(@PathVariable("cType") Integer cType, @PathVariable("paraKey") String paraKey) {
        SysConf sysConf = sysConfService.selectOne(new EntityWrapper<SysConf>().where("c_type={0} and paraKey={1}", cType, paraKey));
        return new PublicResult(PublicResultConstant.SUCCESS, sysConf);
    }


}

