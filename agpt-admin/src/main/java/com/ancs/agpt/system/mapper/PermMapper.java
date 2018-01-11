package com.ancs.agpt.system.mapper;

import java.util.List;

import org.apache.ibatis.annotations.CacheNamespace;

import com.ancs.agpt.mybatis.cache.MybatisRedisCache;
import com.ancs.agpt.system.entity.Perm;
public interface PermMapper extends BaseMapper<Perm> {
	List<Perm>  findByUserId(Long userId);
	
}
