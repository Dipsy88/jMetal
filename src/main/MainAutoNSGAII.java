package main;

import java.io.IOException;
import java.util.HashMap;
import java.util.logging.FileHandler;

import model.ExperimentSchedulingArtificialData;
import jmetal.core.Algorithm;
import jmetal.core.Operator;
import jmetal.core.Problem;
import jmetal.core.SolutionSet;
import jmetal.metaheuristics.nsgaII.NSGAII;
import jmetal.metaheuristics.nsgaII.NSGAII_custom;
import jmetal.operators.crossover.CrossoverFactory;
import jmetal.operators.mutation.MutationFactory;
import jmetal.operators.selection.SelectionFactory;
import jmetal.problems.Kongsberg1;
import jmetal.problems.Kongsberg2;
import jmetal.problems.Kongsberg3;
import jmetal.problems.Kongsberg4;
import jmetal.qualityIndicator.QualityIndicator;
import jmetal.util.Configuration;
import jmetal.util.JMException;

public class MainAutoNSGAII {
	
	private static Algorithm algorithm;
	private static int timeBudget = 24;
	private static int numberTestCase = 38;
	
	private static String[] priorityG = {"0", "25", "50", "75", "100"};
	private static String[] probabilityG = {"0", "50", "100"};
	private static String[] consequenceG = {"0", "25", "50", "75", "100"};
	private static String[] riskG = {"0", "50", "100"};
	
	private static String[] epriorityG = {"0", "25", "50", "75", "100"};
	private static String[] eprobabilityG = {"0", "50", "100"};
	private static String[] econsequenceG = {"0", "25", "50", "75", "100"};
	private static String[] eriskG = {"0", "50", "100"};
	
	public static void main(String[] args) throws Exception {
		double priorityWeight=0, probabilityWeight=0, consequenceWeight=0, riskWeight=0, timeWeight = 0.2;
		double epriorityWeight=0, eprobabilityWeight=0, econsequenceWeight=0, eriskWeight=0;
		String variable, result, best;
		FindBestFitnessValue findBest = new FindBestFitnessValue();	
		Problem problem; 

		int count =1;
		best = "best_nsgaii"+"1";
		
		//for effectiveness
		//for the first 75 problems with priority, probability and risk
		problem = new Kongsberg1("ArrayReal", 38); //75 problems
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
						algorithm = new NSGAII(problem);
						variable = "var"+count;
						result = "nsgaii"+count;				
						
						calculate (problem, timeBudget, timeWeight, priorityWeight, probabilityWeight, consequenceWeight, 0, 0, 0, 0, 0);					
						run (algorithm, problem, variable, result);
						findBest.run(result, best, count);
						
						if (total == 0){
							priorityWeight = 0;
							probabilityWeight = 0;
							consequenceWeight = 0;			
						}
						count++;
						System.out.println(count);
					}
				}
		}
		
		//for the 15 problems with priority and risk
		//first is priority	
		problem = new Kongsberg2("ArrayReal", 38); //15 problems
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
				algorithm = new NSGAII(problem);
				variable = "var"+count;
				result = "nsgaii"+count;

				calculate (problem, timeBudget, timeWeight, priorityWeight, 0, 0, riskWeight, 0, 0, 0, 0);				
				run (algorithm, problem, variable, result);
				findBest.run(result, best, count);
				
				if (total == 0){
					priorityWeight = 0;
					riskWeight = 0;	
				}
				count++;
				System.out.println(count);
			}
		}
		// for efficiency
		//for 75 problems
		count = 1;  	//reset for next set of problem
		best = "best_nsgaii"+"2";
		problem = new Kongsberg3("ArrayReal", 38); //75 problems
		for(int i = 0; i < 5;i++){			
			//next is probability
			epriorityWeight = Double.parseDouble(epriorityG[i]);
			for (int j=0;j<3;j++){
				//last is consequence
				eprobabilityWeight = Double.parseDouble(eprobabilityG[j]);
				for (int k=0;k<5;k++){
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
					algorithm = new NSGAII(problem);
					variable = "2var"+count;
					result = "2nsgaii"+count;		
					
					calculate (problem, timeBudget, timeWeight, 0, 0, 0, 0, epriorityWeight, eprobabilityWeight, econsequenceWeight, 0);
					run (algorithm, problem, variable, result);
					findBest.run(result, best, count);
					
					if (total == 0){
						epriorityWeight = 0;
						eprobabilityWeight = 0;
						econsequenceWeight = 0;			
					}
					count++;
					System.out.println(count);
				}					
			}			
		}

		//for the 15 problems with priority and risk
		//first is priority
		problem = new Kongsberg4("ArrayReal", 38); //15 problems
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
				algorithm = new NSGAII(problem);
				variable = "2var"+count;
				result = "2nsgaii"+count;
				
				calculate (problem, timeBudget, timeWeight, 0, 0, 0, 0, epriorityWeight, 0, 0, eriskWeight);
				run (algorithm, problem, variable, result);
				findBest.run(result, best, count);
				
				if (total == 0){
					epriorityWeight = 0;
					eriskWeight = 0;		
				}
				count++;
				System.out.println(count);
			}
		}
	}
				
	public static void calculate (Problem problem, int timeBudget, 
						double timeWeight, double priorityWeight, double probabilityWeight, 
						double consequenceWeight, double riskWeight, double epriorityWeight, double eprobabilityWeight, 
						double econsequenceWeight, double eriskWeight){
					
		problem.setTimeBudget(timeBudget);
		//experimentScheduling.setContext(context);
		problem.setTimeWeight(timeWeight);
		problem.setPriorityWeight(priorityWeight);
		problem.setProbabilityWeight(probabilityWeight);
		problem.setConsequenceWeight(consequenceWeight);
		problem.setRiskWeight(riskWeight);
		problem.setEffPriorityWeight(epriorityWeight);
		problem.setEffProbabilityWeight(eprobabilityWeight);
		problem.setEffConsequenceWeight(econsequenceWeight);
		problem.setEffRiskWeight(eriskWeight);

		
		algorithm = new NSGAII(problem);
	}
	
	 public static void run(Algorithm algorithm, Problem problem, String variable, String result) throws 
									     JMException, 
									     SecurityException, 
									     IOException, 
									     ClassNotFoundException {

		Operator  crossover ; // Crossover operator
		Operator  mutation  ; // Mutation operator
		Operator  selection ; // Selection operator
		
		HashMap  parameters ; // Operator parameters
		
		QualityIndicator indicators ; // Object to get quality indicators
		
		indicators = null ;
	
		// Algorithm parameters
		algorithm.setInputParameter("populationSize",200);
		algorithm.setInputParameter("maxEvaluations",50000);
		
		// Mutation and Crossover for Real codification 
		parameters = new HashMap() ;
		parameters.put("probability", 0.9) ;
		parameters.put("distributionIndex", 20.0) ;
		crossover = CrossoverFactory.getCrossoverOperator("SBXCrossover", parameters);                   
		
		parameters = new HashMap() ;
		parameters.put("probability", 1.0/problem.getNumberOfVariables()) ;
		parameters.put("distributionIndex", 20.0) ;
		mutation = MutationFactory.getMutationOperator("PolynomialMutation", parameters);                    
		
		// Selection Operator n
		parameters = null ;
		selection = SelectionFactory.getSelectionOperator("BinaryTournament2", parameters) ;                           
		
		// Add the operators to the algorithm
		algorithm.addOperator("crossover",crossover);
		algorithm.addOperator("mutation",mutation);
		algorithm.addOperator("selection",selection);
		
		// Add the indicator object to the algorithm
		algorithm.setInputParameter("indicators", indicators) ;
		
		// Execute the Algorithm
		long initTime = System.currentTimeMillis();
		SolutionSet population = algorithm.execute();
		long estimatedTime = System.currentTimeMillis() - initTime;
		
		// Result messages 
		population.printVariablesToFile(variable);    
		population.printObjectivesToFile(result);
	
	} //main
	
}
