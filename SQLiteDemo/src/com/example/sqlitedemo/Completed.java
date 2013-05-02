package com.example.sqlitedemo;

import java.util.ArrayList;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.widget.ListView;


public class Completed extends Activity {
	
	ListView lv;
    Adapter adapter;
	DBAdapter dbAdapter;
	ArrayList<Task> completedtasks;
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.list);
		dbAdapter = new DBAdapter(getApplicationContext());
		dbAdapter.open();
		completedtasks = dbAdapter.getCompletedTasks();
		dbAdapter.close();
		adapter = new Adapter(Completed.this, completedtasks);
		lv = (ListView) findViewById(R.id.listView1);
		lv.setAdapter(adapter);
				
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}
}
