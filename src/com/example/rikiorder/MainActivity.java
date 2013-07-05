package com.example.rikiorder;

import java.util.List;

import android.os.Bundle;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainActivity extends Activity implements LoadingListener {

	ProgressDialog progressDialog = null;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		DataLoader loader = new DataLoader(this);
		loader.execute(getString(R.string.RIKI_URL_ADDRESS));
		progressDialog = ProgressDialog.show(this, "Loading", "Please wait");
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

	@Override
	public void onLoadingComplite(List<Pizza> pizzaList) {
		progressDialog.hide();
		setItemToView(pizzaList);
	}
	
	protected void setItemToView(final List<Pizza> sublist) {
		ArrayAdapter<Pizza> adapter = new PizzaItemAdapter(this, 
								R.layout.pizza_item, 
								sublist);
		
		ListView lview = (ListView)findViewById(R.id.listView1);
		lview.setAdapter(adapter);
		lview.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				Intent intent = new Intent(getApplicationContext(), OrderActivity.class);
				intent.putExtra("Pizza", sublist.get(arg2).mName);
				startActivity(intent);
			}
			
		});
		
	}
}
