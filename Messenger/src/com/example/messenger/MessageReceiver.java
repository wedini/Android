package com.example.messenger;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.telephony.SmsMessage;
import android.widget.Toast;
import android.os.Bundle;
import android.net.Uri;
import java.util.ArrayList;
import java.util.ListIterator;
import java.util.List; 
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class MessageReceiver extends BroadcastReceiver {
	
	private String[] badWordsMsg = {"blackmail", "abuse", "daggers", "bomb"};
	private List<String> urlList = new ArrayList<String>();
	private String message = "";
	private String phoneNumber = "";
	
	@Override
	public void onReceive(Context context, Intent intent) {
		
		//react to event
		Bundle bundle = intent.getExtras();
		SmsMessage[] msg = null;
		String str ="";
		
		
		if(bundle != null) {
			
			//Retrieve the SMS
			Object[] pdus = (Object[])bundle.get("pdus");
			msg = new SmsMessage[pdus.length];
			for(int i = 0; i < msg.length; i++) {
				
				msg[i] = SmsMessage.createFromPdu((byte[])pdus[i]);
				//if(msgs[i].getOriginatingAddress().equals("+91XXX"))
                //{
				
				phoneNumber += msg[i].getDisplayOriginatingAddress();
                message += msg[i].getMessageBody();
                
                str += "SMS from " + msg[i].getOriginatingAddress();
                str += " :";
                str += msg[i].getMessageBody().toString();
                str += "\n";
                //}
            }
			
            // Display the SMS as Toast.
            Toast.makeText(context, str, Toast.LENGTH_SHORT).show();
            
            Intent replyIntent = new Intent(context, MessengerActivity.class);
            replyIntent.putExtra("phoneNumber", phoneNumber);
            replyIntent.putExtra("message", message);
            replyIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(replyIntent);
            
            
            
            
            /*urlList = getUrl(str);
            
            for(String s : urlList) {
            	
            	if(message.contains(s)) {
            		
            		Toast.makeText(context, s, Toast.LENGTH_SHORT).show();
            		
            		openUrl(s);

            		//Intent openUrlIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(s));
            		//receiver.startActivity(openUrlIntent);
            		
            	}
            	
            }*/
            
            
        }
	}
	
	
	
	
	//Get urls from message
	public ArrayList getUrl(String message) {
		
		ArrayList urlList = new ArrayList<String>();
		
		String regex = "\\(?\\b(http://|www[.])[-A-Za-z0-9+&@#/%?=~_()|!:,.;]*[-A-Za-z0-9+&@#/%=~_()|]";
		
		Pattern urlPattern = Pattern.compile(regex);
		Matcher urlMatcher = urlPattern.matcher(message);
		
		while(urlMatcher.find()) {
			
			String urlString = urlMatcher.group();
			if(urlString.startsWith("(") && urlString.endsWith(")")) {
				
				urlString = urlString.substring(1, urlString.length()-1);
			}
			urlList.add(urlString);
		}
		
		return urlList;		
	}
	
	
	//Message filter function
    public boolean messageFilter(String message) {
    	
    	boolean flag = false;
    	
    	for(int i = 0; i < badWordsMsg.length; i++) {
    		
    		message.toLowerCase();
    		
    		if(message.contains(badWordsMsg[i])) {
    			
    			flag = true;
    		}
    	}
    	return flag;
    	
    }

}
