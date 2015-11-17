package com.db.cs4400.fancyhotel.controller;

import java.util.Date;
import java.util.List;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.db.cs4400.fancyhotel.dao.ReservationDao;
import com.db.cs4400.fancyhotel.exception.FancyHotelException;
import com.db.cs4400.fancyhotel.model.Reservation;
import com.db.cs4400.fancyhotel.model.Room;

@Component
public class ReservationController {
	
	@Autowired
	private ReservationDao reservationDao;
	
	public List<String> getLocations() {
		return reservationDao.getLocations();
	}
	
	public List<Room> searchRooms (final String location,
			final Date startDate, final Date endDate) throws FancyHotelException {
		if (StringUtils.isEmpty(location) || startDate == null || endDate == null) {
			throw new FancyHotelException("Required parametes can't be empty");
		}
		
		return reservationDao.searchRooms(location, startDate, endDate);
	}
	
	public int saveReservation(final List<Reservation> reservations, final Integer cardNumber) throws FancyHotelException {
		if (CollectionUtils.isEmpty(reservations) || cardNumber == null) {
			throw new FancyHotelException("Reservations can't be empty");
		}
		
		Integer reservationNumber = reservationDao.saveReservation(reservations, cardNumber);
		if (reservationNumber == null) {
			throw new FancyHotelException("Reservations can't be empty");
		}
		
		return reservationNumber.intValue();
	}
	
	public List<Reservation> findReservation(Integer reservationId) throws FancyHotelException {
		if (reservationId == null) {
			throw new FancyHotelException("ReservationId can not be null");
		}
		
		return reservationDao.searchReservation(reservationId);
	}
}
