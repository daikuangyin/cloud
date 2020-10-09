package com.sun.cloud.framework.shiro.redis;


import org.apache.shiro.authc.UsernamePasswordToken;

import java.io.Serializable;

public class MyWxH5Token extends UsernamePasswordToken implements Serializable {

	/**
	 * 微信H5code
	 */
	private String code;

	public MyWxH5Token(String code) {
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
