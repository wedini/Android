package com.example.httplogin;

import android.R.string;
import android.net.http.AndroidHttpClient;
import android.os.Bundle;
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

import java.lang.Object;
import java.util.List;

import org.apache.http.cookie.Cookie;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.PersistentCookieStore;
import com.loopj.android.http.RequestParams;

public class LoginActivity extends Activity {

	Button settings, login;
	TextView uname, psword;
	EditText userfield, pwordfield;
	String username, password;
	Intent settingsIntent;
	AsyncHttpClient client;
	PersistentCookieStore pcs;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		final String IP = "https://iris.addteq.com/?action=login";
		uname = (TextView) findViewById(R.id.unametextView1);
		psword = (TextView) findViewById(R.id.pswdtextView2);
		userfield = (EditText) findViewById(R.id.emaileditText1);
		pwordfield = (EditText) findViewById(R.id.passwordeditText2);
		settings = (Button) findViewById(R.id.settingsbutton2);
		login = (Button) findViewById(R.id.loginbutton1);
		client = new AsyncHttpClient();
		pcs = new PersistentCookieStore(getApplicationContext());
		client.setCookieStore(pcs);
		client.get(IP, null);
		Log.d("After get", "");
		List<Cookie> cookies = pcs.getCookies();
		for( Cookie cookie : cookies){
			//Toast.makeText(getApplicationContext(), "Cookie " + cookie.getValue(), Toast.LENGTH_LONG).show();
			Log.d("cookies", ""+cookie);
		}
		
				
		login.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				username = userfield.getText().toString();
				password = pwordfield.getText().toString();
				
				RequestParams params = new RequestParams();
				params.put("username", username);
				params.put("password", password);
				params.put("returntype", "JSON");
				
				
				//Check for blank or " " before
				client.post(IP, params, new AsyncHttpResponseHandler(){
				
					@Override
					public void onSuccess(String response){
						Log.d("response:", ""+response);

						if(response.contains("success")){
							
							Log.v("success", "login successful");
							
						}
						
					}
				     @Override
				     public void onFailure(Throwable e, String response) {
							Log.d("fail response:", ""+response);
				     }
				
				     
				     
					
				});
				
				settings.setOnClickListener(new OnClickListener() {
					
					@Override
					public void onClick(View arg0) {
						// TODO Auto-generated method stub
						settingsIntent = new Intent(LoginActivity.this, Settings.class);
						settingsIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
						startActivity(settingsIntent);
					}
				});
				
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
