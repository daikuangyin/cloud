package com.sun.cloud.framework.shiro.redis;


import org.apache.shiro.authc.UsernamePasswordToken;

import java.io.Serializable;

public class MyWxWebToken extends UsernamePasswordToken implements Serializable {

	/**
	 * 微信code
	 */
	private String code;

	/**
	 * 手机号码
	 */
	private String phone;

	/**
	 * 验证码
	 */
	private String verificationCode;

	public MyWxWebToken(String code, String phone, String verificationCode) {
		this.code = code;
		this.phone = phone;
		this.verificationCode = verificationCode;
	}

	@Override
	public Object getPrincipal() {
		return getPhone();
	}

	@Override
	public Object getCredentials() {
		return "ok";
	}


	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getVerificationCode() {
		return verificationCode;
	}

	public void setVerificationCode(String pwd) {
		this.verificationCode = verificationCode;
	}
}
