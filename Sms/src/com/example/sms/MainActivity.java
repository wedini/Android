package com.example.sms;



import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.app.Activity;
import android.content.ContentResolver;
import android.content.Intent;
import android.database.Cursor;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity {

	protected static final int PICK_CONTACT = 0;
	String [] Words = {"bitch", "bastard", "bloody", "fuck", "shit", "suck"};
    Button AddMoreRecipients, Send;
    EditText NumEditText1, MsgeditText2;
    int x;
        
    @Override    
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
                
        NumEditText1 = (EditText) findViewById(R.id.NumeditText1);
        MsgeditText2 = (EditText) findViewById(R.id.MsgeditText2);
        
        AddMoreRecipients = (Button) findViewById(R.id.button1);
        AddMoreRecipients.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Toast.makeText(getApplicationContext(), "Add More Recipients", Toast.LENGTH_LONG).show();
				Intent intent = new Intent(Intent.ACTION_PICK, ContactsContract.Contacts.CONTENT_URI);  
				startActivityForResult(intent, 1); 
			}
		});
        
        Send = (Button) findViewById(R.id.button2);
        Send.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Toast.makeText(getApplicationContext(), "Send", Toast.LENGTH_LONG).show();
				if((NumEditText1.getText().length()!= 0) && (MsgeditText2.getText().length() != 0)){
				//String [] matchStrings = MsgeditText2.getText().toString().split(" ");
				
				for(int i=0; i<MsgeditText2.getText().length(); i++){
					for(int j=0; j<Words.length; j++){
				if(MsgeditText2.getText().toString().contains(Words[j]))
				{
					 x=1;
				  					
			    }
					}//end of loop j
				}//end of loop i
					if(x!=1){
					Intent sendIntent = new Intent(Intent.ACTION_VIEW);
					sendIntent.putExtra("sms_body", MsgeditText2.getText().toString()); 
					sendIntent.putExtra("address", NumEditText1.getText().toString());
					sendIntent.setType("vnd.android-dir/mms-sms");
					startActivity(sendIntent);
					Toast.makeText(getApplicationContext(), "SMS Sent!",
							Toast.LENGTH_LONG).show();
					}
					else if(x==1)
					{
						Toast.makeText(getApplicationContext(), "Bad Words Written", Toast.LENGTH_LONG).show();
					  	MsgeditText2.setBackgroundColor(-65536);
					}
				 } 
				//end of for loops
				
				}//end of first if condition	
				
//				Intent receiveIntent = new Intent(Intent.ACTION_)
				
				
				
			 //end of onClick function
		});
        
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
    //code to get the picked contact from phn database (cursor is the one that acts like a pointer to the phn db)
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent){
        super.onActivityResult(requestCode, resultCode, intent);
        if (resultCode == Activity.RESULT_OK) {
            Log.d("Result", "Code: " + resultCode);
			Uri contactUri = intent.getData();
			
			ContentResolver cr = getContentResolver();
			// content://authority/data-path/id
			Cursor contactCursor = cr.query(ContactsContract.Contacts.CONTENT_URI,
					null, null, null, null);

			if (contactCursor != null && contactCursor.getCount() > 0) {
				while (contactCursor.moveToNext()) {
					String id = contactCursor.getString(contactCursor
							.getColumnIndex(ContactsContract.Contacts._ID));
					String name = contactCursor
							.getString(contactCursor
									.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
					int hasPhoneNumber = Integer
							.parseInt(contactCursor.getString(contactCursor
									.getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER)));
					if (hasPhoneNumber > 0) {
						// this contact
						Cursor phoneCursor = cr.query(
								ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
								null,
								ContactsContract.CommonDataKinds.Phone.CONTACT_ID
										+ "=?", new String[] { id }, null);
						while (phoneCursor.moveToNext()) {
							String phoneNumber = phoneCursor
									.getString(phoneCursor
											.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
							Toast.makeText(getApplicationContext(),
									id + " " + name + " " + phoneNumber,
									Toast.LENGTH_LONG).show();
							NumEditText1.setText(name);
						}
						phoneCursor.close();
					}

				}
			}
			contactCursor.close();
			
			
		}
    }
}
