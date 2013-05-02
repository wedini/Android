package com.example.sqlitedemo;

import java.util.ArrayList;

import android.R.integer;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;


public class DBAdapter {

	private Context context;
	private SQLiteDatabase ListofTasks;
	private DBHepler helper;
		
	public DBAdapter(Context context){
		
		this.context = context;
		helper = new DBHepler(context);
		
	}
	//open db
	public void open(){
		
		ListofTasks = helper.getWritableDatabase();
				
	}
		
	//queries
	public long inserttask(Task task){
		
		ContentValues values=new ContentValues();
		values.put(DBHepler.TASK_TITLE, task.getTask());
		values.put(DBHepler.TASK_DESCRIPTION, task.getType());
		values.put(DBHepler.TASK_TIME, task.getTime());
		values.put(DBHepler.TASK_COMPLETED, "false");
		return ListofTasks.insert(DBHepler.TABLE_NAME, null, values);
		
		}
	
	public ArrayList<Task> getTasks(){
		
		ArrayList<Task> list1 = new ArrayList<Task>();
		String[] columns={DBHepler.TASK_TITLE, DBHepler.TASK_DESCRIPTION, DBHepler.TASK_TIME, DBHepler.TASK_COMPLETED};	
		Cursor cursor=ListofTasks.query(DBHepler.TABLE_NAME, columns, null, null, null, null, null);
	
		if(cursor!=null && cursor.getCount()>0)
		{
			int count=cursor.getCount();
			cursor.moveToFirst();
			for(int i=0; i<count;i++)
			{
				String title=cursor.getString(cursor.getColumnIndex(DBHepler.TASK_TITLE));
				String des=cursor.getString(cursor.getColumnIndex(DBHepler.TASK_DESCRIPTION));
				String time=cursor.getString(cursor.getColumnIndex(DBHepler.TASK_TIME));
				String completed=cursor.getString(cursor.getColumnIndex(DBHepler.TASK_COMPLETED));
				//list1.add(new Task(title, des, time, completed));
				if (completed.equals("false")) {
					list1.add(new Task(title, des, time, completed));
					}
				

				cursor.moveToNext();
				
			}
			
		}
		cursor.close();
		return list1;
	}
	
	public ArrayList<Task> getCompletedTasks(){
		
		ArrayList<Task> list = new ArrayList<Task>();
		int i;
		String[] columns={DBHepler.TASK_TITLE, DBHepler.TASK_DESCRIPTION, DBHepler.TASK_TIME, DBHepler.TASK_COMPLETED};	
		Cursor cursor=ListofTasks.query(DBHepler.TABLE_NAME, columns, null, null, null, null, null);
		if(cursor!=null && cursor.getCount()>0)
		{
			int count=cursor.getCount();
			cursor.moveToFirst();
			for(i=0; i<count;i++)
			{
				String title=cursor.getString(cursor.getColumnIndex(DBHepler.TASK_TITLE));
				String des=cursor.getString(cursor.getColumnIndex(DBHepler.TASK_DESCRIPTION));
				String time=cursor.getString(cursor.getColumnIndex(DBHepler.TASK_TIME));
				String completed = cursor.getString(cursor.getColumnIndex(DBHepler.TASK_COMPLETED));
				if (completed.equals("true")) {
					list.add(new Task(title, des, time, completed));
					}
				
				cursor.moveToNext();
				
				}
			
		}
		
		cursor.close();
		return list;
	}
	
	public int removetask(Task t){
		
		
		int x=1;
		
		//return ListofTasks.delete(DBHepler.TABLE_NAME, DBHepler.TASK_COMPLETED + "=?", new String[] {String.valueOf(t.getCompleted())});
		return x;
	}
	
	
	//close db
	public void close(){
		ListofTasks.close();
	}
}
