package com.meike.restfulserver.authority;

import javax.validation.constraints.NotNull;

public class LoginWrapper {
	@NotNull(message = "username不能为null")
	private String username;// can be email or username
	@NotNull(message = "password不能为null")
	private String password;
	@NotNull(message = "code不能为null")
	private String code;// validate code

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

}
