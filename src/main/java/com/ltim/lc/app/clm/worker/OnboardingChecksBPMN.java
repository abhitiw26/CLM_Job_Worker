package com.ltim.lc.app.clm.worker;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import io.camunda.zeebe.client.ZeebeClient;
import io.camunda.zeebe.client.api.response.ActivatedJob;
import io.camunda.zeebe.spring.client.annotation.JobWorker;

@Component
public class OnboardingChecksBPMN {

	private final static Logger LOG = LoggerFactory.getLogger(OnboardingChecksBPMN.class);

	@Autowired
	private ZeebeClient zeebeClient;

	
	@JobWorker(type = "clm_manual_check", fetchVariables = { "CLM_ReqID", "CLM_MAN_MSG_WAIT_NM" })
	public void clmManualCheck(final ActivatedJob job) {
		Map<String, Object> inputMap = job.getVariablesAsMap();

		LOG.info("clm_manual_check :: varibales-> {}", inputMap);

		String requestId = (String) inputMap.get("CLM_ReqID");
		String message = (String) inputMap.get("CLM_MAN_MSG_WAIT_NM");
		
		zeebeClient.newPublishMessageCommand().messageName(message).correlationKey(requestId).send();
		
		LOG.info("clm_manual_check :: Message Triigered for Message Name->{}, Request Id->{}", message, requestId);
	}
	
	@JobWorker(type="clm:aml_check")
	public Map<String, Object> amlCheck (final ActivatedJob job) {
		
		Map<String, Object> inputMap = job.getVariablesAsMap();
		LOG.info("amlCheck :: varibales-> {}", inputMap);
		
		String amlCheckType = (String) inputMap.get("aml_check_type");
		
		Map<String, Object> returnMap = new HashMap<String, Object>();
		
		switch (amlCheckType) {
			case "PEP": {
				
				returnMap.put("POL_STATUS", true);
				returnMap.put("POL_STRING", "POSITIVE");
				break;
			}
			case "NN": {
				returnMap.put("POL_STATUS", false);
				returnMap.put("POL_STRING", "NEGETIVE");
				break;
			}
			case "LC": {
				returnMap.put("POL_STATUS", false);
				returnMap.put("POL_STRING", "WARNING");
				break;
			}
			case "SAN": {
						
						returnMap.put("POL_STATUS", true);
						returnMap.put("POL_STRING", "POSITIVE");
						break;
					}
			case "WCNC": {
				
				returnMap.put("POL_STATUS", true);
				returnMap.put("POL_STRING", "POSITIVE");
				break;
			}
			case "GSC": {
				
				returnMap.put("POL_STATUS", true);
				returnMap.put("POL_STRING", "POSITIVE");
				break;
			}
			default:
				returnMap.put("POL_STATUS", true);
				returnMap.put("POL_STRING", "POSITIVE");
				break;
			}
				
		LOG.info("amlCheck :: END Return value-> {}", returnMap);
		return returnMap;
	}
	
	@JobWorker(type = "clm:msg-manual-throw")
	public Map<String, Object> triggerManualCheck(final ActivatedJob job) {
		Map<String, Object> inputMap = job.getVariablesAsMap();

		LOG.info("triggerManualCheck :: varibales-> {}", inputMap);

		String requestId = (String) inputMap.get("CLM_ReqeustId");
		String message = (String) inputMap.get("CLM_MAN_MSG_WAIT_NM");

		zeebeClient.newPublishMessageCommand().messageName("MSG_MANUAL").correlationKey(requestId).send();
		
		Map<String, Object> returnMap = new HashMap<String, Object>();
		
		return returnMap;
	}

}
