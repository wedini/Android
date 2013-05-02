package com.example.currencyconverter1;

import java.lang.Object;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemSelectedListener;

public class CurrencyConverterActivity extends Activity implements OnItemSelectedListener{
	
	private Spinner fromSpinner;
	private Spinner toSpinner;
	private ArrayAdapter<CharSequence> fromAdapter;
	private ArrayAdapter<CharSequence> toAdapter;
	private View from;
	private View to;
	private Button clearBtn;
	private Button swapBtn;
	private Button convertBtn;
	private EditText amount_field;
	private TextView result_field;
	private double[] rates = {0.69881, 0.61095, 0.93188, 0.96680, 44.72, 73.14, 80.55};
	
	
	

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		// Spinner for converting currency from
		fromSpinner = (Spinner) findViewById(R.id.fromSpinner);

		// Spinner from converting currency to
		toSpinner = (Spinner) findViewById(R.id.toSpinner);

		// Array adapter for converting currency from
		fromAdapter = ArrayAdapter.createFromResource(this,
				R.array.currency_type_from,
				android.R.layout.simple_spinner_dropdown_item);
		fromAdapter
				.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

		// Array adapter for converting currency to
		toAdapter = ArrayAdapter.createFromResource(this,
				R.array.currency_type_to,
				android.R.layout.simple_spinner_dropdown_item);
		toAdapter
				.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

		// Set adapter for spinners
		fromSpinner.setAdapter(fromAdapter);
		toSpinner.setAdapter(toAdapter);
		
		//View for spinners
		from = (View)findViewById(R.id.fromSpinner);
		to = (View)findViewById(R.id.toSpinner);
		
		
		//Add listeners
		onItemSelected(fromSpinner,from,fromSpinner.getSelectedItemPosition(),fromSpinner.getSelectedItemId());
		onItemSelected(toSpinner,to, toSpinner.getSelectedItemPosition(),toSpinner.getSelectedItemId());
		
		
		

		// Clear button
		clearBtn = (Button) findViewById(R.id.clear_btn);
		clearBtn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				
				Toast.makeText(getApplicationContext(), "Clear Clicked", Toast.LENGTH_SHORT).show();
				
				EditText amount = (EditText)findViewById(R.id.currency_amount);
				TextView result = (TextView)findViewById(R.id.result_field);
				amount.setText("");
				result.setText("");
			}
		});

		// Swap Button
		swapBtn = (Button) findViewById(R.id.swap_btn);
		swapBtn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				
				int fromPosition;
				int toPosition;
				
				//Swaps the position of items in Spinner
				Toast.makeText(getApplicationContext(), "Swap Clicked", Toast.LENGTH_SHORT).show();
				fromPosition = fromSpinner.getSelectedItemPosition();
				toPosition = toSpinner.getSelectedItemPosition();
				toSpinner.setSelection(fromPosition);
				fromSpinner.setSelection(toPosition);
				
			}
		});

		// Convert Button
		convertBtn = (Button) findViewById(R.id.convert_btn);
		convertBtn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				
				//Convert button check
				Toast.makeText(getApplicationContext(), "Convert Clicked", Toast.LENGTH_SHORT).show();
				
				//EditText field
				amount_field = (EditText)findViewById(R.id.currency_amount);
				
				//Amount field
				String amountStr = amount_field.getText().toString();
				
				//Check for input amount
				if(amountStr.equals("")) {
					
					Toast.makeText(getApplicationContext(), "Please enter an amount", Toast.LENGTH_SHORT).show();
					return;
				}
				
				//Get currency types from Spinner
				int fromPosition = fromSpinner.getSelectedItemPosition();
				Toast.makeText(getApplicationContext(), Integer.toString(fromPosition), Toast.LENGTH_SHORT).show();
				double fromCurrency = rates[fromPosition];
				int toPosition = toSpinner.getSelectedItemPosition();
				Toast.makeText(getApplicationContext(), Integer.toString(toPosition), Toast.LENGTH_SHORT).show();
				double toCurrency = rates[toPosition];
				
				double amount = Double.parseDouble(amountStr);
				
				Toast.makeText(getApplicationContext(),"from "+fromCurrency+" to "+toCurrency, Toast.LENGTH_SHORT).show();
				
				//Get converted currency
				double result = convertCurrency(fromCurrency,toCurrency,amount);
				
				String resulttxt = Double.toString(result);
				
				Toast.makeText(getApplicationContext(), resulttxt, Toast.LENGTH_SHORT).show();
				
				//Set converted result 
				result_field = (TextView)findViewById(R.id.result_field);
				result_field.setText(resulttxt);
				
			}
		});
	}
	
	
	public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
		//An item was selected. You can retrieve the selected item using
		//parent.getItemAtPosition(pos)
		Toast.makeText(parent.getContext(), "The currency is "+
		parent.getItemAtPosition(pos).toString(),Toast.LENGTH_LONG).show();
		
	}
	
	public void onNothingSelected(AdapterView<?> parent) {
		//Another interface fallback
		
	}
	
	public double convertCurrency(double fromCurrency, double toCurrency, double amountCurrency) {
		
		double result = 0;
		
		if(fromCurrency < toCurrency) {
			result = (fromCurrency/toCurrency) * amountCurrency;
		}
		else if(fromCurrency > toCurrency) {
			result = (toCurrency/fromCurrency) * amountCurrency;
		}
		return result;
	}
	
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
}
