package model;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import main.ReadTestCasesArtificialData;

import simula.oclga.Search;


public class ExperimentSchedulingArtificialData {

	private DecimalFormat df;
	private BufferedWriter file1, file2, file3, file4, file5, file6;
	private ProblemScheduling problemScheduling;
	private int jobsMax;
	private int jobsMin;
	private double maxTime;
	private double total;
	
	private double timeWeight;
	public double getTimeWeight() {
		return timeWeight;
	}

	public void setTimeWeight(double timeWeight) {
		this.timeWeight = timeWeight;
	}

	private double priorityWeight;
	private double probabilityWeight;
	private double consequenceWeight;
	private String context;
	private double riskWeight;
	private double epriorityWeight;
	private double eprobabilityWeight;
	private double econsequenceWeight;
	private double eriskWeight;
	public double getEriskWeight() {
		return eriskWeight;
	}

	public void setEriskWeight(double eriskWeight) {
		this.eriskWeight = eriskWeight;
	}

	private String component;
	private String constraint;
	private String effect;

	private int problem;
	
	private ArrayList<TestCase> testCaseList;

	
	private ReadTestCasesArtificialData readTestCasesArtificialData;
	
	private File fileName1, fileName2, fileName3, fileName4, fileName5, fileName6;

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}
	
	public double getRiskWeight() {
		return riskWeight;
	}

	public void setRiskWeight(double riskWeight) {
		this.riskWeight = riskWeight;
	}
	
	public double getEpriorityWeight() {
		return epriorityWeight;
	}

	public void setEpriorityWeight(double epriorityWeight) {
		this.epriorityWeight = epriorityWeight;
	}

	public double getEprobabilityWeight() {
		return eprobabilityWeight;
	}

	public void setEprobabilityWeight(double eprobabilityWeight) {
		this.eprobabilityWeight = eprobabilityWeight;
	}

	public double getEconsequenceWeight() {
		return econsequenceWeight;
	}

	public void setEconsequenceWeight(double econsequenceWeight) {
		this.econsequenceWeight = econsequenceWeight;
	}

	public String getContext() {
		return context;
	}

	public void setContext(String context) {
		this.context = context;
	}

	public String getComponent() {
		return component;
	}

	public void setComponent(String component) {
		this.component = component;
	}

	public String getConstraint() {
		return constraint;
	}

	public void setConstraint(String constraint) {
		this.constraint = constraint;
	}

	public String getEffect() {
		return effect;
	}

	public void setEffect(String effect) {
		this.effect = effect;
	}

	public double getPriorityWeight() {
		return priorityWeight;
	}

	public void setPriorityWeight(double priorityWeight) {
		this.priorityWeight = priorityWeight;
	}

	public double getProbabilityWeight() {
		return probabilityWeight;
	}

	public void setProbabilityWeight(double probabilityWeight) {
		this.probabilityWeight = probabilityWeight;
	}

	public double getConsequenceWeight() {
		return consequenceWeight;
	}

	public void setConsequence(double consequenceWeight) {
		this.consequenceWeight = consequenceWeight;
	}



	public ExperimentSchedulingArtificialData(){
		df = new DecimalFormat("0.000");
		

		readTestCasesArtificialData = new ReadTestCasesArtificialData();
	}
	

	
	public ArrayList<TestCase> getValues_1(int count, int problem) throws Exception {
		if (problem==1){
			createFile1();   //write fitness	
			FileWriter fw1 = new FileWriter(fileName1.getAbsoluteFile(),true);
			file1 = new BufferedWriter(fw1);

			createFile2(); 	//write time
			FileWriter fw2= new FileWriter(fileName2.getAbsoluteFile(),true);
			file2 = new BufferedWriter(fw2);
		}else if (problem ==2){
			createFile3();	//write best
			FileWriter fw1= new FileWriter(fileName3.getAbsoluteFile(),true);
			file1 = new BufferedWriter(fw1);
			
			createFile4();   //write fitness	
			FileWriter fw2 = new FileWriter(fileName4.getAbsoluteFile(),true);
			file2 = new BufferedWriter(fw2);
		}

		
		Search[] s = new Search[] { new simula.oclga.AVM(),
				new simula.oclga.SSGA(100, 0.75), new simula.oclga.OpOEA(),
				new simula.oclga.RandomSearch(),
				new simula.oclga.GreedyAlgorithm()};
		String[] s_name = new String[] { "AVM","GA","(1+1)EA","RS","GrA"};
		ArrayList<TestCase> tempCaseList = new ArrayList<TestCase>();

		double fitnessValue=1;

		file1.write("\r");
		file1.write("It is " + count + "  Problem: " + problem);
		file1.write("\r");
		
		file2.write("\r"); 
		file2.write("It is " + count + " Problem: " + problem + "\r");
		
		int sizeTemp =0;
	
			for (int sea =0; sea < 5; sea++) {
				//System.out.println("New starts");
				long startTime = System.currentTimeMillis();
			
				for (int K = 0; K < 100; K++) {		
					double fitnessTemValue=1; // For each algorithms
	
					problemScheduling = new ProblemScheduling();
					problemScheduling.setTestCaseList(testCaseList);
					problemScheduling.setJobsMax(jobsMax);
					problemScheduling.setJobsMin(jobsMin);
					problemScheduling.setTimeBudget(maxTime);
					problemScheduling.setTimeWeight(timeWeight);
					problemScheduling.setPriorityWeight(priorityWeight);
					problemScheduling.setProbabilityWeight(probabilityWeight);
					problemScheduling.setConsequenceWeight(consequenceWeight);
					problemScheduling.setRiskWeight(riskWeight);
					problemScheduling.setEpriorityWeight(epriorityWeight);
					problemScheduling.setEprobabilityWeight(eprobabilityWeight);
					problemScheduling.setEconsequenceWeight(econsequenceWeight);
					problemScheduling.setEriskWeight(eriskWeight);
			
					s[sea].setMaxIterations(20000);
					
					Search.validateConstraints(problemScheduling);
					int[] v_1 = s[sea].search(problemScheduling);
						
					//System.out.println("max is " + K + " "+ problemScheduling.getMax());
					DecimalFormat f = new DecimalFormat("##.00000");
					file1.write(f.format(problemScheduling.getInitalFitnessValue()) + "\t"); // Fitness value for each algorithm
					
					/*
					//The best fitness value from all the algorithms as one only used in the tool
					if (fitnessValue>problemScheduling.getInitalFitnessValue()){
						tempCaseList= problemScheduling.caseList;
						fitnessValue = problemScheduling.getInitalFitnessValue();
						//System.out.println("The name is" + s[sea].getShortName());
					}
					*/
					
					if (fitnessTemValue>problemScheduling.getInitalFitnessValue()){
						fitnessTemValue = problemScheduling.getInitalFitnessValue();
						sizeTemp = problemScheduling.caseList.size();
					}
					
					/*
					if (fitnessValue2>problemScheduling.getInitalFitnessValue()){
						int size = tempCaseList.size()-1;
					
						tempCaseList2= problemScheduling.caseList;
						fitnessValue2 = problemScheduling.getInitalFitnessValue();
						//System.out.println("The name is" + s[sea].getShortName());
					}

					 */
				}	
				long endTime   = System.currentTimeMillis();
				long totalTime = endTime - startTime;
				long timeOne = totalTime/100;
			//	System.out.println("The name is " + s[sea].getShortName());
				//System.out.println("Time is " + timeOne);
				
				file2.write(timeOne + "\r");
				
				file2.flush();
						//file.write(df.format(m) + "\t"); //		
				file1.write("\r");
				file1.flush();

			}			
		return tempCaseList;
	}
	
	public void createFile1() throws Exception{
		fileName1 = new File("test_" + problem + "Fitness.txt");
		

		// if file does not exists, then create it
		if (!fileName1.exists()) {
			fileName1.createNewFile();
		}
	}
	
	public  void createFile2() throws Exception{
		fileName2 = new File("test" + problem + " time.txt");
		

		// if file does not exists, then create it
		if (!fileName2.exists()) {
			fileName2.createNewFile();
		}
	}
	
	public  void createFile3() throws Exception{
		fileName3 = new File("2test_" + problem + " Fitness.txt");
		

		// if file does not exists, then create it
		if (!fileName3.exists()) {
			fileName3.createNewFile();
		}
	}
	
	public  void createFile4() throws Exception{
		fileName4 = new File("2test" + problem + " time.txt");
		

		// if file does not exists, then create it
		if (!fileName4.exists()) {
			fileName4.createNewFile();
		}
	}
	

	public int getJobsMin() {
		return jobsMin;
	}

	public void setJobsMin(int jobsMin) {
		this.jobsMin = jobsMin;
	}

	public int getJobsMax() {
		return jobsMax;
	}

	public void setJobsMax(int jobsMax) {
		this.jobsMax = jobsMax;
	}

	public double getMaxTime() {
		return maxTime;
	}

	public void setMaxTime(double maxTime) {
		this.maxTime = maxTime;
	}
	public 	ArrayList<TestCase> run(int counter, int problem){
		ArrayList<TestCase> tempCaseList = new ArrayList<TestCase>();
		getTestCases();
//		if (this.total>0){
			try {
				tempCaseList= getValues_1(counter, problem);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
//		}
		return tempCaseList;
		
	}

	public void getTestCases(){
		readTestCasesArtificialData.readFile();
		testCaseList= new ArrayList<TestCase>();
		testCaseList= readTestCasesArtificialData.getTestCaseContents(38, epriorityWeight, eprobabilityWeight, econsequenceWeight);
		
		jobsMax = testCaseList.size();
		
		jobsMin = 0;
	}

	public int getProblem() {
		return problem;
	}

	public void setProblem(int problem) {
		this.problem = problem;
	}	
}
