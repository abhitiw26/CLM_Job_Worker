package com.ltim.lc.app.clm.model;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Component
@Configuration
@ConfigurationProperties(prefix = "clm.auth")
public class ApplicationProps {

	private String clientId;

	private String clientSecret;

	private String url;

	private String audience;

	private String taskURL;
	
	private String taskSearchURL;
	
	private String claimTaskURL;
	
	private String completeTaskURL;

	public String getClientId() {
		return clientId;
	}

	public void setClientId(String clientId) {
		this.clientId = clientId;
	}

	public String getClientSecret() {
		return clientSecret;
	}

	public void setClientSecret(String clientSecret) {
		this.clientSecret = clientSecret;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getAudience() {
		return audience;
	}

	public void setAudience(String audience) {
		this.audience = audience;
	}

	public String getTaskURL() {
		return taskURL;
	}

	public void setTaskURL(String taskURL) {
		this.taskURL = taskURL;
	}

	public String getTaskSearchURL() {
		return taskSearchURL;
	}

	public void setTaskSearchURL(String taskSearchURL) {
		this.taskSearchURL = taskSearchURL;
	}

	public String getClaimTaskURL() {
		return claimTaskURL;
	}

	public void setClaimTaskURL(String claimTaskURL) {
		this.claimTaskURL = claimTaskURL;
	}

	public String getCompleteTaskURL() {
		return completeTaskURL;
	}

	public void setCompleteTaskURL(String completeTaskURL) {
		this.completeTaskURL = completeTaskURL;
	}
	
}
