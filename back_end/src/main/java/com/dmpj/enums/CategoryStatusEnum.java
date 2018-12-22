package com.dmpj.enums;

import lombok.Getter;

/**
 * @Author: Jeremy
 * @Date: 2018/8/30 09:44
 */
@Getter
public enum CategoryStatusEnum implements CodeEnum {
    UP(1, "上线"),
    DOWN(0, "下线");

    /**
     * 状态码.
     */
    private Integer code;

    /**
     * 状态信息.
     */
    private String message;

    CategoryStatusEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
