package com.hedian.controller;


import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.hedian.annotation.CurrentUser;
import com.hedian.annotation.ValidationParam;
import com.hedian.base.Constant;
import com.hedian.base.PageResult;
import com.hedian.base.PublicResult;
import com.hedian.base.PublicResultConstant;
import com.hedian.entity.SysUser;
import com.hedian.entity.SysUserRole;
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
    public PublicResult findList(@RequestParam(name = "pageIndex", defaultValue = "1", required = false) Integer pageIndex,
                                 @RequestParam(name = "pageSize", defaultValue = "10", required = false) Integer pageSize,
                                 @RequestParam(value = "info", defaultValue = "", required = false) String info,
                                 @RequestParam(value = "deptId", defaultValue = "", required = false) String deptId) {
        EntityWrapper<SysUser> ew = new EntityWrapper<>();
        if (!ComUtil.isEmpty(info)) {
            ew.like("username", info);
        }
        if (!ComUtil.isEmpty(deptId)) {
            ew.eq("dept_id", deptId);
        }
        Page<SysUser> page = userService.selectPage(new Page<>(pageIndex, pageSize), ew);
        page.getRecords().stream().forEach(sysUser -> {
            List<SysUserRole> sysUserRoleList = userRoleService.selectList(new EntityWrapper<SysUserRole>().eq("user_id", sysUser.getUserId()));
            sysUser.setRoleIds(sysUserRoleList.stream().map(SysUserRole::getRoleId).collect(Collectors.toList()));
        });
        return new PublicResult<PageResult>(PublicResultConstant.SUCCESS, new PageResult<>(
                page.getTotal(), pageIndex, pageSize, page.getRecords()));
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
        boolean result = userService.register(userRegister, requestJson.getJSONArray("roleId"), requestJson.getString("url"));
        return result ? new PublicResult<>(PublicResultConstant.SUCCESS, null) :
                new PublicResult<>(PublicResultConstant.ERROR, null);
    }

    /**
     * 用户锁定和解锁
     *
     * @param userId
     * @return
     */
    @GetMapping("/lock/{userId}")
    public PublicResult<SysUser> getUserByUserName(@PathVariable("userId") String userId, String lockInfo,
                                                   @CurrentUser SysUser sysUser, HttpServletRequest request) throws Exception {
        SysUser user = userService.selectById(userId);
        if (ComUtil.isEmpty(sysUser)) {
            return new PublicResult<>(PublicResultConstant.INVALID_USER, null);
        }

        if (lockInfo.equals("lock")) {
            String lockReson = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()) + ","
                    + request.getLocalAddr() + "," + sysUser.getUsername() + "锁定";
            //锁定
            user.setLockflag(1);
            user.setLocktype(2);
            user.setWrongTimes(null);
            user.setLocktype(null);
            user.setUnlocktime(null);
            user.setLastwrongTime(null);
            user.setLockreason(lockReson);

        } else {
            //解锁
            user.setLockflag(0);
            user.setLocktype(null);
            user.setWrongTimes(null);
            user.setLocktype(null);
            user.setUnlocktime(null);
            user.setLockreason(null);
        }
        boolean result = userService.updateAllColumnById(sysUser);
        return result ? new PublicResult<>(PublicResultConstant.SUCCESS, null) : new PublicResult<>(PublicResultConstant.ERROR, null);
    }


    /**
     * 修改用户
     *
     * @param requestJson
     * @return
     */
    @PutMapping
    public PublicResult<String> updateSysUser(@ValidationParam("userId,name,username,sex,deptId,mobile,email,status")
                                              @RequestBody JSONObject requestJson) throws Exception {

        //可直接转为java对象,简化操作,不用再set一个个属性
        SysUser userUpdate = requestJson.toJavaObject(SysUser.class);
        if (!StringUtil.checkMobileNumber(userUpdate.getMobile())) {
            return new PublicResult<>(PublicResultConstant.MOBILE_ERROR, null);
        }
        if (!StringUtil.checkEmail(userUpdate.getEmail())) {
            return new PublicResult<>(PublicResultConstant.EMAIL_ERROR, null);
        }
        boolean result = userService.updateInfo(userUpdate, requestJson.getJSONArray("roleIds"), requestJson.getString("url"));
        return result ? new PublicResult<>(PublicResultConstant.SUCCESS, null) :
                new PublicResult<>(PublicResultConstant.ERROR, null);
    }


    /**
     * 修改密码
     *
     * @return
     * @throws Exception
     */
    @PostMapping("/updatePassword")
    public PublicResult<String> updatePassword(@ValidationParam("userId,password,rePassword")
                                               @RequestBody JSONObject requestJson) throws Exception {
        SysUser user = userService.selectById(requestJson.getString("userId"));
        if (ComUtil.isEmpty(user)) {
            return new PublicResult<>(PublicResultConstant.INVALID_USER, null);
        }
        if (!requestJson.getString("password").equals(requestJson.getString("rePassword"))) {
            return new PublicResult<>(PublicResultConstant.INVALID_RE_PASSWORD, null);
        }
        user.setPassword(BCrypt.hashpw(requestJson.getString("passWord"), BCrypt.gensalt()));
        userService.updateById(user);
        return new PublicResult<String>(PublicResultConstant.SUCCESS, null);
    }

    /**
     * 重置密码
     *
     * @return
     * @throws Exception
     */
    @PostMapping("/resetPassword")
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
    public PublicResult<String> deleteUser(@PathVariable("userId") String userId) {
        SysUser user = userService.selectById(userId);
        if (ComUtil.isEmpty(user)) {
            return new PublicResult<>(PublicResultConstant.INVALID_USER, null);
        }
        boolean result = userService.deleteById(userId);
        if (result) {
            result = userRoleService.delete(new EntityWrapper<SysUserRole>().eq("user_id", userId));
        }
        return result ? new PublicResult<>(PublicResultConstant.SUCCESS, null) : new PublicResult<>(PublicResultConstant.ERROR, null);
    }


    @PostMapping("/info")
    public PublicResult<String> resetUserInfo(@CurrentUser SysUser currentUser, @RequestBody JSONObject requestJson) throws Exception {
        if (!ComUtil.isEmpty(requestJson.getString("name"))) {
            currentUser.setName(requestJson.getString("name"));
        }
        if (!ComUtil.isEmpty(requestJson.getString("sex"))) {
            currentUser.setSex(requestJson.getLong("sex"));
        }
        if (!ComUtil.isEmpty(requestJson.getString("email"))) {
            currentUser.setEmail(requestJson.getString("email"));
        }
        if (!ComUtil.isEmpty(requestJson.getString("mobile"))) {
            currentUser.setMobile(requestJson.getString("mobile"));
        }
//        if(!ComUtil.isEmpty(requestJson.getString("mobile"))){
//            currentUser.setJob(requestJson.getString("mobile"));
//        }
//        if(!ComUtil.isEmpty(requestJson.getString("mobile"))){
//            currentUser.setJob(requestJson.getString("mobile"));
//        }
        userService.updateById(currentUser);
        return new PublicResult<String>(PublicResultConstant.SUCCESS, null);
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

