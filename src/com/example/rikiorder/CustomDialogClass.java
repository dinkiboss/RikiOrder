package com.example.rikiorder;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

public class CustomDialogClass extends Dialog implements OnItemClickListener {

	public Product pizza;
	
	public CustomDialogClass(Activity a, Product pizza) {
		super(a);
		// TODO Auto-generated constructor stub
		this.pizza = pizza;
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.pizza_size_dialog);
		
		ListView lview = (ListView)findViewById(R.id.sizes);
		
		ArrayAdapter<ProductEntry> adapter = new ArrayAdapter<ProductEntry>(getContext(), android.R.layout.simple_list_item_1, pizza.GetPriceTable());
		lview.setAdapter(adapter);
		
		lview.setOnItemClickListener(this);
	}

	@Override
	public void onItemClick(AdapterView<?> adapter, View view, int index, long arg3) {
		// TODO Auto-generated method stub
		
		CartItem item = new CartItem(pizza, index);
		Cart.Instance.AddItem(item);		
		dismiss();
	}
}
