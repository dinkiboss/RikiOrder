package com.example.rikiorder;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.telephony.gsm.SmsManager;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;


public class OrderActivity extends Activity {
	

	EditText address;
	EditText phone;
	TextView cost;
	ArrayAdapter<String> cartItemAdapter;
	
	Button orderButton;
	
	String request = "";
	
	protected void setItemToView(final List<CartItem> sublist) {
		
		List<String> items = new ArrayList<String>();
		for (CartItem item : sublist) {
			items.add(item.GetName() + "   " + item.GetPrice());
		}
		
		cartItemAdapter = new ArrayAdapter<String>(this, 
								android.R.layout.simple_list_item_1, items);
		
		ListView lview = (ListView)findViewById(R.id.ordered_pizza);
		lview.setAdapter(cartItemAdapter);
		lview.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				Dialog dialog = CreateAlertDialog(arg2);
				dialog.show();
				cartItemAdapter.notifyDataSetChanged();
			}
		});
	}
		
    public Dialog CreateAlertDialog(final int index) {
        // Use the Builder class for convenient dialog construction
        AlertDialog.Builder builder = new AlertDialog.Builder(OrderActivity.this);
        builder.setMessage(R.string.dialog_fire_missiles)
               .setPositiveButton(R.string.fire, new DialogInterface.OnClickListener() {
                   public void onClick(DialogInterface dialog, int id) {
                       Cart.Instance.RemoveItem(index);
                   }
               })
               .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                   public void onClick(DialogInterface dialog, int id) {
                       // User cancelled the dialog
                   }
               });
        // Create the AlertDialog object and return it
        return builder.create();
    }	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_order);
		
		
		address = (EditText) findViewById(R.id.address);
		phone = (EditText) findViewById(R.id.phone);
		
		orderButton = (Button) findViewById(R.id.order_button);
		
		cost = (TextView)findViewById(R.id.cost);
		cost.setText("" + Cart.Instance.mCost);		
		
		orderButton.setOnClickListener(new OnClickListener() {
			
			public void onClick(View arg0) {				
				//TODO: check parameters is valid
				Cart.Instance.Order(address.getText().toString(),phone.getText().toString());
				
			}
		});
		
		setItemToView(Cart.Instance.itemList);
	}
	
	
}
