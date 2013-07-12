package com.example.rikiorder;

import java.util.List;
import android.telephony.gsm.SmsManager;

public class SmsSender implements OrderSender{

	String mRequest = "";
	
	public SmsSender(String address, String phone, List<CartItem> items)
	{
		mRequest += address + "\n";
		mRequest += phone + "\n";
		
		for (CartItem cartItem : items) {
			mRequest += cartItem.GetName() + " size:";
			mRequest += cartItem.GetEntryIndex() + "\n";
		}
		
	}
	
	@Override
	public void Send() {
		SmsManager.getDefault().sendTextMessage("+79108922109", null, mRequest, null, null);
	}

}
