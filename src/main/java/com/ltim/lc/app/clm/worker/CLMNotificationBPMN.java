package com.ltim.lc.app.clm.worker;
import com.ltim.lc.app.clm.model.SendEmailRequest;
import com.ltim.lc.app.clm.model.SendEmailResult;
import com.ltim.lc.app.clm.util.SendEmailUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import io.camunda.zeebe.client.api.response.ActivatedJob;
import io.camunda.zeebe.spring.client.annotation.JobWorker;

import javax.mail.MessagingException;
import java.io.UnsupportedEncodingException;
import java.util.Map;

@Component
public class CLMNotificationBPMN {
	
	private final static Logger LOGGER = LoggerFactory.getLogger(CLMNotificationBPMN.class);
	
	@Autowired
	SendEmailUtil send_email_util;

	@Autowired
	SendEmailResult sendEmailResult;
	@Autowired
	SendEmailRequest request;


	@JobWorker(type = "mail_notification")
	public void mailNotification(final ActivatedJob job) throws MessagingException, UnsupportedEncodingException {
		final String message_content = job.getVariables();

		LOGGER.info("mailNotification :: varibales-> {}", message_content);

		Map<String, Object> variablesAsMap = job.getVariablesAsMap();


		request.setFromDisplayName(variablesAsMap.get("DisplayName").toString());
		request.setSubject(variablesAsMap.get("Subject").toString());
		request.setToEmail(variablesAsMap.get("TO").toString());
		request.setCcEmail(variablesAsMap.get("CC").toString());
		//request.setBccEmail(variablesAsMap.get("BCC").toString());
		request.setEmailBody("<!DOCTYPE html>\n" +
				"<html>\n" +
				"<head>\n" +
				"    <title>Your Email Template</title>\n" +
				"</head>\n" +
				"<body>\n" +
				"    <table width=\"100%\" cellspacing=\"0\" cellpadding=\"0\">\n" +
				"        <tr>\n" +
				"            <td>\n" +
				"                <table  width=\"600\" cellpadding=\"0\" cellspacing=\"0\">\n" +
				"                    <!-- Header -->\n" +
				"                    <tr>\n" +
				"                        <td>\n" +
				"                            <h1>"+variablesAsMap.get("DisplayName").toString()+"</h1>\n" +
				"                        </td>\n" +
				"                    </tr>\n" +
				"                    <!-- Content -->\n" +
				"                    <tr>\n" +
				"                        <td>\n" +
				"                            <p>"+variablesAsMap.get("Header").toString()+"</p>\n" +
				"                            <p>"+variablesAsMap.get("Body").toString()+"</p>\n" +
				"                        </td>\n" +
				"                    </tr>\n" +
				"                    <!-- Footer -->\n" +
				"                    <tr>\n" +
				"                        <td>\n" +
				"                            <p>"+variablesAsMap.get("Footer").toString()+"</p>\n" +
				"                        </td>\n" +
				"                    </tr>\n" +
				"                </table>\n" +
				"            </td>\n" +
				"        </tr>\n" +
				"    </table>\n" +
				"</body>\n" +
				"</html>");

		/**
		 * Setting required Environment variable values
		 */

		//send_email_util.sendEmail(request);
		LOGGER.info("Input received-> {}", request);

		try {
			send_email_util.sendEmail(request);
		} catch ( UnsupportedEncodingException | MessagingException e ) {//
			LOGGER.debug("Exception occured while sending email", e);

		}


		sendEmailResult.setCode("SUCCESS");
		sendEmailResult.setMessage("Email sent sucessfully");
		
	}


}
