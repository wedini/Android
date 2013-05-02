package com.example.gpssms;

import java.util.Timer;
import java.util.TimerTask;

import android.R.integer;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.IBinder;
import android.telephony.SmsManager;
import android.util.Log;
import android.widget.Toast;

public class LocationService extends Service implements LocationListener{
	
	LocationManager locationManager;
	String provider;
	Timer timer= new Timer();
	
	@Override	
public void onCreate(){
		Log.d("oncreate", "Inside oncreate");
		Toast.makeText(getApplicationContext(), "Oncreate" , Toast.LENGTH_LONG).show();
		locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
		provider = LocationManager.GPS_PROVIDER;
		
		locationManager.requestLocationUpdates(provider, 1000, 0.0f, this);
		//Location currentLocation = locationManager.getLastKnownLocation(provider);
				
		Criteria criteria = new Criteria(); //Criteria is a class indicating the application criteria for selecting a location provider. 
		//Providers maybe ordered according to accuracy, power usage, speed, etc.
		criteria.setAccuracy(Criteria.ACCURACY_FINE);
		criteria.setPowerRequirement(Criteria.ACCURACY_LOW);
		String bestprovider = locationManager.getBestProvider(criteria, true);
		locationManager.requestLocationUpdates(bestprovider, 1000, 10.0f, this);
		
}

	
	@Override
	public int onStartCommand(Intent intent, int flags, int startid) {  
		
				
		Toast.makeText(getApplicationContext(), "Onstartcmd" , Toast.LENGTH_LONG).show();

		timer.schedule(new track(), 10);
				
		return START_STICKY;
		
	}
	
	public class track extends TimerTask{

		@Override
		public void run() {
			// TODO Auto-generated method stub
			Log.d("Track", "track run");
			double latitude = locationManager.getLastKnownLocation(provider).getLatitude();
			double longitude = locationManager.getLastKnownLocation(provider).getLongitude();
			Log.d("Track", "lat " + latitude);
			Log.d("Track", "long " + longitude);

			String msg = latitude +" : " + longitude;
			SmsManager smsM = SmsManager.getDefault();
			PendingIntent sentPI = PendingIntent.getBroadcast(getApplicationContext(), 0,new Intent("SMS_SENT"), 0);
			smsM.sendTextMessage(String.valueOf("5554"), null, msg, sentPI, null);
		}

	}
	
	@Override
	public void onDestroy() {   

	    if (timer != null){
	        timer.cancel();
	    } 
	}
	
	@Override
	public void onLocationChanged(Location location) {
		// TODO Auto-generated method stub
		locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1000, 10.0f, this);
	}
	
	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public void onProviderDisabled(String provider) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onProviderEnabled(String provider) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onStatusChanged(String provider, int status, Bundle extras) {
		// TODO Auto-generated method stub
		
	}

}
