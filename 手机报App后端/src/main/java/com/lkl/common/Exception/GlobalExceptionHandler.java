package com.lkl.common.Exception;

import com.lkl.common.Reponse.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.lang.reflect.Constructor;

/**
 * 描述
 */

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(BusinessException.class)
    public Result BusinessException(BusinessException e){
        log.error("发生自定义异常，exception = {}", e.getMessage(), e);
        if (ObjectUtils.isEmpty(e.getErrEnum())){
            if (ObjectUtils.isEmpty(e.getCode())){
                return Result.error(BusinessMsgEnum.OPERATE_FAIL.getCode(), e.getMessage());
            }
            return Result.error(e.getCode(),e.getMessage());
        }else {
            BusinessMsgEnum errEnum = e.getErrEnum();
            return Result.operateError(errEnum.getMessage());
        }
    }

    @ExceptionHandler(Exception.class)
    public Result handleException(Exception e) {
        log.error("发生全局异常未知，exception = {}", e.getMessage(), e);
        return Result.internalError(e.getMessage());
    }

    @ExceptionHandler(NullPointerException.class)
    public Result nullPointerException(NullPointerException e) {
        log.error("空指针异常 NullPointerException, exception = {}", e.getMessage(),e);
        return Result.internalError(e.getMessage());
    }


}
