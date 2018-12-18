package com.dmpj.enums;

import lombok.Getter;

/**
 * @Author: Jeremy
 * @Date: 2018/8/30 09:44
 */
@Getter
public enum ResultEnum implements CodeEnum {
    SUCCESS(0, "成功"),
    ERROR(1, "失败"),
    ;
    /**
     * 状态码.
     */
    private Integer code;

    /**
     * 状态信息.
     */
    private String message;

    ResultEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
