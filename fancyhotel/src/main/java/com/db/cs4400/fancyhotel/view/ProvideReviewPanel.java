package com.db.cs4400.fancyhotel.view;

import java.awt.CardLayout;

import javax.annotation.PostConstruct;
import javax.swing.JPanel;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.db.cs4400.fancyhotel.controller.ReservationController;
import com.db.cs4400.fancyhotel.controller.ReviewController;
import com.db.cs4400.fancyhotel.model.Customer;
import com.db.cs4400.fancyhotel.util.Util;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JButton;
import javax.swing.SwingUtilities;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.List;

public class ProvideReviewPanel extends JPanel {

	@Autowired
	private JPanel mainPanel;
	@Autowired
	private CardLayout cl;
	@Autowired
	private ReviewController reviewController;
	@Autowired
	private ReservationController reservationController;
	@Autowired
	private Customer user;
	private JComboBox<String> comboBoxLocation;
	private JComboBox<String> comboBoxRating;
	private JTextArea commentArea;

	/**
	 * Create the panel.
	 */
	public ProvideReviewPanel() {
		setLayout(null);
		setBounds(100, 100, 638, 369);

		JLabel lblLocation = new JLabel("Hotel Location");
		lblLocation.setBounds(113, 71, 128, 16);
		add(lblLocation);

		comboBoxLocation = new JComboBox<String>();
		comboBoxLocation.setBounds(297, 67, 136, 27);
		add(comboBoxLocation);

		JLabel lblRating = new JLabel("Rating");
		lblRating.setBounds(113, 127, 61, 16);
		add(lblRating);

		JLabel lblComment = new JLabel("Comment");
		lblComment.setBounds(113, 181, 61, 16);
		add(lblComment);

		commentArea = new JTextArea();
		commentArea.setBounds(297, 168, 190, 78);
		add(commentArea);

		comboBoxRating = new JComboBox<String>();
		comboBoxRating.setBounds(300, 123, 133, 27);
		add(comboBoxRating);
		comboBoxRating.addItem("Excellent");
		comboBoxRating.addItem("Good");
		comboBoxRating.addItem("Bad");
		comboBoxRating.addItem("Very Bad");
		comboBoxRating.addItem("Neutral");

		JButton btnNewButton = new JButton("Submit");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String userName = user.getUserName();
				String location = (String) comboBoxLocation.getSelectedItem();
				String rating = (String) comboBoxRating.getSelectedItem();
				String comment = commentArea.getText();
				JFrame parentFrame = (JFrame) SwingUtilities
						.getWindowAncestor((JButton) e.getSource());
				if (StringUtils.isEmpty(userName)
						|| StringUtils.isEmpty(comment)
						|| StringUtils.isEmpty(rating)
						|| StringUtils.isEmpty(location)) {
					JOptionPane.showMessageDialog(parentFrame,
							"All fields are required.", "error",
							JOptionPane.ERROR_MESSAGE);
					return;
				}

				reviewController
						.postReview(userName, comment, rating, location);
				JOptionPane.showMessageDialog(parentFrame,
						"Your review has been submited", "Message",
						JOptionPane.INFORMATION_MESSAGE);
				cl.show(mainPanel, Util.FUNTIONALITY_PANEL);
			}
		});
		btnNewButton.setBounds(459, 280, 117, 29);
		add(btnNewButton);
	}
	
	@PostConstruct
	public void init() {
		List<String> locations = reservationController.getLocations();
		if (CollectionUtils.isNotEmpty(locations)) {
			for (String location : locations) {
				comboBoxLocation.addItem(location);
			}
		}
	}
	
//	@Override
//	public void setVisible(boolean aFlag) {
//		List<String> locations = reviewController.getLocations();
//		comboBoxLocation.removeAllItems();
//		if (CollectionUtils.isNotEmpty(locations)) {
//			for (String location : locations) {
//				comboBoxLocation.addItem(location);
//			}
//		}
//		
//		super.setVisible(aFlag);
//	}
}
