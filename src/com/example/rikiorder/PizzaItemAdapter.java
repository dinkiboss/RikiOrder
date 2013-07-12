package com.example.rikiorder;

import java.util.List;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;


public class PizzaItemAdapter extends ArrayAdapter<Product> {

	List<Product> mProductList;
	
	public PizzaItemAdapter(Context context, int textViewResourceId,
			List<Product> objects) {
		super(context, textViewResourceId, objects);
		
		mProductList = objects;
	}	
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		PizzaView view = (PizzaView)convertView;		
		Product product = mProductList.get(position);
		
		if(view == null)
		{
			view = new PizzaView(getContext());
		}
		
		view.UpdateFields(product);
		return view;
	}
}

