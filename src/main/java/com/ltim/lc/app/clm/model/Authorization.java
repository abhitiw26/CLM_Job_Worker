package com.ltim.lc.app.clm.model;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonProperty;

@Component
public class Authorization {
	
	@JsonProperty("access_token")
	private String token;
	private String scope;
	@JsonProperty("expires_in")
	private String expirey;
	@JsonProperty("token_type")
	private String tokenType;
	@JsonProperty("client_id")
	private String clientId;
	@JsonProperty("client_secret")
	private String clientSecret;
	private String audience;
	@JsonProperty("grant_type")
	private String grantType;
	
	
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public String getScope() {
		return scope;
	}
	public void setScope(String scope) {
		this.scope = scope;
	}
	public String getExpirey() {
		return expirey;
	}
	public void setExpirey(String expirey) {
		this.expirey = expirey;
	}
	public String getTokenType() {
		return tokenType;
	}
	public void setTokenType(String tokenType) {
		this.tokenType = tokenType;
	}
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
	public String getAudience() {
		return audience;
	}
	public void setAudience(String audience) {
		this.audience = audience;
	}
	public String getGrantType() {
		return grantType;
	}
	public void setGrantType(String grantType) {
		this.grantType = grantType;
	}
	
	
}
