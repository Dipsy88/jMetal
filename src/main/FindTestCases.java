package main;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.TableColumn;



public class FindTestCases {

	
	
	private static double time;
	private static BufferedWriter file;
	private static BufferedWriter file2;
	private static String[] priorityG = {"0", "25", "50", "75", "100"};
	private static String[] probabilityG = {"0", "50", "100"};
	private static String[] consequenceG = {"0", "25", "50", "75", "100"};
	
//	private static String[] epriorityG = {"0", "25", "50", "75", "100"};
//	private static String[] eprobabilityG = {"0", "50", "100"};
//	private static String[] econsequenceG = {"0", "25", "50", "75", "100"};
	

	private static File fileName;
	private static File fileName2;
	
	public static void main(String[] args) throws Exception {
		readFile();
	}
	
	public static void readFile() throws Exception{		
		createFile();
		FileWriter fw = new FileWriter(fileName.getAbsoluteFile());
		file = new BufferedWriter(fw);
		

		int i=0;
		try {
		    BufferedReader in = new BufferedReader(new FileReader("C:\\Users\\Dipesh.DIPSYPC\\workspace\\jMetal2\\VAR"));
		  
		    String str;
		    
		    while ((str = in.readLine()) != null){
//		    	if (i==1000)
//		    		break;
		    	String line = str;
		    	String[] details = line.split(" ");
		    	
		    	int count =0;
		    	while (count < 100){
		    		
		    		if (Double.parseDouble(details[count]) > 0.5){
		    			//file2.write("Min: " + minAlgo + "\t" + "\t" + "\t" + "\t" + "\t" + "\t"); 	
		    			//file.write("Avg: " + avgAlgo + "\t" + "\t" + "\t" + "\t" + "\t" + "\t"); 	
		    			file.write(count+ "\t"); 	
		    			file.flush();
		    		}
		    		
		    		count++;
		    	
		    	}
		    	file.write("\n");
		    	file.flush();
		    }
		    in.close();
		} catch (IOException e) {
		}
		
	}
	
	public static void createFile() throws Exception{
		fileName = new File("C:\\Personal\\practice\\comparison\\max38.txt");
		

		// if file does not exists, then create it
		if (!fileName.exists()) {
			fileName.createNewFile();
		}
	}
	
	public static void createFile2() throws Exception{
		fileName2= new File("C:\\Personal\\practice\\files\\maxAlgo38.txt");
		

		// if file does not exists, then create it
		if (!fileName2.exists()) {
			fileName2.createNewFile();
		}
	}
	
	//Algorithm name
	public static String algoName(int i){
		switch (i){
		case 0:
			return "AVM";
		case 1:
			return "GA";
		case 2:
			return "(1+1)EA";
		case 3:
			return "RS";
		case 4:
			return "HC";
		case 5:
			return "Greedy";
		}
		return "error";
		
	}
	

}
