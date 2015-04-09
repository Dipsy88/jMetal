package main;

import java.io.IOException;

import jmetal.core.Algorithm;
import jmetal.core.Problem;
import jmetal.metaheuristics.nsgaII.NSGAII;
import jmetal.metaheuristics.nsgaII.NSGAII_custom;
import jmetal.problems.Kongsberg;
import jmetal.util.JMException;

public class MainAutoJMetal {
	
	private static NSGAII_custom nsgaii = new NSGAII_custom();
	private static int timeBudget = 10;
	private static int numberTestCase = 100;
	private static double timeWeight = 0.2, effPriorityWeight = 0.8, effProbabilityWeight = 0, effConsequenceWeight = 0;
	
	public static void main(String[] args) throws SecurityException, ClassNotFoundException, JMException, IOException {
		Kongsberg problem = new Kongsberg("ArrayReal", 100);
		problem.setTimeBudget(timeBudget);
		problem.setEffPriorityWeight(effPriorityWeight);
		problem.setEffProbabilityWeight(effProbabilityWeight);
		problem.setEffConsequenceWeight(effConsequenceWeight);	
		
		nsgaii.setProblem(problem);
		nsgaii.run();
		
		Algorithm[] algorithm = new Algorithm[] { new NSGAII(problem)};

		
	}
	public static void setData(){
		
	}
	
}
