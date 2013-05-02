package com.example.activitylifecycledemo;

import android.os.Bundle;
import android.preference.PreferenceManager.OnActivityDestroyListener;
import android.app.Activity;
import android.view.Menu;
import android.view.View.OnCreateContextMenuListener;
import android.widget.Toast;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toast.makeText(getApplicationContext(), "Mainscreen: onCreate", Toast.LENGTH_LONG).show();
    }
   

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }    
}
