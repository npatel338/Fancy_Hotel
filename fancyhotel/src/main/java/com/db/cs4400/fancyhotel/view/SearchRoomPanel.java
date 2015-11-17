package com.db.cs4400.fancyhotel.view;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.annotation.PostConstruct;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.db.cs4400.fancyhotel.controller.ReservationController;
import com.db.cs4400.fancyhotel.controller.UserController;
import com.db.cs4400.fancyhotel.exception.FancyHotelException;
import com.db.cs4400.fancyhotel.model.Customer;
import com.db.cs4400.fancyhotel.model.Room;
import com.db.cs4400.fancyhotel.util.Util;

import javax.swing.JComboBox;

public class SearchRoomPanel extends JPanel {

	@Autowired
	private ReservationController reservationController;
	@Autowired
	private JPanel mainPanel;
	@Autowired
	private CardLayout cl;
	@Autowired
	private Customer user;
	@Autowired
	private ReservationPanel reservationPanel;
	private JTextField txtStartDate;
	private JTextField txtEndDate;
	private JComboBox<String> locationComboBox;

	/**
	 * Create the panel.
	 */
	public SearchRoomPanel() {
		buildLoginPanel();
	}

	private void buildLoginPanel() {
		setLayout(null);

		JLabel lblLocation = new JLabel("Location");
		lblLocation.setBounds(66, 69, 79, 16);
		add(lblLocation);

		JLabel lblSdate = new JLabel("Start Date");
		lblSdate.setBounds(66, 124, 79, 16);
		add(lblSdate);

		txtStartDate = new JTextField();
		txtStartDate.setBounds(160, 118, 106, 28);
		add(txtStartDate);

		JButton btnSearch = new JButton("Search");
		btnSearch.addActionListener(new ButtonClickListener());
		btnSearch.setBounds(431, 209, 79, 29);
		add(btnSearch);

		locationComboBox = new JComboBox<String>();
		locationComboBox.setBounds(160, 65, 106, 27);
		add(locationComboBox);

		JLabel lblEndDate = new JLabel("End Date");
		lblEndDate.setBounds(339, 124, 61, 16);
		add(lblEndDate);

		txtEndDate = new JTextField();
		txtEndDate.setBounds(433, 118, 106, 28);
		add(txtEndDate);

		JLabel lblmmddyyyy = new JLabel("(MM/DD/YYYY)");
		lblmmddyyyy.setBounds(170, 158, 96, 16);
		add(lblmmddyyyy);

		JLabel label = new JLabel("(MM/DD/YYYY)");
		label.setBounds(431, 158, 96, 16);
		add(label);
	}

	@PostConstruct
	public void init() {
		List<String> locations = reservationController.getLocations();
		if (CollectionUtils.isNotEmpty(locations)) {
			for (String location : locations) {
				locationComboBox.addItem(location);
			}
		}
	}

	private class ButtonClickListener implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			String startDate = txtStartDate.getText();
			String endDate = txtEndDate.getText();
			JFrame parentFrame = (JFrame) SwingUtilities
					.getWindowAncestor((JButton) e.getSource());
			if (StringUtils.isEmpty(startDate) || StringUtils.isEmpty(endDate)) {
				JOptionPane.showMessageDialog(parentFrame,
						"Start Date or End Date can not be empty.", "error",
						JOptionPane.ERROR_MESSAGE);
				return;
			}

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
						"Start Date can't be after oe equal to End Date", "error",
						JOptionPane.ERROR_MESSAGE);
				return;
			}
			
			try {
				List<Room> rooms = 
						reservationController.searchRooms(((String)locationComboBox.getSelectedItem()), start, end);				
				reservationPanel.setRooms(rooms);
				reservationPanel.setStartDate(startDate);
				reservationPanel.setEndDate(endDate);
				cl.show(mainPanel, Util.RESERVATION_PANEL);
			} catch (FancyHotelException e1) {
				JOptionPane.showMessageDialog(parentFrame,
						"Start Date or End Date can not be empty.", "error",
						JOptionPane.ERROR_MESSAGE);
				return;
			}
		}
	}
}
