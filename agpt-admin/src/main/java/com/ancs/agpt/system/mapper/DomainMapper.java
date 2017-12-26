package com.ancs.agpt.system.mapper;

import org.apache.ibatis.annotations.CacheNamespace;

import com.ancs.agpt.mybatis.cache.MybatisRedisCache;
import com.ancs.agpt.system.entity.Domain;

@CacheNamespace(implementation = MybatisRedisCache.class)
public interface DomainMapper extends BaseMapper<Domain> {
  Domain findByDomainName(String account);
}