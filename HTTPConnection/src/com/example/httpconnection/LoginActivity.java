package com.example.httpconnection;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

import android.os.Bundle;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends Activity {

	
	TextView uname, pswrd;
	EditText username, password;
	Button login;
	ArrayList<String> params = new ArrayList<String>();
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		
		uname = (TextView) findViewById(R.id.usernametextView1);
		pswrd = (TextView) findViewById(R.id.passwordtextView2);
		username = (EditText) findViewById(R.id.emaileditText1);
		password = (EditText) findViewById(R.id.passwordeditText2);
		login = (Button) findViewById(R.id.loginbutton1);
		login.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				//Toast.makeText(getApplicationContext(), "Login clicked", Toast.LENGTH_LONG).show();
													
				
				params.add(username.getText().toString());
				params.add(password.getText().toString());
				//Toast.makeText(getApplicationContext(), "Login "+params, Toast.LENGTH_LONG).show();
				Intent intent = new Intent(LoginActivity.this, CustomHttpClient.class);
				intent.putExtra("parameters", params);
				intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
				startActivity(intent);
					
								
				
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
