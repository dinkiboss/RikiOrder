package com.example.rikiorder;

class ProductEntry
{
	int Size;
	int Price;
	
	public ProductEntry(int size, int price)
	{
		Size = size;
		Price = price;
	}
	
	@Override
	public String toString() {
		return Size + " см. - " + Price + " руб.\n";
	}
}