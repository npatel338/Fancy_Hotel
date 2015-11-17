package com.db.cs4400.fancyhotel.view;

import java.util.Vector;

import javax.swing.table.DefaultTableModel;

public class SelectedRoomTableModel extends DefaultTableModel {

	public SelectedRoomTableModel() {
		super(new String[] { "Room Number", "Room Category", "#person allowed",
				"cost per day", "cost of extra bed per day", "extra bed"}, 0);
	}

	@Override
	public Class<?> getColumnClass(int columnIndex) {
		Class clazz = String.class;
		switch (columnIndex) {
		case 0:
			clazz = Integer.class;
			break;
		case 2:
			clazz = Integer.class;
			break;
		case 3:
			clazz = Float.class;
			break;
		case 4:
			clazz = Float.class;
			break;
		case 5:
			clazz = Boolean.class;
			break;
		}

		return clazz;
	}
	
	@Override
	public boolean isCellEditable(int row, int column) {
		return false;
	}
}
