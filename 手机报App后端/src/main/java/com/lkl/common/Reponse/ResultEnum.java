package com.lkl.common.Reponse;

import lombok.Data;
import lombok.Getter;

/**
 * 描述
 */

@Getter
public enum ResultEnum {

    SUCCESS(200,"成功"),
    ERROR(500, "失败");
    private final Integer code;
    private final String message;

    ResultEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

}
