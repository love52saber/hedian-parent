package com.hedian.controller;


import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.hedian.annotation.ValidationParam;
import com.hedian.base.PageResult;
import com.hedian.base.PublicResult;
import com.hedian.base.PublicResultConstant;
import com.hedian.entity.*;
import com.hedian.service.*;
import com.hedian.util.ComUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
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
    @Autowired
    private ISysDeptService sysDeptService;
    @Autowired
    private ISysUserService sysUserService;


    /**
     * 管理域列表
     */
    @GetMapping("/pageList")
    public PublicResult getPageList(@RequestParam(name = "pageIndex", defaultValue = "1", required = false) Integer pageIndex,
                                    @RequestParam(name = "pageSize", defaultValue = "10", required = false) Integer pageSize,
                                    //info-->管理域名
                                    @RequestParam(name = "info", defaultValue = "", required = false) String info) {
        EntityWrapper ew = new EntityWrapper();
        if (!ComUtil.isEmpty(info)) {
            ew.like("md_name", info);
        }
        Page<Md> mdPage = mdService.selectPage(new Page<>(pageIndex, pageSize), ew);
        mdPage.getRecords().stream().forEach(md -> {
            List<MdRes> mdResList = mdResService.selectList(new EntityWrapper<MdRes>().eq("md_id", md.getMdId()));
            md.setResIds(mdResList.stream().map(MdRes::getResId).collect(Collectors.toList()));
            List<MdDept> mdDeptList = mdDeptService.selectList(new EntityWrapper<MdDept>().eq("md_id", md.getMdId()));
            md.setDeptIds(mdDeptList.stream().map(MdDept::getDeptId).collect(Collectors.toList()));
            List<SysUser> userList = new ArrayList<>();
            List<MdUser> mdUserList = mdUserService.selectList(new EntityWrapper<MdUser>().eq("md_id", md.getMdId()));
            md.setUserIds(mdUserList.stream().map(MdUser::getUserId).collect(Collectors.toList()));
            mdUserList.stream().forEach(mdUser -> {
                SysUser sysUser = sysUserService.selectById(mdUser.getUserId());
                sysUser.setSysDept(sysDeptService.selectById(sysUser.getDeptId()));
                userList.add(sysUser);
            });
            md.setSysUsers(userList);
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
     * 获取所有管理域（控件）
     */
    @GetMapping("/pageCondition")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "mdName", value = "管理域名字", dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "userId", value = "用户id", dataType = "Long", paramType = "query"),
            @ApiImplicitParam(name = "deptId", value = "部门id", dataType = "Long", paramType = "query")
    })
    public PublicResult getAllPageCondition(@RequestParam(name = "mdName", defaultValue = "", required = false) String mdName,
                                            @RequestParam(name = "userId", defaultValue = "", required = false) Long userId,
                                            @RequestParam(name = "deptId", defaultValue = "", required = false) Long deptId) {
        List<Md> mdList = mdService.selectPageByCondition(mdName, deptId, userId);
        return new PublicResult(PublicResultConstant.SUCCESS, mdList);
    }


    /**
     * 添加管理域
     *
     * @param
     * @return
     */
    @PostMapping
    public PublicResult<String> addMd(@ValidationParam("mdName")
                                      @RequestBody JSONObject requestJson) throws Exception {
        //可直接转为java对象,简化操作,不用再set一个个属性
        Md md = requestJson.toJavaObject(Md.class);
        boolean result = mdService.addAllNodes(md);
        return result ? new PublicResult<>(PublicResultConstant.SUCCESS, null) : new PublicResult<>(PublicResultConstant.ERROR, null);
    }

    /**
     * 修改管理域信息
     */
    @PutMapping
    public PublicResult<String> updateMd(@ValidationParam("mdName,mdId,showorder")
                                         @RequestBody JSONObject requestJson) throws Exception {
        Md md = requestJson.toJavaObject(Md.class);
        boolean result = mdService.updateMd(md);
        return result ? new PublicResult<>(PublicResultConstant.SUCCESS, null) : new PublicResult<>(PublicResultConstant.ERROR, null);
    }

    /**
     * 删除管理域
     */
    @DeleteMapping(value = "/{mdId}")
    public PublicResult deleteMd(@PathVariable("mdId") Integer mdId) {
        if (ComUtil.isEmpty(mdService.selectById(mdId))) {
            return new PublicResult<>(PublicResultConstant.ERROR, null);
        }
        if (!ComUtil.isEmpty(mdDeptService.selectList(new EntityWrapper<MdDept>().eq("md_id", mdId)))) {
            return new PublicResult<>("管理域下有部门，不能删除", null);
        }
        if (!ComUtil.isEmpty(mdResService.selectList(new EntityWrapper<MdRes>().eq("md_id", mdId)))) {
            return new PublicResult<>("管理域下面有设备，不能删除", null);
        }
        if (!ComUtil.isEmpty(mdUserService.selectList(new EntityWrapper<MdUser>().eq("md_id", mdId)))) {
            return new PublicResult<>("管理域下面有用户，不能删除", null);
        }
        boolean result = mdService.deleteById(mdId);
        return result ? new PublicResult<>(PublicResultConstant.SUCCESS, null) : new PublicResult<>(PublicResultConstant.ERROR, null);
    }


}

