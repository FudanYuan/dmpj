import time
import pandas as pd
import numpy as np

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

# 增加特征
def addFeature(dataset):
    # 增加充电时间间隔属性
    charge_end_time_array = np.array(calVecTimeStamp(dataset['charge_end_time']))
    charge_start_time_array = np.array(calVecTimeStamp(dataset['charge_start_time']))
    dataset['charge_duration'] = (charge_end_time_array - charge_start_time_array).tolist()

    # 增加充电SOC变化量
    charge_start_soc_array = np.array(dataset['charge_start_soc'])
    charge_end_soc_array = np.array(dataset['charge_end_soc'])
    dataset['charge_delta_soc'] = (charge_end_soc_array - charge_start_soc_array).tolist()

    # 增加充电前汽车行驶里程数，要分车进行
    delta_mileage_list_all = []
    for i in range(1, count + 1):
        delta_mileage_list_all.append(np.NaN)
        mileage_list = dataset[dataset['vehicle_id'] == i]['mileage'].tolist()
        for j in range(1, len(mileage_list)):
            delta_mileage_list_all.append(mileage_list[j] - mileage_list[j - 1])
    dataset['delta_mileage'] = delta_mileage_list_all

    # 重构数据集
    header.insert(3, 'charge_duration')
    header.insert(5, 'delta_mileage')
    header.insert(8, 'charge_delta_soc')

    dataset = dataset[header]
    return dataset