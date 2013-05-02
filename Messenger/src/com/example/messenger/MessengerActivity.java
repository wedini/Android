package com.example.messenger;

import android.os.Bundle;
import android.graphics.Color;
import android.app.Activity;
import android.view.Menu;
import android.telephony.SmsManager;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.content.Intent;
import android.content.ContentResolver;
import android.provider.ContactsContract.Contacts;
import android.provider.ContactsContract.CommonDataKinds.Phone;
import android.util.Log;
import android.net.Uri;
import android.database.Cursor;
import java.util.ArrayList;
import java.util.ListIterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;




public class MessengerActivity extends Activity {
	
	private TextView header;
	private Button addRecipientBtn;
	private Button sendBtn;
	private EditText recipientField;
	private EditText messageBody;
	private String[] badWords = {"bitch", "bloody", "bastard"};
	private String[] badWordsMsg = {"blackmail", "abuse", "daggers", "bomb"};
	private ArrayList<String> urlList = new ArrayList<String>();
	private String message;
	private String phoneNumber;
	private ArrayList<String> recipientsList = new ArrayList<String>();
	private static final int PICK_CONTACT = 1;
	
	

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_messenger);
        
        //Initialize textview 
        header = (TextView)findViewById(R.id.sendSMS);
        
        //Initialize buttons
        addRecipientBtn = (Button)findViewById(R.id.addMoreRecipient);
        sendBtn = (Button)findViewById(R.id.send);
        
        //Initialize edittext
        recipientField = (EditText)findViewById(R.id.phoneNumber);
        messageBody = (EditText)findViewById(R.id.messageBody);
        
        phoneNumber = getIntent().getStringExtra("phoneNumber");
        message = getIntent().getStringExtra("message");
        
        recipientField.setText(phoneNumber);
        messageBody.setText(message);
        
        
        
        if(message != null) {
        	
        	for(String s : badWordsMsg) {
        		
        		if(message.contains(s)) {
        			
        			Toast.makeText(getApplicationContext(), s, Toast.LENGTH_SHORT).show();
        			
        			Uri uri = Uri.parse("tel:911");
        			Intent openDial = new Intent(Intent.ACTION_DIAL, uri);
        			openDial.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        			startActivity(openDial);	
        		}
        		
        	}
        	
        	urlList = getUrl(message);
        	
        	if(urlList != null) {
	        	for(String s : urlList) {
	        		
	    			Toast.makeText(getApplicationContext(), s, Toast.LENGTH_SHORT).show();
	    			Intent openUrl = new Intent(Intent.ACTION_VIEW);
	    			openUrl.setData(Uri.parse(s));
	    			startActivity(openUrl);
	        				
	        	}
        	}
        	
        	
        	
        }
        
        //Add recipient button
        addRecipientBtn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				
				//Intent to get contact list
				Intent intent = new Intent(Intent.ACTION_PICK, Contacts.CONTENT_URI);
				
				//Start activity with intent and pick contact request code
	            startActivityForResult(intent, PICK_CONTACT);          
			}
        });
        
        //Send button
        sendBtn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				
				String recipients = recipientField.getText().toString();
				String message = messageBody.getText().toString();
				
				if(recipients == "") {
					
					Toast.makeText(getApplicationContext(), "Enter a recipient", Toast.LENGTH_SHORT).show();
				    return;
				}
				
				if(message == "") {
					
					Toast.makeText(getApplicationContext(), "Please enter message", Toast.LENGTH_SHORT).show();
				    return;
				}
				if(messageFilter(message) == true) {
					
					Toast.makeText(getApplicationContext(), "Bad words detected", Toast.LENGTH_SHORT).show();
	    			messageBody.setTextColor(Color.RED);
	    			return;
				}
				try {
					
					for(String number : recipientsList) {
						
						SmsManager smsManager = SmsManager.getDefault();
						smsManager.sendTextMessage(number, null, message, null, null);
						Toast.makeText(getApplicationContext(), "Message sent", Toast.LENGTH_SHORT).show();
					}
					
				
					
				} catch (Exception e) {
					
					Toast.makeText(getApplicationContext(),
							"SMS failed, please try again later!",
							Toast.LENGTH_LONG).show();
						e.printStackTrace();
				}
				
				
			}
        });
        
        
        
    }
    
    //Message filter function
    public boolean messageFilter(String message) {
    	
    	boolean flag = false;
    	
    	for(int i = 0; i < badWords.length; i++) {
    		
    		message.toLowerCase();
    		
    		if(message.contains(badWords[i])) {
    			
    			flag = true;
    		}
    	}
    	return flag;
    	
    }
   
    //In pick contact activity
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
    	
    	super.onActivityResult(requestCode, resultCode, data);
    
    	try {
            if (resultCode == Activity.RESULT_OK) {
                Uri contactData = data.getData();
                Cursor cur = managedQuery(contactData, null, null, null, null);
                ContentResolver contact_resolver = getContentResolver();

                if (cur.moveToFirst()) {
                    String id = cur.getString(cur.getColumnIndexOrThrow(Contacts._ID));
                    String name = "";
                    String no = "";

                    Cursor phoneCur = contact_resolver.query(Phone.CONTENT_URI, null,
                            Phone.CONTACT_ID + " = ?", new String[] { id }, null);

                    if (phoneCur.moveToFirst()) {
                        name = phoneCur.getString(phoneCur.getColumnIndex(Phone.DISPLAY_NAME));
                        no = phoneCur.getString(phoneCur.getColumnIndex(Phone.NUMBER));
                    }
                    
                    Log.e("Phone no & name :***: ", name + " : " + no);
                    recipientField.append(no + ", ");
                    
                    //Add phone number to arraylist
                    recipientsList.add(no);
                    

                    id = null;
                    name = null;
                    no = null;
                    phoneCur = null;
                }
                contact_resolver = null;
                cur = null;
                //                      populateContacts();
            }
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
            Log.e("IllegalArgumentException :: ", e.toString());
        } catch (Exception e) {
            e.printStackTrace();
            Log.e("Error :: ", e.toString());
        }

        
    	
    }
    
    //Get urls from message
  	public ArrayList<String> getUrl(String message) {
  		
  		ArrayList<String> list = new ArrayList<String>();
  		
  		String regex = "\\(?\\b(http://|www[.])[-A-Za-z0-9+&@#/%?=~_()|!:,.;]*[-A-Za-z0-9+&@#/%=~_()|]";
  		
  		Pattern urlPattern = Pattern.compile(regex);
  		Matcher urlMatcher = urlPattern.matcher(message);
  		
  		while(urlMatcher.find()) {
  			
  			String urlString = urlMatcher.group();
  			if(urlString.startsWith("(") && urlString.endsWith(")")) {
  				
  				urlString = urlString.substring(1, urlString.length()-1);
  			}
  			list.add(urlString);
  		}
  		
  		return list;
  	}
    
    
    public void showSelectedNumber(String number) {
        Toast.makeText(this, "Number"+ ": " + number, Toast.LENGTH_LONG).show();      
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_messenger, menu);
        return true;
    }
}
