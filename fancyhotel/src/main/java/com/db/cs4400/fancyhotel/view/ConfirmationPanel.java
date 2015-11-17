package com.db.cs4400.fancyhotel.view;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class ConfirmationPanel extends JPanel {
	private JTextField confirmationIdField;
	private Integer confirmationId;
	

	/**
	 * Create the panel.
	 */
	public ConfirmationPanel() {
		setLayout(null);
		
		JLabel lblYourConfirmationNumber = new JLabel("Your Confirmation Number");
		lblYourConfirmationNumber.setBounds(20, 70, 188, 16);
		add(lblYourConfirmationNumber);
		
		confirmationIdField = new JTextField();
		confirmationIdField.setBounds(232, 64, 168, 28);
		add(confirmationIdField);
		confirmationIdField.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Please save this reservationId for all future communication.");
		lblNewLabel.setBounds(20, 147, 397, 16);
		add(lblNewLabel);
	}
	
	@Override
	public void setVisible(boolean aBoolean) {
		if (confirmationId != null) {
			confirmationIdField.setText(confirmationId.toString());
		}
		
		super.setVisible(aBoolean);
	}


	public Integer getConfirmationId() {
		return confirmationId;
	}

	public void setConfirmationId(Integer confirmationId) {
		this.confirmationId = confirmationId;
	}
}
