package com.roclas.emailActivator;

import com.liferay.mail.kernel.model.MailMessage;
import com.liferay.mail.kernel.service.MailServiceUtil;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

import javax.mail.internet.InternetAddress;

public class Activator implements BundleActivator {

	@Override
	public void start(BundleContext bundleContext) throws Exception {
		MailMessage mailMessage=new MailMessage();
		mailMessage.setBody("set body here");
		mailMessage.setFrom(new InternetAddress("carlos.hernandez@liferay.com","carlos"));
		mailMessage.setSubject("set mail subject here");
		mailMessage.setTo(new InternetAddress("carlos@liferay.com"));
		mailMessage.setHTMLFormat(true);
		MailServiceUtil.sendEmail(mailMessage);
	}

	@Override
	public void stop(BundleContext bundleContext) throws Exception {
	}

}
