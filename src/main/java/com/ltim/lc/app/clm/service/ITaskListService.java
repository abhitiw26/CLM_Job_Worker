package com.ltim.lc.app.clm.service;

import com.ltim.lc.app.clm.model.ClaimTask;
import com.ltim.lc.app.clm.model.Decision;
import com.ltim.lc.app.clm.model.OnboardResponse;
import com.ltim.lc.app.clm.model.Process;
import com.ltim.lc.app.clm.model.TaskRequest;
import com.ltim.lc.app.clm.model.TaskResponse;
import com.ltim.lc.app.clm.model.UserDetails;

public interface ITaskListService {

	public TaskResponse getTasks(TaskRequest taskSearch);
	public Process triggerProcess(Process process);
	public void cliamTask(ClaimTask claimTask);
	public String executeBusinessRule(Decision decision);
	public OnboardResponse triggerOnboardingFlow(UserDetails userDetails);
	public void completeTask(Process task);
}
