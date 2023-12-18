package com.ltim.lc.app.clm.model;

import java.io.Serializable;

public class ClaimTask extends BaseClientRequest implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private String assignee;
	private String taskId;
	private boolean allowOverrideAssignment;

	public String getAssignee() {
		return assignee;
	}

	public void setAssignee(String assignee) {
		this.assignee = assignee;
	}

	public boolean isAllowOverrideAssignment() {
		return allowOverrideAssignment;
	}

	public void setAllowOverrideAssignment(boolean allowOverrideAssignment) {
		this.allowOverrideAssignment = allowOverrideAssignment;
	}	
	
	public String getTaskId() {
		return taskId;
	}

	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}

	@Override
	public String toString() {
		return "ClaimTask [assignee=" + assignee + ", taskId=" + taskId + ", allowOverrideAssignment="
				+ allowOverrideAssignment + "]";
	}
	
	
}
