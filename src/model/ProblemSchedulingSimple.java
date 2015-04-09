package model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import simula.oclga.Problem;



public class ProblemSchedulingSimple implements Problem{
	public ArrayList<TestCase> caseList;
	private int count;
	public double initalFitnessValue;
	private double tm = 0;
	
	public double getInitalFitnessValue() {
		return initalFitnessValue;
	}

	public void setInitalFitnessValue(double initalFitnessValue) {
		this.initalFitnessValue = initalFitnessValue;
	}
	
	
	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	int[][] values;
	
	public ArrayList<TestCase> getTestCaseList() {
		return testCaseList;
	}

	public void setTestCaseList(ArrayList<TestCase> testCaseList) {
		this.testCaseList = testCaseList;
	}
	
	private double timeBudget;

	private int max;

	public int getMax() {
		return max;
	}

	public void setMax(int max) {
		this.max = max;
	}

	public double getTimeBudget() {
		return timeBudget;
	}

	public void setTimeBudget(double timeBudget) {
		this.timeBudget = timeBudget;
	}

	private int jobsMax;
	private ArrayList<TestCase> testCaseList;
	
	public int getJobsMax() {
		return jobsMax;
	}

	public void setJobsMax(int jobsMax) {
		this.jobsMax = jobsMax;
	}

	public int getJobsMin() {
		return jobsMin;
	}

	public void setJobsMin(int jobsMin) {
		this.jobsMin = jobsMin;
	}

	private int jobsMin;
	
	
	public ProblemSchedulingSimple(){
		caseList = new ArrayList<TestCase>();
		initalFitnessValue =1;
	}
	
	
	@Override
	public int[][] getConstraints() {
		// TODO Auto-generated method stub
		int valuesOfConstraints[][] = new int[testCaseList.size()][3];
		
		for (int i=0;i<testCaseList.size();i++){
			valuesOfConstraints[i][0] = 0;
			valuesOfConstraints[i][1] = 1;
			valuesOfConstraints[i][2] = 0;			
		}
		setValues(valuesOfConstraints);
		return values;
	}
	
	public void setValues(int[][] values) {
		this.values = values;
	}
	
	public void calculate() { 
		double temptime =0;

		// get the potential solution
		HashSet<Integer> tempset = new HashSet<Integer>();
		Random rand = new Random();

		while (tempset.size()<jobsMax){ 
			tempset.add(rand.nextInt((jobsMax - jobsMin)) + jobsMin);
		}
		Iterator<Integer> it = tempset.iterator();		
		// calculating fitness function values
		while (it.hasNext()&&temptime<timeBudget){			
			TestCase tempCase = testCaseList.get(it.next());	
			temptime +=tempCase.getTimeExecution();

			if (temptime>timeBudget)
				temptime -=tempCase.getTimeExecution();				
			else{
				this.count++;
				caseList.add(tempCase);
			}
				
	}
	
	}

	@Override
	public double getFitness(int[] v) { //it would be better to rerun a list or String[], the first value is fitness function, the others are the jobid of selected test cases.	
		ArrayList<TestCase> tempCaseList = new ArrayList<TestCase>();	
		ArrayList<TestCase> tempCase2 = new ArrayList<TestCase>();
		int count =0;
		double temptime =0;
		HashSet<Integer> tempset = new HashSet<Integer>();
		int k=0;
		
		for (int i=0;i<testCaseList.size();i++){
			//System.out.println("Value " + i + "is " + v[i]);
			if (tempCase2.size()!=testCaseList.size()){
				if (v[i]==1){
					tempset.add(i);
					tempCase2.add(testCaseList.get(k));					
				}
				k++;
			}		
		}
		
		// calculating fitness function values
		for (int j=0;j<tempCase2.size();j++){		
			TestCase tempCase = tempCase2.get(j);		
			temptime +=tempCase.getTimeExecution();
			
				count++;
				tempCaseList.add(tempCase);
			
		}
		double time =0;
		double t=timeBudget - temptime ;
		if (t<0)
			tm = Nor(count);
		else {
			time = 1-Nor(t);
			tm =1- (Nor(count) + time)/2 ;
			if (initalFitnessValue>tm && count != 0 ){
				initalFitnessValue=tm;
				caseList=tempCaseList;		

			}
		}		
		return tm;
		
	}
	
	// normalization function
	public double Nor(double n) {
		double m = n / (n + 1);
		return m;
	}
}
