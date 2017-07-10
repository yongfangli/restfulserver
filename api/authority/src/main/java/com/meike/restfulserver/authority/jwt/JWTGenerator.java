package com.meike.restfulserver.authority.jwt;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.meike.restfulserver.dao.po.User;

public class JWTGenerator {
	public static final long TOKEN_VALIDATE_DATE = 3 * 60 * 1000;// jwt存活时间3
																	// minute
	public static DateFormat simpleformat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); // 5天
	private static final Logger logger = LoggerFactory.getLogger(JWTConstance.class);

	public static void main(String[] args) throws Exception {
		Payload payload = new Payload("2017-05-13 09:08:33", "2017-05-13 09:08:33", "isap", "web", "admin", "", "");

	}

	/**
	 * 通过用户来生成一个token
	 * 
	 * @param user
	 * @return
	 */
	public static String generator(User user) {
		Date now = new Date();
		long exptime = now.getTime() + TOKEN_VALIDATE_DATE;
		Payload payload = new Payload(simpleformat.format(exptime), simpleformat.format(now), "meike", "api",
				user.getUsername(), user.getPassword(), "");
		try {
			String sign = EncodeStringGenerator.getJWTToken(payload);
			return sign;
		} catch (Exception e) {
			logger.debug("generate JWT token error");
		}
		return null;
	}

	/**
	 * 通过用户来生成一个token
	 * 
	 * @param user
	 * @return
	 */
	public static String generator(String username, String password) {
		Date now = new Date();
		long exptime = now.getTime() + TOKEN_VALIDATE_DATE;
		Payload payload = new Payload(simpleformat.format(exptime), simpleformat.format(now), "meike", "api", username,
				password, "");
		try {
			String sign = EncodeStringGenerator.getJWTToken(payload);
			return sign;
		} catch (Exception e) {
			logger.debug("generate JWT token error");
		}
		return null;
	}

	public static String generateByTime() {
		Date now = new Date();
		long exptime = now.getTime() + TOKEN_VALIDATE_DATE;
		Payload payload = new Payload(simpleformat.format(exptime), simpleformat.format(now), "meike", "api", "", "",
				"");
		try {
			String sign = EncodeStringGenerator.getJWTToken(payload);
			return sign;
		} catch (Exception e) {
			logger.debug("generate JWT token error");
		}
		return null;
	}
}