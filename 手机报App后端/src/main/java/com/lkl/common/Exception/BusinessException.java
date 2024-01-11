package com.lkl.common.Exception;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 描述
 */
@Data
@NoArgsConstructor
public class BusinessException extends RuntimeException{
//    注意包装类的默认值为null
    private Integer code;
    private String message;
    private BusinessMsgEnum errEnum;

    public BusinessException(BusinessMsgEnum code) {
        this.errEnum = code;
    }

    public BusinessException(Throwable cause, BusinessMsgEnum errEnum){
        super(cause);
        this.errEnum = errEnum;
    }

    public BusinessException(Integer code,String message){
        this.code = code;
        this.message = message;
    }

    public BusinessException(String message){
        this.message = message;
    }


}
