package com.hedian.controller;


import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.hedian.annotation.CurrentUser;
import com.hedian.annotation.ValidationParam;
import com.hedian.base.*;
import com.hedian.entity.SysFile;
import com.hedian.entity.SysUser;
import com.hedian.entity.SysUserRole;
import com.hedian.service.ISysDeptService;
import com.hedian.service.ISysFileService;
import com.hedian.service.ISysUserRoleService;
import com.hedian.service.ISysUserService;
import com.hedian.util.ComUtil;
import com.hedian.util.StringUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author hedian123
 * @since 2018-08-17
 */
@RestController
@RequestMapping("/api/sysUser")
@Api(description = "用户管理")
public class SysUserController {
    @Autowired
    private ISysUserService userService;
    @Autowired
    private ISysUserRoleService userRoleService;
    @Autowired
    private ISysDeptService sysDeptService;
    @Autowired
    private ISysFileService sysFileService;


    /**
     * 获取当前登录用户信息
     *
     * @param user
     * @return
     * @throws Exception
     */
    @GetMapping("/currentUser")
    public PublicResult<SysUser> getUser(@CurrentUser SysUser user) throws Exception {
        return new PublicResult<SysUser>(PublicResultConstant.SUCCESS, user);
    }

    @GetMapping(value = "/pageList")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageIndex", value = "第几页"
                    , dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "pageSize", value = "每页多少条"
                    , dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "info", value = "用户名称"
                    , dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "deptId", value = "部门id"
                    , dataType = "String", paramType = "query"),
    })
    public PublicResult findPageList(@RequestParam(name = "pageIndex", defaultValue = "1", required = false) Integer pageIndex,
                                     @RequestParam(name = "pageSize", defaultValue = "10", required = false) Integer pageSize,
                                     @RequestParam(value = "info", defaultValue = "", required = false) String info,
                                     @RequestParam(value = "deptId", defaultValue = "", required = false) String deptId) {
        EntityWrapper<SysUser> ew = new EntityWrapper<>();
        if (!ComUtil.isEmpty(info)) {
            ew.like("username", info).or().like("name", info);
        }
        if (!ComUtil.isEmpty(deptId)) {
            ew.eq("dept_id", deptId);
        }
        Page<SysUser> page = userService.selectPage(new Page<>(pageIndex, pageSize), ew);
        page.getRecords().stream().forEach(sysUser -> {
            List<SysUserRole> sysUserRoleList = userRoleService.selectList(new EntityWrapper<SysUserRole>().eq("user_id", sysUser.getUserId()));
            sysUser.setRoleIds(sysUserRoleList.stream().map(SysUserRole::getRoleId).collect(Collectors.toList()));
            sysUser.setSysDept(ComUtil.isEmpty(sysUser.getDeptId()) ? null : sysDeptService.selectById(sysUser.getDeptId()));
            sysUser.setSysFile(ComUtil.isEmpty(sysUser.getPicId()) ? null : sysFileService.selectById(sysUser.getPicId()));
        });
        return new PublicResult<PageResult>(PublicResultConstant.SUCCESS, new PageResult<>(
                page.getTotal(), pageIndex, pageSize, page.getRecords()));
    }


    @GetMapping(value = "/all")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "grpId", value = "用戶組id"
                    , dataType = "Long", paramType = "query")
    })
    public PublicResult findAll(@RequestParam(value = "grpId", defaultValue = "", required = false) Long grpId) {

        List<SysUser> userList = userService.selectUserList(grpId);
        return new PublicResult<>(PublicResultConstant.SUCCESS, userList);
    }

    @GetMapping(value = "/wfUserList")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "stepType", value = "步驟类型", dataType = "int", paramType = "query"),
            @ApiImplicitParam(name = "deptId", value = "部门id", dataType = "Long", paramType = "query")
    })
    public PublicResult wfUserList(@RequestParam(value = "stepType", defaultValue = "", required = false) Integer stepType,
                                   @RequestParam(value = "deptId", defaultValue = "", required = false) Long deptId) throws BusinessException {
        List<SysUser> userList = userService.getWfUsers(stepType, deptId);
        return new PublicResult<>(PublicResultConstant.SUCCESS, userList);
    }

    /**
     * 添加用户
     *
     * @param requestJson
     * @return
     */
    @PostMapping
    public PublicResult<String> addSysUser(@ValidationParam("name,username,sex,deptId,mobile,email,status")
                                           @RequestBody JSONObject requestJson) throws Exception {

        //可直接转为java对象,简化操作,不用再set一个个属性
        SysUser userRegister = requestJson.toJavaObject(SysUser.class);
        if (!StringUtil.checkMobileNumber(userRegister.getMobile())) {
            return new PublicResult<>(PublicResultConstant.MOBILE_ERROR, null);
        }
        if (!StringUtil.checkEmail(userRegister.getEmail())) {
            return new PublicResult<>(PublicResultConstant.EMAIL_ERROR, null);
        }
        if (!ComUtil.isEmpty(userService.selectOne(new EntityWrapper<SysUser>().eq("username", userRegister.getUsername())))) {
            return new PublicResult<>("用户名重复", null);
        }
        userRegister.setPassword(BCrypt.hashpw(Constant.PASSWORD, BCrypt.gensalt()));
        userRegister.setPwdFlag(2);
        boolean result = userService.register(userRegister, userRegister.getRoleIds(), requestJson.getString("url"));
        return result ? new PublicResult<>(PublicResultConstant.SUCCESS, null) :
                new PublicResult<>(PublicResultConstant.ERROR, null);
    }

    /**
     * 用户锁定和解锁
     *
     * @param userId
     * @return
     */
    @GetMapping("/lock/{userId}/{lockInfo}")
    public PublicResult<SysUser> getUserByUserName(@PathVariable("userId") String userId, @PathVariable("lockInfo") String lockInfo,
                                                   @CurrentUser SysUser sysUser, HttpServletRequest request) throws Exception {
        SysUser user = userService.selectById(userId);
        if (ComUtil.isEmpty(user)) {
            return new PublicResult<>(PublicResultConstant.INVALID_USER, null);
        }

        if (lockInfo.equals("lock")) {
            String lockReson = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()) + ","
                    + request.getLocalAddr() + "," + sysUser.getUsername() + "锁定";
            //锁定
            user.setLockflag(1);
            user.setLocktype(2);
            user.setWrongTimes(null);
            user.setUnlocktime(null);
            user.setLastwrongTime(null);
            user.setLockreason(lockReson);

        } else {
            //解锁
            user.setLockflag(0);
            user.setWrongTimes(null);
            user.setLocktype(null);
            user.setUnlocktime(null);
            user.setLockreason(null);
        }
        boolean result = userService.updateAllColumnById(user);
        return result ? new PublicResult<>(PublicResultConstant.SUCCESS, null) : new PublicResult<>(PublicResultConstant.ERROR, null);
    }


    /**
     * 修改用户
     *
     * @param requestJson
     * @return
     */
    @PutMapping
    public PublicResult<String> updateSysUser(@ValidationParam("userId,name,username,sex,deptId,mobile,email,status,roleIds")
                                              @RequestBody JSONObject requestJson) throws Exception {

        //可直接转为java对象,简化操作,不用再set一个个属性
        SysUser userUpdate = requestJson.toJavaObject(SysUser.class);

        SysUser user = userService.selectById(requestJson.getString("userId"));
        if (!user.getUsername().equals(userUpdate.getUsername()) && !ComUtil.isEmpty(userService.selectOne(new EntityWrapper<SysUser>().eq("username", userUpdate.getUsername())))) {
            return new PublicResult<>("用户名重复", null);
        }
        if (!StringUtil.checkMobileNumber(userUpdate.getMobile())) {
            return new PublicResult<>(PublicResultConstant.MOBILE_ERROR, null);
        }
        if (!StringUtil.checkEmail(userUpdate.getEmail())) {
            return new PublicResult<>(PublicResultConstant.EMAIL_ERROR, null);
        }

        boolean result = userService.updateInfo(userUpdate, userUpdate.getRoleIds(), requestJson.getString("url"));
        return result ? new PublicResult<>(PublicResultConstant.SUCCESS, null) :
                new PublicResult<>(PublicResultConstant.ERROR, null);
    }


    /**
     * 修改密码
     *
     * @return
     * @throws Exception
     */
    @PutMapping("/updatePassword")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "requestJson", value = "{\\\"userId\\\":\\\"xxx\\\",\\\"oldPassword\\\":\\\"xxx\\\"," +
                    "\\\"password\\\":\\\"xxx\\\",\\\"rePassword\\\":\\\"xxx\\\"}", required = true, dataType = "String", paramType = "body")
    })
    public PublicResult<String> updatePassword(@ValidationParam("userId,oldPassword,password,rePassword")
                                               @RequestBody JSONObject requestJson) throws Exception {
        SysUser user = userService.selectById(requestJson.getString("userId"));
        if (ComUtil.isEmpty(user)) {
            return new PublicResult<>(PublicResultConstant.INVALID_USER, null);
        }
        if (!BCrypt.checkpw(requestJson.getString("oldPassword"), user.getPassword())) {
            return new PublicResult<>("旧密码不正确", null);
        }
        if (!requestJson.getString("password").equals(requestJson.getString("rePassword"))) {
            return new PublicResult<>(PublicResultConstant.INVALID_RE_PASSWORD, null);
        }
        user.setPwdFlag(1);
        user.setPassword(BCrypt.hashpw(requestJson.getString("password"), BCrypt.gensalt()));
        userService.updateById(user);
        return new PublicResult<String>(PublicResultConstant.SUCCESS, null);
    }

    /**
     * 重置密码
     *
     * @return
     * @throws Exception
     */
    @PutMapping("/resetPassword")
    public PublicResult<String> resetPassWord(@CurrentUser SysUser user) throws Exception {
        user.setPwdFlag(2);
        user.setLockflag(0);
        user.setWrongTimes(null);
        user.setLocktype(null);
        user.setUnlocktime(null);
        user.setLockreason(null);
        user.setPassword(BCrypt.hashpw(Constant.PASSWORD, BCrypt.gensalt()));
        userService.updateAllColumnById(user);
        return new PublicResult<>(PublicResultConstant.SUCCESS, null);
    }


    @ApiOperation(value = "删除用户", notes = "根据url的id来删除用户")
    @ApiImplicitParam(name = "userId", value = "用户ID", required = true, dataType = "String", paramType = "path")
    @DeleteMapping(value = "/{userId}")
    public PublicResult<String> deleteUser(@PathVariable("userId") Long userId) {
        SysUser user = userService.selectById(userId);
        if (ComUtil.isEmpty(user)) {
            return new PublicResult<>(PublicResultConstant.INVALID_USER, null);
        }
        if (user.getDelflag() == 0) {
            return new PublicResult<>("该用户不能被删除", null);
        }
        boolean result = userService.deleteById(userId);
        if (result) {
            result = userRoleService.delete(new EntityWrapper<SysUserRole>().eq("user_id", userId));
        }
        return result ? new PublicResult<>(PublicResultConstant.SUCCESS, null) : new PublicResult<>(PublicResultConstant.ERROR, null);
    }


    @PutMapping("/info")
    public PublicResult resetUserInfo(@CurrentUser SysUser currentUser, @RequestBody JSONObject requestJson) throws Exception {

        if (!ComUtil.isEmpty(requestJson.getString("email"))) {
            currentUser.setEmail(requestJson.getString("email"));
        }
        if (!ComUtil.isEmpty(requestJson.getString("mobile"))) {
            currentUser.setMobile(requestJson.getString("mobile"));
        }
        if (!ComUtil.isEmpty(requestJson.getString("telephone"))) {
            currentUser.setTelephone(requestJson.getString("telephone"));
        }
        userService.updateById(currentUser);

        if (!ComUtil.isEmpty(requestJson.getString("url"))) {
            SysFile sysFile = new SysFile(0, requestJson.getString("url"));
            boolean result = sysFileService.insert(sysFile);
            if (result) {
                currentUser.setPicId(sysFile.getId());
                userService.updateById(currentUser);
                currentUser.setSysFile(sysFile);
            }
        }
        return new PublicResult<>(PublicResultConstant.SUCCESS, currentUser);
    }
//
//
//
//    @GetMapping("/admin/infoList")
//    @ApiOperation(value="获取用户列表", notes="需要header里加入Authorization")
//    //拥有超级管理员或管理员角色的用户可以访问这个接口
//    @RequiresRoles(value={Constant.RoleType.ADMIN,Constant.RoleType.SYS_ASMIN_ROLE},logical = Logical.OR)
//    @ApiImplicitParams({
//            @ApiImplicitParam(name = "pageIndex", value = "第几页"
//                    , dataType = "String",paramType="query"),
//            @ApiImplicitParam(name = "pageSize", value = "每页多少条"
//                    , dataType = "String",paramType="query"),
//            @ApiImplicitParam(name = "info", value = "会员名称或者电话"
//                    , dataType = "String",paramType="query"),
//            @ApiImplicitParam(name = "startTime", value = "开始时间"
//                    , dataType = "Long",paramType="query"),
//            @ApiImplicitParam(name = "endTime", value = "结束时间"
//                    , dataType = "Long",paramType="query")
//    })
//    public PublicResult findInfoList(@RequestParam(name = "pageIndex", defaultValue = "1", required = false) Integer pageIndex,
//                                     @RequestParam(name = "pageSize", defaultValue = "10", required = false) Integer pageSize,
//                                     //info-->用户名或者电话号码
//                                     @RequestParam(name = "info", defaultValue = "", required = false) String info,
//                                     @RequestParam(name = "startTime", defaultValue = "", required = false) String startTime,
//                                     @RequestParam(name = "endTime", defaultValue = "", required = false) String endTime) throws Exception{
//        //启用或禁用的用户
//        Integer []  status= {1,2};
//        //自定义分页关联查询列表
//        Page<User> userPage = userService.selectPageByConditionUser(new Page<User>(pageIndex, pageSize),info,status,
//                startTime,endTime);
//        return new PublicResult(PublicResultConstant.SUCCESS, new PageResult<>(userPage.getTotal(),pageIndex,pageSize,userPage.getRecords()));
//    }
//
//    @ApiOperation(value="获取用户详细信息", notes="根据url的id来获取用户详细信息")
//    @ApiImplicitParam(name = "userNo", value = "用户ID", required = true, dataType = "String",paramType = "path")
//    @GetMapping(value = "/{userNo}")
//    @RequiresPermissions(value = {"user:list"})
//    public PublicResult<User> findOneUser(@PathVariable("userNo") Integer userNo) {
//        User user = userService.selectById(userNo);
//        return ComUtil.isEmpty(user)?new PublicResult<>(PublicResultConstant.INVALID_USER, null): new PublicResult<>(PublicResultConstant.SUCCESS, user);
//    }
//

}

