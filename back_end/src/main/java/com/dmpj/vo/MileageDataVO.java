package com.dmpj.vo;

import lombok.Data;

/**
 * @Author: Jeremy
 * @Date: 2018/12/18 13:14
 */
@Data
public class MileageDataVO {
    /**
     * 开始时间戳
     */
    private Long time;

    /**
     * 里程数
     */
    private Double mileage;
}
