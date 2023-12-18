package com.ltim.lc.app.clm.rest.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ltim.lc.app.clm.model.BaseClientRequest;
import com.ltim.lc.app.clm.model.ClaimTask;
import com.ltim.lc.app.clm.model.Decision;
import com.ltim.lc.app.clm.model.OnboardResponse;
import com.ltim.lc.app.clm.model.Process;
import com.ltim.lc.app.clm.model.TaskRequest;
import com.ltim.lc.app.clm.model.TaskResponse;
import com.ltim.lc.app.clm.model.UserDetails;
import com.ltim.lc.app.clm.service.ITaskListService;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/clm/v1")
public class CamundaTaskClientImpl implements ICamundaTaskClient {

	private final Logger LOG = LoggerFactory.getLogger(CamundaTaskClientImpl.class);

	@Autowired
	private ITaskListService taskListService;

	@PostMapping(path = "/task/search", consumes = "application/json", produces = "application/json")
	@Override
	public TaskResponse getTasks(@RequestBody TaskRequest clientRequestBO) {

		LOG.info("getTasks:: STARTED request->{}", clientRequestBO);
		TaskResponse taskResponse = taskListService.getTasks(clientRequestBO);
		
		LOG.info("getTasks:: END");
		return taskResponse;
	}
	
	@PatchMapping(path = "/task/assign" , consumes = "application/json", produces = "application/json")
	@Override
	public void cliamTask(@RequestBody ClaimTask claimTask) {
		LOG.info("cliamTask:: STARTED request->{}", claimTask);
		
		taskListService.cliamTask(claimTask);
		
		LOG.info("cliamTask:: END");
		
	}

	@PatchMapping(path = "/task/complete" , consumes = "application/json", produces = "application/json")
	@Override
	public void completeTask(@RequestBody Process task) {
		
		LOG.info("completeTask:: STARTED request->{}", task);
		
		taskListService.completeTask(task);
		
		LOG.info("completeTask:: END");
	}

	@Override
	public void unassignTask(BaseClientRequest clientRequestBO) {
		// TODO Auto-generated method stub
	}

	@Override
	public void reassignTask(BaseClientRequest clientRequestBO) {
		// TODO Auto-generated method stub
	}

	@PostMapping(path = "/triggerProcess", consumes = "application/json", produces = "application/json")
	@Override
	public Process triggerWorkflow(@RequestBody Process process) {
		
		LOG.info("triggerWorkflow:: STARTED request->{}", process);
		
		Process prcs = taskListService.triggerProcess(process);
		
		LOG.info("triggerWorkflow:: END");
		
		return prcs;
	}

    @PostMapping(path = "/trigger/onboarding" , consumes = "application/json", produces = "application/json")
	@Override
	public ResponseEntity<OnboardResponse> triggerOnboardingFlow(@RequestBody UserDetails userDetails) {
    	
    	LOG.info("triggerOnboardingFlow:: STARTED request->{}", userDetails);
    	
    	OnboardResponse onBoardResponse=taskListService.triggerOnboardingFlow(userDetails);
		
    	LOG.info("triggerOnboardingFlow:: END");
		
		return ResponseEntity.status(HttpStatus.CREATED).body(onBoardResponse);

	}
	
	@PostMapping(path = "/executeBusinessRule" , consumes = "application/json", produces = "application/json")
	@Override
	public String executeBusinessRule(@RequestBody Decision decision) {
		
		LOG.info("executeBusinessRule:: STARTED request->{}", decision);
		
		String response = taskListService.executeBusinessRule(decision);
		
		LOG.info("executeBusinessRule:: END{}");
		
		return response;
	}

}
