package com.ancs.agpt.mybatis.cache;

import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import org.apache.ibatis.cache.Cache;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;

import lombok.extern.slf4j.Slf4j;
import redis.clients.jedis.exceptions.JedisConnectionException;

@Slf4j
public class MybatisRedisCache implements Cache {
	private static final String PREFIX = "ANCS_MYBATIS:";

	private final String id;

	private final ReadWriteLock readWriteLock = new ReentrantReadWriteLock(true);

	private JdkSerializationRedisSerializer jdkSerializer = new JdkSerializationRedisSerializer();

	private static RedisConnectionFactory redisConnectionFactory;

	public MybatisRedisCache(final String id) {
		if (id == null) {
			throw new IllegalArgumentException("Cache instances require an ID");
		}
		this.id = id;
	}

	@Override
	public String getId() {
		// TODO Auto-generated method stub
		return this.id;
	}

	@Override
	public void putObject(Object key, Object value) {
		if (key == null)
			return;
		RedisConnection conn = null;
		try {
			conn = redisConnectionFactory.getConnection();
			String strKey = PREFIX + key.toString();
			conn.set(strKey.getBytes(), jdkSerializer.serialize(value));
			if(log.isDebugEnabled()) {
				 log.debug("putObject:" + key + "=" + value);
			}
		} catch (JedisConnectionException e) {
			e.printStackTrace();
		} finally {
			closeQuietly(conn);
		}

	}

	@Override
	public Object getObject(Object key) {
		if (key == null)
			return null;
		RedisConnection conn = null;
		try {
			conn = redisConnectionFactory.getConnection();
			String strKey = PREFIX + key.toString();
			byte[] bs = conn.get(strKey.getBytes());
			Object value = jdkSerializer.deserialize(bs);
			if(log.isDebugEnabled()) {
				log.debug("getObject:" + key + "=" + value);
			}
			return value;
		} catch (JedisConnectionException e) {
			e.printStackTrace();
		} finally {
			closeQuietly(conn);
		}
		return null;
	}

	@Override
	public Object removeObject(Object key) {
		if (key == null)
			return null;
		RedisConnection conn = null;
		try {
			conn = redisConnectionFactory.getConnection();
			conn.del(key.toString().getBytes());
		} catch (JedisConnectionException e) {
			e.printStackTrace();
		} finally {
			closeQuietly(conn);
		}

		return null;
	}

	@Override
	public void clear() {
		// 关键代码，data更新时清理缓存
		RedisConnection conn = null;
		try {
			conn = redisConnectionFactory.getClusterConnection();
			/*
			 * Set<byte[]> keys = conn.keys((PREFIX+"*").getBytes()); for (byte[] bs : keys)
			 * { conn.del(bs); }
			 */
			conn.flushDb();
			conn.flushAll();
		} catch (JedisConnectionException e) {
			e.printStackTrace();
		} finally {
			closeQuietly(conn);
		}

	}

	@Override
	public int getSize() {
		// TODO Auto-generated method stub
		int result = 0;
        RedisConnection conn = null;
        try {
        	conn = redisConnectionFactory.getConnection();
            result = Integer.valueOf(conn.dbSize().toString());
        } catch (JedisConnectionException e) {
            e.printStackTrace();
        } finally {
        	closeQuietly(conn);
        }
        return result;
	}

	@Override
	public ReadWriteLock getReadWriteLock() {
		// TODO Auto-generated method stub
		return this.readWriteLock;
	}

	public static void setRedisConnectionFactory(RedisConnectionFactory redisConnectionFactory) {
		MybatisRedisCache.redisConnectionFactory = redisConnectionFactory;
	}

	private void closeQuietly(RedisConnection conn) {
		if (conn != null) {
			conn.close();
		}
	}
}
