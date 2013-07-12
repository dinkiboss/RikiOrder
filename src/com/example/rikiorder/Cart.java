package com.example.rikiorder;

import java.util.ArrayList;
import java.util.List;


public class Cart {
	public static final Cart Instance = new Cart();

	List<CartItem> itemList = new ArrayList<CartItem>();

	int mCost = 0;

	private Cart() {

	}

	public void AddItem(CartItem item) {
		itemList.add(item);
		UpdateCost(item);
	}

	public void RemoveItem(int index) {
		itemList.remove(index);
	}

	private void UpdateCost(CartItem item) {
		mCost += item.GetPrice();
	}

	public void Order(String address, String phone) {
		OrderSender sender = new SmsSender(address, phone, itemList);
		sender.Send();
	}
}
