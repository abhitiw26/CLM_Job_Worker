package com.ltim.lc.app.clm.model;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class TaskSearch implements Serializable{

	private static final long serialVersionUID = 1L;
	private String candidateGroup;
	private String assignee;
	private String candidateUser;
	private Boolean assigned;
	private String state;
	private String processDefinitionKey;
	private String processInstanceKey;
	private String taskDefinitionId;
	private int pageSize;
	private List<Variable> taskVariables;
	
	public String getCandidateGroup() {
		return candidateGroup;
	}
	public void setCandidateGroup(String candidateGroup) {
		this.candidateGroup = candidateGroup;
	}
	public String getAssignee() {
		return assignee;
	}
	public void setAssignee(String assignee) {
		this.assignee = assignee;
	}
	public String getCandidateUser() {
		return candidateUser;
	}
	public void setCandidateUser(String candidateUser) {
		this.candidateUser = candidateUser;
	}
	public Boolean getAssigned() {
		return assigned;
	}
	public void setAssigned(Boolean assigned) {
		this.assigned = assigned;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getProcessDefinitionKey() {
		return processDefinitionKey;
	}
	public void setProcessDefinitionKey(String processDefinitionKey) {
		this.processDefinitionKey = processDefinitionKey;
	}
	public String getProcessInstanceKey() {
		return processInstanceKey;
	}
	public void setProcessInstanceKey(String processInstanceKey) {
		this.processInstanceKey = processInstanceKey;
	}
	public String getTaskDefinitionId() {
		return taskDefinitionId;
	}
	public void setTaskDefinitionId(String taskDefinitionId) {
		this.taskDefinitionId = taskDefinitionId;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public List<Variable> getTaskVariables() {
		return taskVariables;
	}
	public void setTaskVariables(List<Variable> taskVariables) {
		this.taskVariables = taskVariables;
	}
	@Override
	public String toString() {
		return "TaskSearch [candidateGroup=" + candidateGroup + ", assignee=" + assignee + ", candidateUser="
				+ candidateUser + ", assigned=" + assigned + ", state=" + state + ", processDefinitionKey="
				+ processDefinitionKey + ", processInstanceKey=" + processInstanceKey + ", taskDefinitionId="
				+ taskDefinitionId + ", pageSize=" + pageSize + ", taskVariables=" + taskVariables + "]";
	}
	
	
}
