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
	private ListView taskListView;

	// ArrayAdapter for tasks
	private ArrayAdapter<Task> tasksAdapter;
	private ArrayAdapter<Task> completedAdapter;
	
	//Task arraylist
	private ArrayList<Task> taskList;
	private ArrayList<Task> completedList;
	
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
		taskListView = (ListView) findViewById(R.id.tasksList);
		
		//Initialize arraylists
		taskList = new ArrayList<Task>();
		completedList = new ArrayList<Task>();
		
		//Hard code new tasks
		taskList.add(new Task(false, "Pick up the milk", "Personal"));
		taskList.add(new Task(false, "Return library books", "Personal"));
		taskList.add(new Task(false, "Order stationery", "Work"));
		taskList.add(new Task(false, "Take out the trash", "Personal"));
		taskList.add(new Task(false, "Buy gift for Bob", "Personal"));
		taskList.add(new Task(false, "Submit TPS report", "Work"));
		taskList.add(new Task(false, "Take over the world", "Work"));
		

		//Task adapter
		tasksAdapter = new TaskAdapter(this, R.layout.list_item, taskList);
		completedAdapter = new TaskAdapter(this, R.layout.list_item, completedList);
		//completedAdapter = new TaskAdapter(this, R.layout.list_item, taskList, completedList);

		//Set adapter to custom listview
		taskListView.setAdapter(tasksAdapter);
		
		
		//Add listeners to my tasks button
		myTasks.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				
				for(int i = 0; i < taskList.size(); i++) {
					if(taskList.get(i).getStatus()==true) {
						Task task = taskList.get(i);
						completedList.add(task);
						taskList.remove(i);
					}	
				}
				//Toast.makeText(getApplicationContext(), "Task list size is:" +taskList.size(), Toast.LENGTH_LONG).show();
				
				TaskAdapter tasksAdapter = new TaskAdapter(getApplicationContext(), R.layout.list_item, taskList);
				
				taskListView.setAdapter(tasksAdapter);
				
				
				
			}
		});

		//Add listeners to completed tasks button
		completed.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				
				for(int i = 0; i < completedList.size(); i++) {
					if(completedList.get(i).getStatus()==false) {
						Task task = completedList.get(i);
						taskList.add(task);
						completedList.remove(i);
					}	
				}
				
				completedAdapter = new TaskAdapter(getApplicationContext(), R.layout.list_item, completedList);
				
				taskListView.setAdapter(completedAdapter);

			}
		});
		
		
	}
	

	
	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.activity_organizer, menu);
		return true;
	}
}
