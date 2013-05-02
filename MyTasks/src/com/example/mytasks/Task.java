package com.example.mytasks;

import android.R.integer;

public class Task {
	String task;
	String type;
	String time;
	boolean checked;
	int index;

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

}
