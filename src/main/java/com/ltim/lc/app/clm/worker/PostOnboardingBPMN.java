package com.ltim.lc.app.clm.worker;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.ltim.lc.app.clm.client.AccountClient;
import com.ltim.lc.app.clm.model.AccountUpdate;

import io.camunda.zeebe.client.api.response.ActivatedJob;
import io.camunda.zeebe.spring.client.annotation.JobWorker;

@Component
public class PostOnboardingBPMN {

	private final static Logger LOG = LoggerFactory.getLogger(PostOnboardingBPMN.class);
	
	@Autowired
	AccountClient accountClient;
		
	@JobWorker(type = "welcome_kit")
	public void generateWelcomeKit(final ActivatedJob job) {
		final String message_content = job.getVariables();

		LOG.info("generateWelcomeKit :: varibales-> {}", message_content);
	}

	@JobWorker(type = "provide_access_to_client_portal")
	public void accessClientportal(final ActivatedJob job) {
		final String message_content = job.getVariables();

		LOG.info("accessClientportal :: varibales-> {}", message_content);
	}

	@JobWorker(type = "transfer_corpus")
	public void transferCorpus(final ActivatedJob job) {
		final String message_content = job.getVariables();

		LOG.info("transferCorpus :: varibales-> {}", message_content);
	}
	
	@JobWorker(type = "clm:completeRequest")
	public void completeRequest(final ActivatedJob job) {
		//final String message_content = job.getVariables();
		
		
		LOG.info(" start updated request status");
		final Map<String, Object> message_content = job.getVariablesAsMap();

		String id = String.valueOf(message_content.get("P_systemGeneratedId"));
		String requestId = String.valueOf( message_content.get("P_REQUEST_ID"));
		String leadId = String.valueOf( message_content.get("P_LEAD_ID"));

		LOG.info("updateStateDb :: varibales-> {}", message_content);

		AccountUpdate update = new AccountUpdate();

		update.setId(String.valueOf(id));

		update.setRequestId(requestId);

		update.setStatus("Approved");
		
		update.setReferenceId(leadId);

		ResponseEntity<?> rsp = accountClient.updateAccount(update, id);

		LOG.info("updated request status :: Account Code-> {}", rsp);

		LOG.info("completeRequest :: varibales-> {}", message_content);
	}

}
