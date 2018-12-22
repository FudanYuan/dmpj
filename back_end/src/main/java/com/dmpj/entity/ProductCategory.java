package com.hitao.hitao.entity;

import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

/**
 * @Author: Jeremy
 * @Date: 2018/8/29 10:11
 */
@Entity
@DynamicUpdate
@Data
public class ProductCategory {

    /**
     * 类目id，主键，自增.
     */
    @Id
    @GeneratedValue
    private Integer id;

    /**
     * 类目名称.
     */
    private String name;

    /**
     * 类目编号.
     */
    private Integer type;

    /**
     * 类目icon.
     */
    private String icon;

    /**
     * 类目状态.
     */
    private Integer status;

    /**
     * 创建时间.
     */
    private Date createTime;

    /**
     * 更新时间.
     */
    private Date updateTime;
}
