package com.meike.restfulserver.common;

import com.meike.restfulserver.common.message.ErrorMessage;

@SuppressWarnings("rawtypes")
public class RestResResult<T> {
	private T body;
	private Header header;
	public static String DEFOULT_MSG = "ok";

	public RestResResult() {
		header = new Header();
	}

	private class Header {
		private long code = 0;
		private String msg = DEFOULT_MSG;

		public long getCode() {
			return code;
		}

		public void setCode(long code) {
			this.code = code;
		}

		public String getMsg() {
			return msg;
		}

		public void setMsg(String msg) {
			this.msg = msg;
		}

	}

	public static <T> RestResResult Ok() {
		return new RestResResult<T>();

	}

	public T getBody() {
		return body;
	}

	public void setBody(T body) {
		this.body = body;
	}

	public Header getHeader() {
		return header;
	}

	public void setHeader(Header header) {
		this.header = header;
	}

	public void setHeadContent(int code, String msg) {
		header.code = code;
		header.msg = msg;
	}

	public void setHeadContentEx(int passwordErr, String[] params) {
		header.code = passwordErr;
		header.msg = ErrorMessage.get(passwordErr, params);
	}

	public void setHeadContentEx(int passwordErr) {
		header.code = passwordErr;
		header.msg = ErrorMessage.get(passwordErr);
	}
}
