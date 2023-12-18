package com.ltim.lc.app.clm.model;

import org.springframework.stereotype.Component;

@Component
public class SendEmailResult {
	private String code;
	private String message;
	
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
	@Override
	public String toString() {
		return "SendEmailResult [code=" + code + ", message=" + message + "]";
	}

}
