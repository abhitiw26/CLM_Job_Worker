package com.ltim.lc.app.clm.model;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.ltim.lc.app.clm.Constant.TaskState;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class Task implements Serializable{

	private static final long serialVersionUID = 1L;

	private String id;

	private String name;

	private String processName;

	private String processDefinitionKey;

	private String processInstanceKey;

	private String assignee;

	private String creationDate;
	
	private String completionDate;

	private TaskState taskState;

	private List<String> candidateUsers;

	private List<String> candidateGroups;

	private String followUpDate;

	private String dueDate;

	private String formKey;

	private String taskDefinitionId;

	private List<String> sortValues;

	private Boolean isFirst;

	private List<Variable> variables;
	
	private String responsibility;
	
	private String taskName;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getProcessName() {
		return processName;
	}

	public void setProcessName(String processName) {
		this.processName = processName;
	}

	public String getProcessDefinitionKey() {
		return processDefinitionKey;
	}

	public void setProcessDefinitionId(String processDefinitionKey) {
		this.processDefinitionKey = processDefinitionKey;
	}

	public String getProcessInstanceKey() {
		return processInstanceKey;
	}

	public void setProcessInstanceKey(String processInstanceKey) {
		this.processInstanceKey = processInstanceKey;
	}

	public String getAssignee() {
		return assignee;
	}

	public void setAssignee(String assignee) {
		this.assignee = assignee;
	}

	public String getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(String creationDate) {
		this.creationDate = creationDate;
	}

	public String getCompletionDate() {
		return completionDate;
	}

	public void setCompletionDate(String completionDate) {
		this.completionDate = completionDate;
	}

	public TaskState getTaskState() {
		return taskState;
	}

	public void setTaskState(TaskState taskState) {
		this.taskState = taskState;
	}

	public List<String> getCandidateUsers() {
		return candidateUsers;
	}

	public void setCandidateUsers(List<String> candidateUsers) {
		this.candidateUsers = candidateUsers;
	}

	public List<String> getCandidateGroups() {
		return candidateGroups;
	}

	public void setCandidateGroups(List<String> candidateGroups) {
		this.candidateGroups = candidateGroups;
	}

	public String getFollowUpDate() {
		return followUpDate;
	}

	public void setFollowUpDate(String followUpDate) {
		this.followUpDate = followUpDate;
	}

	public String getDueDate() {
		return dueDate;
	}

	public void setDueDate(String dueDate) {
		this.dueDate = dueDate;
	}

	public String getFormKey() {
		return formKey;
	}

	public void setFormKey(String formKey) {
		this.formKey = formKey;
	}

	public String getTaskDefinitionId() {
		return taskDefinitionId;
	}

	public void setTaskDefinitionId(String taskDefinitionId) {
		this.taskDefinitionId = taskDefinitionId;
	}

	public List<String> getSortValues() {
		return sortValues;
	}

	public void setSortValues(List<String> sortValues) {
		this.sortValues = sortValues;
	}

	public Boolean getIsFirst() {
		return isFirst;
	}

	public void setIsFirst(Boolean isFirst) {
		this.isFirst = isFirst;
	}

	public List<Variable> getVariables() {
		return variables;
	}

	public void setVariables(List<Variable> variables) {
		this.variables = variables;
	}

	public String getResponsibility() {
		return responsibility;
	}

	public void setResponsibility(String responsibility) {
		this.responsibility = responsibility;
	}

	public void setProcessDefinitionKey(String processDefinitionKey) {
		this.processDefinitionKey = processDefinitionKey;
	}

	public String getTaskName() {
		return taskName;
	}

	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}

	@Override
	public String toString() {
		return "Task [id=" + id + ", name=" + name + ", processName=" + processName + ", processDefinitionKey="
				+ processDefinitionKey + ", processInstanceKey=" + processInstanceKey + ", assignee=" + assignee
				+ ", creationDate=" + creationDate + ", completionDate=" + completionDate + ", taskState=" + taskState
				+ ", candidateUsers=" + candidateUsers + ", candidateGroups=" + candidateGroups + ", followUpDate="
				+ followUpDate + ", dueDate=" + dueDate + ", formKey=" + formKey + ", taskDefinitionId="
				+ taskDefinitionId + ", sortValues=" + sortValues + ", isFirst=" + isFirst + ", variables=" + variables
				+ ", responsibility=" + responsibility + ", taskName=" + taskName + "]";
	}

}
