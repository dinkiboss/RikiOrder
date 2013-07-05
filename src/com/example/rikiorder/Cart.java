package com.example.rikiorder;

import java.util.ArrayList;
import java.util.List;

public class Cart {
	public static final Cart Instance = new Cart();
	
	List<Pizza> pizzaList = new ArrayList<Pizza>();
	
	private Cart()
	{
		
	}
	
	public void AddPizza(Pizza pizza)
	{
		pizzaList.add(pizza);
	}
	
	public void RemovePizza(Pizza pizza)
	{
		pizzaList.remove(pizza);
	}
	
	public void Order()
	{
		
	}
}
