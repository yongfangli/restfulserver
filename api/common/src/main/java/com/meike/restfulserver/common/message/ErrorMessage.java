package com.meike.restfulserver.common.message;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 消息模板持有
 * 
 * @author liuzk
 *
 */
@Component
public final class ErrorMessage {
	private static IMessageTemplate template;

	/**
	 * 设置模板
	 * 
	 * @param template
	 *            模板
	 */
	@Autowired(required = true)
	public void setTemplate(IMessageTemplate template) {
		ErrorMessage.template = template;
	}

	/**
	 * 根据错误码获取错误消息
	 * 
	 * @param err
	 *            错误码
	 * @param params
	 *            参数列表
	 * @return 错误信息
	 */
	public static String get(int err, String[] params) {
		return template.getMessage(err, params);
	}

	/**
	 * 根据错误码获取错误消息
	 * 
	 * @param err
	 *            错误码
	 * @return 错误信息
	 */
	public static String get(int err) {
		return template.getMessage(err);
	}

	/**
	 * 根据错误码获取错误消息
	 * 
	 * @param err
	 *            错误码
	 * @param param1
	 *            参数1
	 * @return 错误信息
	 */
	public static String get(int err, String param1) {
		String[] params = new String[] { param1 };
		return template.getMessage(err, params);
	}

	/**
	 * 根据错误码获取错误消息
	 * 
	 * @param err
	 *            错误码
	 * @param param1
	 *            参数1
	 * @param param2
	 *            参数2
	 * @return 错误信息
	 */
	public static String get(int err, String param1, String param2) {
		String[] params = new String[] { param1, param2 };
		return template.getMessage(err, params);
	}

	/**
	 * 根据错误码获取错误消息
	 * 
	 * @param err
	 *            错误码
	 * @param param1
	 *            参数1
	 * @param param2
	 *            参数2
	 * @param param3
	 *            参数3
	 * @return 错误信息
	 */
	public static String get(int err, String param1, String param2, String param3) {
		String[] params = new String[] { param1, param2, param3 };
		return template.getMessage(err, params);
	}

	/**
	 * 根据错误码获取错误消息
	 * 
	 * @param err
	 *            错误码
	 * @param param1
	 *            参数1
	 * @param param2
	 *            参数2
	 * @param param3
	 *            参数3
	 * @param param4
	 *            参数4
	 * @return 错误信息
	 */
	public static String get(int err, String param1, String param2, String param3, String param4) {
		String[] params = new String[] { param1, param2, param3, param4 };
		return template.getMessage(err, params);
	}

	/**
	 * 根据错误码获取错误消息
	 * 
	 * @param err
	 *            错误码
	 * @param param1
	 *            参数1
	 * @param param2
	 *            参数2
	 * @param param3
	 *            参数3
	 * @param param4
	 *            参数4
	 * @param param5
	 *            参数5
	 * @return 错误信息
	 */
	public static String get(int err, String param1, String param2, String param3, String param4, String param5) {
		String[] params = new String[] { param1, param2, param3, param4, param5 };
		return template.getMessage(err, params);
	}

	/**
	 * 根据错误码获取错误消息
	 * 
	 * @param err
	 *            错误码
	 * @param param1
	 *            参数1
	 * @param param2
	 *            参数2
	 * @param param3
	 *            参数3
	 * @param param4
	 *            参数4
	 * @param param5
	 *            参数5
	 * @param param6
	 *            参数6
	 * @return 错误信息
	 */
	public static String get(int err, String param1, String param2, String param3, String param4, String param5,
			String param6) {
		String[] params = new String[] { param1, param2, param3, param4, param5, param6 };
		return template.getMessage(err, params);
	}

	/**
	 * 根据错误码获取错误消息
	 * 
	 * @param err
	 *            错误码
	 * @param param1
	 *            参数1
	 * @param param2
	 *            参数2
	 * @param param3
	 *            参数3
	 * @param param4
	 *            参数4
	 * @param param5
	 *            参数5
	 * @param param6
	 *            参数6
	 * @param param7
	 *            参数7
	 * @return 错误信息
	 */
	public static String get(int err, String param1, String param2, String param3, String param4, String param5,
			String param6, String param7) {
		String[] params = new String[] { param1, param2, param3, param4, param5, param6, param7 };
		return template.getMessage(err, params);
	}

	/**
	 * 根据错误码获取错误消息
	 * 
	 * @param err
	 *            错误码
	 * @param param1
	 *            参数1
	 * @param param2
	 *            参数2
	 * @param param3
	 *            参数3
	 * @param param4
	 *            参数4
	 * @param param5
	 *            参数5
	 * @param param6
	 *            参数6
	 * @param param7
	 *            参数7
	 * @param param8
	 *            参数8
	 * @return 错误信息
	 */
	public static String get(int err, String param1, String param2, String param3, String param4, String param5,
			String param6, String param7, String param8) {
		String[] params = new String[] { param1, param2, param3, param4, param5, param6, param7, param8 };
		return template.getMessage(err, params);
	}

	/**
	 * 根据错误码获取错误消息
	 * 
	 * @param err
	 *            错误码
	 * @param param1
	 *            参数1
	 * @param param2
	 *            参数2
	 * @param param3
	 *            参数3
	 * @param param4
	 *            参数4
	 * @param param5
	 *            参数5
	 * @param param6
	 *            参数6
	 * @param param7
	 *            参数7
	 * @param param8
	 *            参数8
	 * @param param9
	 *            参数9
	 * @return 错误信息
	 */
	public static String get(int err, String param1, String param2, String param3, String param4, String param5,
			String param6, String param7, String param8, String param9) {
		String[] params = new String[] { param1, param2, param3, param4, param5, param6, param7, param8, param9 };
		return template.getMessage(err, params);
	}
}
