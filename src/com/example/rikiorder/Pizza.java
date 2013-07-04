package com.example.rikiorder;

import java.io.InputStream;

import org.jsoup.nodes.Element;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;

class Pizza
{
	public String mName;
	public String mDescription;
	public String mPrice;
	public Bitmap mImage;
	
	public Pizza(Element element) throws Exception
	{
		mName = element.getElementsByTag("h2").text();
		ValidateField(mName);
		
		mDescription = element.getElementsByTag("h6").text();
		ValidateField(mDescription);
		
		mPrice = element.getElementsByTag("h3").text();
		ValidateField(mPrice);
		//mPrice = ParsePrice(mPrice);
		
		String imageURL = element.getElementsByTag("img").attr("src");
		ValidateField(imageURL);
		
		imageURL = "http://www.rikipizza.ru/" + imageURL;
		
		Log.i("succ", "LoadImage:" + imageURL);
		mImage = LoadImage(imageURL);
	}
	
	private String ParsePrice(String price)
	{
		return price.replaceAll("[.]", "\n");
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
        String urldisplay = url;
        Bitmap mIcon11 = null;
        try {
            InputStream in = new java.net.URL(urldisplay).openStream();
            mIcon11 = BitmapFactory.decodeStream(in);
        } catch (Exception e) {
            Log.e("tag", e.getMessage());
            e.printStackTrace();
        }
        return mIcon11;		
	}
	
}