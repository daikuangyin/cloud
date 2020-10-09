package com.sun.cloud.enums;

import cn.hutool.extra.spring.SpringUtil;
import com.alibaba.fastjson.JSONObject;
import com.sun.cloud.service.ISysDictService;
import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 字典类
 *
 * @author: 李涛
 * @version: 2019年04月26日 15:15
 */
public interface DictEnum {

	Map<String, DictEnum> dictCache = new ConcurrentHashMap<>();

	String getKey();

	String getValue();

	/***
	 * 根据枚举类型和值获取枚举
	 * @param enumClass
	 * @param value
	 * @return
	 */
	static <T extends DictEnum> T valueOfEnum(Class<T> enumClass, String value) {
		String cacheKey = enumClass.getSimpleName() + "-" + value;
		if (value == null) {
			return null;
		}
		// 1.缓存中拿到直接返回
		T cacheDict = (T) dictCache.get(cacheKey);
		if (cacheDict != null) {
			return cacheDict;
		}
		// 2.遍历返回
		DictEnum[] enums = enumClass.getEnumConstants();
		if (enums == null) {
			return null;
		}
		Optional<T> optional = (Optional<T>) Arrays.asList(enums).stream().filter(baseEnum -> baseEnum.getKey().equals(value)).findAny();
		if (optional.isPresent()) {
			T dictEnum = optional.get();
			dictCache.put(cacheKey, dictEnum);
			return dictEnum;
		}
		return null;
	}

	/***
	 * 根据枚举类型和值获取枚举
	 * @param enumClass
	 * @param value
	 * @return
	 */
	static boolean exist(Class<? extends DictEnum> enumClass, String value) {
		// 1.缓存中查找
		String dictValue = SpringUtil.getBean(ISysDictService.class).getDictValue(enumClass, value);
		return StringUtils.isNotBlank(dictValue);
	}


	/***
	 * 根据枚举类型和值获取枚举
	 * @param enumClass
	 * @param value
	 * @return
	 */
	static JSONObject valueOfJson(Class<? extends DictEnum> enumClass, String value) {
		if (value == null) {
			return null;
		}
		JSONObject json = new JSONObject();
		json.put("key", value);
		json.put("value", value);

		// 1.缓存中查找
		String dictValue = SpringUtil.getBean(ISysDictService.class).getDictValue(enumClass, value);
		if (StringUtils.isNotBlank(dictValue)) {
			json.put("value", dictValue);
		}
		return json;
	}

}

