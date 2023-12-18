package com.ltim.lc.app.clm.model;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class Process extends BaseClientRequest implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private String processInstanceKey;
	private List<Variable> variables;
	private boolean returnProcessInstanceKey;
	private String processId;
	private String taskId;

	public String getProcessInstanceKey() {
		return processInstanceKey;
	}

	public void setProcessInstanceKey(String processInstanceKey) {
		this.processInstanceKey = processInstanceKey;
	}

	public List<Variable> getVariables() {
		return variables;
	}

	public void setVariables(List<Variable> variables) {
		this.variables = variables;
	}

	public boolean isReturnProcessInstanceKey() {
		return returnProcessInstanceKey;
	}

	public void setReturnProcessInstanceKey(boolean returnProcessInstanceKey) {
		this.returnProcessInstanceKey = returnProcessInstanceKey;
	}
	
	
	public String getProcessId() {
		return processId;
	}

	public void setProcessId(String processId) {
		this.processId = processId;
	}

	public String getTaskId() {
		return taskId;
	}

	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}

	@Override
	public String toString() {
		return "Process [processInstanceKey=" + processInstanceKey + ", variables=" + variables
				+ ", returnProcessInstanceKey=" + returnProcessInstanceKey + ", processId=" + processId + ", taskId="
				+ taskId + "]";
	}	

}
