package com.sun.cloud.common.util.redis;

import org.apache.commons.lang3.StringUtils;

/**
 * 生成RedisKey工具
 *
 * @author: 李涛
 * @version: 2019年05月07日 15:19
 */
public class RedisKeyUtil {
	/**
	 * 主数据系统标识
	 */
	public static final String KEY_PREFIX = "YM:BANK";
	/**
	 * 分割字符，默认[:]，使用:可用于rdm分组查看
	 */
	private static final String KEY_SPLIT_CHAR = ":";

	/**
	 * redis的key键规则定义
	 *
	 * @param module 模块名称
	 * @param func   方法名称
	 * @param args   参数..
	 * @return key
	 */
	public static String keyBuilder(String module, String func, String... args) {
		return keyBuilder(null, module, func, args);
	}

	/**
	 * redis的key键规则定义
	 *
	 * @param module 模块名称
	 * @param func   方法名称
	 * @param objStr 对象.toString()
	 * @return key
	 */
	public static String keyBuilder(String module, String func, String objStr) {
		return keyBuilder(null, module, func, new String[]{objStr});
	}

	/**
	 * redis的key键规则定义
	 *
	 * @param prefix 项目前缀
	 * @param module 模块名称
	 * @param func   方法名称
	 * @param objStr 对象.toString()
	 * @return key
	 */
	public static String keyBuilder(String prefix, String module, String func, String objStr) {
		return keyBuilder(prefix, module, func, new String[]{objStr});
	}

	/**
	 * redis的key键规则定义
	 *
	 * @param prefix 项目前缀
	 * @param module 模块名称
	 * @param func   方法名称
	 * @param args   参数..
	 * @return key
	 */
	public static String keyBuilder(String prefix, String module, String func, String... args) {
		// 项目前缀
		if (prefix == null) {
			prefix = KEY_PREFIX;
		}
		StringBuilder key = new StringBuilder(prefix);
		// KEY_SPLIT_CHAR 为分割字符
		key.append(KEY_SPLIT_CHAR).append(module);
		if (StringUtils.isNotBlank(func)) {
			key.append(KEY_SPLIT_CHAR).append(func);
		}
		for (String arg : args) {
			key.append(KEY_SPLIT_CHAR).append(arg);
		}
		return key.toString();
	}

	/**
	 * redis的key键规则定义
	 *
	 * @param redisKeyEnum 枚举对象
	 * @param objStr       对象.toString()
	 * @return key
	 */
	public static String keyBuilder(RedisKeyEnum redisKeyEnum, String... objStr) {
		return keyBuilder(redisKeyEnum.getKeyPrefix(), redisKeyEnum.getModule(), redisKeyEnum.getFunc(), objStr);
	}

}
