package com.example.gpssms;

import java.util.Timer;

import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.IBinder;
import android.R.anim;
import android.R.integer;
import android.R.xml;
import android.app.Activity;
import android.app.PendingIntent;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.telephony.SmsMessage;
import android.telephony.SmsManager;
import android.view.Menu;
import android.widget.Toast;

public class MainActivity extends Activity {
	
	BroadcastReceiver receiver;
	Service service;
 
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
				
		receiver = new BroadcastReceiver(){

			@Override
			public void onReceive(Context context, Intent intent) {
				// TODO Auto-generated method stub
				
				Intent serviceIntent = new Intent(MainActivity.this, LocationService.class);
				
				Bundle bundle = intent.getExtras();
				SmsMessage[] chunks = null;
				String text = "";
				String address = "";
				if (bundle != null) {
					Object[] pdus = (Object[]) bundle.get("pdus");
					chunks = new SmsMessage[pdus.length];
					for (int i = 0; i < pdus.length; i++) {
						chunks[i] = SmsMessage.createFromPdu((byte[]) pdus[i]);
						text += chunks[i].getMessageBody().toString();
						address += chunks[i].getOriginatingAddress();
					       }
				
				}// if bundle
				if (text.contains("TRACK <1234> START")) {
					
					Toast.makeText(context,
							"Received msg: " + text,
							Toast.LENGTH_LONG).show();
					
					startService(serviceIntent);
					
				}
				else if (text.contains("TRACK <1234> STOP")){
					Toast.makeText(context,
							"Received msg: " + text,
							Toast.LENGTH_LONG).show();
					//unregisterReceiver(receiver);
					stopService(serviceIntent);
				}
				
			}
			
		}; //end of Broadcast Receiver.

		IntentFilter intentFilter = new IntentFilter("android.provider.Telephony.SMS_RECEIVED");
		registerReceiver(receiver, intentFilter);
		

	}
	 @Override
	    protected void onPause(){
	        super.onPause();
	    if (receiver != null){
	        this.unregisterReceiver(receiver);
	        	    }
	    }
}