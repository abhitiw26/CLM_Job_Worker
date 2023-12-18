package com.ltim.lc.app.clm.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.ltim.lc.app.clm.model.ApplicationProps;
import com.ltim.lc.app.clm.model.ClaimTask;
import com.ltim.lc.app.clm.model.Decision;
import com.ltim.lc.app.clm.model.OnboardResponse;
import com.ltim.lc.app.clm.model.Process;
import com.ltim.lc.app.clm.model.Task;
import com.ltim.lc.app.clm.model.TaskRequest;
import com.ltim.lc.app.clm.model.TaskResponse;
import com.ltim.lc.app.clm.model.TaskSearch;
import com.ltim.lc.app.clm.model.UserDetails;
import com.ltim.lc.app.clm.model.Variable;
import com.ltim.lc.app.clm.util.AuthenticationUtil;

import io.camunda.zeebe.client.ZeebeClient;
import io.camunda.zeebe.client.api.response.EvaluateDecisionResponse;
import io.camunda.zeebe.client.api.response.ProcessInstanceEvent;

@Component
public class TaskListServiceImpl implements ITaskListService {
	
	private final Logger LOG = LoggerFactory.getLogger(TaskListServiceImpl.class);	
	
	@Autowired
	private AuthenticationUtil authUtil;
	@Autowired
	private ApplicationProps props;
	@Autowired
	private ZeebeClient zeebeClient;
	@Autowired
	private ObjectMapper mapper;	
	@Autowired
	private RestTemplate rest;
	
	@Override
	public TaskResponse getTasks(TaskRequest taskSearch) {
		
		LOG.info("getTasks:: STARTED");
		
		TaskResponse taskResponse = new TaskResponse();
		
		HttpHeaders headers = new HttpHeaders();
		// set `content-type` header
        headers.setContentType(MediaType.APPLICATION_JSON);
        // set `accept` header
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        headers.setBearerAuth(authUtil.getAuthToken());
        
        if (null == taskSearch) {
        	taskSearch = new TaskRequest();
        }        
               
        HttpEntity<TaskSearch> httpEntity = new HttpEntity<>(taskSearch.getTaskSearch(), headers);
        
        ResponseEntity<String> res = rest.postForEntity(props.getTaskSearchURL(), httpEntity, String.class);
		
		if (null != res && null != res.getBody() && res.getStatusCode().equals(HttpStatus.OK)) {
			
			List<Task> taskLIst = null;
			try {
				taskLIst = mapper.readValue(res.getBody(),
													TypeFactory.defaultInstance()
													.constructCollectionType(List.class,Task.class)
											);
			} catch (JsonProcessingException e) {
				LOG.error("getTasks:: Error Occured while parsing API response into Object->{}", e);	
			}
			
			if (null != taskLIst && !taskLIst.isEmpty()) {
				for (Task task : taskLIst) {
					String responsibility = (null != task.getAssignee() && !task.getAssignee().isEmpty() ? 
														task.getAssignee() : 
														(null != task.getCandidateGroups() && !task.getCandidateGroups().isEmpty() 
														? task.getCandidateGroups().get(0) : ""));
					
					task.setResponsibility(responsibility);
					task.setTaskName(task.getProcessName() + "::" + task.getName());
				}
			}
			
			taskResponse.setTaskList(taskLIst);
			//LOG.info("getTasks:: taskLIst->{}", taskLIst);
		}
		
		return taskResponse;
	}

	@Override
	public Process triggerProcess(Process process) {
		LOG.info("triggerProcess:: STARTED");
		
		Map<String, Object> vars = new HashMap<String, Object>();
		if (null != process.getVariables() && !process.getVariables().isEmpty()) {
			for(Variable var : process.getVariables()) {
				vars.put(var.getName(), var.getValue());
			}
		}
		
		if (process.isReturnProcessInstanceKey()) {
			ProcessInstanceEvent instanceEvent = zeebeClient.newCreateInstanceCommand().bpmnProcessId(process.getProcessId()).
				latestVersion().variables(vars).send().join();
			
			process.setProcessInstanceKey(String.valueOf(instanceEvent.getProcessInstanceKey()));
		} else {
			zeebeClient.newCreateInstanceCommand().bpmnProcessId(process.getProcessId()).
			latestVersion().variables(vars).send();
			
			process = null;
		}
		
		LOG.info("triggerProcess:: END");
		
		return process;
	}

	@Override
	public void cliamTask(ClaimTask claimTask) {
		
		LOG.info("cliamTask:: STARTED");
		
		HttpHeaders headers = new HttpHeaders();
		// set `content-type` header
        headers.setContentType(MediaType.APPLICATION_JSON);
        // set `accept` header
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        headers.setBearerAuth(authUtil.getAuthToken());
        
        HttpEntity<ClaimTask> httpEntity = new HttpEntity<>(claimTask, headers);
        
        Map<String, Object> urlParams = new HashMap<>();
        urlParams.put("taskId", claimTask.getTaskId());
        
        UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(props.getClaimTaskURL());
        builder.uriVariables(urlParams);
        
        
        //String res = rest.patchForObject(builder.build().encode().toUriString() , httpEntity, String.class);
        ResponseEntity<String> res = rest.exchange(builder.build().encode().toUriString() , HttpMethod.PATCH , httpEntity, String.class);
		
        LOG.info("cliamTask:: END with API Response->{}", res);
        
	}

	@Override
	public String executeBusinessRule(Decision decision) {
		
		LOG.info("executeBusinessRule:: STARTED");
		
		 Map<String, Object> mapVariables = decision.getVariables().stream().collect(
	                Collectors.toMap(Variable::getName, Variable::getValue));
		 String decisionId=decision.getDecisionId();
		 
		EvaluateDecisionResponse decisionResponse = zeebeClient.newEvaluateDecisionCommand().
													decisionId(decisionId).
													variables(mapVariables).
													send().join();
		LOG.info("executeBusinessRule:: END-> {}", decisionResponse.getDecisionOutput());
		
		return decisionResponse.getDecisionOutput();
		
	}

	@Override
	public OnboardResponse triggerOnboardingFlow(UserDetails userDetails) {
		Random rand = new Random();
		int num = rand.nextInt(9000000) + 1000000;
		userDetails.setReqeustId(String.valueOf(num));
		
		Process p = new Process();
		p.setProcessId("CLM-Onboarding");
		p.setReturnProcessInstanceKey(false);
		p.setVariables(new ArrayList<Variable>());
		
		Variable var = new Variable();
		var.setName("P_LEAD_ID");
		var.setValue(userDetails.getLeadId());
		p.getVariables().add(var);
		
		var = new Variable();
		var.setName("P_USER_DTL");
		var.setValue(userDetails.getLoggedUser());
		p.getVariables().add(var);
		
		var = new Variable();
		var.setName("P_REQUEST_ID");
		var.setValue(String.valueOf(num));
		p.getVariables().add(var);
		
		this.triggerProcess(p);
		
		OnboardResponse obr = new OnboardResponse();
		obr.setRequestId(String.valueOf(num));
		obr.setStatus("CLM Onboaring flow started");
		return obr;
	}

	@Override
	public void completeTask(Process task) {
		LOG.info("completeTask:: STARTED");
		
		HttpHeaders headers = new HttpHeaders();
		// set `content-type` header
        headers.setContentType(MediaType.APPLICATION_JSON);
        // set `accept` header
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        headers.setBearerAuth(authUtil.getAuthToken());
       
        HttpEntity<Process> httpEntity = new HttpEntity<>(task, headers);
        
        Map<String, Object> urlParams = new HashMap<>();
        urlParams.put("taskId", task.getTaskId());
        
        UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(props.getCompleteTaskURL());
        builder.uriVariables(urlParams);
        
                
        ResponseEntity<String> res = rest.exchange(builder.build().encode().toUriString() , HttpMethod.PATCH , httpEntity, String.class);
		
        LOG.info("completeTask:: END with API Response->{}", res);
		
	}

}
