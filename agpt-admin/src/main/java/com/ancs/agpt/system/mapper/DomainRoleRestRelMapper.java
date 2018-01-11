package com.ancs.agpt.system.mapper;

import java.util.List;

import org.apache.ibatis.annotations.CacheNamespace;

import com.ancs.agpt.mybatis.cache.MybatisRedisCache;
import com.ancs.agpt.system.entity.DomainRoleRestRel;
public interface DomainRoleRestRelMapper{
	/**
     * <p>
     * 插入一条记录
     * </p>
     *
     * @param entity 实体对象
     * @return int
     */
    Integer insert(DomainRoleRestRel entity);
    
    /**
     * <p>
     * 批量插入记录
     * </p>
     *
     * @param entity 实体对象
     * @return int
     */
    Integer insertBatch(List<DomainRoleRestRel> entityArray);
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
    Integer update(DomainRoleRestRel entity);
}