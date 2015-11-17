package com.db.cs4400.fancyhotel.model;

import java.math.BigDecimal;
import java.util.Date;

public class Reservation {
	
	private Long reservationNumber;
	private Integer roomNumber;
	private String location;
	private Date startDate;
	private Date endDate;
	private BigDecimal totalCost;
	private float costOfRoom;
	private float costOfExtraBed;
	private boolean status;
	private Integer cardNumber;
	private String userName;
	private boolean isExtraBed;
	
	public Long getReservationNumber() {
		return reservationNumber;
	}
	
	public void setReservationNumber(Long reservationNumber) {
		this.reservationNumber = reservationNumber;
	}
	
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
	
	public Date getStartDate() {
		return startDate;
	}
	
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	
	public Date getEndDate() {
		return endDate;
	}
	
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	
	public BigDecimal getTotalCost() {
		return totalCost;
	}
	
	public void setTotalCost(BigDecimal totalCost) {
		this.totalCost = totalCost;
	}
	
	public boolean isStatus() {
		return status;
	}
	
	public void setStatus(boolean status) {
		this.status = status;
	}
	
	public Integer getCardNumber() {
		return cardNumber;
	}
	
	public void setCardNumber(Integer cardNumber) {
		this.cardNumber = cardNumber;
	}
	
	public String getUserName() {
		return userName;
	}
	
	public void setUserName(String userName) {
		this.userName = userName;
	}

	public boolean isExtraBed() {
		return isExtraBed;
	}

	public void setExtraBed(boolean isExtraBed) {
		this.isExtraBed = isExtraBed;
	}

	public float getCostOfRoom() {
		return costOfRoom;
	}

	public void setCostOfRoom(float costOfRoom) {
		this.costOfRoom = costOfRoom;
	}

	public float getCostOfExtraBed() {
		return costOfExtraBed;
	}

	public void setCostOfExtraBed(float costOfExtraBed) {
		this.costOfExtraBed = costOfExtraBed;
	}
}
