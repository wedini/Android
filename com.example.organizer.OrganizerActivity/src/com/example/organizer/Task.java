package com.example.organizer;

public class Task {

	private boolean status;
	private String task;
	private String category;
	//private String time;

	public Task(boolean status, String task, String category) {

		this.status = status;
		this.task = task;
		this.category = category;
		//this.time = time;
	}
	
	public boolean getStatus() {

		return this.status;
	}
	
	public void setStatus(boolean status) {

		this.status = status;
	}
	
	public String getCategory() {

		return this.category;
	}

	public void setCategory(String category) {

		this.category = category;
	}
	
	public String getTask() {

		return this.task;
	}
	
	public void setTask(String task) {

		this.task = task;
	}

}
