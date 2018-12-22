import time
import pandas as pd
import numpy as np
import matplotlib.pyplot as plt

# 训练模型的类型:
# 1->补充空值+数据标准化;
# 2->未补充空值+数据标准化;
# 3->补充空值+进行数据标准化;
# 4->补充空值+未进行数据标准化
type_dict = {
    1 : 'with_fill',
    2 : 'without_fill'
}

# 返回模型处理数据的类型
def getType(id):
    return type_dict[id]

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
def evaluate(y_test, y_pred, score=''):
    #评估模型
    from sklearn import metrics
    mse = metrics.mean_squared_error(y_test, y_pred)
    rmse = np.sqrt(metrics.mean_squared_error(y_test, y_pred))
    mae = metrics.mean_absolute_error(y_test, y_pred)
    rank = getRank(y_test, y_pred)
    acc = 1 - np.sum(np.abs(np.array(((y_test - y_pred) / y_test))) / len(y_test))
    r2 = metrics.r2_score(y_test, y_pred)

    score_ret = {}
    score_ret['MSE'] = mse
    score_ret['RMSE'] = rmse
    score_ret['MAE'] = mae
    score_ret['RANK'] = rank
    score_ret['ACC'] = acc
    score_ret['R2'] = r2

    if score == '':
        # 用scikit-learn计算MSE
        print("MSE:", mse)
        # 用scikit-learn计算RMSE
        print("RMSE:", mse)
        # 用scikit-learn计算MAE
        print("MAE:", mae)
        # 用比赛规则要求
        print("RANK:", rank)
        # 正常精确度
        print("ACC:", acc)
        # R2
        print("R2:", r2)
        return score_ret
    else:
        print(score, score_ret[score])
        return {score : score_ret[score]}


# 可视化模型结果
def visualize(y_test, y_pred):
    fig, ax = plt.subplots()
    ax.scatter(y_test, y_pred)
    ax.plot([np.array(y_test).min(), np.array(y_test).max()], [np.array(y_pred).min(), np.array(y_pred).max()], 'k--', lw=4)
    ax.set_xlabel('Measured')
    ax.set_ylabel('Predicted')
    plt.show()

# 获取误差
def getScore(y_test, y_pre, list_algorithm):
    score = pd.DataFrame(columns=list_algorithm,index=['MSE', 'RMSE', 'MAE', 'RANK', 'ACC', 'R2'])
    for i in list_algorithm:
        score_dic = evaluate(y_test, y_pre[i])
        score.loc['MSE',[i]] = score_dic['MSE']
        score.loc['RMSE',[i]] = score_dic['RMSE'] #np.sqrt(metrics.mean_squared_error(y_test, y_pre[i]))
        score.loc['MAE',[i]] = score_dic['MAE'] # metrics.mean_absolute_error(y_test, y_pre[i])
        score.loc['ACC',[i]] = score_dic['ACC'] # get_rank(y_test, y_pre[i])
        score.loc['RANK', [i]] = score_dic['RANK'] # get_rank(y_test, y_pre[i])
        score.loc['R2', [i]] = score_dic['R2'] # get_rank(y_test, y_pre[i])
    return score

# 获取评分（比赛规则要求）
def getRank(y_test, y_pred):
    error_array = np.array(((y_test - y_pred) / y_test))
    error_rate = np.sqrt(np.sum(np.power(error_array,2)))
    return error_rate