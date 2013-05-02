package com.example.gpstracker;

import java.util.ArrayList;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class DBAdapter {
	private Context context;
	private SQLiteDatabase GPSTracking;
	private DBHelper helper;
	Location l1 = new Location();
		
	public DBAdapter(Context context){
		
		this.context = context;
		helper = new DBHelper(context);
		
	}
	//open db
	public void open(){
		
		GPSTracking = helper.getWritableDatabase();
				
	}
		
	//queries
	public long inserttask(Location loc){
		
		ContentValues values=new ContentValues();
		values.put(DBHelper.LOCATION_ID, loc.getId());
		values.put(DBHelper.LOCATION_STREET, loc.getStreet());
		values.put(DBHelper.LOCATION_TIME, loc.getTime());
		
		return GPSTracking.insert(DBHelper.TABLE_NAME, null, values);
		
		}
	
public ArrayList<Location> getLocations(){
		
		ArrayList<Location> loclist = new ArrayList<Location>();
		String[] columns={DBHelper.LOCATION_ID, DBHelper.LOCATION_STREET, DBHelper.LOCATION_TIME};	
		Cursor cursor=GPSTracking.query(DBHelper.TABLE_NAME, columns, null, null, null, null, null);
	
		if(cursor!=null && cursor.getCount()>0)
		{
			int count=cursor.getCount();
			cursor.moveToFirst();
			for(int i=0; i<count;i++)
			{
				int id=Integer.parseInt(cursor.getString(cursor.getColumnIndex(DBHelper.LOCATION_ID)));
				String street=cursor.getString(cursor.getColumnIndex(DBHelper.LOCATION_STREET));
				String time=cursor.getString(cursor.getColumnIndex(DBHelper.LOCATION_TIME));
				l1.id=id;
				l1.street = street;
				l1.time = time;
				loclist.add(l1);					

				cursor.moveToNext();
				
			}
			
		}
		cursor.close();
		return loclist;
	}
	
	public void close(){
		GPSTracking.close();
	}
	
}
