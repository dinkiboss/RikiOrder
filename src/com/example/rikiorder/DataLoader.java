package com.example.rikiorder;

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
	protected List<Pizza> doInBackground(String... arg0) {
		
		List<Pizza> pizzaList = new ArrayList<Pizza>();
		
		try
		{
			Log.i("fail",arg0[0]);
			Document doc = Jsoup.connect(arg0[0]).get();
			Elements divs = doc.getElementsByAttributeValue("id", "tovari");
			
			for (Element element : divs) {
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
	
	@Override
	protected void onPostExecute(List<Pizza> result) {
		listener.onLoadingComplite(result);
	}

}
