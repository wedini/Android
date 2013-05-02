package com.example.sqlitedemo;

import java.util.Calendar;


import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

public class activity_addtask extends Activity {

	EditText Task, Description, date;
	Button AddtoTaskList;
	ImageButton pickerButton;
	private DBAdapter dbAdapter;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.addtask);
		dbAdapter = new DBAdapter(getApplicationContext());

		Task = (EditText) findViewById(R.id.TaskeditText1);
		Description = (EditText) findViewById(R.id.DeseditText2);
		date = (EditText) findViewById(R.id.DateeditText1);
		
		pickerButton = (ImageButton) findViewById(R.id.pickerButton);
		pickerButton.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				DialogFragment newFragment = new activity_addtask.DatePickerFragment();
				newFragment.show(getFragmentManager(), "datePicker");
				
				}

		});

		AddtoTaskList = (Button) findViewById(R.id.button1);
		AddtoTaskList.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				String title, des, time;
				title = Task.getText().toString();
				des = Description.getText().toString();
				time = date.getText().toString();
			    String completed = "false";
				
				if(title.equals("") || des.equals("") || time.equals(""))
				{
					Toast.makeText(getApplicationContext(), "Please input all the fields!!!", Toast.LENGTH_LONG).show();
				}
				else
				{
					dbAdapter.open();
					Task t=new Task(title, des, time, completed);
					long inserted=dbAdapter.inserttask(t);
					if(inserted > -1)
					{
						Toast.makeText(getApplicationContext(), "Task inserted successfully!", Toast.LENGTH_LONG).show();
					}
					dbAdapter.close();
					
				}
			}
		});
		
	}

	public class DatePickerFragment extends DialogFragment implements
			DatePickerDialog.OnDateSetListener {

		
		@Override
		public Dialog onCreateDialog(Bundle savedInstanceState) {
			// Use the current date as the default date in the picker
			final Calendar c = Calendar.getInstance();
			int year = c.get(Calendar.YEAR);
			int month = c.get(Calendar.MONTH);
			int day = c.get(Calendar.DAY_OF_MONTH);

			// Create a new instance of DatePickerDialog and return it
			return new DatePickerDialog(getActivity(), this, year, month, day);
		}

		public void onDateSet(DatePicker view, int year, int month, int day) {
			// Do something with the date chosen by the user
//			Toast.makeText(getActivity(),String.valueOf(month + 1) + "/" + String.valueOf(day)+ "/" + String.valueOf(year), Toast.LENGTH_LONG).show();
			date.setText(String.valueOf(month + 1) + "/" + String.valueOf(day)
					+ "/" + String.valueOf(year));
		}

	}
}
