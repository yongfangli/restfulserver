package com.meike.restfulserver.common.message;

/**
 * 消息模板
 * 
 * @author liuzk
 *
 */
public interface IMessageTemplate {
	/**
	 * 获取消息
	 * 
	 * @param err
	 *            错误码
	 * @return 消息串
	 */
	String getMessage(Integer err);

	/**
	 * 获取消息
	 * 
	 * @param err
	 *            错误码
	 * @param params
	 *            参数列表
	 * @return 消息串
	 */
	String getMessage(Integer err, String[] params);
}
