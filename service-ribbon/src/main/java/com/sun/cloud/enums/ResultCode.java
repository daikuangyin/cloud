package com.sun.cloud.enums;

public enum ResultCode {
	/**
	 * 成功
	 */
	SUCCESS(1, "请求成功"),
	/**
	 * 失败
	 */
	FAIL(0, "未知错误"),

	//-----------------登陆相关------------------
	NO_LOGIN(1000000, "未登录"),
	TOKEN_ERROR(1000001, "token错误"),
	NO_PERMISSION(1000002, "用户无权限"),
	LOGIN_EXPIRED(1000003, "登陆过期"),
	ACCOUNT_DOES_NOT_EXIST(1000004, "账号不存在"),
	ACCOUNT_PASSWORD_IS_WRONG(1000005, "账号或密码错误"),
	USER_LOGGING_IN_MULTIPLE_PLACES(1000006, "用户在多地登录"),
	THE_NUMBER_OF_AUTHENTICATION_EXCEEDS_THE_LIMIT(1000007, "认证次数超过限制"),
	ACCOUNT_IS_LOCKED(1000008, "账号被锁定"),
	ACCOUNT_HAS_BEEN_DISABLED(1000009, "账户已被禁用"),
	VERIFICATION_CODE_ERROR(1000010, "验证码错误"),
	LOGIN_WITHOUT_PERMISSION(1000011, "该用户无权限登录!"),
	//-----------------登陆相关------------------

	/**
	 * 项目捐赠相关错误码
	 */
	NO_ACCOUNT_BALANCE(2000001, "善款账户余额不足！"),
	REPEAT_MONTHLY_DONATION(2000002, "请勿重复开通此项目的月捐！"),
	FUND_RAISING_NOT_SUPPORTED(2000003, "该项目暂不支持筹款哦！"),
	MONTHLY_DONATION_IS_NOT_SUPPORTED(2000004, "短期项目不支持月捐！"),

	/*
	 * 定义其他异常
	 * */
	BAD_REQUEST(400, "参数列表错误: {}"),

	REPEATED_SUBMIT(401, "系统繁忙, 请稍后再试!"),

	UNSUPPORTED_TYPE(415, " 不支持的数据，媒体类型 {}"),

	;


	private Integer code;
	private String msg;

	ResultCode(Integer code, String msg) {
		this.code = code;
		this.msg = msg;
	}

	public Integer getCode() {
		return code;
	}

	public String getMsg() {
		return msg;
	}

}
