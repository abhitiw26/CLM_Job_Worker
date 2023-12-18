package com.ltim.lc.app.clm.model;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;


@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class AccountDetails implements Serializable{
	
	private static final long serialVersionUID = 1L;

	private String requestId;
	
	
	private String status;
	
	public String getReferenceId() {
		return referenceId;
	}


	public void setReferenceId(String referenceId) {
		this.referenceId = referenceId;
	}


	private String referenceId;
	
	


	public String getRequestId() {
		return requestId;
	}


	public void setRequestId(String requestId) {
		this.requestId = requestId;
	}


	public String getStatus() {
		return status;
	}


	public void setStatus(String status) {
		this.status = status;
	}


	@Override
	public String toString() {
		return "AccountDetails [requestId=" + requestId + ", status=" + status + ", referenceId=" + referenceId + "]";
	}
	
	
	

}

