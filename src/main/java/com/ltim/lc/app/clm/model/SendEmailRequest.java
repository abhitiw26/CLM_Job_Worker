package com.ltim.lc.app.clm.model;

import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.Objects;


@Component
public class SendEmailRequest implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private String fromDisplayName;
	private String toEmail;
	private String ccEmail;
	private String bccEmail;
	private String subject;
	private String emailBody;
	private String replyToEmail;

	public String getToEmail() {
		return toEmail;
	}

	public void setToEmail(String toEmail) {
		this.toEmail = toEmail;
	}

	public String getCcEmail() {
		return ccEmail;
	}

	public void setCcEmail(String ccEmail) {
		this.ccEmail = ccEmail;
	}

	public String getBccEmail() {
		return bccEmail;
	}

	public void setBccEmail(String bccEmail) {
		this.bccEmail = bccEmail;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getEmailBody() {
		return emailBody;
	}

	public void setEmailBody(String emailBody) {
		this.emailBody = emailBody;
	}

	public String getReplyToEmail() {
		return replyToEmail;
	}

	public void setReplyToEmail(String replyToEmail) {
		this.replyToEmail = replyToEmail;
	}

	public String getFromDisplayName() {
		return fromDisplayName;
	}

	public void setFromDisplayName(String fromDisplayName) {
		this.fromDisplayName = fromDisplayName;
	}

	@Override
	public int hashCode() {
		return Objects.hash(bccEmail, ccEmail, emailBody, fromDisplayName, replyToEmail, subject, toEmail);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SendEmailRequest other = (SendEmailRequest) obj;
		return Objects.equals(bccEmail, other.bccEmail) && Objects.equals(ccEmail, other.ccEmail)
				&& Objects.equals(emailBody, other.emailBody) && Objects.equals(fromDisplayName, other.fromDisplayName)
				&& Objects.equals(replyToEmail, other.replyToEmail) && Objects.equals(subject, other.subject)
				&& Objects.equals(toEmail, other.toEmail);
	}

	@Override
	public String toString() {
		return "SendEmailRequest [fromDisplayName=" + fromDisplayName + ", toEmail=" + toEmail + ", ccEmail=" + ccEmail
				+ ", bccEmail=" + bccEmail + ", subject=" + subject + ", emailBody=" + emailBody + ", replyToEmail="
				+ replyToEmail + "]";
	}

}
