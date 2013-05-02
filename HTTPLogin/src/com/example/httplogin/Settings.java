package com.example.httplogin;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Settings extends Activity {

	TextView address;
	EditText editText;
	Button save;
	String URLaddress;
	Intent sendaddress;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.settings);
		
		address = (TextView) findViewById(R.id.addresstextView1);
		editText = (EditText) findViewById(R.id.editText1);
		save = (Button) findViewById(R.id.savebutton1);
		URLaddress = address.getText().toString();
		
		save.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				sendaddress = new Intent(Settings.this, LoginActivity.class);
				sendaddress.putExtra("address", sendaddress);
				sendaddress.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
				startActivity(sendaddress);
			}
		});
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_login, menu);
		return true;
	}
}
