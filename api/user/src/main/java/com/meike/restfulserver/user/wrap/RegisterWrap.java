package com.meike.restfulserver.user.wrap;

import org.apache.naming.StringManager;

/**
 * register api received data wrap
 * @author lyf
 *
 */
public class RegisterWrap {
	private String username;// 验证名重复验证
	private String password;// 密码重复校验
	private String rePassword;
	private String email;// 用来邮箱验证的
}
