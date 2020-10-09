package com.sun.cloud.enums;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.util.StdConverter;

@EnumDescription("用户登录类型")
public enum LoginTypeEnum implements DictEnum {

	//--------------------start-----------------

	/**
	 * 会员
	 */
	MEMBER("01","会员"),
	/**
	 * 系统
	 */
	SYSTEM("02","系统"),

	//--------------------end-------------------
	;


	private String key;

	private String value;

	LoginTypeEnum(String key, String value) {
		this.key = key;
		this.value = value;
	}

	@Override
	public String getKey() {
		return this.key;
	}

	@Override
	public String getValue() {
		return this.value;
	}

	/**
	 * 序列化使用
	 */
	public static class Covert extends StdConverter<String, JSONObject> {
		@Override
		public JSONObject convert(String value) {
			return DictEnum.valueOfJson(LoginTypeEnum.class,value);
		}
	}
}
