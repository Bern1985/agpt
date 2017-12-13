package com.ancs.agpt.config;

import java.lang.reflect.Method;

import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.core.RedisTemplate;

@Configuration
@EnableCaching
public class CachingConfig  extends CachingConfigurerSupport {
	
	@Override
	@Bean
	public KeyGenerator keyGenerator() {
		// TODO Auto-generated method stub
		return new KeyGenerator() {

			@Override
			public Object generate(Object object, Method method, Object... objects) {
				// TODO Auto-generated method stub
				StringBuilder sb = new StringBuilder();
				sb.append(object.getClass().getName());
				sb.append(method.getName());
				for (Object obj : objects) {
					sb.append(obj.toString());
				}
				return sb.toString();
			}

		};
	}

	/**
	 * 缓存管理器.
	 * 
	 * @param redisTemplate
	 * @return
	 */
	@Bean
	public CacheManager cacheManager(RedisTemplate<?, ?> redisTemplate) {
		RedisCacheManager cacheManager = new RedisCacheManager(redisTemplate);
		// 设置缓存过期时间
		cacheManager.setDefaultExpiration(1800);
		return cacheManager;
	}
    
}
