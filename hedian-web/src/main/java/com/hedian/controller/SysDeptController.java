package com.hedian.controller;


import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.hedian.annotation.ValidationParam;
import com.hedian.base.PageResult;
import com.hedian.base.PublicResult;
import com.hedian.base.PublicResultConstant;
import com.hedian.entity.SysDept;
import com.hedian.entity.SysUser;
import com.hedian.service.ISysDeptService;
import com.hedian.service.ISysUserService;
import com.hedian.util.ComUtil;
import io.swagger.annotations.Api;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 部门管理 前端控制器
 * </p>
 *
 * @author hedian123
 * @since 2018-08-17
 */
@RestController
@RequestMapping("/api/sysDept")
@Api(description = "部门管理")
public class SysDeptController {
    @Autowired
    private ISysDeptService sysDeptService;
    @Autowired
    private ISysUserService sysUserService;


    /**
     * 组织列表
     */
    @GetMapping("/pageList")
    public PublicResult getPageList(@RequestParam(name = "pageIndex", defaultValue = "1", required = false) Integer pageIndex,
                                    @RequestParam(name = "pageSize", defaultValue = "10", required = false) Integer pageSize) {
        Page<SysDept> rolePage = sysDeptService.selectPage(new Page<>(pageIndex, pageSize));
        return new PublicResult(PublicResultConstant.SUCCESS, new PageResult<>(rolePage.getTotal(), pageIndex, pageSize, rolePage.getRecords()));
    }

    /**
     * 获取所有角色
     */
    @GetMapping("/all")
    public PublicResult getAllDept() {
        List<SysDept> roleList = sysDeptService.selectList(new EntityWrapper<SysDept>());
        return new PublicResult(PublicResultConstant.SUCCESS, roleList);
    }

    /**
     * 获取所有组织  树形结构
     */
    @GetMapping("/treeAll")
    public PublicResult getTreeAllDept() {
        List<SysDept> sysDeptList = sysDeptService.selectList(new EntityWrapper<SysDept>());
        List<SysDept> sysTreeDeptList = sysDeptService.treeDeptList(0L, sysDeptList);
        return new PublicResult(PublicResultConstant.SUCCESS, sysTreeDeptList);
    }


    /**
     * 获取组织详细信息
     */
    @GetMapping(value = "/{deptId}")
    public PublicResult getById(@PathVariable("deptId") Long deptId) {
        SysDept sysDept = sysDeptService.selectById(deptId);
        if (ComUtil.isEmpty(sysDept)) {
            return new PublicResult(PublicResultConstant.INVALID_ROLE, null);
        }
        return new PublicResult<>(PublicResultConstant.SUCCESS, sysDept);
    }


    /**
     * 添加组织
     *
     * @param
     * @return
     */
    @PostMapping
    @RequiresPermissions("sysDept:add")
    public PublicResult<String> addDept(@ValidationParam("parentId,name,shortName,orderNum,orgType")
                                        @RequestBody JSONObject requestJson) throws Exception {
        if (!ComUtil.isEmpty(sysDeptService.selectList(new EntityWrapper<SysDept>().eq("name",requestJson.getString("name"))))) {
            return new PublicResult<>("部门名称已存在", null);
        }
        //可直接转为java对象,简化操作,不用再set一个个属性
        SysDept sysDept = requestJson.toJavaObject(SysDept.class);
        boolean result = sysDeptService.insert(sysDept);
        return result ? new PublicResult<>(PublicResultConstant.SUCCESS, null) : new PublicResult<>(PublicResultConstant.ERROR, null);
    }

    /**
     * 修改组织信息
     */
    @PutMapping
    @RequiresPermissions("sysDept:update")
    public PublicResult<String> updateDept(@ValidationParam("name,shortName,orderNum,orgType,deptId")
                                               @RequestBody JSONObject requestJson) throws Exception {
        SysDept sysDept = requestJson.toJavaObject(SysDept.class);
        SysDept dept = sysDeptService.selectById(sysDept.getDeptId());
        if (!sysDept.getName().equals(dept.getName()) && !ComUtil.isEmpty(sysDeptService.selectList(new EntityWrapper<SysDept>().eq("name",requestJson.getString("name"))))) {
            return new PublicResult<>("部门名称已存在", null);
        }
        boolean result = sysDeptService.updateById(sysDept);
        return !result ? new PublicResult<>(PublicResultConstant.ERROR, null) : new PublicResult<>(PublicResultConstant.SUCCESS, null);
    }

    /**
     * 删除组织
     */
    @DeleteMapping(value = "/{deptId}")
    @RequiresPermissions("sysDept:delete")
    public PublicResult deleteDept(@PathVariable("deptId") Long deptId) {
        if (ComUtil.isEmpty(sysDeptService.selectById(deptId))) {
            return new PublicResult<>(PublicResultConstant.ERROR, null);
        }
        if (!ComUtil.isEmpty(sysDeptService.selectList(new EntityWrapper<SysDept>().eq("parent_id", deptId)))) {
            return new PublicResult<>("组织存在相子组织,请先删除相关组织的子组织", null);
        }
        if (!ComUtil.isEmpty(sysUserService.selectList(new EntityWrapper<SysUser>().eq("dept_id", deptId)))) {
            return new PublicResult<>("组织下面存在用户,请先删除相关组织的用户", null);
        }
        boolean result = sysDeptService.deleteById(deptId);
        return result ? new PublicResult<>(PublicResultConstant.SUCCESS, null) : new PublicResult<>(PublicResultConstant.ERROR, null);
    }

}

