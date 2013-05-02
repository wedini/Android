package com.example.httpconnection;

import java.io.BufferedReader;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.CookieHandler;
import java.net.CookieStore;
import java.net.HttpURLConnection;
import java.net.URI;
import java.util.ArrayList;	 
import java.util.List;
import java.net.URL;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
	import org.apache.http.client.methods.HttpGet;
	import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.protocol.ClientContext;
	import org.apache.http.conn.params.ConnManagerParams;
import org.apache.http.cookie.Cookie;
import org.apache.http.impl.client.AbstractHttpClient;
import org.apache.http.impl.client.BasicCookieStore;
	import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
	import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HttpContext;
import org.json.JSONObject;

import android.app.Activity;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.webkit.CookieManager;
import android.widget.TextView;
import android.widget.Toast;
import android.os.StrictMode.ThreadPolicy.Builder;

 
	public class CustomHttpClient extends Activity{ 
		
		Bundle extras;	    
		static TextView success;
	    static TextView cookies;
	    ArrayList<NameValuePair> params;
	    ArrayList<String> list;
	    String response = null;
	    static String userString;
	    static String passString;
	    
			@Override
			protected void onCreate(Bundle savedInstanceState) {
				super.onCreate(savedInstanceState);
				setContentView(R.layout.success);
				
				Toast.makeText(getApplicationContext(), "oncreate" , Toast.LENGTH_LONG).show();
				success = (TextView) findViewById(R.id.successtextView1);
				cookies = (TextView) findViewById(R.id.cookiestextView2);	
				
				
				    list = new ArrayList<String>();
					params = new ArrayList<NameValuePair>();
					extras = getIntent().getExtras();
		     		list = extras.getStringArrayList("parameters");
		     		userString = list.get(0).toString();
		     		passString = list.get(1).toString();
		     		params.add(new BasicNameValuePair("username", userString));
		     		params.add(new BasicNameValuePair("password", passString));
		     		
		     		
		     		try {
						response = CustomHttpClient.executeHttpPost("https://iris.addteq.com/?returntype=JSON", params);
						
						if(response.contains("success"))
			            {
			             
			             success.setText("Login successful");  
			            }else
			            {
			             
			            success.setText("Login not successful");             
			            }
						
						Toast.makeText(getApplicationContext(), "custom" + response , Toast.LENGTH_LONG).show();
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
			}
		
		    public static final int HTTP_TIMEOUT = 30 * 1000; // milliseconds
			 
			   
		    private static HttpClient mHttpClient;
		   // static DefaultHttpClient defaulthttpclient = new DefaultHttpClient();
		    
		    /*private static HttpClient getHttpClient() {
		        if (mHttpClient == null) {
		            mHttpClient = new DefaultHttpClient();
	            final HttpParams params = mHttpClient.getParams();
	            
		            HttpConnectionParams.setConnectionTimeout(params, HTTP_TIMEOUT);
		            HttpConnectionParams.setSoTimeout(params, HTTP_TIMEOUT);
	            ConnManagerParams.setTimeout(params, HTTP_TIMEOUT);
	            
		        }
		        return mHttpClient;
		    }*/
	 
	      public static String executeHttpPost(String url, ArrayList<NameValuePair> postParameters) throws Exception {
	        BufferedReader in = null;
	        
	        try {
	        	Log.d("executehttp", "inside executehttp");
	        	//Log.d("parameters", ""+postParameters);

	        	//DefaultHttpClient defaulthttpclient = new DefaultHttpClient();	
	            HttpClient client = new DefaultHttpClient();
	            client.getParams();
	            Log.d("params",""+client.getParams());
                HttpPost request = new HttpPost(url);
	            UrlEncodedFormEntity formEntity = new UrlEncodedFormEntity(postParameters);
	            request.setEntity(formEntity);
	              
	            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
	            StrictMode.setThreadPolicy(policy); 
	           
	            request.getHeaders("session-id");
	               Log.d("HttpEntity", "request"+request.getHeaders("session-id"));
	               
	            HttpResponse response = client.execute(request);
	            Log.d("Params","" + response.getParams());
            	            
	            Header[] mCookies = response.getHeaders("cookie");
	            Log.d("cookies", ""+mCookies);
	            String c = mCookies.toString();
	            cookies.setText("Cookies from connection: "+c);
	            
	            Log.d("httpResponse" + response.getStatusLine(), "request");
	            HttpEntity entity = response.getEntity();
	            Log.d("HttpEntity" + entity, "request");
	              
	            
	            in = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
	            Log.d("in ", ""+in);
	            String res = in.toString();
	            Log.d("Resulttt" + res, "request");
	            	            
	            StringBuffer sb = new StringBuffer("");
                String line = "";
	            String NL = System.getProperty("line.separator");
	            while ((line = in.readLine()) != null) {
                sb.append(line + NL);
            }
	            
	            Log.d("sb", ""+sb);
	        
	           //in.close();
	 
	            String result = sb.toString();
	            Log.d("sbresult "+result, "request");
	            if (entity != null) {
		              entity.consumeContent();
		            }
	            
	           
	            return result;
	        } finally {
	            if (in != null) {
	                try {
	                    in.close();
	                } catch (IOException e) {
	                    e.printStackTrace();
	                }
	            }
	        }
	    }
	 
	      	     
    
	}
