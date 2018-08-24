package com.hedian.controller;


import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.hedian.annotation.ValidationParam;
import com.hedian.base.Constant;
import com.hedian.base.PageResult;
import com.hedian.base.PublicResult;
import com.hedian.base.PublicResultConstant;
import com.hedian.entity.SysGrpRole;
import com.hedian.entity.SysRole;
import com.hedian.entity.SysUserRole;
import com.hedian.model.RoleModel;
import com.hedian.model.SysRoleModel;
import com.hedian.service.ISysGrpRoleService;
import com.hedian.service.ISysRoleService;
import com.hedian.service.ISysUserRoleService;
import com.hedian.util.ComUtil;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 角色 前端控制器
 * </p>
 *
 * @author hedian123
 * @since 2018-08-17
 */
@RestController
@RequestMapping("/api/sysRole")
public class SysRoleController {

    @Autowired
    private ISysRoleService roleService;
    @Autowired
    private ISysUserRoleService sysUserRoleService;
    @Autowired
    private ISysGrpRoleService sysGrpRoleService;


    /**
     * 角色列表
     */
    @GetMapping("/pageList")
    public PublicResult getPageList(@RequestParam(name = "pageIndex", defaultValue = "1", required = false) Integer pageIndex,
                                    @RequestParam(name = "pageSize", defaultValue = "10", required = false) Integer pageSize) {
        Page<SysRole> rolePage = roleService.selectPage(new Page<>(pageIndex, pageSize));
        return new PublicResult(PublicResultConstant.SUCCESS, new PageResult<>(rolePage.getTotal(), pageIndex, pageSize, rolePage.getRecords()));
    }

    /**
     * 获取所有角色
     */
    @GetMapping("/all")
    public PublicResult getAllRole() {
        List<SysRole> roleList = roleService.selectList(new EntityWrapper<SysRole>());
        return new PublicResult(PublicResultConstant.SUCCESS, roleList);
    }

    /**
     * 获取角色详细信息
     */
    @GetMapping(value = "/{roleId}")
    public PublicResult getById(@PathVariable("roleId") Long roleId) {
        SysRole role = roleService.selectById(roleId);
        if (ComUtil.isEmpty(role)) {
            return new PublicResult(PublicResultConstant.INVALID_ROLE, null);
        }
        Map<String, Object> result = new HashMap<>();
        result.put("role", role);
        //权限信息
        result.put("nodes", roleService.getMenuByRoleCode(roleId));
        return new PublicResult<>(PublicResultConstant.SUCCESS, result);
    }

    /**
     * 删除角色
     */
    @DeleteMapping(value = "/{roleId}")
    public PublicResult deleteRole(@PathVariable("roleId") Long roleId) {
        if (ComUtil.isEmpty(roleService.selectById(roleId))) {
            return new PublicResult<>(PublicResultConstant.INVALID_ROLE, null);
        }
        if (!ComUtil.isEmpty(sysUserRoleService.selectList(new EntityWrapper<SysUserRole>().eq("role_id", roleId)))) {
            return new PublicResult<>("角色存在相关用户,请先删除相关角色的用户", null);
        }
        if (!ComUtil.isEmpty(sysGrpRoleService.selectList(new EntityWrapper<SysGrpRole>().eq("role_id", roleId)))) {
            return new PublicResult<>("角色存在相关用户组,请先删除相关角色的用户组", null);
        }
        boolean result = roleService.deleteById(roleId);
        return result ? new PublicResult<>(PublicResultConstant.SUCCESS, null) : new PublicResult<>(PublicResultConstant.ERROR, null);
    }

    /**
     * 添加角色
     *
     * @param requestJson
     * @return
     */
    @PostMapping
    public PublicResult<String> addRole(@ValidationParam("roleName")@RequestBody JSONObject requestJson) throws Exception {
        boolean result = roleService.addRoleAndPermission(requestJson);
        return result ? new PublicResult<>(PublicResultConstant.SUCCESS, null) : new PublicResult<>(PublicResultConstant.INVALID_USER, null);
    }

    /**
     * 修改角色信息
     */
    @PutMapping
    public PublicResult<String> updateRole(@ValidationParam("roleName,roleId")@RequestBody JSONObject requestJson) throws Exception {
        boolean result = roleService.updateRoleInfo(requestJson);
        return !result ? new PublicResult<>(PublicResultConstant.INVALID_ROLE, null) : new PublicResult<>(PublicResultConstant.SUCCESS, null);
    }


}

