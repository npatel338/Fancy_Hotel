package com.db.cs4400.fancyhotel.view;

import java.awt.CardLayout;

import javax.swing.JPanel;

import org.springframework.beans.factory.annotation.Autowired;

import com.db.cs4400.fancyhotel.model.Customer;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class PaymentInfoPanel extends JPanel {
	
	@Autowired
	private JPanel mainPanel;
	@Autowired
	private CardLayout cl;
	@Autowired
	private Customer user;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;

	/**
	 * Create the panel.
	 */
	public PaymentInfoPanel() {
		setLayout(null);
		
		JLabel lblAddCard = new JLabel("Add Card");
		lblAddCard.setBounds(72, 55, 83, 16);
		add(lblAddCard);
		
		JLabel lblNameOnCard = new JLabel("Name on Card");
		lblNameOnCard.setBounds(42, 96, 100, 16);
		add(lblNameOnCard);
		
		JLabel lblCardNumber = new JLabel("Card Number");
		lblCardNumber.setBounds(42, 138, 100, 16);
		add(lblCardNumber);
		
		JLabel lblNewLabel = new JLabel("Expiration Date");
		lblNewLabel.setBounds(42, 184, 113, 16);
		add(lblNewLabel);
		
		JLabel lblCvv = new JLabel("CVV");
		lblCvv.setBounds(42, 233, 61, 16);
		add(lblCvv);
		
		textField = new JTextField();
		textField.setBounds(170, 90, 134, 28);
		add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(170, 227, 134, 28);
		add(textField_1);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(170, 132, 134, 28);
		add(textField_2);
		
		textField_3 = new JTextField();
		textField_3.setColumns(10);
		textField_3.setBounds(170, 178, 134, 28);
		add(textField_3);
		
		JSeparator separator = new JSeparator();
		separator.setOrientation(SwingConstants.VERTICAL);
		separator.setBounds(352, 6, -6, 357);
		add(separator);
		
		JLabel lblDeleteCard = new JLabel("Delete Card");
		lblDeleteCard.setBounds(431, 55, 93, 16);
		add(lblDeleteCard);
		
		JLabel lblCardNumber_1 = new JLabel("Card Number");
		lblCardNumber_1.setBounds(395, 96, 93, 16);
		add(lblCardNumber_1);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(517, 92, 83, 27);
		add(comboBox);
		
		JLabel lblNewLabel_1 = new JLabel("Card Number");
		lblNewLabel_1.setBounds(42, 329, 100, 16);
		add(lblNewLabel_1);
		
		JComboBox comboBox_1 = new JComboBox();
		comboBox_1.setBounds(192, 325, 52, 27);
		add(comboBox_1);
		
		JButton btnNewButton = new JButton("Use This Card");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnNewButton.setBounds(95, 384, 117, 29);
		add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Save");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnNewButton_1.setBounds(237, 278, 83, 29);
		add(btnNewButton_1);
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnDelete.setBounds(459, 278, 100, 29);
		add(btnDelete);

	}
}
