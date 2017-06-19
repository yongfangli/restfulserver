package com.meike.restfulserver.authority.jwt;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.ResourceUtils;

public class JWTConstance {
	private static final Logger logger = LoggerFactory.getLogger(JWTConstance.class);
	// 加密方式
	public static final String HmacSHA256 = "HS256";
	public static final String TYPE_NAME = "JWT";
	public static final String JWT_HEAD_KEY = "Authorization";
	public static final String BEGIN_OF_HEADER = "Bearer";
	public static final String APP_FILE_NAME = "classpath:application.properties";
	public static final String ECODE_KEY_NAME = "jwt.encode.key";
	// 属性文件xmlconf.properties中对应的各个键值对
	private static HashMap<String, String> paramsMap = new HashMap<String, String>();

	private static void loadProperties() {
		URL url;
		try {
			url = ResourceUtils.getURL(APP_FILE_NAME);
			InputStream in = null;
			Properties p = new Properties();
			try {
				in = url.openStream();
				p.load(in);
			} catch (FileNotFoundException e) {
				logger.error("file is not exists!");
			} catch (IOException e) {
				logger.error("IOException when load file");
			} finally {
				if (in != null) {
					try {
						in.close();
					} catch (IOException e) {
						logger.error("Close IO error!");
					}
				}
			}
			Set<Entry<Object, Object>> set = p.entrySet();
			Iterator<Entry<Object, Object>> it = set.iterator();
			while (it.hasNext()) {
				Entry<Object, Object> entry = it.next();
				String key = (String) entry.getKey();
				String value = (String) entry.getValue();
				logger.debug(key + "=" + value);
				// System.out.println(key + "=" + value);
				if (key != null && value != null) {
					paramsMap.put(key.trim(), value.trim());
				}
			}
		} catch (FileNotFoundException e1) {
			logger.info("file not found");
		}
	}

	public static String getPropertyByKeyName(String keyName) {
		if (paramsMap.isEmpty()) {
			loadProperties();
		}
		return paramsMap.get(keyName);
	}

	public static String getJWTEcodeKey() {
		return getPropertyByKeyName(ECODE_KEY_NAME);
	}
}
