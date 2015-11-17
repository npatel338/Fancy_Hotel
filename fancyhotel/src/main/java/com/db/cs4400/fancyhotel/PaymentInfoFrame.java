package com.db.cs4400.fancyhotel;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;

import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JComboBox;

public class PaymentInfoFrame extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PaymentInfoFrame frame = new PaymentInfoFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public PaymentInfoFrame() {
		setFont(new Font("Dialog", Font.BOLD, 16));
		setTitle("New User Registration");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 638, 369);
		getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Name on Card");
		lblNewLabel.setBounds(26, 73, 100, 16);
		getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Add Card");
		lblNewLabel_1.setFont(new Font("Lucida Grande", Font.BOLD, 16));
		lblNewLabel_1.setBounds(50, 29, 100, 16);
		getContentPane().add(lblNewLabel_1);
		
		textField = new JTextField();
		textField.setBounds(138, 67, 134, 28);
		getContentPane().add(textField);
		textField.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Card Number");
		lblNewLabel_2.setBounds(26, 116, 91, 16);
		getContentPane().add(lblNewLabel_2);
		
		textField_1 = new JTextField();
		textField_1.setBounds(138, 110, 134, 28);
		getContentPane().add(textField_1);
		textField_1.setColumns(10);
		
		JLabel lblNewLabel_3 = new JLabel("Expiration Date");
		lblNewLabel_3.setBounds(26, 163, 100, 16);
		getContentPane().add(lblNewLabel_3);
		
		textField_2 = new JTextField();
		textField_2.setBounds(138, 157, 134, 28);
		getContentPane().add(textField_2);
		textField_2.setColumns(10);
		
		JLabel lblNewLabel_4 = new JLabel("CVV");
		lblNewLabel_4.setBounds(26, 213, 61, 16);
		getContentPane().add(lblNewLabel_4);
		
		textField_3 = new JTextField();
		textField_3.setBounds(138, 207, 134, 28);
		getContentPane().add(textField_3);
		textField_3.setColumns(10);
		
		JButton btnNewButton = new JButton("Save");
		btnNewButton.setBounds(82, 269, 82, 29);
		getContentPane().add(btnNewButton);
		
		JLabel lblDeleteCard = new JLabel("Delete Card");
		lblDeleteCard.setFont(new Font("Lucida Grande", Font.BOLD, 16));
		lblDeleteCard.setBounds(378, 30, 114, 16);
		getContentPane().add(lblDeleteCard);
		
		JLabel lblNewLabel_5 = new JLabel("Card Number");
		lblNewLabel_5.setBounds(340, 73, 91, 16);
		getContentPane().add(lblNewLabel_5);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(488, 69, 52, 27);
		getContentPane().add(comboBox);
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.setBounds(431, 269, 82, 29);
		getContentPane().add(btnDelete);
		
	}
}
