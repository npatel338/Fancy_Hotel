package com.db.cs4400.fancyhotel.view;

import java.awt.CardLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

import org.springframework.beans.factory.annotation.Autowired;

import com.db.cs4400.fancyhotel.util.Util;

public class FancyHotelFrame extends JFrame {
	@Autowired
	private JPanel mainPanel;
	@Autowired
	private CardLayout cl;
	@Autowired
	private LoginPanel loginPanel;
	@Autowired
	private ProvideReviewPanel provideReviewPanel;
	@Autowired
	private UserRegistrationPanel userRegistrationPanel;
	@Autowired
	private FunctionalityPanel functionalityPanel;
	@Autowired
	private SearchRoomPanel searchRoomPanel;
	@Autowired
	private ReservationPanel reservationPanel;
	@Autowired
	private ConfirmationPanel confirmationPanel;
	@Autowired
	private UpdateReservationPanel updateReservationPanel;

	/**
	 * Create the frame.
	 */
	public FancyHotelFrame() {
	}
	
	private void loadPanels() {
		mainPanel.add(Util.LOGIN_PANEL, loginPanel);
		mainPanel.add(Util.USER_REGISTRATION_PANEL, userRegistrationPanel);
		mainPanel.add(Util.FUNTIONALITY_PANEL, functionalityPanel);
		mainPanel.add(Util.PROVIDE_REVIEW_PANEL, provideReviewPanel);
		mainPanel.add(Util.SEARCH_ROOM_PANEL, searchRoomPanel);
		mainPanel.add(Util.RESERVATION_PANEL, reservationPanel);
		mainPanel.add(Util.CONFIRMATION_PANEL, confirmationPanel);
		mainPanel.add(Util.UPDATE_RESERVATION_PANEL, updateReservationPanel);
	}
	
	public void loadUI() {
		mainPanel.setLayout(cl);
		loadPanels();
		cl.show(mainPanel, "loginPanel");
		add(mainPanel);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		pack();
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		this.setVisible(true);	
	}
}
