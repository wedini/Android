package com.example.myclock;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;
import android.widget.Button;
import java.util.Date;

public class ClockActivity extends Activity {
	
	Button updateTime;
	TextView time;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clock);
        
        updateTime=(Button)findViewById(R.id.timebtn);
        time=(TextView)findViewById(R.id.timeTextView);
        
        updateTime=(Button)findViewById(R.id.timebtn);
        updateTime.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Date date = new Date();
				String timeString = "Time:"+date.getHours()+":"+date.getMinutes()+":"+date.getSeconds();
				time.setText(timeString);
			}
		});
    }
    

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_clock, menu);
        return true;
    }
}
