package com.dmpj.enums;

import lombok.Getter;

/**
 * @Author: Jeremy
 * @Date: 2018/12/13 22:37
 */
@Getter
public enum ModelEnum implements CodeEnum {
    LINEAR_REGRESSION(0, "线性回归模型"),
    POLYNOMIAL_REGRESSION(1, "多项式回归模型"),
    SVR_WITH_LINEAR_KERNEL_REGRESSION(2, "SVR(线性核)模型"),
    SVR_WITH_POLYNOMIAL_KERNEL_REGRESSION(3, "SVR(多项式核)模型预测值"),
    SVR_WITH_RBF_KERNEL_REGRESSION(4, "SVR(RBF核)模型预测值"),
    DECISION_TREE_REGRESSION(5, "决策树回归模型预测值"),
    ADA_BOOST_REGRESSION(6, "自适应增强决策树模型预测值"),
    RANDOM_TREE_REGRESSION(7, "随机森林模型预测值"),
    EXTRA_TREE_REGRESSION(8, "极端随机森林模型预测值"),
    BAGGING_REGRESSION(9, "打包回归模型预测值"),
    GB_BOOST_REGRESSION(10, "梯度提升模型预测值"),
    XGB_BOOST_REGRESSION(11, "X梯度提升模型预测值"),
    MLP_REGRESSION(12, "多层神经网络模型预测值"),
    BP_REGRESSION(13, "BP神经网络模型预测值"),
    ;
    /**
     * 状态码.
     */
    private Integer code;

    /**
     * 状态信息.
     */
    private String message;

    ModelEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
