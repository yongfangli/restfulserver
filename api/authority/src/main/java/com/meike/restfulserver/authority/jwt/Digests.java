package com.meike.restfulserver.authority.jwt;

/**
 * 支持SHA-1/MD5消息摘要的工具类.
 * 
 * 返回ByteSource，可进一步被编码为Hex, Base64或UrlSafeBase64
 * 
 * @author calvin
 */

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;

public class Digests {
	/**
	 * 
	 * @param message
	 * @param secret
	 * @return
	 */
	public static String HmacSHA256(String message, String secret) {
		try {
			Mac sha256_HMAC = Mac.getInstance("HmacSHA256");
			SecretKeySpec secret_key = new SecretKeySpec(secret.getBytes(), "HmacSHA256");
			sha256_HMAC.init(secret_key);
			String hash = Base64.encodeBase64String(sha256_HMAC.doFinal(message.getBytes()));
			return hash;
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
	}
}
