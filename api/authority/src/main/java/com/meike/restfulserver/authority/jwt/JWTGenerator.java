package com.meike.restfulserver.authority.jwt;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.meike.restfulserver.dao.po.User;

public class JWTGenerator {
	public static final long TOKEN_VALIDATE_DATE = 5 * 24 * 60 * 60 * 1000;// jwt
																			// token过期时间
	public static DateFormat simpleformat = new SimpleDateFormat("YYYY-MM-dd hh:mm:ss"); // 5天
	private static final Logger logger = LoggerFactory.getLogger(JWTConstance.class);

	public static void main(String[] args) throws Exception {
		Payload payload = new Payload("2017-05-13 09:08:33", "2017-05-13 09:08:33", "isap", "web", "admin", "", "");

	}

	public static String generator(User user) {
		Date now = new Date();
		long exptime = now.getTime() + TOKEN_VALIDATE_DATE;
		Payload payload = new Payload(simpleformat.format(exptime), simpleformat.format(now), "meike", "api",
				user.getUsername(), user.getPassword(), "");
		try {
			String sign = EncodeStringGenerator.getJWTToken(payload);
			return sign;
		} catch (Exception e) {
			logger.debug("generator JWT token error");
		}
		return null;
	}
}