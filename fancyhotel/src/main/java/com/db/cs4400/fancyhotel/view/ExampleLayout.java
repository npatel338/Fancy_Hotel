package com.db.cs4400.fancyhotel.view;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ExampleLayout extends JPanel {

	/**
	 * Create the panel.
	 */
	public ExampleLayout() {
		setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 450, 171);
		add(panel);
		panel.setLayout(null);
		
		JButton btnNewButton = new JButton("Check Details");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnNewButton.setBounds(316, 124, 117, 29);
		panel.add(btnNewButton);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(0, 209, 450, 91);
		add(panel_1);

	}
}
