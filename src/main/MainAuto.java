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

import model.ExperimentSchedulingArtificialData;
import model.TestCase;


public class MainAuto {

	private static ExperimentSchedulingArtificialData experimentSchedulingArtificialData;

	private static double time;
	
	private static String[] priorityG = {"0", "25", "50", "75", "100"};
	private static String[] probabilityG = {"0", "50", "100"};
	private static String[] consequenceG = {"0", "25", "50", "75", "100"};
	private static String[] riskG = {"0", "50", "100"};
	
	private static String[] epriorityG = {"0", "25", "50", "75", "100"};
	private static String[] eprobabilityG = {"0", "50", "100"};
	private static String[] econsequenceG = {"0", "25", "50", "75", "100"};
	private static String[] eriskG = {"0", "50", "100"};
	
	
	private static BufferedWriter file;
	private static File fileName;
	
	public static void main(String[] args) {
		//IndustrialData();
		IndustrialData();
	}
	// This is with priority, probability and consequence
//	/*
	
	public static void IndustrialData(){
		time = 24;
		experimentSchedulingArtificialData = new ExperimentSchedulingArtificialData();
		effectiveness(time, experimentSchedulingArtificialData);
		efficiency(time, experimentSchedulingArtificialData);		
	}
	
	private static void effectiveness(double time, ExperimentSchedulingArtificialData experimentSchedulingArtificialData){
		double priorityWeight=0, probabilityWeight=0, consequenceWeight=0, riskWeight=0, timeWeight = 0.2;
		int counter = 1;
		//first for the 75 problems
		//first is priority
		for(int i = 0; i < 5;i++){			
			//next is probability
			priorityWeight = Double.parseDouble(priorityG[i]);
			for (int j=0;j<3;j++){
				//last is consequence
				probabilityWeight = Double.parseDouble(probabilityG[j]);
				for (int k=0;k<5;k++){
					
					consequenceWeight = Double.parseDouble(consequenceG[k]);
					double total = priorityWeight + probabilityWeight + consequenceWeight;
					if (total == 0){
						priorityWeight = 0.267;
						probabilityWeight = 0.267;
						consequenceWeight = 0.267;			
					}else{
						
						priorityWeight = priorityWeight/total * 0.8;
						probabilityWeight = probabilityWeight/total * 0.8;
						consequenceWeight = consequenceWeight/total * 0.8;
					}
					calculate (experimentSchedulingArtificialData, time, timeWeight, priorityWeight, probabilityWeight, consequenceWeight, 0, 0, 0, 0, 0,counter,1);	
					if (total == 0){
						priorityWeight = 0;
						probabilityWeight = 0;
						consequenceWeight = 0;			
					}
					
					counter++;
				}					
			}			
		}
		//for the 15 problems with priority and risk
		//first is priority
		for(int i = 0; i < 5;i++){	
			priorityWeight = Double.parseDouble(priorityG[i]);
			//next is risk
			for (int j=0;j<3;j++){
				riskWeight = Double.parseDouble(riskG[j]);
				double total = priorityWeight + riskWeight;
				if (total == 0){
					priorityWeight = 0.5;
					riskWeight = 0.5;							
				}else{
					priorityWeight = priorityWeight/total * 0.8;
					riskWeight = riskWeight/total * 0.8;
				}
				calculate (experimentSchedulingArtificialData, time, timeWeight, priorityWeight, 0, 0, riskWeight, 0, 0, 0, 0,counter,1);
				if (total == 0){
					priorityWeight = 0;
					probabilityWeight = 0;
					consequenceWeight = 0;			
				}
				counter++;
			}
		}
	}
	
	//Below for efficiency
	private static void efficiency(double time, ExperimentSchedulingArtificialData experimentSchedulingArtificialData){
		double timeWeight = 0.2;
		double epriorityWeight=0, eprobabilityWeight=0, econsequenceWeight=0, eriskWeight=0;

		int counter = 1;
		//first for the 75 problems
		//first is priority
		for(int i = 0; i < 5;i++){			
			//next is probability
			epriorityWeight = Double.parseDouble(epriorityG[i]);
			for (int j=0;j<3;j++){
				//last is consequence
				eprobabilityWeight = Double.parseDouble(eprobabilityG[j]);
				for (int k=0;k<5;k++){
					experimentSchedulingArtificialData = new ExperimentSchedulingArtificialData();
					econsequenceWeight = Double.parseDouble(consequenceG[k]);
					double total = epriorityWeight + eprobabilityWeight + econsequenceWeight;
					if (total == 0){
						epriorityWeight = 0.267;
						eprobabilityWeight = 0.267;
						econsequenceWeight = 0.267;	
					}else{
						epriorityWeight = epriorityWeight/total * 0.8;
						eprobabilityWeight = eprobabilityWeight/total * 0.8;
						econsequenceWeight = econsequenceWeight/total * 0.8;
					}
					calculate (experimentSchedulingArtificialData, time, timeWeight, epriorityWeight, eprobabilityWeight, 
							econsequenceWeight, 0, 0, 0, 0, 0,counter,2);	
					if (total == 0){
						epriorityWeight = 0;
						eprobabilityWeight = 0;
						econsequenceWeight = 0;			
					}
					counter++;
				}					
			}			
		}
		//for the 15 problems with priority and risk
		//first is priority
		for(int i = 0; i < 5;i++){	
			epriorityWeight = Double.parseDouble(priorityG[i]);
			//next is risk
			for (int j=0;j<3;j++){
				eriskWeight = Double.parseDouble(riskG[j]);
				double total = epriorityWeight + eriskWeight;
				if (total == 0){
					epriorityWeight = 0.4;
					eriskWeight = 0.4;						
				}else{
					epriorityWeight = epriorityWeight/total * 0.8;
					eriskWeight = eriskWeight/total * 0.8;
				}
				calculate (experimentSchedulingArtificialData, time, timeWeight, epriorityWeight, 0, 0, eriskWeight, 0, 0, 0, 0,counter,2);
				if (total == 0){
					epriorityWeight = 0;
					eriskWeight = 0;		
				}
				counter++;
			}
		}
	}
	
	
	public static void calculate (ExperimentSchedulingArtificialData experimentSchedulingArtificialData, double time, 
			double timeWeight, double priorityWeight, double probabilityWeight, 
			double consequenceWeight, double riskWeight, double epriorityWeight, double eprobabilityWeight, 
			double econsequenceWeight, double eriskWeight, int counter, int problem){
		
		experimentSchedulingArtificialData.setMaxTime(time);
		//experimentScheduling.setContext(context);
		experimentSchedulingArtificialData.setTimeWeight(timeWeight);
		experimentSchedulingArtificialData.setPriorityWeight(priorityWeight);
		experimentSchedulingArtificialData.setProbabilityWeight(probabilityWeight);
		experimentSchedulingArtificialData.setConsequence(consequenceWeight);
		experimentSchedulingArtificialData.setRiskWeight(riskWeight);
		experimentSchedulingArtificialData.setEpriorityWeight(epriorityWeight);
		experimentSchedulingArtificialData.setEprobabilityWeight(eprobabilityWeight);
		experimentSchedulingArtificialData.setEconsequenceWeight(econsequenceWeight);
		experimentSchedulingArtificialData.setEriskWeight(eriskWeight);
		
		
		experimentSchedulingArtificialData.run(counter, problem);
	}
	
	
	/*
	public static void ArtificialData(){
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
	*/

}
