package com.ltim.lc.app.clm.util;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.ltim.lc.app.clm.Constant.EmailConstant;
import com.ltim.lc.app.clm.model.SendEmailRequest;

@Component
public class SendEmailUtil {

	private static final Logger LOGGER = LoggerFactory.getLogger(SendEmailUtil.class);
	private static String SMTP_HOST;
	private static String SMTP_PORT;
	private static String SMTP_EMAIL_ACCOUNT;
	private static String SMTP_EMAIL_ACCOUNT_PASSWORD;
	private static String SMTP_AUTH_ENABLED;
	private static String SMTP_STARTTLS_ENABLED;

	public SendEmailUtil() {

		SMTP_HOST = PropertyUtil.getProperty(EmailConstant.ENV_SMTP_HOST);
		SMTP_PORT = PropertyUtil.getProperty(EmailConstant.ENV_SMTP_PORT);
		SMTP_EMAIL_ACCOUNT = PropertyUtil.getProperty(EmailConstant.ENV_SMTP_EMAIL_ACCOUNT);
		SMTP_EMAIL_ACCOUNT_PASSWORD = PropertyUtil.getProperty(EmailConstant.ENV_SMTP_EMAIL_ACCOUNT_PASSWORD);
		SMTP_AUTH_ENABLED = PropertyUtil.getProperty(EmailConstant.ENV_SMTP_AUTH_ENABLED);
		SMTP_STARTTLS_ENABLED = PropertyUtil.getProperty(EmailConstant.ENV_SMTP_STARTTLS_ENABLED);
	}

	public void sendEmail(SendEmailRequest emailRequest) throws MessagingException, UnsupportedEncodingException {

		MimeMessage msg = new MimeMessage(createEmailSession(emailRequest));
		// set message headers
		msg.addHeader("Content-type", EmailConstant.CONTENT_TYPE);
		msg.addHeader("format", EmailConstant.FORMAT);
		msg.addHeader("Content-Transfer-Encoding", EmailConstant.CONTENT_TRANSFER_ENCODING);

		msg.setFrom(new InternetAddress(SMTP_EMAIL_ACCOUNT,
				(null != emailRequest.getFromDisplayName() ? emailRequest.getFromDisplayName() : SMTP_EMAIL_ACCOUNT)));

		if (null != emailRequest.getReplyToEmail() && "" != emailRequest.getReplyToEmail()) {
			msg.setReplyTo(InternetAddress.parse(emailRequest.getReplyToEmail(), false));
		}

		msg.setSubject(emailRequest.getSubject(), EmailConstant.ENCONDING);

		//msg.setText(emailRequest.getEmailBody(), EmailConstant.ENCONDING);

		msg.setContent(emailRequest.getEmailBody(), EmailConstant.CONTENT_TYPE);

		msg.setSentDate(new Date());

		msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(emailRequest.getToEmail(), false));

		if (null != emailRequest.getCcEmail() && "" != emailRequest.getCcEmail()) {
			msg.setRecipients(Message.RecipientType.CC, InternetAddress.parse(emailRequest.getCcEmail(), false));
		}
		if (null != emailRequest.getBccEmail() && "" != emailRequest.getBccEmail()) {
			msg.setRecipients(Message.RecipientType.BCC, InternetAddress.parse(emailRequest.getBccEmail(), false));
		}

		
		
		Transport.send(msg);

		LOGGER.debug("Email sent sucessful !!!");

	}

	private Session createEmailSession(SendEmailRequest emailRequest) {

		LOGGER.debug("Going to create a new email session for {} ", SMTP_EMAIL_ACCOUNT);

		Properties props = new Properties();

		props.put("mail.smtp.host", SMTP_HOST); // SMTP Host
		props.put("mail.smtp.port", SMTP_PORT); // TLS Port

		props.put("mail.smtp.auth", SMTP_AUTH_ENABLED); // enable authentication
		props.put("mail.smtp.starttls.enable", SMTP_STARTTLS_ENABLED); // enable STARTTLS

		// create Authenticator object to pass in Session.getInstance argument
		Authenticator auth = new Authenticator() {
			// override the getPasswordAuthentication method
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(SMTP_EMAIL_ACCOUNT, SMTP_EMAIL_ACCOUNT_PASSWORD);
			}
		};

		Session session = Session.getInstance(props, auth);

		LOGGER.debug("Email session created for {} ", SMTP_EMAIL_ACCOUNT);

		return session;
	}

}
