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
	private BufferedWriter file, file3;
	private ProblemScheduling problemScheduling;
	private ProblemSchedulingSimple problemSchedulingSimple;
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

	private double priority;
	private double probability;
	private double consequence;
	private String context;
	private double risk;
	private double epriority;
	private double eprobability;
	private double econsequence;
	private String component;
	private String constraint;
	private String effect;

	private int problem;
	
	private ArrayList<TestCase> testCaseList;

	
	private ReadTestCasesArtificialData readTestCasesArtificialData;
	
	private File fileName, fileName3;
	private static File fileName2;
	private static BufferedWriter file2;

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}
	
	public double getRisk() {
		return risk;
	}

	public void setRisk(double risk) {
		this.risk = risk;
	}
	
	public double getEpriority() {
		return epriority;
	}

	public void setEpriority(double epriority) {
		this.epriority = epriority;
	}

	public double getEprobability() {
		return eprobability;
	}

	public void setEprobability(double eprobability) {
		this.eprobability = eprobability;
	}

	public double getEconsequence() {
		return econsequence;
	}

	public void setEconsequence(double econsequence) {
		this.econsequence = econsequence;
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

	public double getPriority() {
		return priority;
	}

	public void setPriority(double priority) {
		this.priority = priority;
	}

	public double getProbability() {
		return probability;
	}

	public void setProbability(double probability) {
		this.probability = probability;
	}

	public double getConsequence() {
		return consequence;
	}

	public void setConsequence(double consequence) {
		this.consequence = consequence;
	}



	public ExperimentSchedulingArtificialData(){
		df = new DecimalFormat("0.000");
		

		readTestCasesArtificialData = new ReadTestCasesArtificialData();
	}
	
	public void createFile() throws Exception{
		fileName = new File("test_" + problem + ".txt");
		

		// if file does not exists, then create it
		if (!fileName.exists()) {
			fileName.createNewFile();
		}
	}
	
	public ArrayList<TestCase> getValues_1(int count) throws Exception {
		createFile();
		
		FileWriter fw = new FileWriter(fileName.getAbsoluteFile(),true);
		file = new BufferedWriter(fw);

		createFile2();
		FileWriter fw2= new FileWriter(fileName2.getAbsoluteFile(),true);
		file2 = new BufferedWriter(fw2);
		
		createFile3();
		FileWriter fw3= new FileWriter(fileName3.getAbsoluteFile(),true);
		file3 = new BufferedWriter(fw3);
		
		Search[] s = new Search[] { new simula.oclga.AVM(),
				new simula.oclga.SSGA(100, 0.75), new simula.oclga.OpOEA(),
				new simula.oclga.RandomSearch(),
				new simula.oclga.GreedyAlgorithm()};
		String[] s_name = new String[] { "AVM","GA","(1+1)EA","RS","GrA"};
		ArrayList<TestCase> tempCaseList = new ArrayList<TestCase>();
		int counter=0;
		double fitnessValue=1;
	
		
		file.write("\r");
		file.write("It is " + count + "  Problem: " + problem);
		file.write("\r");
		
		file2.write("\r"); 
		file2.write("It is " + count + " Problem: " + problem + "\r");
		
		int sizeTemp =0;
		

			for (int sea =0; sea < 5; sea++) {
				//System.out.println("New starts");
				long startTime = System.currentTimeMillis();
				ArrayList<TestCase> tempCaseList2 = new ArrayList<TestCase>();
				double fitnessValue2 = 1;
				
				for (int K = 0; K < 10; K++) {		
					double fitnessTemValue=1;
	
					problemScheduling = new ProblemScheduling();
					problemScheduling.setTestCaseList(testCaseList);
					problemScheduling.setJobsMax(jobsMax);
					problemScheduling.setJobsMin(jobsMin);
					problemScheduling.setTimeBudget(maxTime);
					problemScheduling.setTimeWeight(timeWeight);
					problemScheduling.setPriority(priority);
					problemScheduling.setProbability(probability);
					problemScheduling.setConsequence(consequence);
					problemScheduling.setRisk(risk);
					problemScheduling.setEpriority(epriority);
					problemScheduling.setEprobability(eprobability);
					problemScheduling.setEconsequence(econsequence);
					problemScheduling.setMax(counter);
						
					
						s[sea].setMaxIterations(20000);
					Search.validateConstraints(problemScheduling);
					int[] v_1 = s[sea].search(problemScheduling);
						
					//System.out.println("max is " + K + " "+ problemScheduling.getMax());
					DecimalFormat f = new DecimalFormat("##.00000");
					file.write(f.format(problemScheduling.getInitalFitnessValue()) + "\t"); //
					
					if (counter<=problemScheduling.getMax()){
						counter=problemScheduling.getMax();
						//System.out.println("counter is  is " + K+ " " +counter);
					}
					if (fitnessValue>problemScheduling.getInitalFitnessValue()){
						int size = tempCaseList.size()-1;
					
						tempCaseList= problemScheduling.caseList;
						fitnessValue = problemScheduling.getInitalFitnessValue();
						//System.out.println("The name is" + s[sea].getShortName());
					}
					
					if (fitnessTemValue>problemScheduling.getInitalFitnessValue()){
						fitnessTemValue = problemScheduling.getInitalFitnessValue();
						sizeTemp = problemScheduling.caseList.size();
					}
					
					if (fitnessValue2>problemScheduling.getInitalFitnessValue()){
						int size = tempCaseList.size()-1;
					
						tempCaseList2= problemScheduling.caseList;
						fitnessValue2 = problemScheduling.getInitalFitnessValue();
						//System.out.println("The name is" + s[sea].getShortName());
					}

				}	
				long endTime   = System.currentTimeMillis();
				long totalTime = endTime - startTime;
				long timeOne = totalTime/10;
			//	System.out.println("The name is " + s[sea].getShortName());
				//System.out.println("Time is " + timeOne);
				
				file2.write(timeOne + "\r");
				
				file2.flush();
						//file.write(df.format(m) + "\t"); //		
				file.write("\r");
				file.flush();
				
				file3.write("Algorithm is " + s[sea].getShortName() + "\n");

				
				for (int k=0;k<tempCaseList2.size();k++){
					file3.write(tempCaseList2.get(k).getCaseName() + "\t");
				}
				file3.write("\r");
				file3.flush();
			}	
		
			file3.write("\n" + "Best is" + "\n");

			
			for (int i=0;i<tempCaseList.size();i++){
				file3.write(tempCaseList.get(i).getCaseName() + "\t");
			}
			
			file3.flush();
			
		return tempCaseList;
	}
	
	public  void createFile2() throws Exception{
		fileName2 = new File("test" + problem + " time.txt");
		

		// if file does not exists, then create it
		if (!fileName2.exists()) {
			fileName2.createNewFile();
		}
	}
	
	public  void createFile3() throws Exception{
		fileName3 = new File("test" + problem + " cases.txt");
		

		// if file does not exists, then create it
		if (!fileName3.exists()) {
			fileName3.createNewFile();
		}
	}
	
	public ArrayList<TestCase> getValues_2() throws Exception {
		createFile();
		
		FileWriter fw = new FileWriter(fileName.getAbsoluteFile());
		file = new BufferedWriter(fw);
		
		Search[] s = new Search[] { new simula.oclga.AVM(),
				new simula.oclga.SSGA(100, 0.75), new simula.oclga.OpOEA(),
				new simula.oclga.RandomSearch(),
				new simula.oclga.GreedyAlgorithm()};
		String[] s_name = new String[] { "AVM","GA","(1+1)EA","RS","GrA"};
		ArrayList<TestCase> tempCaseList = new ArrayList<TestCase>();
		int counter=0;
		double fitnessValue=1;

		for (int sea = 0; sea < 5; sea++) {
			long startTime = System.currentTimeMillis();
			for (int K = 0; K < 10; K++) {	
				problemSchedulingSimple = new ProblemSchedulingSimple();
			
				problemSchedulingSimple.setTestCaseList(testCaseList);
				problemSchedulingSimple.setJobsMax(jobsMax);
				problemSchedulingSimple.setJobsMin(jobsMin);
				problemSchedulingSimple.setTimeBudget(maxTime);			
				problemSchedulingSimple.calculate();
	
				s[sea].setMaxIterations(20000);
				Search.validateConstraints(problemSchedulingSimple);
				int[] v_1 = s[sea].search(problemSchedulingSimple);
				
				file.write(problemSchedulingSimple.getInitalFitnessValue() + "\t"); //
				if (fitnessValue>problemSchedulingSimple.getInitalFitnessValue()){
				
					tempCaseList= problemSchedulingSimple.caseList;
					fitnessValue = problemSchedulingSimple.getInitalFitnessValue();
				}
			}
			
			long endTime   = System.currentTimeMillis();
			long totalTime = endTime - startTime;
			
			file.write("\r");
			file.flush();
		}
		
		return tempCaseList;
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
	public 	ArrayList<TestCase> run(int counter){
		ArrayList<TestCase> tempCaseList = new ArrayList<TestCase>();
		getTestCases();
//		if (this.total>0){
			try {
				tempCaseList= getValues_1(counter);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
//		}
		return tempCaseList;
		
	}

	public void getTestCases(){
		readTestCasesArtificialData.readFile();
		testCaseList= readTestCasesArtificialData.getTestCaseContents(100, epriority, eprobability, econsequence);
		
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
