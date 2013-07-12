package com.example.rikiorder;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import org.jsoup.nodes.Element;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;



class Pizza implements Product
{

	protected String mName;
	protected String mDescription;
	protected Bitmap mImage;
	protected List<ProductEntry> mPriceTable = new ArrayList<ProductEntry>();
	
	public String GetDescription() {
		return mDescription;
	}

	public Bitmap GetImage() {
		return mImage;
	}	
	
	public List<ProductEntry> GetPriceTable()
	{
		return mPriceTable;
	}
	
	public String GetName()
	{
		return mName;
	}
	
	public Pizza(Element element) throws Exception
	{		
		mName			= ParseByTag(element, RikiConstants.NAME_FIELD_TAG);		
		mDescription	= ParseByTag(element, RikiConstants.DESCRIPTION_FIELD_TAG);		
		String prices	= ParseByTag(element, RikiConstants.PRICE_FIELD_TAG);
		
		ParsePriceField(prices);
		ParseImageField(element);
	}
	
	
	private void ParsePriceField(String data)
	{	
		//replace all except numbers and spaces
		String res = data.replaceAll("[^0-9 ]","");
		//split string by one or more spaces
		String[] items = res.split("( +)");		
		
		int[] values = new int[items.length];
		
		for (int i = 0; i < values.length; ++i)
		{
			values[i] = Integer.parseInt(items[i]);
		}
		
		for (int i = 0; i < values.length; i+=2)
		{
			int size = values[i];
			int price = values[i+1];
			mPriceTable.add(new ProductEntry(size, price));
		}		
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
		return RikiConstants.RIKI_URL + url;
	}
	
	private String ParseByTag(Element element, String tag) throws Exception
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





















