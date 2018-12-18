package com.dmpj.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;

/**
 * @Author: Jeremy
 * @Date: 2018/12/13 20:13
 */
@Data
@Entity
@IdClass(CombinePrimaryKey.class)
public class PredictData {
    @Id
    /**
     * 数据id
     */
    private Integer id;

    @Id
    /**
     * 训练模型的类型
     */
    private Integer type;

    /**
     *  线性回归模型预测值
     */
    private Double LR;

    /**
     *  多项式回归模型预测值
     */
    private Double PLR;

    /**
     *  SVR(线性核)模型预测值
     */
    private Double SVR_LR;

    /**
     *  SVR(多项式核)模型预测值
     */
    private Double SVR_PLR;

    /**
     *  SVR(多项式核)模型预测值
     */
    private Double SVR_RBF;

    /**
     * 决策树回归模型预测值
     */
    private Double DTR;

    /**
     * 随机森林模型预测值
     */
    private Double RTR;

    /**
     * 极端随机森林模型预测值
     */
    private Double ETR;

    /**
     * 自适应增强决策树模型预测值
     */
    private Double ABR;

    /**
     * BaggingRegression模型预测值
     */
    private Double BR;

    /**
     * 梯度提升模型预测值
     */
    private Double GBR;

    /**
     * 梯度提升模型预测值
     */
    private Double XGBR;

    /**
     * 多层神经网络模型预测值
     */
    private Double MLPR;

    /**
     * BP神经网络模型预测值
     */
    private Double BPR;
}
