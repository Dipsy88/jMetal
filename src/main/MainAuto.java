package main;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.io.BufferedWriter;
import java.io.File;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.TableColumn;

import model.ExperimentScheduling;
import model.ExperimentSchedulingArtificialData;
import model.TestCase;


public class MainAuto {

	private static ExperimentSchedulingArtificialData experimentSchedulingArtificialData;

	private static double time;
	
	private static String[] priorityG = {"0", "25", "50", "75", "100"};
	private static String[] probabilityG = {"0", "50", "100"};
	private static String[] consequenceG = {"0", "25", "50", "75", "100"};
	
	private static String[] epriorityG = {"0", "25", "50", "75", "100"};
	private static String[] eprobabilityG = {"0", "50", "100"};
	private static String[] econsequenceG = {"0", "25", "50", "75", "100"};
	
	private static ExperimentScheduling experimentScheduling;
	
	
	private static BufferedWriter file;
	private static File fileName;
	
	public static void main(String[] args) {
		//IndustrialData();
		ArtificialData();
		//IndustrialData2();
		//ArtificialData2();
		
	}
	
	// This is with priority, probability and consequence
//	/*
	


	public static void ArtificialData(){
		int problem = 1;
		
			int counter = 1;
			time = 10;
			//double priority = 0.8, probability= 0.0, consequence= 0.0, timeWeight = 0.2;
			double epriority = 0.8, eprobability= 0.0, econsequence= 0.0, timeWeight = 0.2;
			
			
			experimentSchedulingArtificialData = new ExperimentSchedulingArtificialData();
					
					
			ArrayList<TestCase> tempCaseList = new ArrayList<TestCase>();
						
					
			experimentSchedulingArtificialData.setMaxTime(time);
						//experimentScheduling.setContext(context);
			experimentSchedulingArtificialData.setTimeWeight(timeWeight);			
//			experimentSchedulingArtificialData.setPriority(priority);
//			experimentSchedulingArtificialData.setProbability(probability);
//			experimentSchedulingArtificialData.setConsequence(consequence);
			experimentSchedulingArtificialData.setEpriority(epriority);
			experimentSchedulingArtificialData.setEprobability(eprobability);
			experimentSchedulingArtificialData.setEconsequence(econsequence);
			
			experimentSchedulingArtificialData.setProblem(problem);			
			experimentSchedulingArtificialData.setProblem(problem);	
						
			experimentSchedulingArtificialData.setTotal(1);
					
			tempCaseList=experimentSchedulingArtificialData.run(counter);
			counter++;					
									
		
	}
	
	
	public static void ArtificialData2(){
		int counter =0;
		//time = 186;
		time = 10;
		double epriority, eprobability, econsequence;
	
		//first is priority
		for(int i=0; i<5;i++){			
			//next is probability
			epriority = Double.parseDouble(epriorityG[i]);
			for (int j=0;j<3;j++){
				//last is consequence
				eprobability = Double.parseDouble(eprobabilityG[j]);
				for (int k=0;k<5;k++){
					experimentSchedulingArtificialData = new ExperimentSchedulingArtificialData();
					econsequence = Double.parseDouble(econsequenceG[k]);
					double total = epriority + eprobability + econsequence;
				
					ArrayList<TestCase> tempCaseList = new ArrayList<TestCase>();
					
					
					experimentSchedulingArtificialData.setMaxTime(time);
					//experimentScheduling.setContext(context);
					experimentSchedulingArtificialData.setEpriority(epriority);
					experimentSchedulingArtificialData.setEprobability(eprobability);
					experimentSchedulingArtificialData.setEconsequence(econsequence);
					
					experimentSchedulingArtificialData.setTotal(total);
					
					tempCaseList=experimentSchedulingArtificialData.run(counter);
					counter++;					
				}					
			}			
		}		
	}

}
