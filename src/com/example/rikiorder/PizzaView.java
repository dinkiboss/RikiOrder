package com.example.rikiorder;

import java.util.List;
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
		
		mName		= (TextView) findViewById(R.id.Name);
		mDescritopn	= (TextView) findViewById(R.id.Description);
		mPrice		= (TextView) findViewById(R.id.Price);		
		mImage		= (ImageView) findViewById(R.id.Image);	
	}
	
	public void UpdateFields(Product pizza)
	{
		mName.setText(pizza.GetName());
		
		mDescritopn.setText(pizza.GetDescription());
		
		String prices = PreparePrices(pizza);		
		mPrice.setText(prices);
	
		mImage.setImageBitmap(pizza.GetImage());
	}
	
	protected String PreparePrices(Product pizza)
	{
		List<ProductEntry> priceList = pizza.GetPriceTable();
		
		String pricesString = "";
		for (ProductEntry pizzaEntry : priceList) {
			pricesString += pizzaEntry;
		}
		
		return pricesString;		
	}
		

}
