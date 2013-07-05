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
	void onLoadingComplite(List<Pizza> pizzaList);
}

public class DataLoader extends AsyncTask<String, Void, List<Pizza>> {
	
	LoadingListener listener;
	
	public DataLoader(LoadingListener listener)
	{
		this.listener = listener;
	}
	
	@Override
	protected List<Pizza> doInBackground(String... urls) {

		List<Pizza> pizzaList = new ArrayList<Pizza>();
		String url = urls[0];
		
		try
		{
			Log.i("fail",url);
			Document doc = ConnectTo(url);
			Elements divs = GetPizzaDivisitions(doc);
			
			for (Element element : divs)
			{
				for(Element child : element.children())
				{
					try
					{
						pizzaList.add(new Pizza(child));
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
		return pizzaList;
	}
	
	private Document ConnectTo(String url) throws IOException
	{
		return Jsoup.connect(url).get();
	}
	
	private Elements GetPizzaDivisitions(Document doc)
	{
		final String PIZZA_ID = "tovari";		
		return doc.getElementsByAttributeValue("id", PIZZA_ID);
	}
	
	@Override
	protected void onPostExecute(List<Pizza> result) {
		listener.onLoadingComplite(result);
	}

}
