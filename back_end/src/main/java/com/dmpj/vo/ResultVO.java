package com.dmpj.vo;

import lombok.Data;

/**
 * http请求返回的最外层对象
 *
 * @Author: Jeremy
 * @Date: 2018/8/29 15:05
 */
@Data
public class ResultVO<T> {
    /**
     * 错误码.
     */
    private Integer code;

    /**
     * 提示信息.
     */
    private String msg;

    /**
     * 具体内容.
     */
    private T data;
}
