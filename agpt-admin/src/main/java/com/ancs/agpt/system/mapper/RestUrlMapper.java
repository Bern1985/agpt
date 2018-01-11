package com.ancs.agpt.system.mapper;


import java.util.List;

import org.apache.ibatis.annotations.CacheNamespace;
import org.apache.ibatis.annotations.Param;

import com.ancs.agpt.mybatis.cache.MybatisRedisCache;
import com.ancs.agpt.system.entity.RestUrl;
public interface RestUrlMapper extends BaseMapper<RestUrl>{
	//int insertBatch(List<RestUrl> restUrlList);
	RestUrl selectByUrlAndMethod(@Param("restUrl")String restUrl,@Param("method") String method);
	
	List<RestUrl> findByDomainId(Long domainId);
}