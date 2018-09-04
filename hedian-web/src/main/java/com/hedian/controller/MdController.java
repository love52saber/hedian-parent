package com.hedian.controller;


import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.hedian.annotation.ValidationParam;
import com.hedian.base.PageResult;
import com.hedian.base.PublicResult;
import com.hedian.base.PublicResultConstant;
import com.hedian.entity.Md;
import com.hedian.entity.MdDept;
import com.hedian.entity.MdRes;
import com.hedian.entity.MdUser;
import com.hedian.service.IMdDeptService;
import com.hedian.service.IMdResService;
import com.hedian.service.IMdService;
import com.hedian.service.IMdUserService;
import com.hedian.util.ComUtil;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 管理域 前端控制器
 * </p>
 *
 * @author hedian123
 * @since 2018-08-27
 */
@RestController
@RequestMapping("/api/md")
@Api(description = "管理域管理")
public class MdController {

    @Autowired
    private IMdService mdService;
    @Autowired
    private IMdResService mdResService;
    @Autowired
    private IMdUserService mdUserService;
    @Autowired
    private IMdDeptService mdDeptService;


    /**
     * 管理域列表
     */
    @GetMapping("/pageList")
    public PublicResult getPageList(@RequestParam(name = "pageIndex", defaultValue = "1", required = false) Integer pageIndex,
                                    @RequestParam(name = "pageSize", defaultValue = "10", required = false) Integer pageSize,
                                    //info-->管理域名
                                    @RequestParam(name = "info", defaultValue = "", required = false) String info) {
        EntityWrapper<Md> ew = new EntityWrapper<>();
        if (!ComUtil.isEmpty(info)) {
            ew.like("md_name", info);
        }
        Page<Md> mdPage = mdService.selectPage(new Page<>(pageIndex, pageSize), ew);
        mdPage.getRecords().stream().forEach(md -> {
            List<MdUser> mdUsers = mdUserService.selectList(new EntityWrapper<MdUser>().eq("md_id", md.getMdId()));
            md.setUserIds(mdUsers.stream().map(MdUser::getUserId).collect(Collectors.toList()));
            List<MdRes> mdResList = mdResService.selectList(new EntityWrapper<MdRes>().eq("md_id", md.getMdId()));
            md.setResIds(mdResList.stream().map(MdRes::getResId).collect(Collectors.toList()));
            List<MdDept> mdDepts = mdDeptService.selectList(new EntityWrapper<MdDept>().eq("md_id", md.getMdId()));
            md.setDeptIds(mdDepts.stream().map(MdDept::getDeptId).collect(Collectors.toList()));
        });
        return new PublicResult(PublicResultConstant.SUCCESS, new PageResult<>(mdPage.getTotal(), pageIndex, pageSize, mdPage.getRecords()));
    }

    /**
     * 获取所有管理域
     */
    @GetMapping("/all")
    public PublicResult getAllMd() {
        List<Md> mdList = mdService.selectList(new EntityWrapper<Md>());
        return new PublicResult(PublicResultConstant.SUCCESS, mdList);
    }


    /**
     * 添加管理域
     *
     * @param
     * @return
     */
    @PostMapping
    public PublicResult<String> addMd(@ValidationParam("mdName,showorder")
                                      @RequestBody JSONObject requestJson) throws Exception {
        //可直接转为java对象,简化操作,不用再set一个个属性
        Md md = requestJson.toJavaObject(Md.class);
        boolean result = mdService.insert(md);
        return result ? new PublicResult<>(PublicResultConstant.SUCCESS, null) : new PublicResult<>(PublicResultConstant.ERROR, null);
    }

    /**
     * 修改管理域信息
     */
    @PutMapping
    public PublicResult<String> updateMd(@ValidationParam("mdName,mdDesc,mdId,showorder")
                                         @RequestBody JSONObject requestJson) throws Exception {
        Md md = requestJson.toJavaObject(Md.class);
        boolean result = mdService.updateById(md);
        return result ? new PublicResult<>(PublicResultConstant.SUCCESS, null) : new PublicResult<>(PublicResultConstant.ERROR, null);
    }

    /**
     * 删除管理域
     */
    @DeleteMapping(value = "/{mdId}")
    public PublicResult deleteMd(@PathVariable("mdId") Long mdId) {
        if (ComUtil.isEmpty(mdService.selectById(mdId))) {
            return new PublicResult<>(PublicResultConstant.ERROR, null);
        }
        boolean result = mdService.deleteById(mdId);
        return result ? new PublicResult<>(PublicResultConstant.SUCCESS, null) : new PublicResult<>(PublicResultConstant.ERROR, null);
    }


}

