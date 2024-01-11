package com.lkl.common.Reponse;

import com.lkl.common.Exception.BusinessMsgEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 描述  全局统一返回
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Result<T>{
    private Integer code;
    private String message;
    private T data;

    public static <T> Result<T> success(){
        Result<T> result = new Result<>();
        result.setCode(ResultEnum.SUCCESS.getCode());
        result.setMessage(ResultEnum.SUCCESS.getMessage());
        return result;
    }
    public static <T> Result<T> success(T data){
        Result<T> result = new Result<>();
        result.setCode(ResultEnum.SUCCESS.getCode());
        result.setMessage(ResultEnum.SUCCESS.getMessage());
        result.setData(data);
        return result;
    }

    public static Result error(Integer code, String msg){
        Result result = new Result();
        result.setCode(code);
        result.setMessage(msg);
        return result;
    }

    public static Result internalError(String message) {
        Result resp = new Result();
        resp.setCode(BusinessMsgEnum.INTERNAL_SERVER_ERROR.getCode());
        resp.setMessage(message);

        return resp;
    }

    public static Result operateError(String message) {
        Result resp = new Result();

        resp.setCode(BusinessMsgEnum.OPERATE_FAIL.getCode());
        resp.setMessage(message);

        return resp;
    }
}
