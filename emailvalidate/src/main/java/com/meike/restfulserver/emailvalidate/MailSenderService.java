package com.meike.restfulserver.emailvalidate;

import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.NoSuchProviderException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.meike.restfulserver.emailvalidate.msg.impl.DefaultMsgProducer;

@Component
public class MailSenderService {
	private static final Logger logger = LoggerFactory.getLogger(MailSenderService.class);
	final String from = "liyongfang2017@foxmail.com";
	final String password = "uvzutirbtakoggba";

	private Properties getServerProperty(String debug) {
		Properties props = new Properties();
		props.put("mail.transport.protocol", "smtp");
		props.put("mail.host", "smtp.qq.com");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.port", "465");
		props.put("mail.debug", debug);
		props.put("mail.smtp.socketFactory.port", "465");
		props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		return props;
	}

	private Session getSession(String debug) {
		Session session = Session.getDefaultInstance(getServerProperty(debug), new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(from, password);
			}
		});
		return session;
	}

	public void sendText(String msg, String toMail, String subject, String debug) {
		Session session = getSession(debug);
		// session.setDebug(true);
		Transport transport;
		try {
			transport = session.getTransport();
			InternetAddress addressFrom;
			try {
				addressFrom = new InternetAddress(from);
				MimeMessage message = new MimeMessage(session);
				try {
					message.setSender(addressFrom);
					message.setSubject(subject);
					message.setContent(msg, "text/html;charset=UTF-8");
					message.addRecipient(Message.RecipientType.TO, new InternetAddress(toMail));
					transport.connect();
					Transport.send(message);
					transport.close();
				} catch (AddressException e) {
					logger.info("发送邮件的时间接收人格式出错，接收人为：" + toMail);
					e.printStackTrace();
				} catch (MessagingException e) {
					logger.info("发送邮件失败");
					e.printStackTrace();
				}

			} catch (AddressException e) {
				logger.info("发送邮件的时间发送人格式出错，发送人为：" + from);
				e.printStackTrace();
			}
		} catch (NoSuchProviderException e) {
			logger.info("服务器不存在");
			e.printStackTrace();
		}

	}

	/**
	 * 发送附件
	 */
	public void sendHtml(String msg, String fileName, String toMail, String subject, String debug) {
		Session session = getSession(debug);
		// session.setDebug(true);
		Transport transport;
		try {
			transport = session.getTransport();
			InternetAddress addressFrom;
			try {
				addressFrom = new InternetAddress(from);
				MimeMessage message = new MimeMessage(session);
				try {
					message.setSender(addressFrom);
					message.setSubject(subject);
					// begin insert some image
					MimeMultipart multipart = new MimeMultipart("related");

					// first part (the html)
					BodyPart messageBodyPart = new MimeBodyPart();
					String htmlText = "<H1>Hello 你好</H1><img src=\"cid:image\">";
					messageBodyPart.setContent(htmlText, "text/html;charset=UTF-8");
					// add it
					multipart.addBodyPart(messageBodyPart);

					// second part (the image)
					messageBodyPart = new MimeBodyPart();
					DataSource fds = new FileDataSource("C:\\Users\\lyf\\git\\restfulserver\\emailvalidate\\target\\classes\\static\\beautiful.jpg");

					messageBodyPart.setDataHandler(new DataHandler(fds));
					messageBodyPart.setHeader("Content-ID", "<image>");

					// add image to the multipart
					multipart.addBodyPart(messageBodyPart);

					// put everything together
					message.setContent(multipart, "text/html;charset=UTF-8");

					message.addRecipient(Message.RecipientType.TO, new InternetAddress(toMail));
					transport.connect();
					Transport.send(message);
					transport.close();
				} catch (AddressException e) {
					logger.info("发送邮件的时间接收人格式出错，接收人为：" + toMail);
					e.printStackTrace();
				} catch (MessagingException e) {
					logger.info("发送邮件失败");
					e.printStackTrace();
				}

			} catch (AddressException e) {
				logger.info("发送邮件的时间发送人格式出错，发送人为：" + from);
				e.printStackTrace();
			}
		} catch (NoSuchProviderException e) {
			logger.info("服务器不存在");
			e.printStackTrace();
		}

	}
}
