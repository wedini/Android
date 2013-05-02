package com.example.mytasks;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;

public class TaskAdapter extends ArrayAdapter<Task> {
	Activity mContext;
	ArrayList<Task> listItems, checkedlistItems;
	Task t = new Task();

	public TaskAdapter(Activity context, ArrayList<Task> taskList) {
		super(context, R.layout.taskcell, taskList);
		mContext = context;
		listItems = taskList;

	}

	/*
	 * public TaskAdapter(android.view.View.OnClickListener onClickListener,
	 * ArrayList<Task> checked_items) { super((Context) onClickListener,
	 * R.layout.taskcell, checked_items); // TODO Auto-generated constructor
	 * stub mContext = (Activity) onClickListener; listItems = checked_items; }
	 */
	static class ViewHolder {
		public TextView taskView;
		public TextView typeView;
		public TextView timeView;
		public CheckBox checkedBox;
	}

	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {

		ViewHolder holder;
		View v = convertView;
		if (v == null) {
			LayoutInflater inflator = mContext.getLayoutInflater();
			v = inflator.inflate(R.layout.taskcell, null, false);

			holder = new ViewHolder();

			holder.taskView = (TextView) v.findViewById(R.id.textView1);
			holder.typeView = (TextView) v.findViewById(R.id.textView2);
			holder.timeView = (TextView) v.findViewById(R.id.textView3);

			holder.taskView.setText(listItems.get(position).getTask());
			holder.typeView.setText(listItems.get(position).getType());
			holder.timeView.setText(listItems.get(position).getTime());
			v.setTag(holder);

			holder.checkedBox = (CheckBox) v.findViewById(R.id.checkBox1);
			listItems.get(position).setIndex(position);
			holder.checkedBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
				
				public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
					// TODO Auto-generated method stub
					Toast.makeText(getContext(), "Checked " + isChecked + "Position " + position, Toast.LENGTH_LONG).show();
					if(isChecked){
						listItems.get(position).setChecked(true);
					}else{
						listItems.get(position).setChecked(false);
					}
				
				}
			});

		} else {
			holder = (ViewHolder) convertView.getTag();

			// lvCheckBox.setAdapter(new ArrayAdapter<String>(this,
			// android.R.layout.simple_list_item_multiple_choice, arr));
			/*
			 * if(holder.checkedBox.requestFocus() == true){
			 * Toast.makeText(getContext(), "the..", Toast.LENGTH_LONG).show();
			 * }
			 */
			/*
			 * if(holder.checkedBox.isChecked()){ Toast.makeText(getContext(),
			 * "the..", Toast.LENGTH_LONG).show();
			 * 
			 * }
			 */
		}
		/*
		 * holder.checkedBox.setOnClickListener( new View.OnClickListener() {
		 * public void onClick(View v1) { CheckBox cb = (CheckBox) v1 ; Task t =
		 * (Task) cb.getTag(); Toast.makeText(getContext(),
		 * "Clicked on Checkbox: " + cb.isChecked(), Toast.LENGTH_LONG).show();
		 * t.setChecked((cb.isChecked())); } });
		 */

		/*
		 * } else { v = convertView; }
		 */
		/*
		 * Task country = countryList.get(position); holder.code.setText(" (" +
		 * country.getCode() + ")"); holder.name.setText(country.getName());
		 * holder.name.setChecked(country.isSelected());
		 * holder.name.setTag(country);
		 */
		return v;
	}
}
