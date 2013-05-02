package com.example.todolist;

import java.util.ArrayList;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class DatabaseAdapter {

	private Context context;
	private SQLiteDatabase taskDb;
	private DatabaseHelper helper;

	public DatabaseAdapter(Context context) {
		this.context = context;
		this.helper = new DatabaseHelper(context);
	}

	// Open database
	public void openDb() {
		taskDb = this.helper.getWritableDatabase();
	}

	// Insert into database
	public long insertTask(Task task) {

		ContentValues values = new ContentValues();
		String status = "false";
		values.put(DatabaseHelper.TASK_STATUS, status);
		values.put(DatabaseHelper.TASK_NAME, task.getTask());
		values.put(DatabaseHelper.TASK_CATEGORY, task.getCategory());

		return taskDb.insert(DatabaseHelper.TABLE_NAME, null, values);
	}

	// Insert into database
	public long insertCompleted(Task task) {

		ContentValues values = new ContentValues();
		String status = "true";
		values.put(DatabaseHelper.TASK1_STATUS, status);
		values.put(DatabaseHelper.TASK1_NAME, task.getTask());
		values.put(DatabaseHelper.TASK1_CATEGORY, task.getCategory());

		return taskDb.insert(DatabaseHelper.TABLE1_NAME, null, values);
	}

	public long removeTask(Task task) {
		return taskDb.delete(DatabaseHelper.TABLE_NAME, DatabaseHelper.TASK_ID
				+ "=?", new String[] { String.valueOf(task.getId()) });
	}
	
	public long removeCompleted(Task task) {
		return taskDb.delete(DatabaseHelper.TABLE1_NAME, DatabaseHelper.TASK1_ID
				+ "=?", new String[] { String.valueOf(task.getId()) });
	}

	// Get all the tasks from database
	public ArrayList<Task> getAllTasks() {

		ArrayList<Task> tasks = new ArrayList<Task>();
		String[] columns = { DatabaseHelper.TASK_ID,
				DatabaseHelper.TASK_STATUS, DatabaseHelper.TASK_NAME,
				DatabaseHelper.TASK_CATEGORY };
		Cursor cursor = taskDb.query(DatabaseHelper.TABLE_NAME, columns, null,
				null, null, null, null);
		if (cursor != null && cursor.getCount() > 0) {
			int count = cursor.getCount();
			cursor.moveToFirst();
			for (int i = 0; i < count; i++) {
				int id = cursor.getInt(cursor
						.getColumnIndex(DatabaseHelper.TASK_ID));
				String taskName = cursor.getString(cursor
						.getColumnIndex(DatabaseHelper.TASK_NAME));
				String taskCategory = cursor.getString(cursor
						.getColumnIndex(DatabaseHelper.TASK_CATEGORY));
				String taskStatus = cursor.getString(cursor
						.getColumnIndex(DatabaseHelper.TASK_STATUS));

				boolean status = false;
				if (taskStatus.equals("true")) {
					status = true;
				} else if (taskStatus.equals("false")) {
					status = false;
				}
				/*
				 * boolean status = cursor.getInt(cursor
				 * .getColumnIndex(DatabaseHelper.TASK_STATUS)) > 0
				 */;
				tasks.add(new Task(id, status, taskName, taskCategory));
				cursor.moveToNext();
			}

		}

		cursor.close();
		return tasks;

	}

	// Get all the tasks from database
	public ArrayList<Task> getAllCompleted() {

		ArrayList<Task> completed = new ArrayList<Task>();
		String[] columns = { DatabaseHelper.TASK1_ID,
				DatabaseHelper.TASK1_STATUS, DatabaseHelper.TASK1_NAME,
				DatabaseHelper.TASK1_CATEGORY };
		Cursor cursor = taskDb.query(DatabaseHelper.TABLE1_NAME, columns, null,
				null, null, null, null);
		if (cursor != null && cursor.getCount() > 0) {
			int count = cursor.getCount();
			cursor.moveToFirst();
			for (int i = 0; i < count; i++) {
				int id = cursor.getInt(cursor
						.getColumnIndex(DatabaseHelper.TASK1_ID));
				String taskName = cursor.getString(cursor
						.getColumnIndex(DatabaseHelper.TASK1_NAME));
				String taskCategory = cursor.getString(cursor
						.getColumnIndex(DatabaseHelper.TASK1_CATEGORY));
				String taskStatus = cursor.getString(cursor
						.getColumnIndex(DatabaseHelper.TASK1_STATUS));

				boolean status = false;
				if (taskStatus.equals("true")) {
					status = true;
				} else if (taskStatus.equals("false")) {
					status = false;
				}
				/*
				 * boolean status = cursor.getInt(cursor
				 * .getColumnIndex(DatabaseHelper.TASK_STATUS)) > 0
				 */;
				completed.add(new Task(id, status, taskName, taskCategory));
				cursor.moveToNext();
			}

		}

		cursor.close();
		return completed;

	}

	public void closeDb() {
		this.taskDb.close();
	}
}
