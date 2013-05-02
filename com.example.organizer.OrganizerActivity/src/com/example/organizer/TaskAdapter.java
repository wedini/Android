package com.example.organizer;

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
	private Task data[];
	private Task task;
	private boolean[] checkBoxState;

	public TaskAdapter(Context context, int layoutResourceId, Task[] data) {
		super(context, layoutResourceId, data);
		this.context = context;
		this.layoutResourceId = layoutResourceId;
		this.data = data;
		this.checkBoxState = new boolean[data.length];
	}

	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		View row = convertView;
		taskHolder holder = new taskHolder();

		if (row == null) {

			LayoutInflater inflater = ((Activity) context).getLayoutInflater();

			row = inflater.inflate(R.layout.list_item, parent, false);

			holder.checkBox = (CheckBox) row.findViewById(R.id.checkBox);
			holder.taskView = (TextView) row.findViewById(R.id.task);
			
			holder.checkBox.setOnCheckedChangeListener(new OnCheckedChangeListener() {

				@Override
				public void onCheckedChanged(CompoundButton buttonView,
						boolean isChecked) {
					if(isChecked) {
						Toast.makeText(getContext(), task.getTask()+"is checked.", Toast.LENGTH_LONG).show();
					}
					else {
						Toast.makeText(getContext(), task.getTask()+"not checked.", Toast.LENGTH_LONG).show();
					}
					
				}
				
			});

			row.setTag(holder);

		} else {

			holder = (taskHolder) row.getTag();
		}
		

		task = data[position];
		
		holder.taskView.setText(task.getTask());
		holder.checkBox.setChecked(checkBoxState[position]);
		
		

		/*holder.checkBox.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				// TODO Auto-generated method stub
				holder.checkBox.setChecked(task.getStatus());
				Toast.makeText(getContext(), holder.taskView.getText()+" is checked.", Toast.LENGTH_LONG).show();
				
			}
			
			
		});*/

		return row;
	}

	//Holder for task row views
	private class taskHolder {

		CheckBox checkBox;
		TextView taskView;
	}

}
