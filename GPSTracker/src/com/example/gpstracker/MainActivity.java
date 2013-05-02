package com.example.gpstracker;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.TimerTask;


import com.google.android.gms.internal.c;

import android.os.Bundle;
import android.R.integer;
import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ReceiverCallNotAllowedException;
import android.telephony.SmsMessage;
import android.text.format.Time;
import android.view.Menu;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

	Switch switch1;
	TextView Tracker;
	ListView lView;
	Button Refresh;
	BroadcastReceiver receiver;
	LocationAdapter adapter;
	DBAdapter dbAdapter;
	ArrayList<Location>loclist= new ArrayList<Location>();
	ArrayList<Location>refreshloc= new ArrayList<Location>();
	SimpleDateFormat sdf = new SimpleDateFormat("EEE, d MMM yyyy HH:mm:ss z");
	Location l = new Location();
	int x;
	String currentDateandTime;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
       	x=1;
		lView = (ListView) findViewById(R.id.listView1);
		dbAdapter = new DBAdapter(getApplicationContext());
		receiver = new BroadcastReceiver() {
			
			@Override
			public void onReceive(Context context, Intent intent) {
				// TODO Auto-generated method stub
				
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
					
					Toast.makeText(context, "msg:"+ text, Toast.LENGTH_LONG).show();
					
					
				}// if bundle
				
				currentDateandTime = sdf.format(new Date());
				l.id=x;
				l.street = text;
				l.time = currentDateandTime;
				loclist.add(l);
				x++;
				adapter = new LocationAdapter(com.example.gpstracker.MainActivity.this, loclist);
				lView.setAdapter(adapter);
				
				dbAdapter.open();
				dbAdapter.inserttask(l);
				Toast.makeText(getApplicationContext(), "Inserted into db Successfully", Toast.LENGTH_LONG).show();
				dbAdapter.close();
			}
		};
		
		
		IntentFilter intentFilter = new IntentFilter("android.provider.Telephony.SMS_RECEIVED");
		registerReceiver(receiver, intentFilter);
		switch1 = (Switch) findViewById(R.id.switch1);
		Tracker = (TextView) findViewById(R.id.textView1);
		Refresh = (Button) findViewById(R.id.Refreshbutton1);
		
		
		switch1.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				//On
				if(switch1.isChecked()){
					Toast.makeText(getApplicationContext(), "Checked: " + switch1.isChecked(), Toast.LENGTH_LONG).show();
					String msg = "TRACK <1234> START";
					int num = 5556;
					Intent sendIntent = new Intent(Intent.ACTION_VIEW);
					sendIntent.putExtra("sms_body", msg); 
					sendIntent.putExtra("address", String.valueOf(num));
					sendIntent.setType("vnd.android-dir/mms-sms");
					startActivity(sendIntent);
					
				}
				//Off
				else {
					Toast.makeText(getApplicationContext(), "Checked: " + switch1.isChecked(), Toast.LENGTH_LONG).show();
					setContentView(R.layout.activity_main);
				
				}
			}
		});
		
		Refresh.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Toast.makeText(getApplicationContext(), "Refreshing: ", Toast.LENGTH_LONG).show();
				dbAdapter.open();
				refreshloc = dbAdapter.getLocations();
				dbAdapter.close();
				adapter = new LocationAdapter(com.example.gpstracker.MainActivity.this, refreshloc);
				lView.setAdapter(adapter);
			}
		});
		
		lView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// TODO Auto-generated method stub
				Toast.makeText(getApplicationContext(), "Item clicked ", Toast.LENGTH_LONG).show();
				String street = l.getStreet();
				String [] pos = street.split(":"); //now pos[0] = longitude value & pos[1] = latitude value.
				Toast.makeText(getApplicationContext(), "Latitude: " + pos[0], Toast.LENGTH_LONG).show();	
				Toast.makeText(getApplicationContext(), "Longitude: " + pos[1], Toast.LENGTH_LONG).show();
				
				Intent intent = new Intent(com.example.gpstracker.MainActivity.this, MapActivity.class);
				intent.putExtra("latitude", pos[0]);
				intent.putExtra("longitude", pos[1]);
				intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
				startActivity(intent);
				
			}
		});
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}
	

}
