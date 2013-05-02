package com.example.todolist;

import java.util.ArrayList;



import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.widget.ListView;

public class ShowCompleted extends Activity {

	private ListView completedListView;
	private TaskAdapter taskAdapter;
	private TaskAdapter completedAdapter;
	private ArrayList<Task> completedList;
	private DatabaseAdapter dbAdapter;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_show_completed);

		// Initialize array list
		completedList = new ArrayList<Task>();

		// Initialize database adapter
		dbAdapter = new DatabaseAdapter(getApplicationContext());

		// Open database adapter
		dbAdapter.openDb();

		// Return array list which contains all the tasks
		completedList = dbAdapter.getAllCompleted();

		// Initialize taskAdapter with tasks array list
		taskAdapter = new TaskAdapter(this, R.layout.list_item, completedList);

		// Initialize listview
		completedListView = (ListView) findViewById(R.id.completedList);

		// Set adapter to listview
		completedListView.setAdapter(taskAdapter);
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
		getMenuInflater().inflate(R.menu.activity_show_completed, menu);
		return true;
	}
}
