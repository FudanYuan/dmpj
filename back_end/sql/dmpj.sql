# ************************************************************
# ************************************************************

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
# Dump of database dmpj
# ------------------------------------------------------------

SET NAMES utf8mb4;

CREATE DATABASE if NOT EXISTS `dmpj`;

USE `dmpj`;

# 训练-测试数据表
DROP TABLE IF EXISTS `dmpj_train_test_data`;
CREATE TABLE `dmpj_train_test_data` (
  `id` INT NOT NULL AUTO_INCREMENT COMMENT 'id',
  `vehicle_id` INT NOT NULL COMMENT '车辆编号',
  `charge_start_time` INT DEFAULT 0 COMMENT '开始充电时间戳',
  `charge_end_time` INT DEFAULT 0 COMMENT '结束充电时间戳',
  `mileage` DOUBLE DEFAULT NULL COMMENT '行驶里程',
  `charge_start_soc` DOUBLE DEFAULT NULL COMMENT '充电起始soc',
  `charge_end_soc` DOUBLE DEFAULT NULL COMMENT '充电结束soc',
  `charge_start_u` DOUBLE DEFAULT NULL COMMENT '充电起始电压',
  `charge_end_u` DOUBLE DEFAULT NULL COMMENT '充电结束电压',
  `charge_start_i` DOUBLE DEFAULT NULL COMMENT '充电起始电流',
  `charge_end_i` DOUBLE DEFAULT NULL COMMENT '充电结束电流',
  `charge_max_temp` DOUBLE DEFAULT NULL COMMENT '充电过程中最高温度',
  `charge_min_temp` DOUBLE DEFAULT NULL COMMENT '充电过程中最低温度',
  `charge_energy` DOUBLE DEFAULT NULL COMMENT '充电能量',

  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT '训练-测试数据表';

# 预测数据-总表
DROP TABLE IF EXISTS `dmpj_predict_data`;
CREATE TABLE `dmpj_predict_data` (
  `id` INT NOT NULL COMMENT '数据id',
  `type` INT NOT NULL COMMENT '训练模型的类型，1->补充空值;2->未补充空值表;3->进行数据标准化表;4->未进行数据标准化表',
  `lr` DOUBLE DEFAULT NULL COMMENT '线性回归模型预测值',
  `plr` DOUBLE DEFAULT NULL COMMENT '多项式回归模型预测值',
  `svr_lr` DOUBLE DEFAULT NULL COMMENT 'SVR(线性核)模型预测值',
  `svr_plr` DOUBLE DEFAULT NULL COMMENT 'SVR(多项式核)模型预测值',
  `svr_rbf` DOUBLE DEFAULT NULL COMMENT 'SVR(RBF核)模型预测值',
  `dtr` DOUBLE DEFAULT NULL COMMENT '决策树回归模型预测值',
  `rtr` DOUBLE DEFAULT NULL COMMENT '随机森林模型预测值',
  `etr` DOUBLE DEFAULT NULL COMMENT '极端随机森林模型预测值',
  `abr` DOUBLE DEFAULT NULL COMMENT '自适应增强决策树模型预测值',
  `br` DOUBLE DEFAULT NULL COMMENT 'BaggingRegression模型预测值',
  `gbr` DOUBLE DEFAULT NULL COMMENT '梯度提升模型预测值',
  `xgbr` DOUBLE DEFAULT NULL COMMENT 'X梯度提升模型预测值',
  `mlpr` DOUBLE DEFAULT NULL COMMENT '多层神经网络模型预测值',
  `bpr` DOUBLE DEFAULT NULL COMMENT 'BP神经网络模型预测值',
  PRIMARY KEY (`id`, `type`),
  FOREIGN KEY (`id`) REFERENCES dmpj_train_test_data(`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT '预测数据-总表';
/*
DROP TABLE IF EXISTS `dmpj_predict_data`;
DROP TABLE IF EXISTS `dmpj_train_test_data`;
*/