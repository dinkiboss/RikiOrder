package com.example.rikiorder;

import java.io.InputStream;

import org.jsoup.nodes.Element;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

enum PizaSize
{
	SMALL,
	MEDIUM,
	LARGE
}
/*
abstract class Pizza
{	
	protected String mName;
	protected String mDescription;
	protected String mPrice;
	protected Bitmap mImage;
	
	
	public String GetName() {
		return mName;
	}

	public String GetDescription() {
		return mDescription;
	}

	public String GetPrices() {
		return mPrice;
	}	
}
*/
class Pizza
{
	public String mName;
	public String mDescription;
	public String mPrice;
	public Bitmap mImage;	
	
	
	public String GetName() {
		return mName;
	}

	public String GetDescription() {
		return mDescription;
	}

	public String GetPrice() {
		return mPrice;
	}
	
	public Pizza(Element element) throws Exception
	{
		ParseTextFields(element);
		ParseImageField(element);
	}
	
	private void ParseTextFields(Element element) throws Exception
	{
		final String NAME_FIELD_TAG			= "h2";
		final String DESCRIPTION_FIELD_TAG	= "h6";
		final String PRICE_FIELD_TAG		= "h3";		
		
		mName			= ParseField(element, NAME_FIELD_TAG);		
		mDescription	= ParseField(element, DESCRIPTION_FIELD_TAG);		
		mPrice			= ParseField(element, PRICE_FIELD_TAG);	
	}
	
	private void ParsePriceField()
	{
		
	}
	
	private void ParseImageField(Element element) throws Exception
	{
		String imageURL = element.getElementsByTag("img").attr("src");
		ValidateField(imageURL);
		
		imageURL = GetFullAddress(imageURL);		
		mImage = LoadImage(imageURL);		
	}
	
	private String GetFullAddress(String url)
	{
		return R.string.RIKI_URL_ADDRESS  + url;
	}
	
	private String ParseField(Element element, String tag) throws Exception
	{
		String field = element.getElementsByTag(tag).text();
		ValidateField(field);
		return field;
	}
	
	private void ValidateField(String field) throws Exception
	{
		if(field == null || field.equals(""))
		{
			throw new Exception("Not founded field");
		}
	}
	
	
	@Override
	public String toString() {
		return	" Name: " + mName + 
				" Description: " + mDescription +
				" Price: " + mPrice;
	}
	
	private Bitmap LoadImage(String url)
	{
        Bitmap image = null;
        try {
            InputStream in = new java.net.URL(url).openStream();
            image = BitmapFactory.decodeStream(in);
        } catch (Exception e) {
            Log.e("tag", e.getMessage());
            e.printStackTrace();
        }
        return image;		
	}


}

/*
class SelectedPizza extends Pizza
{
	protected Pizza mConmponent;
	PizaSize mPizaSize;
	
	int mSmallPizaPrice = 0;
	int mMedPizzaPrice = 0;
	int mLargePizzaPrice = 0;
	
	
	public SelectedPizza(Pizza component)
	{
		mConmponent = component;
	}
	
	public void SetSize(PizaSize size)
	{
		mPizaSize = size;
	}
	
	protected void ParsePrice()
	{
		
	}
	
	public int GetPizaPrice()
	{
		int price = 0;
		switch(mPizaSize)
		{
		case SMALL:
			price = mSmallPizaPrice;
		case MEDIUM:
			price = mMedPizzaPrice;
		case LARGE:
			price = mLargePizzaPrice;
		}

		return price;
	}
	
	public String GetName() {
		return mConmponent.mName;
	}

	public String GetDescription() {
		return mConmponent.mDescription;
	}

	public String GetPrices() {
		return mConmponent.mPrice;
	}	
}*/

























