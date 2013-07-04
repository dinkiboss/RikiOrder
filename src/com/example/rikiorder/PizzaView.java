package com.example.rikiorder;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

class PizzaView extends FrameLayout
{
	TextView mName;
	TextView mDescritopn;
	TextView mPrice;
	ImageView mImage;

	public PizzaView(Context context) {
		super(context);
		
		LayoutInflater layoutInflater = (LayoutInflater) getContext().
				getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		layoutInflater.inflate(R.layout.pizza_item, this);
		
		mName = (TextView) findViewById(R.id.Name);
		mDescritopn = (TextView) findViewById(R.id.Description);
		mPrice = (TextView) findViewById(R.id.Price);
		
		mImage = (ImageView) findViewById(R.id.Image);	
	}
	
	public void UpdateFields(Pizza pizza)
	{
		mName.setText(pizza.mName);
		mDescritopn.setText(pizza.mDescription);
		mPrice.setText(pizza.mPrice);
		mImage.setImageBitmap(pizza.mImage);
	}
	

}
