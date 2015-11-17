package com.db.cs4400.fancyhotel.model;

import java.util.Date;

public class PaymentInfo {
	
	private Integer cardNumber;
	private String name;
	private Integer cvv;
	private Date expDate;
	private String Username;
	
	public Integer getCardNumber() {
		return cardNumber;
	}
	
	public void setCardNumber(Integer cardNumber) {
		this.cardNumber = cardNumber;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public Integer getCvv() {
		return cvv;
	}
	
	public void setCvv(Integer cvv) {
		this.cvv = cvv;
	}
	
	public Date getExpDate() {
		return expDate;
	}
	
	public void setExpDate(Date expDate) {
		this.expDate = expDate;
	}
	
	public String getUsername() {
		return Username;
	}
	
	public void setUsername(String username) {
		Username = username;
	}
}
