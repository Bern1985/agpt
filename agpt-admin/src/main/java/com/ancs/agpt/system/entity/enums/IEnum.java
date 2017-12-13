package com.ancs.agpt.system.entity.enums;

import java.io.Serializable;

/**
 * <p>
 * 自定义枚举接口
 * </p>
 *
 */
public interface IEnum {

    /**
     * 枚举数据库存储值
     */
    Serializable getValue();
    
    
    Serializable getDesc();

}
