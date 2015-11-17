package com.db.cs4400.fancyhotel.view;

import java.util.Vector;

import javax.swing.table.DefaultTableModel;

public class SelectRoomTableModel extends DefaultTableModel {

	public SelectRoomTableModel() {
		super(new String[] { "Room Number", "Room Category", "#person allowed",
				"cost per day", "cost of extra bed per day", "select room", "extra bed"}, 0);
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
		case 6:
			clazz = Boolean.class;
			break;
		}

		return clazz;
	}

	@Override
	public boolean isCellEditable(int row, int column) {
		return column == 5 || column == 6;
	}

	@Override
	public void setValueAt(Object aValue, int row, int column) {
		if (aValue instanceof Boolean && (column == 5 || column == 6)) {
			Vector<Object> rowData = (Vector<Object>) (getDataVector().get(row));
			rowData.set(column, (boolean) aValue);
			fireTableCellUpdated(row, column);
		}
	}

}
