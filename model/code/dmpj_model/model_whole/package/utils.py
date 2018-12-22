import time
import pandas as pd
import numpy as np
import matplotlib.pyplot as plt

# 将时间字符串转化为时间
def str2timestamp(string):
    year = string[0:4]
    month = string[4:6]
    day = string[6:8]
    hour = string[8:10]
    minute = string[10:12]
    second = string[12:14]
    time_str = '%s-%s-%s %s:%s:%s' % (year, month, day, hour, minute, second)
    timestamp = time.mktime(time.strptime(time_str, '%Y-%m-%d %H:%M:%S'))  # 变为时间戳
    return int(timestamp)

# 将字符串转化为固定格式
def str2time(string):
    year = string[0:4]
    month = string[4:6]
    day = string[6:8]
    hour = string[8:10]
    minute = string[10:12]
    second = string[12:14]
    time_str = '%s-%s-%s %s:%s:%s' % (year, month, day, hour, minute, second)
    return time_str

# 将矩阵内的字符串转化为时间戳
def calVecTimeStamp(vector):
    timestamps = []
    for v in vector:
        timestamp = str2timestamp(str(v))
        timestamps.append(timestamp)
    return timestamps

# 计算欧式距离
def distance(vectorA=[], vectorB=[]):
    result = 0.0
    for i in range(len(vectorB)):
        x=vectorA[i]
        y=vectorB[i]
        if (not pd.isnull(y)) and (y!=0.0):
            result += (x-y)*(x-y)
    return np.sqrt(result)

# 填充数据集
def filling(vectorA=[], vectorB=[]):
    for i in range(len(vectorB)):
        x = vectorA[i]
        y = vectorB[i]
        if pd.isnull(y) or (y==0.0):
            vectorB[i] = x
    return vectorB

# 评估模型
def evaluate(y_test, y_pred):
    #评估模型
    from sklearn import metrics
    # 用scikit-learn计算MSE
    print("MSE:",metrics.mean_squared_error(y_test, y_pred))
    # 用scikit-learn计算RMSE
    print("RMSE:",np.sqrt(metrics.mean_squared_error(y_test, y_pred)))
    # 用scikit-learn计算MAE
    print("MAE:",metrics.mean_absolute_error(y_test, y_pred))
    # 正常精确度
    print("ER:", np.sum(np.abs(np.array(((y_test - y_pred) / y_test))) / len(y_test)))
    # 用比赛规则要求
    print("RANK:", getRank(y_test, y_pred))
    # R2
    print("R2:", metrics.r2_score(y_test, y_pred))


# 可视化模型结果
def visualize(y_test, y_pred):
    fig, ax = plt.subplots()
    ax.scatter(y_test, y_pred)
    ax.plot([np.array(y_test).min(), np.array(y_test).max()], [np.array(y_pred).min(), np.array(y_pred).max()], 'k--', lw=4)
    ax.set_xlabel('Measured')
    ax.set_ylabel('Predicted')
    plt.show()
   
# 获取误差
def getError(y_test, y_pre, list_algorithm):
    error = pd.DataFrame(columns=list_algorithm,index=['MSE', 'RMSE', 'MAE', 'ER', 'RANK', 'R2'])
    for i in list_columns:
        error.loc['MSE',[i]] = metrics.mean_squared_error(y_test['charge_energy'], y_pre[i])
        error.loc['RMSE',[i]] = np.sqrt(metrics.mean_squared_error(y_test['charge_energy'], y_pre[i]))
        error.loc['MAE',[i]] = metrics.mean_absolute_error(y_test['charge_energy'], y_pre[i])
        error.loc['RANK',[i]] = get_rank(y_test['charge_energy'], y_pre[i])
    return error

# 获取评分（比赛规则要求）
def getRank(y_test, y_pred):
    error_array = np.array(((y_test - y_pred) / y_test))
    error_rate = np.sqrt(np.sum(np.power(error_array,2)))
    return error_rate