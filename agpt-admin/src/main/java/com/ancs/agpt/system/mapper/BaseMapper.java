package com.ancs.agpt.system.mapper;

import java.util.List;

import com.ancs.agpt.mybatis.plugin.Page;
import com.ancs.agpt.system.entity.SuperEntity;

public interface BaseMapper<T extends SuperEntity> {
	
	/**
     * <p>
     * 插入一条记录
     * </p>
     *
     * @param entity 实体对象
     * @return int
     */
    Integer insert(T entity);
    
    /**
     * <p>
     * 批量插入记录
     * </p>
     *
     * @param entity 实体对象
     */
    void insertBatch(List<T> entityArray);
    
    
    /**
     * <p>
     * 根据 ID 删除
     * </p>
     *
     * @param id 主键ID
     * @return int
     */
    //Integer deleteById(Long id);
    
    /**
     * <p>
     * 根据 ID 修改
     * </p>
     *
     * @param obj 实体对象
     * @return int
     */
    Integer update(T entity);
    
    /**
     * <p>
     * 根据 ID 查询
     * </p>
     *
     * @param id 主键ID
     * @return T
     */
    T selectById(Long id);
    
    Integer setStatus(T paramT);
    
    T get(Object param);
    
    List<T> selectPage(Page<T> page);
    
}
