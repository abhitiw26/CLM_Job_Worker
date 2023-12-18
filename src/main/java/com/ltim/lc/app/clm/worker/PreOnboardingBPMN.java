package com.ltim.lc.app.clm.worker;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ltim.lc.app.clm.client.AccountClient;
import com.ltim.lc.app.clm.model.AccountDetails;
import com.ltim.lc.app.clm.model.AccountResponse;

import io.camunda.zeebe.client.api.response.ActivatedJob;
import io.camunda.zeebe.spring.client.annotation.JobWorker;

@Component
public class PreOnboardingBPMN {

	private final static Logger LOG = LoggerFactory.getLogger(PreOnboardingBPMN.class);
	
	@Autowired
	AccountClient accountClient;
	
	@Autowired
	private ObjectMapper objMapper;

	@JobWorker(type = "kyc_validation")
	public void kycValidation(final ActivatedJob job) {
		final String message_content = job.getVariables();
		LOG.info("kycValidation:: varibales->{}", message_content);
	}
	
	//save_request_db
	
	@JobWorker(type = "save_request_db")
	public Map<String, Object> save_request_db(final ActivatedJob job) {
		final Map<String, Object> message_content = job.getVariablesAsMap();
		LOG.info("save_request_db:: varibales->{}", message_content);
		String leadId = String.valueOf(message_content.get("P_LEAD_ID"));
		String requestId = String.valueOf( message_content.get("P_REQUEST_ID"));
		AccountDetails accountDetails = new AccountDetails();
		accountDetails.setReferenceId(leadId);
		accountDetails.setRequestId(requestId);
		accountDetails.setStatus("Open");
		ResponseEntity<?> rsp=accountClient.createAccount(accountDetails);
		AccountResponse accountResponse = null;
		if (rsp != null && (rsp.getStatusCode().equals(HttpStatus.OK))) {
			try {
				accountResponse = objMapper.readValue(objMapper.writeValueAsString(rsp.getBody()), AccountResponse.class);
			} catch (JsonProcessingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			LOG.info("**************response from account service********** " + accountResponse);
		}
		
		Map<String, Object> returnMap = new HashMap<>();
		
		returnMap.put("systemGeneratedId", accountResponse.getId());
		LOG.info("save_request_db:: Save API Response ->{}, Worker ReturnMap->{}", rsp, returnMap);
		
		return returnMap;
	}

}
