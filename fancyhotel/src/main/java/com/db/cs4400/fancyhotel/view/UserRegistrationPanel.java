package com.db.cs4400.fancyhotel.view;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.db.cs4400.fancyhotel.controller.UserController;
import com.db.cs4400.fancyhotel.exception.FancyHotelException;
import com.db.cs4400.fancyhotel.exception.FancyHotelUserExistException;
import com.db.cs4400.fancyhotel.util.Util;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Arrays;

public class UserRegistrationPanel extends JPanel {

	@Autowired
	private JPanel mainPanel;
	@Autowired
	private CardLayout cl;
	@Autowired
	private UserController userControler;
	private JTextField userNameField;
	private JPasswordField passwordField;
	private JPasswordField confirmPasswordField;
	private JTextField emailField;
	
	/**
	 * Create the panel.
	 */
	public UserRegistrationPanel() {
		setLayout(null);
		
		JLabel lblUsername = new JLabel("Username");
		lblUsername.setBounds(104, 83, 81, 16);
		add(lblUsername);
		
		JLabel lblNewLabel = new JLabel("Password");
		lblNewLabel.setBounds(104, 112, 81, 16);
		add(lblNewLabel);
		
		JLabel lblConfirmPassword = new JLabel("Confirm Password");
		lblConfirmPassword.setBounds(104, 142, 125, 16);
		add(lblConfirmPassword);
		
		JLabel lblEmail = new JLabel("Email");
		lblEmail.setBounds(104, 170, 61, 16);
		add(lblEmail);
		
		userNameField = new JTextField();
		userNameField.setBounds(281, 77, 134, 28);
		add(userNameField);
		userNameField.setColumns(10);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(281, 106, 134, 28);
		add(passwordField);
		
		confirmPasswordField = new JPasswordField();
		confirmPasswordField.setBounds(281, 136, 134, 28);
		add(confirmPasswordField);
		
		emailField = new JTextField();
		emailField.setBounds(281, 167, 134, 28);
		add(emailField);
		emailField.setColumns(10);
		
		JButton btnNewButton = new JButton("Submit");
		btnNewButton.addActionListener(new ButtonListener());
		btnNewButton.setBounds(202, 236, 101, 29);
		add(btnNewButton);
		
		JLabel loginPage = new JLabel("Back to Login Page");
		loginPage.addMouseListener(new MouseClickListener());
		loginPage.setForeground(Color.BLUE);
		loginPage.setFont(new Font("Lucida Grande", Font.BOLD, 13));
		loginPage.setBounds(435, 28, 134, 16);
		add(loginPage);
	}
	
	private class MouseClickListener extends MouseAdapter {
		@Override
		public void mouseClicked(MouseEvent e) {
			cl.show(mainPanel, Util.LOGIN_PANEL);
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
	
	private class ButtonListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			String userName = userNameField.getText();
			String password = passwordField.getPassword().toString();
			String cPassword = confirmPasswordField.getPassword().toString();
			String email = emailField.getText();
			JFrame parentFrame = (JFrame) SwingUtilities.getWindowAncestor((JButton)e.getSource());
			if (StringUtils.isEmpty(email) || StringUtils.isEmpty(password) 
					|| StringUtils.isEmpty(userName) || StringUtils.isEmpty(cPassword)) {
				JOptionPane.showMessageDialog(parentFrame,
						"All fields are required.", "error",
						JOptionPane.ERROR_MESSAGE);
				return;
			}
			
			if (!(Arrays.equals(passwordField.getPassword(), confirmPasswordField.getPassword()))) {
				JOptionPane.showMessageDialog(parentFrame,
						"Password and confirm password should match.", "error",
						JOptionPane.ERROR_MESSAGE);
				return;
			}
			
			try {
				userControler.isUserExist(userName, email);
			} catch (FancyHotelUserExistException e1) {
				JOptionPane.showMessageDialog(parentFrame,
						"Username or email exist.", "error",
						JOptionPane.ERROR_MESSAGE);
				return;
			}
			
			try {
				userControler.saveCustomer(userName, new String (passwordField.getPassword()), email);
			} catch (FancyHotelException e1) {
				JOptionPane.showMessageDialog(parentFrame,
						"All fields are required.", "error",
						JOptionPane.ERROR_MESSAGE);
				return;
			}
			
			JOptionPane.showMessageDialog(parentFrame,
					"User created", "Message",
					JOptionPane.INFORMATION_MESSAGE);
			cl.show(mainPanel, Util.LOGIN_PANEL);
		}	
	}
}
