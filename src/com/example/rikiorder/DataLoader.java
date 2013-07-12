package com.example.rikiorder;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.jsoup.nodes.Element;

import android.os.AsyncTask;
import android.util.Log;

interface LoadingListener
{
	void onLoadingComplite(List<Product> pizzaList);
}

public class DataLoader extends AsyncTask<String, Void, List<Product>> {
	
	LoadingListener listener;
	
	public DataLoader(LoadingListener listener)
	{
		this.listener = listener;
	}
	
	@Override
	protected List<Product> doInBackground(String... urls) {

		List<Product> productList = new ArrayList<Product>();
		String url = urls[0];
		
		try
		{
			Log.i("fail",url);
			Document doc = ConnectTo(url);
			Elements divs = GetPizzaDivisions(doc);	
			
			for (Element element : divs)
			{
				for(Element child : element.children())
				{
					try
					{
						productList.add(new Pizza(child));
					}
					catch(Exception e)
					{
						Log.i("fail",e.toString() + " is not a pizza");
					}
				}
			}
			
		}
		catch(Exception e)
		{
			Log.i("fail",e.toString());
		}
		return productList;
	}
	
	private Document ConnectTo(String url) throws IOException
	{
		return Jsoup.connect(url).get();
	}
	
	private Elements GetPizzaDivisions(Document doc)
	{	
		return doc.getElementsByAttributeValue("id", RikiConstants.PIZZA_ID);
	}
	
	@Override
	protected void onPostExecute(List<Product> result) {
		listener.onLoadingComplite(result);
	}

}
