package com.ancs.agpt.system.mapper;

import org.apache.ibatis.annotations.CacheNamespace;

import com.ancs.agpt.mybatis.cache.MybatisRedisCache;
import com.ancs.agpt.system.entity.DomainRoleRel;
@CacheNamespace(implementation = MybatisRedisCache.class)
public interface DomainRoleRelMapper {
	
	/**
     * <p>
     * 插入一条记录
     * </p>
     *
     * @param entity 实体对象
     * @return int
     */
    Integer insert(DomainRoleRel entity);
    
    
    /**
     * <p>
     * 根据 ID 删除
     * </p>
     *
     * @param id 主键ID
     * @return int
     */
    Integer deleteById(Long id);
    
    /**
     * <p>
     * 根据 ID 修改
     * </p>
     *
     * @param obj 实体对象
     * @return int
     */
    Integer update(DomainRoleRel entity);
    
}