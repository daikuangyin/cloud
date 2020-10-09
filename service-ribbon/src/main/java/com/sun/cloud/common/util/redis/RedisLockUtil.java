package com.sun.cloud.common.util.redis;

import cn.hutool.crypto.SecureUtil;
import cn.hutool.extra.spring.SpringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.script.RedisScript;

import java.util.Collections;

/**
 * 基于redis lua分布式锁
 *
 * @author: 李涛
 * @version: 2019年05月08日 16:15
 */
public class RedisLockUtil {

	private static final Logger LOGGER = LoggerFactory.getLogger(RedisLockUtil.class);

	/**
	 * 成功标识
	 */
	private static final Long SUCCESS = 1L;

	/**
	 * 加锁lua脚本,不可重入,reqId只是为了解锁使用,代表当前线程在使用资源,给UUID比较好
	 */
	private static final String SCRIPT_LOCK = "if redis.call('setnx', KEYS[1], ARGV[1]) == 1 then redis.call('pexpire', KEYS[1], ARGV[2]) return 1 else return 0 end";

	/**
	 * 解锁lua脚本
	 */
	private static final String SCRIPT_UNLOCK = "if redis.call('get', KEYS[1]) == ARGV[1] then return redis.call('del', KEYS[1]) else return 0 end";

	/**
	 * 加锁脚本sha1值
	 */
	private static final String SCRIPT_LOCK_SHA1 = SecureUtil.sha1(SCRIPT_LOCK);

	/**
	 * 解锁脚本sha1值
	 */
	private static final String SCRIPT_UNLOCK_SHA1 = SecureUtil.sha1(SCRIPT_UNLOCK);

	/**
	 * 内部持有模板
	 */
	private static final RedisTemplate redisTemplate = (RedisTemplate) SpringUtil.getBean("redisTemplate");

	/**
	 * 尝试获取分布式锁
	 *
	 * @param lockKey                锁
	 * @param requestId              请求标识,唯一ID
	 * @param expireTimeMilliseconds 超期时间，多少毫秒后这把锁自动释放
	 * @return 返回true表示拿到锁
	 */
	public static boolean tryGetDistributedLock(String lockKey, String requestId, long expireTimeMilliseconds) {
		LOGGER.info("[{}]尝试获取[{}]锁,超时时间为:{}毫秒", requestId, lockKey, expireTimeMilliseconds);
		/**
		 * 脚本设置
		 */
		RedisScript<Long> redisScript = new RedisScript<Long>() {
			@Override
			public String getSha1() {
				return SCRIPT_LOCK_SHA1;
			}

			@Override
			public Class<Long> getResultType() {
				return Long.class;
			}

			@Override
			public String getScriptAsString() {
				return SCRIPT_LOCK;
			}

		};
		Object result = redisTemplate.execute(
				redisScript,// lua脚本
				Collections.singletonList(lockKey),// KEYS[1]
				requestId, // ARGV[1]
				expireTimeMilliseconds // ARGV[2]
		);
		boolean b = SUCCESS.equals(result);
		LOGGER.info("获取锁:{}", b);
		return b;
	}

	/**
	 * 释放分布式锁
	 *
	 * @param lockKey   锁
	 * @param requestId 请求标识
	 * @return 返回true表示释放锁成功
	 */
	public static boolean releaseDistributedLock(String lockKey, String requestId) {
		LOGGER.info("[{}]释放锁[{}]锁", requestId, lockKey);

		/**
		 * lua脚本
		 */
		RedisScript<Long> redisScript = new RedisScript<Long>() {
			@Override
			public String getSha1() {
				return SCRIPT_UNLOCK_SHA1;
			}

			@Override
			public Class<Long> getResultType() {
				return Long.class;
			}

			@Override
			public String getScriptAsString() {
				return SCRIPT_UNLOCK;
			}

		};
		Object result = redisTemplate.execute(
				redisScript,
				Collections.singletonList(lockKey),
				requestId
		);
		boolean b = SUCCESS.equals(result);
		LOGGER.info("释放锁:{}", b);
		return b;
	}
}
