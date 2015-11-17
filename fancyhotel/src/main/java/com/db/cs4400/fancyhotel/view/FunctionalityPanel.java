package com.db.cs4400.fancyhotel.view;

import javax.swing.JPanel;
import javax.swing.JLabel;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.db.cs4400.fancyhotel.model.Customer;
import com.db.cs4400.fancyhotel.util.Util;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class FunctionalityPanel extends JPanel {

	/**
	 * Create the panel.
	 */
	@Autowired
	private Customer user;
	@Autowired
	private JPanel mainPanel;
	@Autowired
	private CardLayout cl;
	JLabel lblWelcome;
	
	public FunctionalityPanel() {
		setBounds(100, 100, 638, 369);
		setLayout(null);
		
		JLabel makeReservationLabel = new JLabel("Make a new reservation");
		addLinkToLabel(makeReservationLabel);
		makeReservationLabel.addMouseListener(new MouseClickListener());
		makeReservationLabel.setBounds(228, 62, 223, 21);
		add(makeReservationLabel);
		
		JLabel updateResevationLabel = new JLabel("Update your reservation");
		addLinkToLabel(updateResevationLabel);
		updateResevationLabel.addMouseListener(new MouseClickListener());
		updateResevationLabel.setBounds(228, 106, 202, 16);
		add(updateResevationLabel);
		
		JLabel cancelReservationLabel = new JLabel("Cancel reservation");
		addLinkToLabel(cancelReservationLabel);
		cancelReservationLabel.addMouseListener(new MouseClickListener());
		cancelReservationLabel.setBounds(228, 148, 150, 16);
		add(cancelReservationLabel);
		
		JLabel provideFeedbackLabel = new JLabel("Provide feedback");
		addLinkToLabel(provideFeedbackLabel);
		provideFeedbackLabel.addMouseListener(new MouseClickListener());
		provideFeedbackLabel.setBounds(228, 192, 150, 16);
		add(provideFeedbackLabel);
		
		JLabel viewFeedbackLabel = new JLabel("View feedback");
		addLinkToLabel(viewFeedbackLabel);
		viewFeedbackLabel.addMouseListener(new MouseClickListener());
		viewFeedbackLabel.setBounds(228, 239, 150, 16);
		add(viewFeedbackLabel);
		lblWelcome = new JLabel("Welcome");
		lblWelcome.setBounds(41, 35, 103, 16);
		add(lblWelcome);
	}
	
	private void addLinkToLabel(final JLabel label) {
		label.setForeground(Color.BLUE);
		label.setFont(new Font("Lucida Grande", Font.BOLD, 13));
	}
	
	@Override
	public void setVisible(boolean aFlag) {
		if (StringUtils.isNoneEmpty(user.getUserName())) {
			lblWelcome.setText(lblWelcome.getText() + " " + user.getUserName() + ",");
		}

		lblWelcome.setSize(lblWelcome.getPreferredSize());
		super.setVisible(aFlag);
	}
	
	private class MouseClickListener extends MouseAdapter {
		@Override
		public void mouseClicked(MouseEvent e) {
			if (("Provide feedback").equals(((JLabel)e.getComponent()).getText())) {
				cl.show(mainPanel, Util.PROVIDE_REVIEW_PANEL);
				return;
			}
			
			if (("Make a new reservation").equals(((JLabel)e.getComponent()).getText())) {
				cl.show(mainPanel, Util.SEARCH_ROOM_PANEL);
				return;
			}
			
			if (("Update your reservation").equals(((JLabel)e.getComponent()).getText())) {
				cl.show(mainPanel, Util.UPDATE_RESERVATION_PANEL);
				return;
			}
		}
		
		@Override
		public void mouseEntered(MouseEvent e) {
			 e.getComponent().setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		}
		
		@Override
		public void mouseExited(MouseEvent e) {
			e.getComponent().setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		}
	}
}
