package com.meike.restfulserver.authority.jwt;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import com.meike.restfulserver.common.JsonUtils;

public class JWTChecker {
	private boolean dateCheck = false;// 是否验证时间
	private static final Logger logger = LoggerFactory.getLogger(JWTChecker.class);
	private boolean headHasToken;
	private String token;

	public JWTChecker() {
		super();
		this.dateCheck = true;
	}

	public boolean isDateCheck() {
		return dateCheck;
	}

	public void setDateCheck(boolean dateCheck) {
		this.dateCheck = dateCheck;
	}

	public boolean isHeadHasToken() {
		return headHasToken;
	}

	public void setHeadHasToken(boolean headHasToken) {
		this.headHasToken = headHasToken;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public JWTChecker(HttpServletRequest request) {
		super();
		this.dateCheck = true;
		String authString = request.getHeader(JWTConstance.JWT_HEAD_KEY);
		if (!StringUtils.isEmpty(authString)) {
			headHasToken = true;
			token = authString;
		}
		headHasToken = false;
	}

	public boolean checkJWTFromHeader(HttpServletRequest request) {
		if (headHasToken) {
			String[] JWTparts = getEncodeArrayFromHead(request);
			// 判断token是否相同
			try {
				if (JWTparts[2].equals(EncodeStringGenerator.getJWTSign(JWTparts[0], JWTparts[1]))
						&& dateValidate(JWTparts[1])) {
					return true;
				}
			} catch (Exception e) {
				logger.info("check JWT token error,mssage is:" + e.getMessage());
				return false;
			}
		}
		return false;
	}

	private String[] getEncodeArrayFromHead(HttpServletRequest request) {
		if (headHasToken) {
			String tokenTrim = token.replace(JWTConstance.BEGIN_OF_HEADER, "").trim();
			String[] JWTparts = tokenTrim.split("\\.");
			return JWTparts;
		}
		return null;
	}

	public String getUserInfoFromPayload(HttpServletRequest request) throws Exception {
		if (headHasToken) {
			String[] JWTparts = getEncodeArrayFromHead(request);
			String tokenEncode = JWTparts[1];
			Payload payload = JsonUtils.json2pojo(Encodes.decodeBase64String(tokenEncode), Payload.class);
			return payload.getAud();
		}
		return null;
	}

	/**
	 * 是否到了失效时间
	 * 
	 * @param payloadEncodeString
	 * @return
	 * @throws Exception
	 */
	private boolean dateValidate(String payloadEncodeString) throws Exception {
		String payloadJson = Encodes.decodeBase64String(payloadEncodeString);
		Payload payload = (Payload) JsonUtils.json2pojo(payloadJson, Payload.class);
		if (dateCheck) {
			Date deadDate = JWTGenerator.simpleformat.parse(payload.getExp());
			return new Date().before(deadDate);
		} else {
			return true;
		}
	}

}
