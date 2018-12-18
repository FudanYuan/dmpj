package com.dmpj.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * @Author: Jeremy
 * @Date: 2018/12/18 18:52
 */
@Data
public class CombinePrimaryKey implements Serializable {

    /**
     * 数据id
     */
    private Integer id;

    /**
     * 训练模型的类型
     */
    private Integer type;

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((type == null) ? 0 : type.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj){
        if(this == obj){
            return true;
        }
        if(obj == null){
            return false;
        }
        if(getClass() != obj.getClass()){
            return false;
        }

        final CombinePrimaryKey other = (CombinePrimaryKey)obj;
        if(id == null){
            if(other.id != null){
                return false;
            }
        }else if(!id.equals(other.id)){
            return false;
        }
        if(type == null){
            if(other.type != null){
                return false;
            }
        }else if(!type.equals(other.type)){
            return false;
        }

        return true;
    }
}
