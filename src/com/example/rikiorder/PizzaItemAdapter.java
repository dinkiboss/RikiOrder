package com.example.rikiorder;

import java.util.List;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;


public class PizzaItemAdapter extends ArrayAdapter<Pizza> {

	List<Pizza> pizzaList;
	
	public PizzaItemAdapter(Context context, int textViewResourceId,
			List<Pizza> objects) {
		super(context, textViewResourceId, objects);
		
		pizzaList = objects;
	}	
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		PizzaView view = (PizzaView)convertView;		
		Pizza pizza = pizzaList.get(position);
		
		if(view == null)
		{
			view = new PizzaView(getContext());
		}
		
		view.UpdateFields(pizza);
		return view;
	}

}
