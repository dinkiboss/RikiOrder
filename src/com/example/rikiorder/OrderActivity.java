package com.example.rikiorder;

import android.app.Activity;
import android.os.Bundle;
import android.telephony.gsm.SmsManager;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;


public class OrderActivity extends Activity {
	
	String pizzaName = "";
	EditText address;
	EditText phone;
	Spinner size;
	
	Button orderButton;
	
	String request = "";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_order);
		
		pizzaName = getIntent().getStringExtra("Pizza");
		
		address = (EditText) findViewById(R.id.address);
		phone = (EditText) findViewById(R.id.phone);
		
		orderButton = (Button) findViewById(R.id.button1);
		
		size = (Spinner) findViewById(R.id.spinner1);
		
		orderButton.setOnClickListener(new OnClickListener() {
			
			public void onClick(View arg0) {				
				//TODO: check parameters is valid
				request = "\n" + pizzaName + "\n";
				request += address.getText().toString() + "\n";
				request += phone.getText().toString() + "\n";
				request += String.valueOf(size.getSelectedItem());
				
				SmsManager.getDefault().sendTextMessage("+79108922109", null, request, null, null);
				
				finish();
				
			}
		});
	}
	
	
}
