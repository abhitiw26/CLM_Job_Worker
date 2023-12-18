package com.ltim.lc.app.clm.model;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserDetails implements Serializable{

	private static final long serialVersionUID = 1L;

	private String leadId;
	
	private String reqeustId;

	private LoggedUser loggedUser;

	public String getLeadId() {
		return leadId;
	}

	public void setLeadId(String leadId) {
		this.leadId = leadId;
	}

	public LoggedUser getLoggedUser() {
		return loggedUser;
	}

	public void setLoggedUser(LoggedUser loggedUser) {
		this.loggedUser = loggedUser;
	}
	

	public String getReqeustId() {
		return reqeustId;
	}

	public void setReqeustId(String reqeustId) {
		this.reqeustId = reqeustId;
	}

	@Override
	public String toString() {
		return "UserDetails [leadId=" + leadId + ", reqeustId=" + reqeustId + ", loggedUser=" + loggedUser + "]";
	}

}
