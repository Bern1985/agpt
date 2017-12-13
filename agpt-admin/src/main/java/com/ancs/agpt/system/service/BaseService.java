package com.ancs.agpt.system.service;


import java.util.Collection;
import java.util.List;

import com.ancs.agpt.mybatis.plugin.Page;

public interface BaseService<T> {
	
	/**
     * <p>
     * 插入一条记录（选择字段，策略插入）
     * </p>
     *
     * @param entity 实体对象
     * @return boolean
     */
    boolean insert(T entity);
    
    /**
     * <p>
     * 批量插入记录
     * </p>
     *
     * @param entity 实体对象
     */
    void insertBatch(Collection<T> entityArray);

    
    /**
     * <p>
     * 根据 ID 删除
     * </p>
     *
     * @param id 主键ID
     * @return boolean
     */
    boolean deleteById(Long id);
    
    /**
     * <p>
     * 根据 ID 修改
     * </p>
     *
     * @param entity 实体对象
     * @return int
     */
    boolean update(T entity);
    
    /**
     * <p>
     * 根据 ID 查询
     * </p>
     *
     * @param id 主键ID
     * @return T
     */
    T selectById(Long id);
    
    boolean setStatus(T paramT);
    
    T get(Object param);
    
    Page<T> selectPage(Page<T> page);
    
}
