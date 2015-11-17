package com.db.cs4400.fancyhotel.view;

import java.awt.CardLayout;
import java.awt.Component;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Vector;
import java.util.concurrent.TimeUnit;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import org.apache.commons.collections4.CollectionUtils;
import org.joda.time.Days;
import org.springframework.beans.factory.annotation.Autowired;

import com.db.cs4400.fancyhotel.controller.PaymentInfoController;
import com.db.cs4400.fancyhotel.controller.ReservationController;
import com.db.cs4400.fancyhotel.exception.FancyHotelException;
import com.db.cs4400.fancyhotel.model.Customer;
import com.db.cs4400.fancyhotel.model.Reservation;
import com.db.cs4400.fancyhotel.model.Room;
import com.db.cs4400.fancyhotel.util.Util;

import javax.swing.JTable;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ReservationPanel extends JPanel {

	/**
	 * Create the panel.
	 */
	@Autowired
	private JPanel mainPanel;
	@Autowired
	private CardLayout cl;
	@Autowired
	private PaymentInfoController paymentInfoController;
	@Autowired
	private Customer user;
	@Autowired
	private ReservationController reservationController;
	@Autowired
	private ConfirmationPanel confirmationPanel;
	private List<Room> rooms;
	private String startDate;
	private String endDate;
	private JTable table;
	private JScrollPane scrollPane;
	private SelectRoomTableModel selectRoomTableModel;
	private JPanel northPanel;
	private JPanel southPanel;

	public ReservationPanel() {
		setLayout(null);
	}

	@Override
	public void setVisible(boolean aBoolean) {
		if (aBoolean && CollectionUtils.isNotEmpty(rooms)) {
			selectRoomTableModel = new SelectRoomTableModel();
			for (Room room : rooms) {
				Object[] row = new Object[] { room.getRoomNumber(),
						room.getCategory(), room.getNumOfPeopleAllowed(),
						room.getCostDay().floatValue(),
						room.getCostExtraBed().floatValue(), false, false };
				selectRoomTableModel.addRow(row);
			}

			table = new JTable(selectRoomTableModel);
			scrollPane = new JScrollPane(table);
			scrollPane.setBounds(0, 0, 900, 250);
			JButton checkDetailButton = new JButton("Check Details");
			checkDetailButton.setBounds(725, 300, 117, 29);
			checkDetailButton.addActionListener(new ButtonListener());
			northPanel = new JPanel();
			northPanel.setLayout(null);
			northPanel.add(scrollPane);
			northPanel.add(checkDetailButton);
			northPanel.setBounds(0, 0, 900, 350);
			add(northPanel);
		}

		super.setVisible(aBoolean);
	}

	private class ButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			if (southPanel != null) {
				southPanel.setVisible(false);
			}
			
			if (CollectionUtils
					.isNotEmpty(selectRoomTableModel.getDataVector())) {
				SelectedRoomTableModel selectedRoomTableModel = new SelectedRoomTableModel();
				Vector<Vector<Object>> records = selectRoomTableModel
						.getDataVector();
				Date start = null;
				Date end = null;
				DateFormat format = new SimpleDateFormat("MM/dd/yyyy");
				try {
					start = format.parse(startDate);
					end = format.parse(endDate);
				} catch (ParseException e1) {
					return;
				}
				
				final List<Reservation> reservations = new ArrayList<>();
				long noOfDaysStay = Util.getDifferenceDays(start, end);
				float totalCost = 0;
				for (Vector<Object> record : records) {
					if ((boolean) record.elementAt(5)) {
						Object[] row = new Object[] { record.elementAt(0),
								record.elementAt(1), record.elementAt(2),
								record.elementAt(3), record.elementAt(4),
								record.elementAt(6) };
						selectedRoomTableModel.addRow(row);
						Reservation reservation = new Reservation();
						reservation.setStartDate(start);
						reservation.setEndDate(end);
						reservation.setRoomNumber((Integer)record.elementAt(0));
						reservation.setCostOfExtraBed((float)record.elementAt(4));
						reservation.setCostOfRoom((float)record.elementAt(3));
						reservation.setExtraBed((boolean)record.elementAt(6));
						reservation.setUserName(user.getUserName());
						reservation.setLocation(rooms.get(0).getLocation());
						reservations.add(reservation);
						totalCost += noOfDaysStay * (float)record.elementAt(3);
						if ((boolean)record.elementAt(6)) {
							totalCost += noOfDaysStay * (float)record.elementAt(4);
						}
					}
				}

				if (CollectionUtils.isNotEmpty(selectedRoomTableModel
						.getDataVector())) {
					JTable selectedRoomstable = new JTable(selectedRoomTableModel);
					JScrollPane selectedRoomsScrollPane = new JScrollPane(
							selectedRoomstable);
					selectedRoomsScrollPane.setBounds(0, 0, 900, 150);
					
					JLabel startDateLabel = new JLabel("Start Date");
					startDateLabel.setBounds(10, 175, 80, 15);
					
					JTextField startDateField = new JTextField();
					startDateField.setBounds(100, 175, 110, 20);
					startDateField.setText(startDate);
					startDateField.setEditable(false);
					startDateField.setColumns(10);
					
					JLabel endDateLabel = new JLabel("End Date");
					endDateLabel.setBounds(250, 175, 110, 15);
					
					JTextField endDateField = new JTextField();
					endDateField.setBounds(340, 175, 110, 20);
					endDateField.setText(endDate);
					endDateField.setEditable(false);
					endDateField.setColumns(10);
					
					JLabel totalCostLabel = new JLabel("Total Cost");
					totalCostLabel.setBounds(10, 215, 80, 15);
					
					JTextField totalCostField = new JTextField();
					totalCostField.setBounds(100, 215, 90, 20);
					totalCostField.setText(Float.toString(totalCost));
					totalCostField.setEditable(false);
					totalCostField.setColumns(10);
					
					JLabel useCardLabel = new JLabel("Use Card");
					useCardLabel.setBounds(10, 255, 80, 15);
					
					final JComboBox<Integer> cardNumberComboBox = new JComboBox<>();
					populatePaymentInfo(cardNumberComboBox);
					cardNumberComboBox.setBounds(100, 255, 110, 30);
					
					JLabel addCardLabel = new JLabel("Add Card");
					addCardLabel.setBounds(220, 255, 80, 15);
					
					JButton submit = new JButton("Submit");
					submit.setBounds(725, 300, 117, 29);
					submit.addActionListener(new ActionListener() {

						@Override
						public void actionPerformed(ActionEvent e) {
							JFrame parentFrame = (JFrame) SwingUtilities
									.getWindowAncestor((JButton) e.getSource());
							try {
								int reservationNumber = reservationController.saveReservation(
										reservations, (Integer) cardNumberComboBox.getSelectedItem());
								confirmationPanel.setConfirmationId(reservationNumber);
								cl.show(mainPanel, Util.CONFIRMATION_PANEL);
							} catch (FancyHotelException e1) {
								JOptionPane.showMessageDialog(parentFrame,
										"Reservation can't be empty", "error",
										JOptionPane.ERROR_MESSAGE);
								return;
							}
						}
						
					});
					
					southPanel = new JPanel();
					southPanel.setLayout(null);
					southPanel.add(startDateLabel);
					southPanel.add(startDateField);
					southPanel.add(endDateLabel);
					southPanel.add(endDateField);
					southPanel.add(totalCostLabel);
					southPanel.add(totalCostField);
					southPanel.add(useCardLabel);
					southPanel.add(cardNumberComboBox);
					southPanel.add(addCardLabel);
					southPanel.add(selectedRoomsScrollPane);
					southPanel.add(submit);
					southPanel.setBounds(0, 400, 900, 500);
					southPanel.setVisible(true);
					add(southPanel);
					revalidate();
					repaint();
				}
			}
		}
	}
	
	private void populatePaymentInfo(final JComboBox<Integer> comboBox) {
		List<Integer> cardNumbers = paymentInfoController.getCardNumbers(user.getUserName());
		for (Integer cardNumber : cardNumbers) {
			Integer displayCardNumber = cardNumber;
//			if (cardNumber.toString().length() > 3) {
//				displayCardNumber = Integer.parseInt(cardNumber.toString().substring(
//						cardNumber.toString().length()-3, cardNumber.toString().length()));
//			}
			
			comboBox.addItem(displayCardNumber);
		}
	}
	
	public List<Room> getRooms() {
		return rooms;
	}

	public void setRooms(List<Room> rooms) {
		this.rooms = rooms;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
}
