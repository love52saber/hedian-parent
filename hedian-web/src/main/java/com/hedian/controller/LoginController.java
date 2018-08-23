package com.hedian.controller;

import com.alibaba.fastjson.JSONObject;
import com.hedian.annotation.Log;
import com.hedian.annotation.Pass;
import com.hedian.annotation.ValidationParam;
import com.hedian.base.Constant;
import com.hedian.base.PublicResult;
import com.hedian.base.PublicResultConstant;
import com.hedian.entity.SmsVerify;
import com.hedian.entity.SysUser;
import com.hedian.entity.User;
import com.hedian.service.ISmsVerifyService;
import com.hedian.service.ISysUserService;
import com.hedian.service.IUserService;
import com.hedian.util.ComUtil;
import com.hedian.util.SmsSendUtil;
import com.hedian.util.StringUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

import java.util.List;
import java.util.Map;

/**
 *  登录接口
 * @author hedian
 * @since 2018-05-03
 */
@RestController
@RequestMapping("/api")
@Api(description="身份认证模块")
public class LoginController {
    @Autowired
    private ISysUserService userService;


    @PostMapping("/login")
    @Log(description="前台密码登录接口:/login")
    @Pass
    public PublicResult<Map<String, Object>> login(
            @ValidationParam("username,password")@RequestBody JSONObject requestJson) throws Exception{
        //由于 @ValidationParam注解已经验证过mobile和passWord参数，所以可以直接get使用没毛病。
        String userName = requestJson.getString("username");
        SysUser user = userService.getUserByUserName(userName);
        if (ComUtil.isEmpty(user) || !BCrypt.checkpw(requestJson.getString("password"), user.getPassword())) {
            return new PublicResult<>(PublicResultConstant.INVALID_USERNAME_PASSWORD, null);
        }
        Map<String, Object> result = userService.getLoginUserAndMenuInfo(user);
        return new PublicResult<>(PublicResultConstant.SUCCESS, result);
    }



//    @ApiOperation(value="手机密码登录", notes="body体参数,不需要Authorization",produces = "application/json")
//    @ApiImplicitParams({
//            @ApiImplicitParam(name = "requestJson", value = "{\"mobile\":\"17765071662\",\"passWord\":\"123456\"}"
//                    , required = true, dataType = "String",paramType="body")
//    })
//    @PostMapping("/login")
//    @Log(description="前台密码登录接口:/login")
//    @Pass
//    public PublicResult<Map<String, Object>> login(
//            @ValidationParam("mobile,passWord")@RequestBody JSONObject requestJson) throws Exception{
//        //由于 @ValidationParam注解已经验证过mobile和passWord参数，所以可以直接get使用没毛病。
//        String mobile = requestJson.getString("mobile");
//        if(!StringUtil.checkMobileNumber(mobile)){
//            return new PublicResult<>(PublicResultConstant.MOBILE_ERROR, null);
//        }
//        User user = userService.getUserByMobile(mobile);
//        if (ComUtil.isEmpty(user) || !BCrypt.checkpw(requestJson.getString("passWord"), user.getPassWord())) {
//            return new PublicResult<>(PublicResultConstant.INVALID_USERNAME_PASSWORD, null);
//        }
//        Map<String, Object> result = userService.getLoginUserAndMenuInfo(user);
//        return new PublicResult<>(PublicResultConstant.SUCCESS, result);
//    }
//
//    @ApiOperation(value="短信验证码登录", notes="body体参数,不需要Authorization",produces = "application/json")
//    @ApiImplicitParams({
//            @ApiImplicitParam(name = "requestJson", value = "{\"mobile\":\"17765071662\",\"captcha\":\"5780\"}"
//                    , required = true, dataType = "String",paramType="body")
//    })
//    @PostMapping("/login/captcha")
//    @Log(description="前台短信验证码登录接口:/login/captcha")
//    @Pass
//    public PublicResult<Map<String, Object>> loginBycaptcha(
//            @ValidationParam("mobile,captcha")@RequestBody JSONObject requestJson) throws Exception{
//        String mobile = requestJson.getString("mobile");
//        if(!StringUtil.checkMobileNumber(mobile)){
//            return new PublicResult<>(PublicResultConstant.MOBILE_ERROR, null);
//        }
//        User user = userService.getUserByMobile(mobile);
//        if (ComUtil.isEmpty(user)) {
//            return new PublicResult<>(PublicResultConstant.INVALID_USER, null);
//        }
//        List<SmsVerify> smsVerifies = smsVerifyService.getByMobileAndCaptchaAndType(mobile,
//                requestJson.getString("captcha"), SmsSendUtil.SMSType.getType(SmsSendUtil.SMSType.AUTH.name()));
//        if(ComUtil.isEmpty(smsVerifies)){
//            return new PublicResult<>(PublicResultConstant.VERIFY_PARAM_ERROR, null);
//        }
//        if(SmsSendUtil.isCaptchaPassTime(smsVerifies.get(0).getCreateTime())){
//            return new PublicResult<>(PublicResultConstant.VERIFY_PARAM_PASS, null);
//        }
//        Map<String, Object> result = userService.getLoginUserAndMenuInfo(user);
//        return new PublicResult<>(PublicResultConstant.SUCCESS, result);
//    }
//
//
//
//    @ApiOperation(value="手机验证码注册", notes="body体参数,不需要Authorization",produces = "application/json")
//    @ApiImplicitParams({
//            @ApiImplicitParam(name = "requestJson", value = "{\"userName\":\"hedian\",\"mobile\":\"17765071662\",</br>" +
//                    "\"captcha\":\"5780\",\"passWord\":\"123456\",</br>\"rePassWord\":\"123456\",\"job\":\"java开发\"," +
//                    "</br>\"unit(可不传)\":\"xxx公司\"}"
//                    , required = true, dataType = "String",paramType="body")
//    })
//    @PostMapping("/register")
//    @Log(description="注册接口:/register")
//    @Pass
//    public PublicResult<User> register(@ValidationParam("userName,passWord,rePassWord,mobile,captcha,job")
//                                       @RequestBody JSONObject requestJson) {
//        //可直接转为java对象,简化操作,不用再set一个个属性
//        User userRegister = requestJson.toJavaObject(User.class);
//        if(!StringUtil.checkMobileNumber(userRegister.getMobile())){
//            return new PublicResult<>(PublicResultConstant.MOBILE_ERROR, null);
//        }
//        if (!userRegister.getPassWord().equals(requestJson.getString("rePassWord"))) {
//            return new PublicResult<>(PublicResultConstant.INVALID_RE_PASSWORD, null);
//        }
//        List<SmsVerify> smsVerifies = smsVerifyService.getByMobileAndCaptchaAndType(userRegister.getMobile(),
//                requestJson.getString("captcha"), SmsSendUtil.SMSType.getType(SmsSendUtil.SMSType.REG.name()));
//        if(ComUtil.isEmpty(smsVerifies)){
//            return new PublicResult<>(PublicResultConstant.VERIFY_PARAM_ERROR, null);
//        }
//        //验证码是否过期
//        if(SmsSendUtil.isCaptchaPassTime(smsVerifies.get(0).getCreateTime())){
//            return new PublicResult<>(PublicResultConstant.VERIFY_PARAM_PASS, null);
//        }
//        userRegister.setPassWord(BCrypt.hashpw(requestJson.getString("passWord"), BCrypt.gensalt()));
//        //默认注册普通用户
//        boolean result = userService.register(userRegister, Constant.RoleType.USER);
//        return result? new PublicResult<>(PublicResultConstant.SUCCESS, null):
//                new PublicResult<>("注册失败，请联系管理员！",null);
//    }
//
//
//    @ApiOperation(value="忘记密码", notes="body体参数,不需要Authorization",produces = "application/json")
//    @ApiImplicitParams({
//            @ApiImplicitParam(name = "requestJson", value = "{\"mobile\":\"17765071662\",\"captcha\":\"5780\",</br>" +
//                    "\"passWord\":\"123456\",\"rePassWord\":\"123456\"}"
//                    , required = true, dataType = "String",paramType="body")
//    })
//    @PostMapping("/forget/password")
//    @Pass
//    public PublicResult<String> resetPassWord (@ValidationParam("mobile,captcha,passWord,rePassWord")
//                                               @RequestBody JSONObject requestJson ) throws Exception{
//        String mobile = requestJson.getString("mobile");
//        if(!StringUtil.checkMobileNumber(mobile)){
//            return new PublicResult<>(PublicResultConstant.MOBILE_ERROR, null);
//        }
//        if (!requestJson.getString("passWord").equals(requestJson.getString("rePassWord"))) {
//            return new PublicResult<>(PublicResultConstant.INVALID_RE_PASSWORD, null);
//        }
//        User user = userService.getUserByMobile(mobile);
//        if(ComUtil.isEmpty(user)){
//            return new PublicResult<>(PublicResultConstant.INVALID_USER, null);
//        }
//        List<SmsVerify> smsVerifies = smsVerifyService.getByMobileAndCaptchaAndType(mobile,
//                requestJson.getString("captcha"), SmsSendUtil.SMSType.getType(SmsSendUtil.SMSType.FINDPASSWORD.name()));
//        if(ComUtil.isEmpty(smsVerifies)){
//            return new PublicResult<>(PublicResultConstant.VERIFY_PARAM_ERROR, null);
//        }
//        if(SmsSendUtil.isCaptchaPassTime(smsVerifies.get(0).getCreateTime())){
//            return new PublicResult<>(PublicResultConstant.VERIFY_PARAM_PASS, null);
//        }
//        user.setPassWord(BCrypt.hashpw(requestJson.getString("passWord"),BCrypt.gensalt()));
//        userService.updateById(user);
//        return  new PublicResult<String>(PublicResultConstant.SUCCESS, null);
//    }

}
