package com.meike.restfulserver.authority.jwt;

/**
 * JWT Payload 部分
 * 
 * @author lyf
 *
 */
public class Payload {
	private String exp;// 过期时间
	private String iat;// 签发时间
	private String iss;// 签发者
	private String aud;// 接收的一方
	private String sub;// 所面向的用户
	private String jti;// jwt的唯一身份标识，主要用来作为一次性token,从而回避重放攻击
	private String nbf;// 定义在什么时候之前不可用

	public Payload() {

	}

	public Payload(String exp, String iat, String iss, String aud, String sub, String jti, String nbf) {
		super();
		this.exp = exp;
		this.iat = iat;
		this.iss = iss;
		this.aud = aud;
		this.sub = sub;
		this.jti = jti;
		this.nbf = nbf;
	}

	public String getExp() {
		return exp;
	}

	public void setExp(String exp) {
		this.exp = exp;
	}

	public String getIat() {
		return iat;
	}

	public void setIat(String iat) {
		this.iat = iat;
	}

	public String getIss() {
		return iss;
	}

	public void setIss(String iss) {
		this.iss = iss;
	}

	public String getAud() {
		return aud;
	}

	public void setAud(String aud) {
		this.aud = aud;
	}

	public String getSub() {
		return sub;
	}

	public void setSub(String sub) {
		this.sub = sub;
	}

	public String getJti() {
		return jti;
	}

	public void setJti(String jti) {
		this.jti = jti;
	}

	public String getNbf() {
		return nbf;
	}

	public void setNbf(String nbf) {
		this.nbf = nbf;
	}

}
