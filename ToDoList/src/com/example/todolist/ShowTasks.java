package com.example.todolist;

import java.util.ArrayList;



import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.widget.ListView;
import android.widget.Toast;

public class ShowTasks extends Activity {

	private DatabaseAdapter dbAdapter;
	private ListView taskListView;
	private TaskAdapter taskAdapter;
	private ArrayList<Task> taskList;
	private ArrayList<Task> completedList;
	private long flag;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_show_tasks);

		// Initialize array list
		taskList = new ArrayList<Task>();

		// Initialize database adapter
		dbAdapter = new DatabaseAdapter(getApplicationContext());

		// Open database adapter
		dbAdapter.openDb();

		// Return array list which contains all the tasks
		taskList = dbAdapter.getAllTasks();

		// Initialize taskAdapter with tasks array list
		taskAdapter = new TaskAdapter(this, R.layout.list_item, taskList);

		// Initialize listview
		taskListView = (ListView) findViewById(R.id.tasksList);

		// Set adapter to listview
		taskListView.setAdapter(taskAdapter);
	}

	// Open database on resume
	@Override
	protected void onResume() {
		super.onResume();
		dbAdapter.openDb();

	}

	// Close database when program is closed
	@Override
	protected void onPause() {
		super.onPause();
		dbAdapter.closeDb();

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.activity_show_tasks, menu);
		return true;
	}
}
