package com.ltim.lc.app.clm.rest.api;

import org.springframework.http.ResponseEntity;

import com.ltim.lc.app.clm.model.BaseClientRequest;
import com.ltim.lc.app.clm.model.ClaimTask;
import com.ltim.lc.app.clm.model.Decision;
import com.ltim.lc.app.clm.model.OnboardResponse;
import com.ltim.lc.app.clm.model.Process;
import com.ltim.lc.app.clm.model.TaskRequest;
import com.ltim.lc.app.clm.model.TaskResponse;
import com.ltim.lc.app.clm.model.UserDetails;

public interface ICamundaTaskClient {
	
	public TaskResponse getTasks(TaskRequest clientRequestBO);
	public void cliamTask(ClaimTask claimTask);
	public void completeTask(Process task);
	public void unassignTask(BaseClientRequest clientRequestBO);
	public void reassignTask(BaseClientRequest clientRequestBO);
	public Process triggerWorkflow(Process process);
	public ResponseEntity<OnboardResponse> triggerOnboardingFlow(UserDetails userDetails);
	public String executeBusinessRule(Decision decision);
}
