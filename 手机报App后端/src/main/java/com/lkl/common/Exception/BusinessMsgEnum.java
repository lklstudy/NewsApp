package com.lkl.common.Exception;

import lombok.Getter;

@Getter
public enum BusinessMsgEnum {

    INVALID_PARAMETER(400, "Invalid parameter"),
    UNAUTHORIZED(401, "Unauthorized"),
    NOT_FOUND(404, "Not found"),
    OPERATE_FAIL(501, "操作失败"),
    INTERNAL_SERVER_ERROR(500, "Internal server error");

    private final int code;
    private final String message;

    BusinessMsgEnum(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
