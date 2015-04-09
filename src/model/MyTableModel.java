package model;

import java.util.Vector;

import javax.swing.table.DefaultTableModel;

public class MyTableModel extends DefaultTableModel{
	
	
	
	public MyTableModel(Vector data, Vector<String> columnNames) {
		super(data, columnNames);
		// TODO Auto-generated constructor stub
	}
	public Object getValueAt(int row,int column){
		Object object = new Object();
		
			object = super.getValueAt(row, column);
			
		
			if (object== null)
				object = new String("");
			
		//System.out.println("I go out from error");
		return object;
			
	}
	

}
