package com.meike.restfulserver.common.message;

import java.util.MissingResourceException;
import java.util.ResourceBundle;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * 消息模板实现
 * 
 * @author liuzk
 *
 */

@Component
@Scope("singleton")
public class MessageTemplateImpl implements IMessageTemplate {

	private ResourceBundle bundle = null;
	private static String fileLoaction = "Message_zh";

	MessageTemplateImpl() {
		loadResource(fileLoaction);
	}

	@Override
	public String getMessage(Integer err) {
		return getMessage(err, null);
	}

	@Override
	public String getMessage(Integer err, String[] params) {
		String s;
		try {
			String t = bundle.getString(String.valueOf(err));
			s = print(t, params);
		} catch (MissingResourceException e) {
			s = e.getMessage();
		}
		return s;
	}

	private void loadResource(String resource) {
		bundle = ResourceBundle.getBundle(resource);
	}

	private String print(String message, String[] values) {
		if ((values == null) || (values.length == 0)) {
			return message;
		}

		String msg = message;
		for (int i = 0; i < values.length; ++i) {
			msg = msg.replace("{" + i + "}", values[i]);
		}

		return msg;
	}

}