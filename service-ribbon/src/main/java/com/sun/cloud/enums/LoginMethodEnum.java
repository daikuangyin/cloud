package com.sun.cloud.enums;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.util.StdConverter;

@EnumDescription("用户登录方式")
public enum LoginMethodEnum implements DictEnum {

	//--------------------start-----------------

	SMALL_PROGRAM("01", "小程序"),

	ACCOUNT_PWD("02", "账号密码"),

	WECHAT_AUTHORIZATION("03", "微信公众号授权"),

	TEXT_MESSAGES_WEIXIN("04", "短信验证码"),
	//--------------------end-------------------
	;

	private String key;

	private String value;

	LoginMethodEnum(String key, String value) {
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
			return DictEnum.valueOfJson(LoginMethodEnum.class, value);
		}
	}
}
