package com.example.todolist;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;
import android.util.Log;

public class DatabaseHelper extends SQLiteOpenHelper {

	public static final String DB_NAME = "taskDb";

	// Task table
	public static final String TABLE_NAME = "tasks";
	public static final String TASK_ID = "_id";
	public static final String TASK_STATUS = "taskStatus";
	public static final String TASK_NAME = "taskName";
	public static final String TASK_CATEGORY = "taskCategory";

	// Completed table
	public static final String TABLE1_NAME = "completed";
	public static final String TASK1_ID = "_id";
	public static final String TASK1_STATUS = "taskStatus";
	public static final String TASK1_NAME = "taskName";
	public static final String TASK1_CATEGORY = "taskCategory";

	private static String CREATE_TABLE_SQL = "Create table "
			+ DatabaseHelper.TABLE_NAME + " (" + DatabaseHelper.TASK_ID
			+ " INTEGER PRIMARY KEY AUTOINCREMENT, "
			+ DatabaseHelper.TASK_STATUS + " TEXT, " + DatabaseHelper.TASK_NAME
			+ " TEXT, " + DatabaseHelper.TASK_CATEGORY + " TEXT);";
	
	private static String CREATE_TABLE1_SQL = "Create table "
			+ DatabaseHelper.TABLE1_NAME + " (" + DatabaseHelper.TASK1_ID
			+ " INTEGER PRIMARY KEY AUTOINCREMENT, "
			+ DatabaseHelper.TASK1_STATUS + " TEXT, " + DatabaseHelper.TASK1_NAME
			+ " TEXT, " + DatabaseHelper.TASK1_CATEGORY + " TEXT);";

	public DatabaseHelper(Context context) {
		super(context, DatabaseHelper.DB_NAME, null, 1);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {

		db.execSQL(DatabaseHelper.CREATE_TABLE_SQL);
		db.execSQL(DatabaseHelper.CREATE_TABLE1_SQL);
		Log.d("DBHelper", "Table Created: " + DatabaseHelper.CREATE_TABLE_SQL);
		Log.d("DBHelper", "Table Created: " + DatabaseHelper.CREATE_TABLE1_SQL);


	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

	}

}
