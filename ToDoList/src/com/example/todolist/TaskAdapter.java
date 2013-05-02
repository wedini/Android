package com.example.todolist;

import java.util.List;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.widget.ArrayAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.view.LayoutInflater;
import android.widget.BaseAdapter;
import android.widget.CompoundButton;
import android.widget.Toast;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.TextView;
import android.widget.CheckBox;
import java.util.ArrayList;



public class TaskAdapter extends ArrayAdapter<Task> {

	private int layoutResourceId;
	private Context context;
	private ArrayList<Task> taskList;

	// Test new constructor that does not have taskList parameter

	public TaskAdapter(Context context, int layoutResourceId,
			ArrayList<Task> taskList) {

		super(context, layoutResourceId, taskList);
		this.context = context;
		this.layoutResourceId = layoutResourceId;
		this.taskList = taskList;
	}

	// Generate views for list item
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		// Row view for each list item
		View row = convertView;

		viewHolder holder;

		// If row view is empty
		if (row == null) {

			// Inflate empty row view and set and views
			LayoutInflater inflater = (LayoutInflater) context
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			row = inflater.inflate(R.layout.list_item, null);

			holder = new viewHolder();

			holder.checkBox = (CheckBox) row.findViewById(R.id.checkBox);
			holder.taskView = (TextView) row.findViewById(R.id.task);
			holder.categoryView = (TextView) row.findViewById(R.id.category);

			row.setTag(holder);

		} else {

			holder = (viewHolder) row.getTag();
		}

		final Task task = taskList.get(position);

		if (task != null) {
			
			if(task.getStatus()==true) {
				holder.checkBox.setChecked(true);
			}

			holder.taskView.setText(task.getTask());
			holder.categoryView.setText(task.getCategory());
			holder.checkBox
					.setOnCheckedChangeListener(new OnCheckedChangeListener() {

						@Override
						public void onCheckedChanged(CompoundButton buttonView,
								boolean isChecked) {
							if (isChecked) {
								task.setStatus(true);
								deleteTask(task);
								insertCompleted(task);
								Toast.makeText(getContext(),
										task.getTask() + " is checked",
										Toast.LENGTH_LONG).show();
							} else {
								deleteCompleted(task);
								insertTask(task);
								task.setStatus(false);
								Toast.makeText(getContext(),
										task.getTask() + " is un-checked",
										Toast.LENGTH_LONG).show();
							}

						}

					});

		}
		return row;
	}

	public void deleteCompleted(Task task) {
		DatabaseAdapter dbAdapter = new DatabaseAdapter(getContext());
		dbAdapter.openDb();
		dbAdapter.removeCompleted(task);
		dbAdapter.closeDb();
	}

	public void insertTask(Task task) {
		DatabaseAdapter dbAdapter = new DatabaseAdapter(getContext());
		dbAdapter.openDb();
		dbAdapter.insertTask(task);
		dbAdapter.closeDb();
	}

	
	public void deleteTask(Task task) {
		DatabaseAdapter dbAdapter = new DatabaseAdapter(getContext());
		dbAdapter.openDb();
		dbAdapter.removeTask(task);
		dbAdapter.closeDb();
	}

	public void insertCompleted(Task task) {
		DatabaseAdapter dbAdapter = new DatabaseAdapter(getContext());
		dbAdapter.openDb();
		dbAdapter.insertCompleted(task);
		dbAdapter.closeDb();
	}

	// Holder for task row views
	public static class viewHolder {

		CheckBox checkBox;
		TextView taskView;
		TextView categoryView;
	}

}
