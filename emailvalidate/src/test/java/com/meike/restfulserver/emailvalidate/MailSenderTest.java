package com.meike.restfulserver.emailvalidate;

import java.util.Properties;

import javax.mail.MessagingException;

import org.junit.Before;
import org.junit.Test;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import com.meike.restfulserver.emailvalidate.msg.impl.DefaultMsgProducer;

public class MailSenderTest {
	MailSenderService mailSenderService;

	@Before
	public void before() {
		mailSenderService = new MailSenderService();
	}

	@Test
	public void testSendMsg() throws MessagingException {

		mailSenderService.sendText(new DefaultMsgProducer().produceConfirmMailMsg(), "effective_java@outlook.com", "美课",
				"flase");
		
		 
	}

	@Test
	public void testSendHtml() {
		mailSenderService.sendHtml("", "", "78435431@qq.com", "美课", "flase");
	}
}
