package com.ltim.lc.app.clm.model;

public class TaskRequest extends BaseClientRequest  {
	
	private TaskSearch taskSearch;

	public TaskSearch getTaskSearch() {
		return taskSearch;
	}

	public void setTaskSearch(TaskSearch taskSearch) {
		this.taskSearch = taskSearch;
	}

	@Override
	public String toString() {
		return "TaskRequest [taskSearch=" + taskSearch + "]";
	}
	
	
}
