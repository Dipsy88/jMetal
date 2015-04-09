package main;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Random;

import model.Consequence;
import model.Context;
import model.Effect;
import model.Model;
import model.ModelConstraint;
import model.Priority;
import model.Probability;
import model.RCUType;
import model.Risk;
import model.TestCase;


public class ReadTestCasesArtificialData {


	public TestCase testCase[] = new TestCase[100];
	private ArrayList<TestCase> testCaseList = new ArrayList<>() ;
//	public void main(String[] args) throws Exception {
//		readFile();
//	}
	
	
	public void readFile(){		
		try {
		    BufferedReader in = new BufferedReader(new FileReader("files/case100.txt"));
		    String str;
		    int i=0;
		    while ((str = in.readLine()) != null){
//		    	if (i==1000)
//		    		break;
		    	String line = str;
		    	String[] details = line.split("\t");
		    	
		    	testCase[i] = new TestCase();
		    	Priority priority = new Priority();
				Probability probability = new Probability();
				Consequence consequence = new Consequence();
				Risk risk = new Risk();
				Context context = new Context();
				RCUType rcuType = new RCUType();
				ModelConstraint modelConstraint = new ModelConstraint();
				Effect effect = new Effect();
				Model model = new Model();
		    	
		    	
		    	testCase[i].setId(Integer.parseInt(details[0]));
		    	testCase[i].setCaseName(details[1]);
		    	
		    	priority.setName(details[2]);
		    	testCase[i].setPriority(priority);
		    	
		    	probability.setName(details[3]);
		    	testCase[i].setProbability(probability);
		    	
		    	consequence.setName(details[4]);
		    	testCase[i].setConsequence(consequence);
		    	
		    	testCase[i].setTimeExecution(Double.parseDouble(details[5]));
		    	
		    	context.setName(details[6]);
		    	testCase[i].setContext(context);
		    	
		    	rcuType.setName(details[7]);
		    	testCase[i].setRcuType(rcuType);
		    	
		    	modelConstraint.setName(details[8]);
		    	testCase[i].setModelConstraint(modelConstraint);
		    	
		    	effect.setName(details[9]);
		    	testCase[i].setEffect(effect);
		    	
		    	model.setName(details[10]);
		    	testCase[i].setModel(model);

		    	risk.setName(details[11]);
		    	testCase[i].setRisk(risk);
		    	
		    	
		    	i++;
		    }
		    in.close();
		} catch (IOException e) {
		}
		
	}
	
	public ArrayList<TestCase> getTestCaseContents(int caseNumber, double priority, double probability, double consequence){
		for (int i=0;i<caseNumber;i++){		
			double priorityValue, probabilityValue, consequenceValue;
			
			if (testCase[i].getPriority().getName().equalsIgnoreCase("higher")||testCase[i].getPriority().getName().equalsIgnoreCase("high")||testCase[i].getPriority().getName().equalsIgnoreCase("medium")
					||testCase[i].getPriority().getName().equalsIgnoreCase("low")||testCase[i].getPriority().getName().equalsIgnoreCase("lower"))
				priorityValue= convertPriority(testCase[i].getPriority());
			else 
				priorityValue=0;
			
			if (testCase[i].getProbability().getName().equalsIgnoreCase("high") || testCase[i].getProbability().getName().equalsIgnoreCase("medium") || testCase[i].getProbability().getName().equalsIgnoreCase("low"))
				probabilityValue= convertProbability(testCase[i].getProbability());
			else
				probabilityValue= 0 ;
			
			if (testCase[i].getConsequence().getName().equalsIgnoreCase("higher")||testCase[i].getConsequence().getName().equalsIgnoreCase("high")||testCase[i].getConsequence().getName().equalsIgnoreCase("medium")
					||testCase[i].getConsequence().getName().equalsIgnoreCase("low")||testCase[i].getConsequence().getName().equalsIgnoreCase("lower"))
				consequenceValue = convertConsequence(testCase[i].getConsequence());			
			else 
				consequenceValue =0;
			
		//	double fitness = priorityValue * priority +  probabilityValue * probability + consequenceValue*consequence +  testCase[i].getTimeExecution()/(testCase[i].getTimeExecution()+1) ;
	
			double ePriorityValue, eProbabilityValue, eConsequenceValue;
			ePriorityValue = Nor(priorityValue/testCase[i].getTimeExecution());
			eProbabilityValue = Nor(probabilityValue/testCase[i].getTimeExecution());
			eConsequenceValue = Nor(consequenceValue/testCase[i].getTimeExecution());
			
			double fitness = ePriorityValue * priority +  eProbabilityValue * probability + eConsequenceValue*consequence ;
			
			testCase[i].setFitnessGrA(fitness);
	
			testCaseList.add(testCase[i]);			
			}

		Collections.sort(testCaseList, new Comparator<TestCase>() {
	        @Override
	        public int compare(TestCase  testCase1, TestCase  testCase2)
	        {
	        	return Double.compare(testCase1.getFitnessGrA(), testCase2.getFitnessGrA());
	        }
	    });
		return testCaseList;
		
	}
	
	// normalization function
	public double Nor(double n) {
		double m = n / (n + 1);
		return m;
	}
	
	public double convertPriority(Priority priority){
		double priorityNum=0;
		if (priority.getName().equalsIgnoreCase("higher"))
			priorityNum=0;
		else if (priority.getName().equalsIgnoreCase("high"))
			priorityNum=0.2;
		else if (priority.getName().equalsIgnoreCase("medium"))
			priorityNum=0.4;
		else if (priority.getName().equalsIgnoreCase("low"))
			priorityNum=0.6;
		else if (priority.getName().equalsIgnoreCase("lower"))
			priorityNum=0.8;
		
		return priorityNum;
	}
	
	public double convertProbability(Probability probability){
		double priorityNum=0;
		if (probability.getName().equalsIgnoreCase("high"))
			priorityNum=0;
		else if (probability.getName().equalsIgnoreCase("medium"))
			priorityNum=0.33;
		else if (probability.getName().equalsIgnoreCase("low"))
			priorityNum=0.66;
				
		return priorityNum;
	}
	
	public double convertConsequence(Consequence consequence){
		double consequenceNum=0;
		if (consequence.getName().equalsIgnoreCase("higher"))
			consequenceNum=0;
		else if (consequence.getName().equalsIgnoreCase("high"))
			consequenceNum=0.2;
		else if (consequence.getName().equalsIgnoreCase("medium"))
			consequenceNum=0.4;
		else if (consequence.getName().equalsIgnoreCase("low"))
			consequenceNum=0.6;
		else if (consequence.getName().equalsIgnoreCase("lower"))
			consequenceNum=0.8;
		
		return consequenceNum;
	}
	
	
	
	
}
