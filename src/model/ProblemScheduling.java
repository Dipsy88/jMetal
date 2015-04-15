package model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

import simula.oclga.Problem;

public class ProblemScheduling implements Problem{
	public ArrayList<TestCase> caseList;
	int counter;
	int[][] values;
	public double initalFitnessValue;
	private double timeBudget;
	
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

	private int max;
	private double tm = 0;
	private String context;
	private int jobsMin;
	
	public double getInitalFitnessValue() {
		return initalFitnessValue;
	}

	public void setInitalFitnessValue(double initalFitnessValue) {
		this.initalFitnessValue = initalFitnessValue;
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

	public double getTm() {
		return tm;
	}

	public void setTm(double tm) {
		this.tm = tm;
	}

	public int getMax() {
		return max;
	}

	public void setMax(int max) {
		this.max = max;
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

	public void setConsequenceWeight(double consequenceWeight) {
		this.consequenceWeight = consequenceWeight;
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

	public ProblemScheduling(){
		caseList = new ArrayList<TestCase>();
		initalFitnessValue =1;		
	}

	public double getFitness(int[] v) { //it would be better to rerun a list or String[], the first value is fitness function, the others are the jobid of selected test cases.	
		ArrayList<TestCase> tempCaseList = new ArrayList<TestCase>();
		
		ArrayList<TestCase> tempCase2 = new ArrayList<TestCase>();
		
		int count =0;
		double temptime =0,priorityValue=0, probabilityValue=0, consequenceValue =0, riskValue=0, ePriorityValue=0, eProbabilityValue=0, eConsequenceValue =0, eRiskValue=0;
		double prioritySum =0, probabilitySum =0, consequenceSum =0, riskSum =0, ePrioritySum = 0, eProbabilitySum = 0, eConsequenceSum = 0, eRiskSum=0;
		double overallProbability=0, overallPriority=0, overallConsequence=0, overallRisk=0, overallEProbability=0, overallEPriority=0, overallEConsequence=0, overallERisk=0;
		
		for (int i=0;i<testCaseList.size();i++){
			//System.out.println("Value " + i + "is " + v[i]);			
				if (v[i]==1){					
					tempCase2.add(testCaseList.get(i));					
				}
		}
		// calculating fitness function values
		for (int j=0;j<tempCase2.size();j++){
			double timeTemp=0;
			Priority priorityTemp = new Priority();
			Probability probabilityTemp = new Probability();	
			Consequence consequenceTemp = new Consequence();
			Risk riskTemp = new Risk();
			TestCase tempCase = new TestCase();
			
			tempCase = tempCase2.get(j);	
			
			timeTemp = tempCase.getTimeExecution();
			temptime += timeTemp;
			priorityTemp = tempCase.getPriority();
			probabilityTemp = tempCase.getProbability();
			consequenceTemp = tempCase.getConsequence();
			riskTemp = tempCase.getRisk();
	
			if (priorityTemp.getName().equalsIgnoreCase("higher")||priorityTemp.getName().equalsIgnoreCase("high")||priorityTemp.getName().equalsIgnoreCase("medium")
					||priorityTemp.getName().equalsIgnoreCase("low")||priorityTemp.getName().equalsIgnoreCase("lower"))
				priorityValue= convertPriority(priorityTemp);
			else 
				priorityValue=0;
			
			if (probabilityTemp.getName().equalsIgnoreCase("high") || probabilityTemp.getName().equalsIgnoreCase("medium") || probabilityTemp.getName().equalsIgnoreCase("low"))
				probabilityValue= convertProbability(probabilityTemp);
			else
				probabilityValue= 0 ;
			
			if (consequenceTemp.getName().equalsIgnoreCase("higher")||consequenceTemp.getName().equalsIgnoreCase("high")||consequenceTemp.getName().equalsIgnoreCase("medium")
					||consequenceTemp.getName().equalsIgnoreCase("low")||consequenceTemp.getName().equalsIgnoreCase("lower"))
				consequenceValue = convertConsequence(consequenceTemp);			
			else 
				consequenceValue =0;
			
			if (riskTemp.getName().equalsIgnoreCase("high") || riskTemp.getName().equalsIgnoreCase("medium") || riskTemp.getName().equalsIgnoreCase("low"))
				riskValue= convertRisk(riskTemp);
			else
				riskValue= 0 ;

			count++;
			tempCaseList.add(tempCase);
			prioritySum+=  priorityValue;
			probabilitySum += probabilityValue;
			consequenceSum += consequenceValue;
			riskSum += riskValue;
			
			ePriorityValue = Nor(priorityValue/timeTemp);
			eProbabilityValue = Nor(probabilityValue/timeTemp);
			eConsequenceValue = Nor(consequenceValue/timeTemp);
			eRiskValue = Nor(riskValue/timeTemp);
			
			ePrioritySum += ePriorityValue;
			eProbabilitySum += eProbabilityValue;
			eConsequenceSum += eConsequenceValue;
			eRiskSum += eRiskValue;
		}
		overallPriority = prioritySum/count;
		overallProbability = probabilitySum/count;
		overallConsequence = consequenceSum/count;
		overallRisk = riskSum/count;
		
		overallEPriority = ePrioritySum/count;
		overallEProbability = eProbabilitySum/count;
		overallEConsequence = eConsequenceSum/count;
		overallERisk= eRiskSum/count;

		double time =0;

		time = 1-Nor(Math.abs(temptime-timeBudget));

		//calculate fitness function
		tm =1- (timeWeight*time+ priorityWeight*overallPriority + probabilityWeight*overallProbability + consequenceWeight*overallConsequence + 
				riskWeight*overallRisk + epriorityWeight*overallEPriority + eprobabilityWeight*overallEProbability + econsequenceWeight*overallEConsequence+
				eriskWeight*overallERisk);

		if (initalFitnessValue>tm && count != 0 ){
			initalFitnessValue=tm;
			caseList=tempCaseList;		

		}		
		counter++;
		return tm;		 
	}
	
	
	
	
	public double convertPriority(Priority priority){
		double priorityNum=0;
		if (priority.getName().equalsIgnoreCase("higher"))
			priorityNum=1;
		else if (priority.getName().equalsIgnoreCase("high"))
			priorityNum=0.8;
		else if (priority.getName().equalsIgnoreCase("medium"))
			priorityNum=0.6;
		else if (priority.getName().equalsIgnoreCase("low"))
			priorityNum=0.4;
		else if (priority.getName().equalsIgnoreCase("lower"))
			priorityNum=0.2;
		
		return priorityNum;
	}
	
	public double convertProbability(Probability probability){
		double priorityNum=0;
		if (probability.getName().equalsIgnoreCase("high"))
			priorityNum=1;
		else if (probability.getName().equalsIgnoreCase("medium"))
			priorityNum=0.66;
		else if (probability.getName().equalsIgnoreCase("low"))
			priorityNum=0.33;
				
		return priorityNum;
	}
	
	public double convertConsequence(Consequence consequence){
		double consequenceNum=0;
		if (consequence.getName().equalsIgnoreCase("higher"))
			consequenceNum=1;
		else if (consequence.getName().equalsIgnoreCase("high"))
			consequenceNum=0.8;
		else if (consequence.getName().equalsIgnoreCase("medium"))
			consequenceNum=0.6;
		else if (consequence.getName().equalsIgnoreCase("low"))
			consequenceNum=0.4;
		else if (consequence.getName().equalsIgnoreCase("lower"))
			consequenceNum=0.2;
		
		return consequenceNum;
	}
	
	public double convertRisk(Risk risk){
		double riskNum=0;
		if (risk.getName().equalsIgnoreCase("high"))
			riskNum=1;
		else if (risk.getName().equalsIgnoreCase("medium"))
			riskNum=0.66;
		else if (risk.getName().equalsIgnoreCase("low"))
			riskNum=0.33;
				
		return riskNum;
	}

	// normalization function
	public double Nor(double n) {
		double m = n / (n + 1);
		return m;
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
	
	public int[][] getConstraintsGreedy() {
		// TODO Auto-generated method stub
		int valuesOfConstraints[][] = new int[testCaseList.size()][3];
		
		for (int i=0;i<testCaseList.size();i++){
			valuesOfConstraints[i][0] = 0;
			valuesOfConstraints[i][1] = 0;
			valuesOfConstraints[i][2] = 0;			
		}
		setValues(valuesOfConstraints);
		return values;
	}
		
	public void setValues(int[][] values) {
		this.values = values;
	}

	public ArrayList<TestCase> getTestCaseList() {
		return testCaseList;
	}

	public void setTestCaseList(ArrayList<TestCase> testCaseList) {
		this.testCaseList = testCaseList;
	}
	
}