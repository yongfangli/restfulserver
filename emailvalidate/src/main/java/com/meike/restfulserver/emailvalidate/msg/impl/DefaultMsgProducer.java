package com.meike.restfulserver.emailvalidate.msg.impl;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.MessagingException;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMultipart;

import com.meike.restfulserver.emailvalidate.msg.IMsgProducer;

public class DefaultMsgProducer implements IMsgProducer {
	private String title;
	private String content;

	@Override
	public String produceConfirmMailMsg() {
		StringBuffer htmlBuf = new StringBuffer();
		htmlBuf.append("<!DOCTYPE html>").append("<html>").append("<meta charset=\"utf-8\">")
				.append("<meta charset=\"utf-8\">").append("<title>" + title + "</title>")
				.append("<style type=\"text/css\">")
				.append(".test{font-family:\"Microsoft Yahei\";font-size: 18px;color: red;}").append("</style>")
				.append("</head>").append("<body>").append("<div>" + content + "</div>").append("</body>")
				.append("</html>");
		return htmlBuf.toString();
	}

	public void addImage(MimeMultipart multipart) throws MessagingException {
		BodyPart messageBodyPart = new MimeBodyPart();
		String htmlText = "<H1>Hello</H1><img src=\"cid:image\">";
		messageBodyPart.setContent(htmlText, "text/html");
		// add it
		multipart.addBodyPart(messageBodyPart);
	}

	public void userDataHandlerForImage(String filePath) throws MessagingException {
		BodyPart messageBodyPart = new MimeBodyPart();
		DataSource fds = new FileDataSource(filePath);
		messageBodyPart.setDataHandler(new DataHandler(fds));
		messageBodyPart.setHeader("Content-ID", "<image>");
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

}
