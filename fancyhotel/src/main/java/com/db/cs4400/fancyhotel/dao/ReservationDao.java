package com.db.cs4400.fancyhotel.dao;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.db.cs4400.fancyhotel.model.Customer;
import com.db.cs4400.fancyhotel.model.Reservation;
import com.db.cs4400.fancyhotel.model.Room;
import com.db.cs4400.fancyhotel.util.Util;

@Component
public class ReservationDao {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	@Value("${room.location.query}")
	private String roomLocationQuery;
	@Value("${room.search.reservation.query}")
	private String roomSearchReservationQuery;
	@Value("${room.save.reservation.query}")
	private String roomSaveReservationQuery;
	@Value("${room.save.reservation.room.query}")
	private String roomSaveReservationExtraBedQuery;
	@Value("${room.get.max.reservation.number}")
	private String roomGetMaxReservationNumberQuery;
	@Value("${room.search.reservationbyid.query}")
	private String roomSearchReservationByIdQuery;
	
	
	public List<String> getLocations() {
		List<String> locations = new ArrayList<String>();
		List<Map<String, Object>> rows = jdbcTemplate.queryForList(
				String.format(roomLocationQuery));
		if (CollectionUtils.isNotEmpty(rows)) {
			for (Map<String, Object> row : rows) {
				locations.add((String)row.get("Loc"));
			}
		}

		return locations;
	}
	
	public List<Room> searchRooms(final String location, final Date startDate, final Date endDate) {
		if (StringUtils.isEmpty(location) || startDate == null || endDate == null) {
			return null;
		}
		
		java.sql.Date sqlStartDate = new java.sql.Date(startDate.getTime());
		java.sql.Date sqlEndDate = new java.sql.Date(endDate.getTime());
		List<Room> rooms = new ArrayList<>();
		String sqlQuery = String.format(roomSearchReservationQuery, 
				sqlStartDate, sqlStartDate, sqlEndDate, sqlEndDate, location, location);
		List<Map<String, Object>> rows = jdbcTemplate.queryForList(sqlQuery);
		if (CollectionUtils.isNotEmpty(rows)) {
			for (Map<String, Object> row : rows) {
				if (MapUtils.isNotEmpty(row)) {
					Room room = new Room();
					room.setRoomNumber((Integer) row.get("Room_num"));
					room.setCategory((String) row.get("Category"));
					room.setCostDay((BigDecimal) row.get("Cost_day"));
					room.setCostExtraBed((BigDecimal) row.get("Cost_extra_bed"));
					room.setNumOfPeopleAllowed((Integer) row.get("Num_People"));
					room.setLocation((String) row.get("Loc"));
					rooms.add(room);
				}
			}
		}
		
		return rooms;
	}
	
	public List<Room> searchRoomsByRooms(final String location, 
			final Date startDate, final Date endDate, final List<Integer> roomNums)  {
		if (StringUtils.isEmpty(location) || startDate == null || endDate == null) {
			return null;
		}
		
		java.sql.Date sqlStartDate = new java.sql.Date(startDate.getTime());
		java.sql.Date sqlEndDate = new java.sql.Date(endDate.getTime());
		List<Room> rooms = new ArrayList<>();
		String sqlQuery = String.format(roomSearchReservationQuery, 
				sqlStartDate, sqlStartDate, sqlEndDate, sqlEndDate, location, location);
		List<Map<String, Object>> rows = jdbcTemplate.queryForList(sqlQuery);
		if (CollectionUtils.isNotEmpty(rows)) {
			for (Map<String, Object> row : rows) {
				if (MapUtils.isNotEmpty(row)) {
					Room room = new Room();
					room.setRoomNumber((Integer) row.get("Room_num"));
					room.setCategory((String) row.get("Category"));
					room.setCostDay((BigDecimal) row.get("Cost_day"));
					room.setCostExtraBed((BigDecimal) row.get("Cost_extra_bed"));
					room.setNumOfPeopleAllowed((Integer) row.get("Num_People"));
					room.setLocation((String) row.get("Loc"));
					rooms.add(room);
				}
			}
		}
		
		return rooms;
	}
	
	public Integer saveReservation(final List<Reservation> reservations, final Integer cardNumber) {
		if (CollectionUtils.isEmpty(reservations) || cardNumber == null) {
			return null;
		}
		
		int reservationNumber = getMaxReservationNumber() + 1;
		for (Reservation reservation : reservations) {
			java.sql.Date sqlStartDate = new java.sql.Date(reservation.getStartDate().getTime());
			java.sql.Date sqlEndDate = new java.sql.Date(reservation.getEndDate().getTime());
			float totalCostPerRoom = 0;
			long noOfDaysStay = Util.getDifferenceDays(reservation.getStartDate(), reservation.getEndDate());
			totalCostPerRoom += noOfDaysStay * reservation.getCostOfRoom();
			if (reservation.isExtraBed()) {
				totalCostPerRoom += noOfDaysStay * reservation.getCostOfExtraBed();
			}
			
			this.jdbcTemplate.execute(String.format(
					roomSaveReservationQuery, reservationNumber, reservation.getRoomNumber(), reservation.getLocation(),
					sqlStartDate, sqlEndDate, totalCostPerRoom, 1, cardNumber, reservation.getUserName()));
			int extraBed = reservation.isExtraBed() ? 1 : 0;
			this.jdbcTemplate.execute(String.format(
					roomSaveReservationExtraBedQuery, reservationNumber, reservation.getRoomNumber(), reservation.getLocation(),
					extraBed));	
		}
		
		return new Integer(reservationNumber);
	}
	
	public List<Reservation> searchReservation(final Integer reservationId) {
		if (reservationId == null) {
			return null;
		}

		List<Reservation> reservations = new ArrayList<Reservation>();
		List<Map<String, Object>> rows = this.jdbcTemplate.queryForList(String
				.format(roomSearchReservationByIdQuery, reservationId));
		if (CollectionUtils.isNotEmpty(rows)) {
			for (Map<String, Object> row : rows) {

				java.sql.Date sqlStartDate = (java.sql.Date) row.get("St_dt");
				java.sql.Date sqlEndDate = (java.sql.Date) row.get("End_dt");
				Reservation reservation = new Reservation();
				reservation.setLocation((String) row.get("Loc"));
				reservation.setStartDate(new Date(sqlStartDate.getTime()));
				reservation.setEndDate(new Date(sqlEndDate.getTime()));
				reservations.add(reservation);
			}
		}

		return reservations;
	}

	public int getMaxReservationNumber() {
		int max = 0;
		List<Map<String, Object>> rows = jdbcTemplate.queryForList(
				String.format(roomGetMaxReservationNumberQuery));
		if (CollectionUtils.isNotEmpty(rows)) {
			Map<String, Object> row = rows.iterator().next();
			if (MapUtils.isNotEmpty(row) && row.get("Res_num") != null) {
				max = (Integer) row.get("Res_num");
			}
			
		}
		
		return max;
	}
	
	private long generateReservationNumber() {
		Date now = new Date();
		return now.getTime();
	}
}
