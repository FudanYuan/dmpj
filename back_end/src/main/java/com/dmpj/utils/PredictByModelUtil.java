package com.dmpj.utils;

import com.dmpj.entity.PredictData;
import com.dmpj.enums.ModelEnum;

/**
 * @Author: Jeremy
 * @Date: 2018/12/18 14:23
 */
public class PredictByModelUtil {
    public static Double getPredictDataByModel(PredictData predictData, ModelEnum modelEnum){
        Double value = 0D;
        switch (modelEnum){
            case LINEAR_REGRESSION:
                value = predictData.getLR();
                break;
            case POLYNOMIAL_REGRESSION:
                value = predictData.getPLR();
                break;
            case SVR_WITH_LINEAR_KERNEL_REGRESSION:
                value = predictData.getSVR_LR();
                break;
            case SVR_WITH_POLYNOMIAL_KERNEL_REGRESSION:
                value = predictData.getSVR_PLR();
                break;
            case SVR_WITH_RBF_KERNEL_REGRESSION:
                value = predictData.getSVR_RBF();
                break;
            case DECISION_TREE_REGRESSION:
                value = predictData.getDTR();
                break;
            case ADA_BOOST_REGRESSION:
                value = predictData.getABR();
                break;
            case RANDOM_TREE_REGRESSION:
                value = predictData.getRTR();
                break;
            case EXTRA_TREE_REGRESSION:
                value = predictData.getETR();
                break;
            case BAGGING_REGRESSION:
                value = predictData.getBR();
                break;
            case GB_BOOST_REGRESSION:
                value = predictData.getGBR();
                break;
            case XGB_BOOST_REGRESSION:
                value = predictData.getXGBR();
                break;
            case MLP_REGRESSION:
                value = predictData.getMLPR();
                break;
            case BP_REGRESSION:
                value = predictData.getBPR();
                break;
            default:
                break;
        }

        return value;
    }
}
