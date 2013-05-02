package com.example.todolist;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainMenu extends Activity {

	private Intent openMyTasks;
	private Intent addNewTask;
	private Intent showCompleted;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main_menu);

		// Initialize buttons
		Button showTasksButton = (Button) findViewById(R.id.myTasksButton);
		Button addNewTaskButton = (Button) findViewById(R.id.addNewTaskButton);
		Button completedButton = (Button) findViewById(R.id.completedButton);

		// Initialize intents
		openMyTasks = new Intent(this, ShowTasks.class);
		addNewTask = new Intent(this, AddTask.class);
		showCompleted = new Intent(this, ShowCompleted.class);

		// Add listeners to buttons
		showTasksButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				startActivity(openMyTasks);
			}

		});

		addNewTaskButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				startActivity(addNewTask);
			}

		});

		completedButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				startActivity(showCompleted);
			}

		});

	}

	/*
	 * public void showTasks(View view) { Intent showTasks = new Intent(this,
	 * MyTasks.class); startActivity(showTasks); }
	 * 
	 * public void addNewTask(View view) { Intent addNewTask = new Intent(this,
	 * AddNewTask.class); startActivity(addNewTask); }
	 * 
	 * public void showCompleted(View view) { Intent showCompleted = new
	 * Intent(this, ShowCompleted.class); startActivity(showCompleted); }
	 */

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.activity_main_menu, menu);
		return true;
	}
}
