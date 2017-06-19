package com.meike.restfulserver.authority.jwt;

import java.io.FileNotFoundException;

import com.meike.restfulserver.common.JsonUtils;

public class EncodeStringGenerator {
	/**
	 * 获得默认的JWT 头部 header Json String
	 * 
	 * @return
	 * @throws Exception
	 * @throws MapperException
	 */
	private static String getDefaultHeadedJsonString() throws Exception {
		Header header = new Header(JWTConstance.HmacSHA256, JWTConstance.TYPE_NAME);
		return Encodes.encodeBase64(JsonUtils.obj2json(header));
	}

	/**
	 * 
	 * @param payload
	 *            请求体,信息的载体 返回信息体的json字符串
	 * @return
	 * @throws Exception
	 * @throws MapperException
	 */
	private static String getPayloadEncodeString(Payload payload) throws Exception {
		return Encodes.encodeBase64(JsonUtils.obj2json(payload));
	}

	/**
	 * 返回最中的sign
	 * 
	 * @return
	 * @throws Exception
	 * @throws MapperException
	 */
	public static String getJWTToken(Payload payload) throws Exception {
		return getDefaultHeadedJsonString() + "." + getPayloadEncodeString(payload) + "." + Digests.HmacSHA256(
				getDefaultHeadedJsonString() + "." + getPayloadEncodeString(payload), JWTConstance.getJWTEcodeKey());
	}

	/**
	 * 重写生成sign方法
	 * 
	 * @param encodeHeader
	 * @param encodePayload
	 * @return
	 * @throws FileNotFoundException
	 * @throws MapperException
	 */
	public static String getJWTSign(String encodeHeader, String encodePayload) {
		return Digests.HmacSHA256(encodeHeader + "." + encodePayload, JWTConstance.getJWTEcodeKey());
	}
}
