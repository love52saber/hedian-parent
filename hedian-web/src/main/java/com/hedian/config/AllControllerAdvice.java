package com.hedian.config;

import com.hedian.base.BaseResult;
import com.hedian.base.BusinessException;
import com.hedian.base.PublicResult;
import com.hedian.base.PublicResultConstant;
import com.hedian.exception.ParamJsonException;
import com.hedian.exception.UnauthorizedException;
import org.apache.shiro.ShiroException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.thymeleaf.exceptions.TemplateInputException;

/**
 * Controller统一异常处理
 * @author : hedian
 * @date : 2018/05/08
 */
@RestControllerAdvice
public class AllControllerAdvice {
    private static Logger logger = LoggerFactory.getLogger(AllControllerAdvice.class);

    /**
     * 应用到所有@RequestMapping注解方法，在其执行之前初始化数据绑定器
     */
    @InitBinder
    public void initBinder(WebDataBinder binder) {
    }

    /**
     * 把值绑定到Model中，使全局@RequestMapping可以获取到该值
     */
    @ModelAttribute
    public void addAttributes(Model model) {
    }

    /**
     * 全局异常捕捉处理
     */
    @ResponseBody
    @ExceptionHandler(value = Exception.class)
    public PublicResult<String> errorHandler(Exception ex) {
        ex.printStackTrace();
        logger.error("接口出现严重异常：{}", ex.getMessage());
        return new PublicResult<>(PublicResultConstant.FAILED, null);
    }

    /**
     * 捕捉UnauthorizedException
     * @return
     */
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(value = UnauthorizedException.class)
    @ResponseBody
    public PublicResult<String> handleUnauthorized() {
        return new PublicResult<>(PublicResultConstant.USER_NO_PERMITION, null);
    }

    /**
     * 捕捉shiro的异常
     * @param e
     * @return
     */
    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler(ShiroException.class)
    @ResponseBody
    public PublicResult<String> handleShiroException(ShiroException e) {
        return new PublicResult<>(PublicResultConstant.USER_NO_PERMITION, null);
    }

    /**
     * 捕捉BusinessException自定义抛出的异常
     * @return
     */
    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler(BusinessException.class)
    @ResponseBody
    public BaseResult handleBusinessException(BusinessException e) {
        if(e instanceof BusinessException) {
            logger.info("数据操作失败："+e.getMessage());
            return new BaseResult(PublicResultConstant.DATA_ERROR.getResult(), e.getMessage(),null);
        }
        return new PublicResult(PublicResultConstant.ERROR, null);
    }

    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler(TemplateInputException.class)
    @ResponseBody
    public PublicResult<String> handleTemplateInputException(TemplateInputException e) {
        return new PublicResult<>(PublicResultConstant.USER_NO_PERMITION, null);
    }

    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler(value = ParamJsonException.class)
    @ResponseBody
    public BaseResult<String> handleParamJsonException(Exception e) {
        if(e instanceof ParamJsonException) {
            logger.info("参数错误："+e.getMessage());
            return new BaseResult<>(PublicResultConstant.PARAM_ERROR.getResult(), e.getMessage(),null);
        }
        return new PublicResult<>(PublicResultConstant.ERROR, null);
    }


}