package com.db.cs4400.fancyhotel;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JComboBox;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JButton;

public class FindRoomFrame extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FindRoomFrame frame = new FindRoomFrame();
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
	public FindRoomFrame() {
		setFont(new Font("Dialog", Font.BOLD, 16));
		setTitle("New User Registration");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 638, 369);
		getContentPane().setLayout(null);
		
		JLabel lblLocation = new JLabel("Location");
		lblLocation.setBounds(63, 73, 61, 16);
		getContentPane().add(lblLocation);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(191, 69, 52, 27);
		getContentPane().add(comboBox);
		
		JLabel lblNewLabel = new JLabel("Start Date");
		lblNewLabel.setBounds(63, 159, 88, 16);
		getContentPane().add(lblNewLabel);
		
		textField = new JTextField();
		textField.setBounds(148, 153, 134, 28);
		getContentPane().add(textField);
		textField.setColumns(10);
		
		JLabel lblEndDate = new JLabel("End Date");
		lblEndDate.setBounds(337, 159, 61, 16);
		getContentPane().add(lblEndDate);
		
		textField_1 = new JTextField();
		textField_1.setBounds(417, 153, 134, 28);
		getContentPane().add(textField_1);
		textField_1.setColumns(10);
		
		JButton btnSearch = new JButton("Search");
		btnSearch.setBounds(453, 254, 98, 29);
		getContentPane().add(btnSearch);
	}
}
