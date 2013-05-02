//Activity to add a new task and insert it into the database

package com.example.todolist;



import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddTask extends Activity {

	private Task task;
	private EditText taskField;
	private EditText descField;
	private Button addTaskBtn;
	private DatabaseAdapter dbAdapter;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add_task);

		taskField = (EditText) findViewById(R.id.taskField);
		descField = (EditText) findViewById(R.id.descField);

		addTaskBtn = (Button) findViewById(R.id.addTaskBtn);

		// Initialize database adapter
		dbAdapter = new DatabaseAdapter(getApplicationContext());

		// Add on click listener to button
		addTaskBtn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				// open database
				dbAdapter.openDb();

				String taskName = taskField.getText().toString();
				String desc = descField.getText().toString();
				boolean taskStatus = false;

				// create new task based on textfields
				task = new Task(taskStatus, taskName, desc);

				// Insert new task into database
				long status = dbAdapter.insertTask(task);

				if (status > -1) {
					Toast.makeText(getApplicationContext(),
							"Insert task successful", Toast.LENGTH_LONG).show();
					Toast.makeText(getApplicationContext(),
							"Task is " + task.getTask(), Toast.LENGTH_LONG)
							.show();
					Toast.makeText(getApplicationContext(),
							"Category is " + task.getCategory(),
							Toast.LENGTH_LONG).show();
				} else {
					Toast.makeText(getApplicationContext(),
							"Insert task failed", Toast.LENGTH_LONG).show();
				}
			}

		});

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
		getMenuInflater().inflate(R.menu.activity_add_task, menu);
		return true;
	}
}
