package com.dmpj.entity;

import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

/**
 * @Author: Jeremy
 * @Date: 2018/8/29 10:11
 */
@Entity
@Data
public class TrainTestData {
    /**
     * id，主键，自增.
     */
    @Id
    @GeneratedValue
    private Integer id;

    /**
     * 车辆编号.
     */
    private Integer vehicleId;

    /**
     * 充电开始时间.
     */
    private Long chargeStartTime;

    /**
     * 充电结束时间.
     */
    private Long chargeEndTime;

    /**
     * 行驶里程.
     */
    private Double mileage;

    /**
     * 充电起始soc.
     */
    private Double chargeStartSoc;

    /**
     * 充电结束soc.
     */
    private Double chargeEndSoc;

    /**
     * 充电起始电压.
     */
    @Column(name = "charge_start_u")
    private Double chargeStartU;

    /**
     * 充电结束电压.
     */
    @Column(name = "charge_end_u")
    private Double chargeEndU;

    /**
     * 充电起始电流.
     */
    @Column(name = "charge_start_i")
    private Double chargeStartI;

    /**
     * 充电结束电流.
     */
    @Column(name = "charge_end_i")
    private Double chargeEndI;

    /**
     * 充电最高温度.
     */
    private Double chargeMaxTemp;

    /**
     * 充电最低温度.
     */
    private Double chargeMinTemp;

    /**
     * 充点能量.
     */
    private Double chargeEnergy;
}
