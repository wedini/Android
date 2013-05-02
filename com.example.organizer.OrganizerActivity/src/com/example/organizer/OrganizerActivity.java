package com.example.organizer;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Button;
import android.widget.ArrayAdapter;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;
import java.util.ArrayList;
import java.util.List;

public class OrganizerActivity extends Activity {

	// Buttons
	private Button myTasks;
	private Button completed;

	// ListView
	private ListView tasksList;

	// ArrayAdapter for tasks
	private ArrayAdapter<Task> tasksAdapter;
	
	//CheckBox for tasks
	private CheckBox status;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_organizer);

		// Initialize buttons
		myTasks = (Button) findViewById(R.id.myTasks);
		completed = (Button) findViewById(R.id.completed);

		// Initialize listview
		tasksList = (ListView) findViewById(R.id.tasksList);

		//Hard code new tasks
		Task task[] = new Task[] { 
				new Task(false, "Pick up the milk", "Personal"),
				new Task(false, "Return library books", "Personal"),
				new Task(false, "Order stationery", "Work"),
				new Task(false, "Take out the trash", "Personal"),
				new Task(false, "Buy gift for Bob", "Personal"),
				new Task(false, "Submit TPS report", "Work"),
				new Task(false, "Take over the world", "Work") };

		//Task adapter
		tasksAdapter = new TaskAdapter(this, R.layout.list_item,task);

		//Set adapter to listview
		tasksList.setAdapter(tasksAdapter);
		
		tasksList.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parentView, View childView, int pos,
					long id) {
				// TODO Auto-generated method stub
				CheckBox cb = (CheckBox)findViewById(R.id.checkBox);
				cb.setChecked(true);
				
				Toast.makeText(getApplicationContext(), tasksList.getCheckedItemPosition()+"Checked", Toast.LENGTH_SHORT).show();
			}
		});

		//Add listeners to my tasks button
		myTasks.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				
				Intent showTasks = new Intent(v.getContext(), OrganizerActivity.class);
				
				
				
				

			}
		});

		//Add listeners to completed tasks button
		completed.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				
				Intent showCompleted = new Intent(v.getContext(), OrganizerActivity.class);
				
				

			}
		});
		
		
	}
	
	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.activity_organizer, menu);
		return true;
	}
}
