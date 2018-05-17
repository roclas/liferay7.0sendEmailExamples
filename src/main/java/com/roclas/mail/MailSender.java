package com.roclas.mail;

import java.io.UnsupportedEncodingException;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;

import com.liferay.mail.kernel.model.MailMessage;
import com.liferay.mail.kernel.service.MailServiceUtil;

public class MailSender {
	public void sendEmail(String email) throws UnsupportedEncodingException, AddressException {
		MailMessage mailMessage=new MailMessage();
		mailMessage.setBody("set body here");
		mailMessage.setFrom(new InternetAddress("carlos.hernandez@myemail.com","carlos"));
		mailMessage.setSubject("set mail subject here");
		mailMessage.setTo(new InternetAddress(email));
		mailMessage.setHTMLFormat(true);
		MailServiceUtil.sendEmail(mailMessage);
	}
}
