package com.example.webview;

import java.io.IOException;
import java.util.ArrayList;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import android.os.Bundle;
import android.os.Handler;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;



public class WebActivity extends Activity {

	ArrayList<String> mywebsites;
	ArrayList<Headline> headlines;
	ArrayAdapter<String> adapter;
	TextView espn;
	ListView lView;
		
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_web);
				
		espn = (TextView) findViewById(R.id.textView1);
		lView = (ListView) findViewById(R.id.weblistView1);
		
		mywebsites = new ArrayList<String>();
		headlines = new ArrayList<Headline>();
		
		RequestThread thread = new RequestThread();
		thread.start();
		
		lView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int pos,
					long arg3) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(WebActivity.this, RelatedActivity.class);
				Headline send = headlines.get(pos);
				intent.putExtra("website", send.link);
				intent.putStringArrayListExtra("related", send.relatedLinks);
				intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
				startActivity(intent);
				
			}
		});
		
	}

	DefaultHttpClient client = new DefaultHttpClient();
	
	class RequestThread extends Thread{
		
		public void run(){
			
			String reqURL = "http://api.espn.com/v1/sports/news/headlines/top?apikey=xa4kd3zkyn932qd4qzazh5z7";
			HttpGet getReq = new HttpGet(reqURL);
			try{
				
				HttpResponse getResp = client.execute(getReq);
			    int status = getResp.getStatusLine().getStatusCode();
			    if(status == HttpStatus.SC_OK){
			    	
			    	HttpEntity entity = getResp.getEntity();
			    		
			    	if(entity!=null){
			    			String jsonString = EntityUtils.toString(entity);
			    			Log.d("JSON", jsonString);
							
							JSONObject object = (JSONObject) new JSONTokener(jsonString).nextValue();
							JSONArray jsonArray = object.getJSONArray("headlines");
							int count = jsonArray.length();
							
							for(int i=0; i<count; i++){
									Headline head = new Headline();
									//Headline
									String headline = jsonArray.getJSONObject(i).getString("headline").toString();
									head.headline = headline;
									Log.d("Headline ", headline);
									mywebsites.add(headline); //set adapter is handled in handleMessage method below
									
																	
									//Get Links
									JSONObject linksObject = jsonArray.getJSONObject(i).getJSONObject("links");
									//Get Mobile
									JSONObject mObject = linksObject.getJSONObject("mobile");
									//Get href
									//Link
									String hrefString = mObject.getString("href").toString();
									head.link = hrefString;

									Log.d("Headline link", hrefString);
									
									//Get RelatedLinks
									ArrayList<String> relatedList = new ArrayList<String>();
									JSONArray relatedObject = jsonArray.getJSONObject(i).getJSONArray("related");
									Log.d("Related", "" + relatedObject);
									//Get Mobile
									int count1 = relatedObject.length();
									
									for (int j = 0; j < count1; j++) {
										//Get href
										//Link
										JSONObject links = relatedObject.getJSONObject(j).getJSONObject("links");
										Log.d("Links", "" + links);
										JSONObject linkMobile = links.getJSONObject("mobile");
										Log.d("Mobile", "" + linkMobile);
										String relatedLink = linkMobile.getString("href").toString();
										Log.d("href", relatedLink);
										relatedList.add(relatedLink);
									}
									
									
									head.relatedLinks = relatedList;
									headlines.add(head);
							}
									
							
						
			    		}
			    }
			}//end of try block
			
			catch (ClientProtocolException e) {
				e.printStackTrace();
			}
			catch (IOException e) {
				e.printStackTrace();
			}
			catch (JSONException e) {
				e.printStackTrace();
			}
			
			//parse JSON
			handler.sendEmptyMessage(0);
			
		}
		
			}
	
	
	Handler handler = new Handler(){
		public void handleMessage(android.os.Message msg) {
			
			adapter = new ArrayAdapter<String>(WebActivity.this, android.R.layout.simple_list_item_1, mywebsites);
			lView.setAdapter(adapter);
			
		}
	};
	
	

	

}
