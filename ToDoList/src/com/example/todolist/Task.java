package com.example.todolist;

public class Task {

	private int id;
	private boolean status;
	private String task;
	private String category;

	// private String time;

	public Task(boolean status, String task, String category) {
		super();
		// this.id = id;
		this.status = status;
		this.task = task;
		this.category = category;
		// this.time = time;
	}

	public Task(int id, boolean status, String task, String category) {
		super();
		this.id = id;
		this.status = status;
		this.task = task;
		this.category = category;
		// this.time = time;
	}

	public int getId() {

		return this.id;
	}

	public void setId(int id) {

		this.id = id;
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

	/*
	 * public String getTime() {
	 * 
	 * return this.time; }
	 * 
	 * public void setTime(String time) {
	 * 
	 * this.time = time; }
	 */

}
