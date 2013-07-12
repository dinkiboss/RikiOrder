package com.example.rikiorder;
import android.graphics.Bitmap;

import java.util.List;


public interface Product {	
	
	String GetName();
	String GetDescription();
	Bitmap GetImage();	
	List<ProductEntry> GetPriceTable();
}
