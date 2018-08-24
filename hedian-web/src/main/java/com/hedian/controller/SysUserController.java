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
import com.hedian.service.ISysUserRoleService;
import com.hedian.service.ISysUserService;
import com.hedian.util.ComUtil;
import com.hedian.util.StringUtil;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

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
    public PublicResult findList(@RequestParam(name = "pageIndex", defaultValue = "1", required = false) Integer pageIndex,
                                 @RequestParam(name = "pageSize", defaultValue = "10", required = false) Integer pageSize,
                                 @RequestParam(value = "userName", defaultValue = "", required = false) String userName,
                                 @RequestParam(value = "deptId", defaultValue = "", required = false) String deptId) {
        EntityWrapper<SysUser> ew = new EntityWrapper<>();
        if (!ComUtil.isEmpty(userName)) {
            ew.like("user_name", userName);
        }
        if (!ComUtil.isEmpty(deptId)) {
            ew.eq("dept_id", deptId);
        }
        Page<SysUser> page = userService.selectPage(new Page<>(pageIndex, pageSize), ew);
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
    public PublicResult<String> addSysUser(@ValidationParam("name,userName,sex,deptId,mobile,telephone,email,status,roleIds,url")
                                           @RequestBody JSONObject requestJson) throws Exception {

        //可直接转为java对象,简化操作,不用再set一个个属性
        SysUser userRegister = requestJson.toJavaObject(SysUser.class);
        if (!StringUtil.checkMobileNumber(userRegister.getMobile())) {
            return new PublicResult<>(PublicResultConstant.MOBILE_ERROR, null);
        }
        if (!StringUtil.checkEmail(userRegister.getEmail())) {
            return new PublicResult<>(PublicResultConstant.EMAIL_ERROR, null);
        }
        if (!ComUtil.isEmpty(userService.selectOne(new EntityWrapper<SysUser>().eq("username",userRegister.getUsername())))) {
            return new PublicResult<>("用户名重复", null);
        }
        userRegister.setPassword(BCrypt.hashpw(Constant.PASSWORD, BCrypt.gensalt()));
        userRegister.setPwdFlag(2);
        boolean result = userService.register(userRegister, requestJson.getJSONArray("roleId"),requestJson.getString("url"));
        return result ? new PublicResult<>(PublicResultConstant.SUCCESS, null) :
                new PublicResult<>(PublicResultConstant.ERROR, null);
    }


    /**
     * 用户详情
     *
     * @param userId
     * @return
     */
    @GetMapping(value = "/{userId}")
    public PublicResult<Map<String, Object>> getById(@PathVariable("userId") Long userId) throws Exception {
        Map<String, Object> result = userService.getUserInfoAndRoles(userId);
        return new PublicResult<>(PublicResultConstant.SUCCESS, result);
    }

    /**
     * 修改用户
     * @param requestJson
     * @return
     */
    @PutMapping
    public PublicResult<String> updateSysUser(@ValidationParam("userId,name,userName,sex,deptId,mobile,telephone,email,status,roleIds,url")
                                              @RequestBody JSONObject requestJson) throws Exception {

        //可直接转为java对象,简化操作,不用再set一个个属性
        SysUser userUpdate = requestJson.toJavaObject(SysUser.class);
        if (!StringUtil.checkMobileNumber(userUpdate.getMobile())) {
            return new PublicResult<>(PublicResultConstant.MOBILE_ERROR, null);
        }
        if (!StringUtil.checkEmail(userUpdate.getEmail())) {
            return new PublicResult<>(PublicResultConstant.EMAIL_ERROR, null);
        }
        boolean result = userService.updateInfo(userUpdate, requestJson.getJSONArray("roleIds"),requestJson.getString("url"));
        return result ? new PublicResult<>(PublicResultConstant.SUCCESS, null) :
                new PublicResult<>(PublicResultConstant.ERROR, null);
    }


    /**
     * 修改密码
     *
     * @return
     * @throws Exception
     */
    @PostMapping("/password")
    public PublicResult<String> resetPassWord(@ValidationParam("userId,password,rePassword")
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

    @ApiOperation(value="删除用户", notes="根据url的id来删除用户")
    @ApiImplicitParam(name = "userId", value = "用户ID", required = true, dataType = "String",paramType = "path")
    @DeleteMapping(value = "/{userId}")
    public PublicResult<String> deleteUser(@PathVariable("userId") String userId) {
        SysUser user = userService.selectById(userId);
        if (ComUtil.isEmpty(user)) {
            return new PublicResult<>(PublicResultConstant.INVALID_USER, null);
        }
        boolean result = userService.deleteById(userId);
        return result?new PublicResult<>(PublicResultConstant.SUCCESS, null): new PublicResult<>(PublicResultConstant.ERROR, null);
    }

//
//    @PostMapping("/info")
//    public PublicResult<String> resetUserInfo (@CurrentUser User currentUser,@RequestBody JSONObject requestJson) throws Exception{
//        if(!ComUtil.isEmpty(requestJson.getString("userName"))){
//            currentUser.setUserName(requestJson.getString("userName"));
//        }
//        if(!ComUtil.isEmpty(requestJson.getString("avatar"))){
//            currentUser.setAvatar(requestJson.getString("avatar"));
//        }
//        if(!ComUtil.isEmpty(requestJson.getString("unit"))){
//            currentUser.setUnit(requestJson.getString("unit"));
//        }
//        if(!ComUtil.isEmpty(requestJson.getString("job"))){
//            currentUser.setJob(requestJson.getString("job"));
//        }
//        userService.updateById(currentUser);
//        return  new PublicResult<String>(PublicResultConstant.SUCCESS, null);
//    }
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

