package com.ancs.agpt.mybatis.cache;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.stereotype.Component;

@Component 
public class RedisCacheTransfer {
	@Autowired
	public void setJedisConnectionFactory(RedisConnectionFactory jedisConnectionFactory) {
		MybatisRedisCache.setRedisConnectionFactory(jedisConnectionFactory);
	}
}
