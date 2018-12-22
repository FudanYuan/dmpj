package com.dmpj.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * 类目
 *
 * @Author: Jeremy
 * @Date: 2018/8/30 09:38
 */
@Data
public class CategoryVO {

    @JsonProperty("name")
    private String name;

    @JsonProperty("type")
    private Integer type;

    @JsonProperty("icon")
    private String icon;
}
