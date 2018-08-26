package com.hedian.controller;


import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.hedian.base.PageResult;
import com.hedian.base.PublicResult;
import com.hedian.base.PublicResultConstant;
import com.hedian.entity.SysGroup;
import com.hedian.entity.SysGrpRole;
import com.hedian.entity.SysGrpUser;
import com.hedian.model.SysGroupModel;
import com.hedian.service.*;
import com.hedian.util.ComUtil;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * <p>
 * 用户组 前端控制器
 * </p>
 *
 * @author hedian123
 * @since 2018-08-17
 */
@RestController
@RequestMapping("/api/sysGroup")
@Api(description = "用户组管理")
public class SysGroupController {

    @Autowired
    private ISysGroupService sysGroupService;

    @Autowired
    private ISysGrpUserService sysGrpUserService;

    @Autowired
    private ISysGrpRoleService sysGrpRoleService;

    @Autowired
    private ISysRoleService sysRoleService;

    @Autowired
    private ISysUserService sysUserService;

    @GetMapping(value = "/pageList")
    public PublicResult findList(@RequestParam(name = "pageIndex", defaultValue = "1", required = false) Integer pageIndex,
                                 @RequestParam(name = "pageSize", defaultValue = "10", required = false) Integer pageSize,
                                 @RequestParam(value = "grpName", defaultValue = "", required = false) String grpName) {
        EntityWrapper<SysGroup> ew = new EntityWrapper<>();
        if (!ComUtil.isEmpty(grpName)) {
            ew.like("grp_name", grpName);
        }
        Page<SysGroup> page = sysGroupService.selectPage(new Page<>(pageIndex, pageSize), ew);
        page.getRecords().stream().forEach(sysGroup -> {
            List<SysGrpRole> sysGrpRoleList = sysGrpRoleService.selectList(new EntityWrapper<SysGrpRole>().eq("grp_id", sysGroup.getGrpId()));
            sysGroup.setRoleIds(sysGrpRoleList.stream().map(SysGrpRole::getRoleId).collect(Collectors.toList()));
            List<SysGrpUser> sysGrpUserList = sysGrpUserService.selectList(new EntityWrapper<SysGrpUser>().eq("grp_id", sysGroup.getGrpId()));
            sysGroup.setUserIds(sysGrpUserList.stream().map(SysGrpUser::getUserId).collect(Collectors.toList()));
        });
        return new PublicResult<PageResult>(PublicResultConstant.SUCCESS, new PageResult<>(
                page.getTotal(), pageIndex, pageSize, page.getRecords()));
    }

    /**
     * 获取所有用户组
     */
    @GetMapping("/all")
    public PublicResult getAllGroup() {
        List<SysGroup> roleList = sysGroupService.selectList(new EntityWrapper<SysGroup>());
        return new PublicResult(PublicResultConstant.SUCCESS, roleList);
    }

    /**
     * 获取用户组详细信息
     */
    @GetMapping(value = "/{grpId}")
    public PublicResult getById(@PathVariable("grpId") Long grpId) {
        SysGroup sysGroup = sysGroupService.selectById(grpId);
        if (ComUtil.isEmpty(sysGroup)) {
            return new PublicResult(PublicResultConstant.INVALID_ROLE, null);
        }
        Map<String, Object> result = new HashMap<>();
        result.put("group", sysGroup);
        //权限信息
        result.put("nodes", sysRoleService.getRolesByGrpId(grpId));
        //用户
        result.put("users", sysUserService.getUsersByGrpId(grpId));
        return new PublicResult<>(PublicResultConstant.SUCCESS, result);
    }

    /**
     * 添加用户组
     *
     * @param groupModel
     * @return
     */
    @PostMapping
    public PublicResult<String> addGroup(@RequestBody SysGroupModel groupModel) throws Exception {
        boolean result = sysGroupService.addRoleAndUsers(groupModel);
        return result ? new PublicResult<>(PublicResultConstant.SUCCESS, null) : new PublicResult<>(PublicResultConstant.INVALID_USER, null);
    }


    /**
     * 修改用户组信息
     */
    @PutMapping
    public PublicResult<String> updateGroup(@RequestBody SysGroupModel groupModel) throws Exception {
        boolean result = sysGroupService.updateGroupInfo(groupModel);
        return !result ? new PublicResult<>(PublicResultConstant.INVALID_ROLE, null) : new PublicResult<>(PublicResultConstant.SUCCESS, null);
    }


    /**
     * 删除用户组
     */
    @DeleteMapping(value = "/{grpId}")
    public PublicResult deleteGroup(@PathVariable("grpId") Long grpId) {
        if (ComUtil.isEmpty(sysGroupService.selectById(grpId))) {
            return new PublicResult<>(PublicResultConstant.INVALID_USER, null);
        }
        if (!ComUtil.isEmpty(sysGrpUserService.selectList(new EntityWrapper<SysGrpUser>().eq("grp_id", grpId)))) {
            return new PublicResult<>("用户组下面存在相关用户,请先删除相关用户组的用户", null);
        }
        if (!ComUtil.isEmpty(sysGrpRoleService.selectList(new EntityWrapper<SysGrpRole>().eq("grp_id", grpId)))) {
            return new PublicResult<>("用户组下面存在相关角色,请先删除相关用户组的角色", null);
        }
        boolean result = sysGroupService.delete(new EntityWrapper<SysGroup>().eq("grp_id", grpId));
        return result ? new PublicResult<>(PublicResultConstant.SUCCESS, null) : new PublicResult<>(PublicResultConstant.ERROR, null);
    }


}

