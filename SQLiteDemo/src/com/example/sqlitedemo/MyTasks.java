package com.example.sqlitedemo;


import java.util.ArrayList;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.widget.ListView;
import android.widget.Toast;


public class MyTasks extends Activity {
	ListView lv;
	Adapter adapter;
	ArrayList<Task> tasks;
	DBAdapter dbAdapter;
	DBHepler dbhelper;
	Task t;
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.list);
	
		dbAdapter = new DBAdapter(getApplicationContext());
		dbhelper = new DBHepler(getApplicationContext());
		dbAdapter.open();
		tasks = dbAdapter.getTasks();
		dbAdapter.close();
		adapter = new Adapter(MyTasks.this, tasks);
		lv = (ListView) findViewById(R.id.listView1);
		lv.setAdapter(adapter);
		lv.setChoiceMode(lv.CHOICE_MODE_MULTIPLE);
			
			}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

	}
