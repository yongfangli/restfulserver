package com.meike.restfulserver.authority.jwt;

/**
 * JWT的最终前面组成
 * 
 * @author lyf
 *
 */
public class Signature {
	private String headerEncodeString;// base64加密的头部信息
	private String payloadEncodeString;// base64加密的body信息
	private String secret;// 加密秘钥

	public Signature(String headerEncodeString, String payloadEncodeString, String secret) {
		this.headerEncodeString = headerEncodeString;
		this.payloadEncodeString = payloadEncodeString;
		this.secret = secret;
	}

	public String getHeaderEncodeString() {
		return headerEncodeString;
	}

	public void setHeaderEncodeString(String headerEncodeString) {
		this.headerEncodeString = headerEncodeString;
	}

	public String getPayloadEncodeString() {
		return payloadEncodeString;
	}

	public void setPayloadEncodeString(String payloadEncodeString) {
		this.payloadEncodeString = payloadEncodeString;
	}

	public String getSecret() {
		return secret;
	}

	public void setSecret(String secret) {
		this.secret = secret;
	}

}
