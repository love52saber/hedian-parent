package com.hedian.controller;

import com.alibaba.fastjson.JSONObject;
import com.hedian.annotation.Log;
import com.hedian.annotation.Pass;
import com.hedian.annotation.ValidationParam;
import com.hedian.base.PublicResult;
import com.hedian.base.PublicResultConstant;
import com.hedian.entity.SysUser;
import com.hedian.service.ISysUserService;
import com.hedian.util.ComUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import org.apache.tools.ant.util.DateUtils;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.Map;

/**
 * 登录接口
 *
 * @author hedian
 * @since 2018-05-03
 */
@RestController
@RequestMapping("/api")
@Api(description = "身份认证模块")
public class LoginController {
    @Autowired
    private ISysUserService userService;


    @PostMapping("/login")
    @Log(description = "前台密码登录接口:/login")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "requestJson", value = "{\\\"username\\\":\\\"admin\\\",\\\"password\\\":\\\"XXXX\\\"}", required = true, dataType = "String", paramType = "body")
    })
    @Pass
    public PublicResult<Map<String, Object>> login(
            @ValidationParam("username,password") @RequestBody JSONObject requestJson) throws Exception {
        //由于 @ValidationParam注解已经验证过mobile和password参数，所以可以直接get使用没毛病。
        String userName = requestJson.getString("username");
        SysUser user = userService.getUserByUserName(userName);
        if (ComUtil.isEmpty(user)) {
            return new PublicResult<>(PublicResultConstant.INVALID_USERNAME_PASSWORD, null);
        }
        if (user.getStatus().equals(0)) {
            return new PublicResult<>("该用户已被禁用请联系管理员", null);
        }
        if (!ComUtil.isEmpty(user.getLocktype()) && !ComUtil.isEmpty(user.getLocktype())) {
            if (user.getLockflag().equals(1) && user.getLocktype().equals(1)) {
                if (user.getUnlocktime().getTime() > System.currentTimeMillis()) {
                    return new PublicResult<>("用户已锁定请联系管理员", null);
                }
            }
            if (user.getLockflag().equals(1) && user.getLocktype().equals(2)) {
                return new PublicResult<>("用户已锁定请联系管理员", null);
            }
        }


        if (!BCrypt.checkpw(requestJson.getString("password"), user.getPassword())) {
            if (!ComUtil.isEmpty(user.getLastwrongTime()) && user.getLastwrongTime().getTime() < System.currentTimeMillis() + (30 * 60 * 1000)) {
                user.setWrongTimes((ComUtil.isEmpty(user.getWrongTimes())) ? 1 : user.getWrongTimes() + 1);
            } else {
                user.setWrongTimes(1);
            }
            user.setLastwrongTime(new Date());
            if (user.getWrongTimes().equals(5)) {
                user.setLockreason(DateUtils.format(new Date(), "yyyy-MM-dd HH:mm:ss") + "密码输入错误次数超过5次");
                user.setLocktype(1);
                user.setLockflag(1);
                user.setUnlocktime(new Date(System.currentTimeMillis() + (30 * 60 * 1000)));
                user.setLastwrongTime(null);
                user.setWrongTimes(null);
            }
            userService.updateAllColumnById(user);
            return new PublicResult<>(!ComUtil.isEmpty(user.getWrongTimes()) ? "用户名或密码错误，剩余" + (5 - user.getWrongTimes()) + "将被锁定" : "用户已锁定请联系管理员", null);
        }
        Map<String, Object> result = userService.getLoginUserAndMenuInfo(user);
        //用户被锁定 登录完清空消息
        if (!ComUtil.isEmpty(user.getLockflag()) && user.getLockflag() != 0) {
            user.setUnlocktime(null);
            user.setLocktype(null);
            user.setLockflag(0);
            user.setLockreason(null);
            user.setLastwrongTime(null);
            userService.updateAllColumnById(user);
        }
        return new PublicResult<>(PublicResultConstant.SUCCESS, result);
    }


    public static void main(String[] args) {

        System.out.println("1,2,3");

        System.out.println(BCrypt.hashpw("12345678", BCrypt.gensalt()));
    }


}
