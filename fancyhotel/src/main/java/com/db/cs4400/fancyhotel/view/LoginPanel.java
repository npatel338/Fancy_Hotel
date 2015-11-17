package com.db.cs4400.fancyhotel.view;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

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
import com.db.cs4400.fancyhotel.model.Customer;
import com.db.cs4400.fancyhotel.util.Util;

public class LoginPanel extends JPanel {
	
	@Autowired
	private UserController userControler;
	@Autowired
	private JPanel mainPanel;
	@Autowired
	private CardLayout cl;
	@Autowired
	private Customer user;
	private JTextField txtUsername;
	private JPasswordField txtPassword;

	/**
	 * Create the panel.
	 */
	public LoginPanel() {
		buildLoginPanel();
	}

	private void buildLoginPanel() {
		setLayout(null);
		
		
		JLabel lblUsername = new JLabel("Username");
		lblUsername.setBounds(108, 79, 62, 16);
		add(lblUsername);
		
		txtUsername = new JTextField();
		txtUsername.setBounds(210, 73, 134, 28);
		add(txtUsername);
		txtUsername.setColumns(10);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setBounds(108, 124, 59, 16);
		add(lblPassword);
		
		txtPassword = new JPasswordField();
		txtPassword.setBounds(210, 118, 134, 28);
		add(txtPassword);
		
		JButton btnLogin = new JButton("Login");
		btnLogin.addActionListener(new ButtonClickListener());
		btnLogin.setBounds(158, 178, 79, 29);
		add(btnLogin);
		
		JLabel lblNewUser = new JLabel("New User? Create Account");
		lblNewUser.addMouseListener(new MouseClickListener());
		lblNewUser.setForeground(Color.BLUE);
		lblNewUser.setFont(new Font("Lucida Grande", Font.BOLD, 13));
		lblNewUser.setBounds(388, 250, 177, 16);
		add(lblNewUser);
	}
	
	private class ButtonClickListener implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			JFrame parentFrame = (JFrame) SwingUtilities.getWindowAncestor((JButton)e.getSource());
			String userName = txtUsername.getText();
			String password = String.valueOf(txtPassword.getPassword());
			if (StringUtils.isEmpty(userName)) {
				JOptionPane.showMessageDialog(parentFrame,
						"Username can not be empty", "error",
						JOptionPane.ERROR_MESSAGE);
				return;
			}
			
			if (StringUtils.isEmpty(password)) {
				JOptionPane.showMessageDialog(parentFrame,
						"password can not be empty", "error",
						JOptionPane.ERROR_MESSAGE);
				return;
			}
			
			Customer customer;
			try {
				customer = userControler.getCustomer(userName, password);
			} catch (FancyHotelException e1) {
				JOptionPane.showMessageDialog(parentFrame,
						"Username or password can not be empty", "error",
						JOptionPane.ERROR_MESSAGE);
				return;
			}
			
			if (customer == null) {
				JOptionPane.showMessageDialog(parentFrame,
						"User doesn't exist", "error",
						JOptionPane.ERROR_MESSAGE);
				return;
			}
			
			user.setUserName(customer.getUserName());
			cl.show(mainPanel, Util.FUNTIONALITY_PANEL);
		}
	}
	
	private class MouseClickListener extends MouseAdapter {
		@Override
		public void mouseClicked(MouseEvent e) {
			cl.show(mainPanel, Util.USER_REGISTRATION_PANEL);
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
