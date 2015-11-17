package com.db.cs4400.fancyhotel.view;

import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.SwingUtilities;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.db.cs4400.fancyhotel.controller.ReservationController;
import com.db.cs4400.fancyhotel.exception.FancyHotelException;
import com.db.cs4400.fancyhotel.model.Customer;
import com.db.cs4400.fancyhotel.model.Reservation;
import com.db.cs4400.fancyhotel.model.Room;
import com.db.cs4400.fancyhotel.util.Util;

public class UpdateReservationPanel extends JPanel {
	
	@Autowired
	private JPanel mainPanel;
	@Autowired
	private CardLayout cl;
	@Autowired
	private Customer user;
	@Autowired
	private ReservationController reservationController;
	private JTextField reservationIdField;
	private JPanel topJPanel;
	private JPanel middlePanel;
	private JPanel bottomPanel;
	private JTextField currentStartDatetextField;
	private JTextField currentEndDatetextField;
	private JTextField newStartDatetextField;
	private JTextField newEndDatetextField;
	private List<Reservation> reservations;

	/**
	 * Create the panel.
	 */
	public UpdateReservationPanel() {
		setLayout(null);
		
		topJPanel = new JPanel();
		topJPanel.setLayout(null);
		topJPanel.setBounds(0, 0, 900, 100);
		JLabel lblReservationid = new JLabel("ReservationId");
		lblReservationid.setBounds(30, 38, 107, 16);
		
		reservationIdField = new JTextField();
		reservationIdField.setBounds(149, 32, 134, 28);
		reservationIdField.setColumns(10);
		
		JButton searchButton = new JButton("Search");
		searchButton.setBounds(307, 33, 97, 29);
		searchButton.addActionListener(new ButtonListener());
		topJPanel.add(lblReservationid);
		topJPanel.add(reservationIdField);
		topJPanel.add(searchButton);	
		add(topJPanel);
	}
	
	private class ButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			if (middlePanel != null) {
				middlePanel.setVisible(false);
			}
			
			JFrame parentFrame = (JFrame) SwingUtilities
					.getWindowAncestor((JButton) e.getSource());
			if (StringUtils.isEmpty(reservationIdField.getText())) {
				JOptionPane.showMessageDialog(parentFrame,
						"ReservationId can't be empty.", "error",
						JOptionPane.ERROR_MESSAGE);
				return;
			}
			
			Integer reservationId = null;
			try {
				reservationId = Integer.parseInt(reservationIdField.getText());
			} catch (NumberFormatException nf) {
				JOptionPane.showMessageDialog(parentFrame,
						"Enter valid reservationId", "error",
						JOptionPane.ERROR_MESSAGE);
				return;
			}
			
			try {
				reservations = reservationController.findReservation(reservationId);
				if (CollectionUtils.isEmpty(reservations)) {
					JOptionPane.showMessageDialog(parentFrame,
							"Reservation not found", "Message",
							JOptionPane.INFORMATION_MESSAGE);
					return;
				}
				
				middlePanel = new JPanel();
				middlePanel.setBounds(0, 112, 900, 179);
				add(middlePanel);
				middlePanel.setLayout(null);
				
				JLabel startDateLabel = new JLabel("Current Start Date");
				startDateLabel.setBounds(20, 20, 134, 16);
				middlePanel.add(startDateLabel);
				
				Reservation reservation = reservations.iterator().next();
				currentStartDatetextField = new JTextField();
				currentStartDatetextField.setBounds(166, 14, 134, 28);
				currentStartDatetextField.setText(Util.parseDate(reservation.getStartDate()));
				currentStartDatetextField.setEditable(false);
				middlePanel.add(currentStartDatetextField);
				currentStartDatetextField.setColumns(10);
				
				JLabel endDateNewLabel = new JLabel("Current End Date");
				endDateNewLabel.setBounds(361, 20, 117, 16);
				middlePanel.add(endDateNewLabel);
				
				currentEndDatetextField = new JTextField();
				currentEndDatetextField.setBounds(508, 14, 134, 28);
				currentEndDatetextField.setText(Util.parseDate(reservation.getEndDate()));
				currentEndDatetextField.setEditable(false);
				middlePanel.add(currentEndDatetextField);
				currentEndDatetextField.setColumns(10);
				
				JLabel newStartDateLabel = new JLabel("New Start Date");
				newStartDateLabel.setBounds(20, 73, 117, 16);
				middlePanel.add(newStartDateLabel);
				
				newStartDatetextField = new JTextField();
				newStartDatetextField.setBounds(166, 67, 134, 28);
				middlePanel.add(newStartDatetextField);
				newStartDatetextField.setColumns(10);
				
				JLabel newEndDateNewLabel = new JLabel("New End Date");
				newEndDateNewLabel.setBounds(361, 73, 106, 16);
				middlePanel.add(newEndDateNewLabel);
				
				newEndDatetextField = new JTextField();
				newEndDatetextField.setBounds(508, 67, 134, 28);
				middlePanel.add(newEndDatetextField);
				newEndDatetextField.setColumns(10);
				
				JButton searchAvailablitybtn = new JButton("Search Availibility");
				searchAvailablitybtn.addActionListener(new SearchAvailibilityActionListener());
				searchAvailablitybtn.setBounds(234, 124, 180, 29);
				middlePanel.add(searchAvailablitybtn);
				add(middlePanel);
				middlePanel.setVisible(true);
				revalidate();
				repaint();
			} catch (FancyHotelException e1) {
				JOptionPane.showMessageDialog(parentFrame,
						"Enter valid reservationId", "error",
						JOptionPane.ERROR_MESSAGE);
				return;
			}		
		}	
	}
	
	private class SearchAvailibilityActionListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			JFrame parentFrame = (JFrame) SwingUtilities
					.getWindowAncestor((JButton) e.getSource());
			if (StringUtils.isEmpty(newStartDatetextField.getText()) 
					|| StringUtils.isEmpty(newEndDatetextField.getText())) {
				JOptionPane.showMessageDialog(parentFrame,
						"start date or end date can't be empty", "error",
						JOptionPane.ERROR_MESSAGE);
				return;
			}
			
			String startDate = newStartDatetextField.getText();
			String endDate = newEndDatetextField.getText();
			Date start = null;
			Date end = null;
			DateFormat format = new SimpleDateFormat("MM/dd/yyyy");
			try {
				start = format.parse(startDate);
				end = format.parse(endDate);
			} catch (ParseException e1) {
				JOptionPane.showMessageDialog(parentFrame,
						"Start Date or End Date was not entered correctly.", "error",
						JOptionPane.ERROR_MESSAGE);
				return;
			}
			
			if (start == null || end == null || start.after(end) || start.equals(end)) {
				JOptionPane.showMessageDialog(parentFrame,
						"Start Date can't be after or equal to End Date", "error",
						JOptionPane.ERROR_MESSAGE);
				return;
			}
			
			List<Room> rooms = new ArrayList<>();
			Reservation reservation = reservations.iterator().next();
			try {
				rooms = 
						reservationController.searchRooms(reservation.getLocation(), reservation.getStartDate(), reservation.getEndDate());				
			} catch (FancyHotelException e1) {
				JOptionPane.showMessageDialog(parentFrame,
						"Start Date or End Date can not be empty.", "error",
						JOptionPane.ERROR_MESSAGE);
				return;
			}
			
			if (CollectionUtils.isEmpty(rooms)) {
				JOptionPane.showMessageDialog(parentFrame,
						"Rooms are not available for the selected dates", "Message",
						JOptionPane.INFORMATION_MESSAGE);
				return;
			}
			
			SelectedRoomTableModel newRoomsTableModel = new SelectedRoomTableModel();
			for (Room room : rooms) {
			}
			bottomPanel = new JPanel();
			bottomPanel.setLayout(null);
			bottomPanel.setBounds(0, 0, 900, 100);
		}
	}
}
