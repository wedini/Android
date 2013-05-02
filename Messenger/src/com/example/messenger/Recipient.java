package com.example.messenger;

public class Recipient {
	
	private String phoneNumber;
	
	Recipient(String phoneNumber) {
		
		this.phoneNumber = phoneNumber;	
	}
	
	public String getPhoneNumber() {
		
		return this.phoneNumber;
	}
	
	public void setPhoneNumber(String phoneNumber) {
		
		this.phoneNumber = phoneNumber;
	}

}
