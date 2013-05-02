package com.currenyconverter;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.widget.*;
import android.view.*;
import android.widget.AdapterView.OnItemSelectedListener;

public class MyOnItemSelected extends MainActivity implements OnItemSelectedListener  {
	
	public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
		//An item was selected. You can retrieve the selected item using
		//parent.getItemAtPosition(pos)
	}
	
	public void onNothingSelected(AdapterView<?> parent) {
		//Another interface fallback
		
	}

}
