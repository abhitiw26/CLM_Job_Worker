package com.ltim.lc.app.clm.Constant;

public class TaskState {

	public static TaskState CREATED = new TaskState("CREATED");

	public static TaskState COMPLETED = new TaskState("COMPLETED");

	public static TaskState CANCELED = new TaskState("CANCELED");

	public String rawValue;

	public TaskState(String rawValue) {
		this.rawValue = rawValue;
	}

	public String getRawValue() {
		return rawValue;
	}

	public static TaskState safeValueOf(String rawValue) {
		switch (rawValue) {
		case "CREATED":
			return TaskState.CREATED;
		case "COMPLETED":
			return TaskState.COMPLETED;
		case "CANCELED":
			return TaskState.CANCELED;
		default:
			return new TaskState("UNKNOWN");
		}
	}
}
