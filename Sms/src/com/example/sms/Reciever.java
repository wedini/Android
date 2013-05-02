package com.example.sms;


import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.util.Log;
//import android.util.Log;
import android.widget.Toast;

public class Reciever extends BroadcastReceiver {

	String [] Wordslist = {"blackmail", "abuse", "bomb", "daggers", "shoot", "murder", "Blackmail", "Abuse", "Bomb", "Daggers", "Shoot", "Murder"};
	
	String lsms, body, address;
	int x;
	/* package */ static final String ACTION =
	        "android.provider.Telephony.SMS_RECEIVED";

	    @Override
	    public void onReceive(Context context, Intent intent) {
	         //---get the SMS message passed in---        
	        Bundle bundle = intent.getExtras();                
	        SmsMessage[] msgs = null;        
	        String str = "";                    
	        if (bundle != null)        {           
	            //---retrieve the SMS message received---            
	            Object[] pdus = (Object[]) bundle.get("pdus");            
	            msgs = new SmsMessage[pdus.length];                        
	            for (int i=0; i<pdus.length; i++){                
	                msgs[i] = SmsMessage.createFromPdu((byte[])pdus[i]);                                
	                str += "SMS from " + msgs[i].getOriginatingAddress();                                     
	                str += " :";                
	                str += msgs[i].getMessageBody().toString();                
	                str += "\n";  
	                body = msgs[i].getMessageBody().toString();
	                address = msgs[i].getOriginatingAddress();
	                
	                for(int k=0; k<str.length(); k++){
						for(int j=0; j<Wordslist.length; j++){
					if(str.contains(Wordslist[j]))
					{
						 x=1; //flag variable
				    		Toast.makeText(context, "Bad Words Received", Toast.LENGTH_LONG).show();
/*							Intent intent2 = new Intent(Intent.ACTION_VIEW);         
							intent2.setData(Uri.parse("sms:"));*/
				    		Log.d("Bad Word", str);
							Intent callIntent = new Intent(Intent.ACTION_DIAL);
							callIntent.setData(Uri.parse("tel:911"));
							callIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
							context.startActivity(callIntent);
					  					
				    }
						}//end of loop j
					}//end of loop k
	            }
	        }
	                if(x!=1){
	                	Toast.makeText(context, "Good Message", Toast.LENGTH_LONG).show();
	                	Intent sendIntent = new Intent(Intent.ACTION_VIEW);         
				    	sendIntent.setData(Uri.parse("sms:"));
				    	if(body.equalsIgnoreCase("www")){
				    	Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(body));
				    	context.startActivity(browserIntent);
	                }
				    	else if(x==1){
				    		Toast.makeText(context, "Bad Words Received", Toast.LENGTH_LONG).show();
/*							Intent intent2 = new Intent(Intent.ACTION_VIEW);         
							intent2.setData(Uri.parse("sms:"));*/
				    		Log.d("Bad Word", str);
							Intent callIntent = new Intent(Intent.ACTION_CALL);
							callIntent.setData(Uri.parse("tel:911"));
							context.startActivity(callIntent);
				    	}
	                            
	                
	                }   //close of if bundle         
	            //---display the new SMS message---            
	            Toast.makeText(context, str, Toast.LENGTH_LONG).show();        
	            }

	  

		    
			

			/*Toast.makeText(context,
					"SMS from " + address + " Content: " + text,
					Toast.LENGTH_LONG).show();
			String [] matchStrings = text.split(" ");
			
			for(int i=0; i<text.length(); i++){
				for(int j=0; j<text.length(); j++){
				
				if(matchStrings[i].equalsIgnoreCase(Wordslist[j]))
				{
					Toast.makeText(context, "Bad Words Received", Toast.LENGTH_LONG).show();
					Intent sendIntent = new Intent(Intent.ACTION_VIEW);         
					sendIntent.setData(Uri.parse("sms:"));
					Intent callIntent = new Intent(Intent.ACTION_CALL);
					callIntent.setData(Uri.parse("tel://911"));
					context.startActivity(callIntent);
					
					Uri callUri = Uri.parse("tel://911");
					Intent callIntent = new Intent(Intent.ACTION_CALL,callUri);
					callIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_NO_USER_ACTION);
					context.startActivity(callIntent);
				  					
			    }else {
			    	Intent sendIntent = new Intent(Intent.ACTION_VIEW);         
			    	sendIntent.setData(Uri.parse("sms:"));
			    	if(text.equalsIgnoreCase("www")){
			    	Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(text));
			    	context.startActivity(browserIntent);
			    	}
			    	
			    		
			    	
			    }
				
			}
			}*/


			//} //end of if(bundle)
			
			
			
		
	}




