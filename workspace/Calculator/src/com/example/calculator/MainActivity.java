package com.example.calculator;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity implements OnClickListener{
	
	Button calculate;
	EditText inputRadius;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        calculate = (Button)findViewById(R.id.button1);
        inputRadius = (EditText)findViewById(R.id.editText1);
        
        calculate.setOnClickListener(this);
        //inputRadius.setOnClickListener(this);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }


	@Override
	public void onClick(View v) {
		Toast.makeText(getApplicationContext(), "Button Clicked", Toast.LENGTH_LONG ).show();		
	}
    
}
