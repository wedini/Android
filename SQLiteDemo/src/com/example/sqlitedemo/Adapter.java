package com.example.sqlitedemo;

import java.util.ArrayList;
import java.util.Iterator;

import android.R.integer;
import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class Adapter extends ArrayAdapter<Task>{

	Activity mContext;
	private DBAdapter dbAdapter1;
	DBHepler dbhelper;
	ArrayList<Task> listItems, delListItems;
	ViewHolder holder;
			
	public Adapter(Activity context, ArrayList<Task> taskList) {
		super(context, R.layout.tasks, taskList);
		mContext = context;
		listItems = taskList;
	}
	static class ViewHolder {
		public TextView taskView;
		public TextView typeView;
		public TextView timeView;
		public CheckBox checkedBox;
	}

	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {

		
		View v = convertView;
		if (v == null) {
			LayoutInflater inflator = mContext.getLayoutInflater();
			v = inflator.inflate(R.layout.tasks, null, false);
			 
			holder = new ViewHolder();

			holder.taskView = (TextView) v.findViewById(R.id.textView1);
			holder.typeView = (TextView) v.findViewById(R.id.textView2);
			holder.timeView = (TextView) v.findViewById(R.id.textView3);

			holder.taskView.setText(listItems.get(position).getTask());
			holder.typeView.setText(listItems.get(position).getType());
			holder.timeView.setText(listItems.get(position).getTime());
			v.setTag(holder);
			
	
            dbAdapter1 = new DBAdapter(mContext);
			holder.checkedBox = (CheckBox) v.findViewById(R.id.checkBox1);
			dbhelper = new DBHepler(mContext);
			listItems.get(position).setIndex(position);
			holder.checkedBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
				
				public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
					// TODO Auto-generated method stub
					//Toast.makeText(getContext(), "Checked " + isChecked + "Position " + position, Toast.LENGTH_LONG).show();
					if(isChecked){
						listItems.get(position).setChecked(true);
						if(listItems.get(position).getCompleted() == "false"){
							listItems.get(position).setCompleted("true");
							dbAdapter1.open();
							dbhelper.update(new Task(listItems.get(position).getTask(), listItems.get(position).getType(), listItems.get(position).getTime(), "true"));						
							dbAdapter1.close();
							   }
							else{
							listItems.get(position).setCompleted("false");
							dbAdapter1.open();
							dbhelper.update(new Task(listItems.get(position).getTask(), listItems.get(position).getType(), listItems.get(position).getTime(), "false"));						
						    dbAdapter1.close();
								}
					}else {
						listItems.get(position).setChecked(false);
						listItems.get(position).setCompleted("false");
												
					}
				
				}
			});

		} 
		
		else {
			holder = (ViewHolder) convertView.getTag();
				}
		return v;
}

}
