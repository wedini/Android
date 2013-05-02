package com.example.gpstracker;

import java.util.ArrayList;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class LocationAdapter extends ArrayAdapter<Location> {

	Activity mContext;
	ArrayList<Location> locItems;
	ViewHolder holder;
	
	public LocationAdapter(Activity context, ArrayList<Location> locationList) {
		super(context, R.layout.location, locationList);
		mContext = context;
		locItems = locationList;
	}
	
	static class ViewHolder{
		public TextView StreetView;
		public TextView TimeView;
	}
	
	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {

		
		View v = convertView;
		if (v == null) {
			LayoutInflater inflator = mContext.getLayoutInflater();
			v = inflator.inflate(R.layout.location, null, false);
			 
			holder = new ViewHolder();

			holder.StreetView = (TextView) v.findViewById(R.id.StreettextView1);
			holder.TimeView = (TextView) v.findViewById(R.id.TimetextView2);
			
			holder.StreetView.setText(locItems.get(position).getStreet());
			holder.TimeView.setText(locItems.get(position).getTime());
			v.setTag(holder);
	
		}
		return v;
	}//end of getView
}
