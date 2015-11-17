package com.db.cs4400.fancyhotel.model;

import java.math.BigDecimal;

public class Room {
	
	private Integer roomNumber;
	private String location;
	private Integer NumOfPeopleAllowed;
	private String category;
	private BigDecimal costDay;
	private BigDecimal costExtraBed;
	
	public Integer getRoomNumber() {
		return roomNumber;
	}

	public void setRoomNumber(Integer roomNumber) {
		this.roomNumber = roomNumber;
	}

	public String getLocation() {
		return location;
	}
	
	public void setLocation(String location) {
		this.location = location;
	}
	
	public Integer getNumOfPeopleAllowed() {
		return NumOfPeopleAllowed;
	}
	
	public void setNumOfPeopleAllowed(Integer numOfPeopleAllowed) {
		NumOfPeopleAllowed = numOfPeopleAllowed;
	}
	
	public String getCategory() {
		return category;
	}
	
	public void setCategory(String category) {
		this.category = category;
	}
	
	public BigDecimal getCostDay() {
		return costDay;
	}
	
	public void setCostDay(BigDecimal costDay) {
		this.costDay = costDay;
	}
	
	public BigDecimal getCostExtraBed() {
		return costExtraBed;
	}
	
	public void setCostExtraBed(BigDecimal costExtraBed) {
		this.costExtraBed = costExtraBed;
	}
}
