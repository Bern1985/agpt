package com.ancs.agpt.system.mapper;

import java.util.List;

import org.apache.ibatis.annotations.CacheNamespace;

import com.ancs.agpt.mybatis.cache.MybatisRedisCache;
import com.ancs.agpt.system.entity.DomainRole;
@CacheNamespace(implementation = MybatisRedisCache.class)
public interface DomainRoleMapper extends BaseMapper<DomainRole> {
	List<DomainRole> findByDomainId(Long domainId);
}