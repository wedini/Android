package com.example.webview;


import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Menu;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.webkit.WebViewClient;


public class RelatedActivity extends Activity {

	WebView webview;
	ListView lView;
	TextView relatednews;
	Bundle extras;
	String href;
	ArrayList<String> relatedlinks;
	ArrayAdapter<String> adapter;
	

	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.related);
		
	    relatednews = (TextView) findViewById(R.id.textView1);
	    webview = (WebView) findViewById(R.id.webView1);
	   lView = (ListView) findViewById(R.id.relatedlistView1);
	    
	    relatedlinks = new ArrayList<String>();
	    //Toast.makeText(getApplicationContext(), "oncreate", Toast.LENGTH_LONG).show();
	   // if (savedInstanceState != null) {
	    	//Toast.makeText(getApplicationContext(), "instance", Toast.LENGTH_LONG).show();
            extras = getIntent().getExtras();
            if (extras != null) {
            	//Toast.makeText(getApplicationContext(), "inside webview", Toast.LENGTH_LONG).show();
                href = extras.getString("website");
                relatedlinks = extras.getStringArrayList("related");
                Log.d("RelatedActivity", "" + relatedlinks);
                //Toast.makeText(getApplicationContext(), "instance" + relatedlinks, Toast.LENGTH_LONG).show();
    			adapter = new ArrayAdapter<String>(RelatedActivity.this, android.R.layout.simple_list_item_1, relatedlinks);
    			lView.setAdapter(adapter);
    			WebSettings webSettings = webview.getSettings();
    			webSettings.setJavaScriptEnabled(true);
    			/*Intent intent = new Intent(Intent.ACTION_VIEW);
    	        intent.setData(Uri.parse(href));
    	        startActivity(intent);*/
                webview.loadUrl(href);
               
                webview.setWebViewClient(new WebViewClient(){
                	public boolean shouldOverrideUrlLoading(WebView view, String url) {
            			view.loadUrl(url);
            			return false;
            		}
                });
    			   			
                
            } 
            
            
		 } 
  // }
	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_web, menu);
		return true;
	}
}