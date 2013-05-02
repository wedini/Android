package com.example.gpstracker;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DBHelper extends SQLiteOpenHelper {

	public static final String DB_Name = "GPSTracking";
	public static final String TABLE_NAME = "Locations";
	public static final String LOCATION_ID = "id";
	public static String LOCATION_STREET = "street";
	public static String LOCATION_TIME = "time";
	

	private static String CREATE_TABLE_SQL = "Create table "
			+ DBHelper.TABLE_NAME + " (" + DBHelper.LOCATION_ID
			+ " INTEGER, " + DBHelper.LOCATION_STREET
			+ " TEXT, " + DBHelper.LOCATION_TIME
			+ " TEXT);";
	
		
	public DBHelper(Context context) {
		super(context, DBHelper.DB_Name, null, 1);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		db.execSQL(DBHelper.CREATE_TABLE_SQL);
		Log.d("DBHelper", "Table Created: "+ CREATE_TABLE_SQL);
	}

	@Override
	public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) {
		// TODO Auto-generated method stub

	}

}
