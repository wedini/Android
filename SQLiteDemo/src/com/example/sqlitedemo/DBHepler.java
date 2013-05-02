package com.example.sqlitedemo;

import java.util.ArrayList;

import android.R.integer;
import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

public class DBHepler extends SQLiteOpenHelper {

	public static final String DB_Name = "ListofTasks";
	public static final String TABLE_NAME = "Tasks";
	public static String TASK_TITLE = "title";
	public static String TASK_DESCRIPTION = "description";
	public static String TASK_TIME = "time";
	public static String TASK_COMPLETED = "completed";

	private static String CREATE_TABLE_SQL = "Create table "
			+ DBHepler.TABLE_NAME + " (" + DBHepler.TASK_TITLE
			+ " TEXT, " + DBHepler.TASK_DESCRIPTION
			+ " TEXT, " + DBHepler.TASK_TIME
			+ " TEXT, "+ DBHepler.TASK_COMPLETED + " TEXT);";
	 
    
	public DBHepler(Context context) {
		super(context, DBHepler.DB_Name, null, 1);

	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// create new db & tables here
		
		db.execSQL(DBHepler.CREATE_TABLE_SQL);
		Log.d("DBHelper", "Table Created: "+ CREATE_TABLE_SQL);

	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		//logic for db version

	}
	public void update(Task t) {
	    SQLiteDatabase db = this.getWritableDatabase();
		String UPDATE_TABLE_SQL = "Update " + DBHepler.TABLE_NAME + " set " + DBHepler.TASK_COMPLETED + " = "+"'"+t.getCompleted()+ "' where "+ DBHepler.TASK_TITLE + " = '"+ t.getTask()+"';";
	    db.execSQL(UPDATE_TABLE_SQL);
	   
	}

    }
