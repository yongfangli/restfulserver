package com.meike.restfulserver.authority.jwt;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.meike.restfulserver.authority.filter.TokenValidateFilter;
import com.meike.restfulserver.common.JsonUtils;

import junit.awtui.Logo;

@Component
public class JWTChecker {
	private boolean dateCheck = false;// 是否验证时间
	private static final Logger logger = LoggerFactory.getLogger(JWTChecker.class);

	public JWTChecker() {
		super();
		this.dateCheck = true;
	}

	public boolean checkJWTFromHeader(HttpServletRequest request) {
		String authString = request.getHeader(JWTConstance.JWT_HEAD_KEY);
		if (!StringUtils.isEmpty(authString)) {
			String token = authString.replace(JWTConstance.BEGIN_OF_HEADER, "").trim();
			String[] JWTparts = token.split("\\.");
			// 判断token是否相同，
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
			return new Date().after(deadDate);
		} else {
			return true;
		}
	}

}
