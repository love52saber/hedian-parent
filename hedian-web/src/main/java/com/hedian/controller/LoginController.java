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

import java.text.SimpleDateFormat;
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
    public PublicResult login(
            @ValidationParam("username,password") @RequestBody JSONObject requestJson) throws Exception {
        //由于 @ValidationParam注解已经验证过mobile和password参数，所以可以直接get使用没毛病。
       SysUser user = requestJson.toJavaObject(SysUser.class);
        Map<String, Object> result = userService.login(user);
        return new PublicResult<>(PublicResultConstant.SUCCESS, result);
    }

}
