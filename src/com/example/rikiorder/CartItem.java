package com.example.rikiorder;

import java.util.List;

import android.graphics.Bitmap;

public class CartItem implements Product {
	
	Product mProduct;
	int mEntryIndex;
	int mPrice;
	
	public CartItem(Product product, int entryIndex)
	{
		this.mProduct = product;
		this.mEntryIndex = entryIndex;
		this.mPrice = ComputePrice();
	}
	
	public int GetPrice()
	{
		return mPrice;
	}
	
	public int GetEntryIndex()
	{
		return mEntryIndex;
	}
	
	protected int ComputePrice()
	{
		List<ProductEntry> prices = mProduct.GetPriceTable();
		ProductEntry entry = prices.get(mEntryIndex);
		return entry.Price;
	}

	@Override
	public String GetName() {
		// TODO Auto-generated method stub
		return mProduct.GetName();
	}

	@Override
	public String GetDescription() {
		// TODO Auto-generated method stub
		return mProduct.GetDescription();
	}

	@Override
	public Bitmap GetImage() {
		// TODO Auto-generated method stub
		return mProduct.GetImage();
	}

	@Override
	public List<ProductEntry> GetPriceTable() {
		// TODO Auto-generated method stub
		return mProduct.GetPriceTable();
	}
}
