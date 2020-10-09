package com.sun.cloud.framework.shiro.redis;


import org.apache.shiro.authc.UsernamePasswordToken;

import java.io.Serializable;

public class MyWxToken extends UsernamePasswordToken implements Serializable {

	/**
	 * 微信code
	 */
	private String code;

	public MyWxToken(String code) {
		this.code = code;
	}

	@Override
	public Object getPrincipal() {
		return getCode();
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

}
