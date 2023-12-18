package com.ltim.lc.app.clm.worker;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.ltim.lc.app.clm.client.AccountClient;
import com.ltim.lc.app.clm.model.AccountUpdate;

import io.camunda.zeebe.client.ZeebeClient;
import io.camunda.zeebe.client.api.response.ActivatedJob;
import io.camunda.zeebe.spring.client.annotation.JobWorker;

@Component
public class ClientOnboardingBPMN {
	
	@Autowired
	AccountClient accountClient;
	@Autowired
	ZeebeClient zeebeClient;

	private final static Logger LOG = LoggerFactory.getLogger(ClientOnboardingBPMN.class);

	@JobWorker(type = "generate_account_codes")
	public Map<String, Object> genarateAccountCodes(final ActivatedJob job) {
		final String message_content = job.getVariables();

		LOG.info("genarateAccountCodes :: varibales-> {}", message_content);
		
		Random rand = new Random();
		int num = rand.nextInt(5000000) + 100;
		String accountCode = "" + num;
		
		Map<String, Object> returnMap = new HashMap<String, Object>();
		returnMap.put("ACCOUNT_CODE", accountCode);
		
		LOG.info("genarateAccountCodes :: Account Code-> {}", accountCode);
		
		return returnMap;
	}

	@JobWorker(type = "update_state_in_db")
	public void updateStateDb(final ActivatedJob job) {

		LOG.info(" start updated request status");
		final Map<String, Object> message_content = job.getVariablesAsMap();

		String id = String.valueOf(message_content.get("P_systemGeneratedId"));
		String requestId = String.valueOf( message_content.get("P_REQUEST_ID"));
		String leadId = String.valueOf( message_content.get("P_LEAD_ID"));

		LOG.info("updateStateDb :: varibales-> {}", message_content);

		AccountUpdate update = new AccountUpdate();

		update.setId(String.valueOf(id));

		update.setRequestId(requestId);

		update.setStatus("Rejected");
		
		update.setReferenceId(leadId);

		ResponseEntity<?> rsp = accountClient.updateAccount(update, id);

		LOG.info("updated request status :: Account Code-> {}", rsp);

	}
	
	@JobWorker(type = "clm:approval-collector", fetchVariables = {"IN_ApprovalCount"})
	public Map<String, Object> generateApprovalCollection(final ActivatedJob job) {
		final Map<String, Object> message_content = job.getVariablesAsMap();

		LOG.info("generateApprovalCollection :: varibales-> {}", message_content);
		
		int loopCnt = 0;
		int appvCnt = (int) message_content.get("IN_ApprovalCount");
		List<String> approvalCollection = new ArrayList<>();
		while(loopCnt < appvCnt) {
			approvalCollection.add("Manager_" + loopCnt);
			loopCnt++;
		}
		
		Map<String, Object> returnMap = new HashMap<String, Object>();
		returnMap.put("APPROVAL_COLL", approvalCollection);
		
		LOG.info("generateApprovalCollection :: Return value-> {}", returnMap);
		
		return returnMap;
	}
	
	
	@JobWorker(type = "clm:cancel-onboarding", fetchVariables = {"P_REQUEST_ID"})
	public void publishCancelMsg(final ActivatedJob job) {
		
		final Map<String, Object> message_content = job.getVariablesAsMap();

		LOG.info("publishCancelMsg :: varibales-> {}", message_content);
		
		zeebeClient.newPublishMessageCommand().messageName("CLM_CancelOnboarding_Msg")
					  .correlationKey((String) message_content.get("P_REQUEST_ID"))
					  .send();

		
		LOG.info("publishCancelMsg :: End");
		
	}

}
