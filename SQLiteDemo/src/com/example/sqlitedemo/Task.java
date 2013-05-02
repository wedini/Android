package com.example.sqlitedemo;

public class Task {

	String task;
	String type;
	String time;
	String completed;
	boolean checked;
	int index;
	
	public Task(String task, String type, String time, String completed) {
		this.task = task;
		this.type = type;
		this.time = time;
		this.completed = completed;
	}
	

	public void setIndex(int index) {
		this.index = index;
	}

	public int getIndex() {
		return index;
	}

	public String getTask() {
		return task;
	}

	public String getType() {
		return type;
	}

	public String getTime() {
		return time;
	}

	public boolean getChecked() {
		return checked;
	}

	public String getCompleted() {
		return completed;
	}
	public void setTask(String task) {
		this.task = task;
	}

	public void setType(String type) {
		this.type = type;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public void setChecked(boolean checked) {
		this.checked = checked;
	}
	
	public void setCompleted(String completed) {
		this.completed = completed;
	}

	
}
