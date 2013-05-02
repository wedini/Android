package com.example.mytasks;

import java.security.PublicKey;
import java.util.ArrayList;

import android.R.color;
import android.R.integer;
import android.R.xml;
import android.os.Bundle;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ListActivity;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.PorterDuff.Mode;
import android.graphics.drawable.Drawable;
import android.util.SparseBooleanArray;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;


public class MainActivity extends Activity {

	protected static final Activity OnClickListener = null;
	ListView lView;
	int x = 0;
	Button Mytasks, Completed;
	ArrayList<Task>task_items = new ArrayList<Task>();
	ArrayList<Task>completed_items = new ArrayList<Task>();
	TaskAdapter adapter, compAdapter;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		lView = (ListView) findViewById(R.id.listView1);
				
		//task 1
		Task task1 = new Task();
		task1.task = "Pick up the milk";
		task1.type = "Personal";
		task1.time = "8:00 am";
		task1.checked = false;
		task_items.add(task1);
		
		
		
		// task 2
		Task task2 = new Task();
		task2.task = "Return Library Books";
		task2.type = "Personal";
		task2.time = "6:00 pm";
		task2.checked = false;
		task_items.add(task2);	
		
		// task 3
		Task task3 = new Task();
		task3.task = "Order Stationery";
		task3.type = "Work";
		task3.time = "Today";
		task3.checked = false;
		task_items.add(task3);
		
		// task 4
		Task task4 = new Task();
		task4.task = "Take out the trash";
		task4.type = "Personal";
		task4.time = "Today";
		task4.checked = false;
		task_items.add(task4);
		
		// task 5
		Task task5 = new Task();
		task5.task = "Buy gift for Bob";
		task5.type = "Personal";
		task5.time = "Today";
		task5.checked = false;
		task_items.add(task5);
		
		// task 6
		Task task6 = new Task();
		task6.task = "Submit TPS report";
		task6.type = "Work";
		task6.time = "Today";
		task6.checked = false;
		task_items.add(task6);
		
		// task 7
		Task task7 = new Task();
		task7.task = "Take over the world";
		task7.type = "Work";
		task7.time = "Today";
		task7.checked = false;
		task_items.add(task7);
		
				
		adapter = new TaskAdapter(this, task_items);
		lView.setAdapter(adapter);
			
		
		Mytasks = (Button) findViewById(R.id.button1);
		Completed = (Button) findViewById(R.id.button2);
		
		Mytasks.setBackgroundColor(Color.BLUE);		
		Completed.setBackgroundColor(Color.LTGRAY);

		Mytasks.setOnClickListener(new View.OnClickListener() {
			
		
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Mytasks.setBackgroundColor(Color.BLUE);
				Completed.setBackgroundColor(Color.LTGRAY);
//				Mytasks.getBackground().setColorFilter(Color.BLUE,Mode.MULTIPLY);
//				Completed.getBackground().setColorFilter(Color.GRAY, Mode.CLEAR);
				Toast.makeText(getApplicationContext(), "List of Tasks", Toast.LENGTH_LONG).show();
				lView.setAdapter(adapter);
				lView.setChoiceMode(lView.CHOICE_MODE_MULTIPLE);

			}
		});
		
				
		Completed.setOnClickListener(new View.OnClickListener() {
			
		
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Mytasks.setBackgroundColor(Color.LTGRAY);
				Completed.setBackgroundColor(Color.BLUE);
//				Completed.getBackground().setColorFilter(Color.BLUE,Mode.MULTIPLY);
//				Mytasks.getBackground().setColorFilter(Color.GRAY, Mode.CLEAR);
//				Toast.makeText(getApplicationContext(), "Completed Tasks", Toast.LENGTH_LONG).show();
				for(int j=0; j<8; j++){
				for (int i = 0; i < task_items.size(); i++) {
					Task t = task_items.get(i);
					if(t.checked == true){
						
						completed_items.add(t);
						task_items.remove(i);
					}	
				}
				}
									
				compAdapter = new TaskAdapter(MainActivity.this, completed_items);
				lView.setAdapter(compAdapter);
				
				//get positions of checks
				//get object at position from task_item array
				//move object to completed array
				//create new adapter with completed array
				//set lview adapter to new adapter
				
			}	

		});
		
	}
	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

}
