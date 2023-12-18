package com.ltim.lc.app.clm.model;

import java.util.List;

public class TaskResponse extends BaseClientResponse {
	
	private List<Task> taskList;

	public List<Task> getTaskList() {
		return taskList;
	}

	public void setTaskList(List<Task> taskList) {
		this.taskList = taskList;
	}

	@Override
	public String toString() {
		return "TaskResponse [taskList=" + taskList + "]";
	}	
	
}
