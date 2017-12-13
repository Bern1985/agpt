package com.ancs.agpt.system.service;

import java.util.Collection;

import com.ancs.agpt.system.entity.DomainRoleRestRel;

public interface DomainRoleRestRelService{
	/**
     * <p>
     * 插入一条记录（选择字段，策略插入）
     * </p>
     *
     * @param entity 实体对象
     * @return boolean
     */
    boolean insert(DomainRoleRestRel entity);

    
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
    boolean update(DomainRoleRestRel entity);
    
    /**
     * <p>
     * 批量插入记录
     * </p>
     *
     * @param entityArray 实体对象集合
     */
    void insertBatch(Collection<DomainRoleRestRel> entityArray);
}