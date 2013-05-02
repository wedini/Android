package com.currenyconverter;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.widget.*;

public class MainActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        //Spinner for converting currency from
        //Spinner spinner1 = (Spinner)findViewById(R.id.spinner1);
        
        //Spinner from converting currency to
        //Spinner spinner2 = (Spinner)findViewById(R.id.spinner2);
        
        //Array adapter for converting currency from
        ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(
        		this,R.array.currency_type_from, android.R.layout.simple_spinner_dropdown_item);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        
        //Array adapter for converting currency to
        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(
        		this,R.array.currency_type_to, android.R.layout.simple_spinner_dropdown_item);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        
        //Set adapter for spinner 1 & 2
        //spinner1.setAdapter(adapter1);
        //spinner2.setAdapter(adapter2);
        
        //Add listeners to spinners 1 & 2
        //spinner1.setOnItemSelectedListener(new MyOnItemSelected());
        //spinner2.setOnItemSelectedListener(new MyOnItemSelected());
    }
}
