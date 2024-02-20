package com.example.userproject.enums;

public enum ErrorCodeEnum {
    USER_NOT_FOUND(1001, "Can not find user with given id"),
    VALIDATION_ERROR(1002, " is not valid"),
    ACCESS_DENIED(1003, "Access is denied"),
    UNKNOWN_ERROR(1000, "Unknown error");


    private final int code;
    private final String message;

    ErrorCodeEnum(int code, String message) {
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
