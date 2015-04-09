package main;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.sql.SQLException;
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


public class GenerateTestCases {


	public static TestCase testCase[] = new TestCase[1900];
	private static File fileName;
	private static BufferedWriter file;
	
	public static void main(String[] args) throws Exception {
		addCase();
		addInfo();
	}
	
	
	public static void generateRuns(){
		
		
	}
	
	public static void addCase(){
		int min=1, maxPriority =5, maxProbability =3, maxConsequence =5, maxTime=16, maxContext =2, maxComponent =5, maxConstraint =5, maxEffect =8;
		
		for (int i=0;i<1900;i++){
			Priority priority = new Priority();
			Probability probability = new Probability();
			Consequence consequence = new Consequence();
			Context context = new Context();
			RCUType rcuType = new RCUType();
			ModelConstraint modelConstraint = new ModelConstraint();
			Effect effect = new Effect();
			Model model = new Model();
			Risk risk = new Risk();
			
			testCase[i] = new TestCase();
			testCase[i].setCaseName("Case"+i);
			
			int numPriority = randInt(min,maxPriority);
			priority.setName(returnNamePriority(numPriority));
			testCase[i].setPriority(priority);
			
			int numProbability	= randInt (min,maxProbability);
			String probabilityName= returnNameProbability(numProbability);
			probability.setName(probabilityName);
			testCase[i].setProbability(probability);
			
			int numConsequence	= randInt (min,maxConsequence);
			String proabilityConsequence= returnNameConsequence(numConsequence);
			consequence.setName(proabilityConsequence);
			testCase[i].setConsequence(consequence);
			
			int num = randInt (min,maxTime);
			double time = addTime (num);
			testCase[i].setTimeExecution(time);
			
			int numContext= randInt(min,maxContext);
			context.setName(returnNameContext(numContext));
			testCase[i].setContext(context);
			
			int numComponent= randInt(min,maxComponent);
			rcuType.setName(returnNameComponent(numComponent));
			testCase[i].setRcuType(rcuType);
			
			int numConstraint	= randInt (min,maxConstraint);
			modelConstraint.setName(returnNameConstraint(numConstraint));
			testCase[i].setModelConstraint(modelConstraint);
			
			int numEffect	= randInt (min,maxEffect);
			effect.setName(returnNameEffect(numEffect));
			testCase[i].setEffect(effect);
			
			model.setName("RCU");
			testCase[i].setModel(model);
			
			risk.setName(findRisk(probabilityName, proabilityConsequence));
			testCase[i].setRisk(risk);
			
		}
	}
	
	public static void addInfo() throws Exception{
		createFile();
		
		FileWriter fw = new FileWriter(fileName.getAbsoluteFile());
		file = new BufferedWriter(fw);
		
		for (int i=0;i<1900;i++){
			file.write(i+ "\t" + testCase[i].getCaseName()+ "\t" + testCase[i].getPriority().getName()+ "\t" + testCase[i].getProbability().getName()
					+ "\t" + testCase[i].getConsequence().getName()+ "\t" + testCase[i].getTimeExecution()+ "\t" + testCase[i].getContext().getName()+ "\t" + 
					testCase[i].getRcuType().getName()+ "\t" + testCase[i].getModelConstraint().getName()+ "\t"  + testCase[i].getEffect().getName()+ "\t"  + 
					testCase[i].getModel().getName()+ "\t"  + testCase[i].getRisk().getName()+ "\n"); //
			
		}
		
		file.flush();
	}
	public static String findRisk(String probability, String consequence){
		String risk = null;
		if (probability == "low"){
			switch (consequence) {
				case "lower": risk = "low";	
							break;
				case "low": risk = "low";		
							break;
				case "medium": risk = "medium";
							break;
				case "high": risk = "medium";
							break;
				case "higher": risk = "high";
							break;
			}
		}else if (probability == "medium"){
			switch (consequence) {
				case "lower": risk = "medium";	
							break;
				case "low": risk = "medium";
							break;
				case "medium": risk = "medium";
							break;
				case "high": risk = "medium";
							break;
				case "higher": risk = "high";
							break;
			}
		}else if (probability == "high"){
			switch (probability) {
				case "lower": risk = "medium";	
							break;
				case "low": risk = "medium";	
							break;
				case "medium": risk = "high";	
							break;
				case "high": risk = "high";		
							break;
				case "higher": risk = "high";	
							break;
			}
		}
		return risk;
	}
	
	public static void sqlCode(int i, int caseId, int min, int max){
		int num = randInt(min,max);
		String value= null;
		if (num ==1)
			value = "pass";
		else if (num ==2)
			value = "fail";
		else if (num==3)
			value = "undecided";
			
	}
	
	public static String returnNamePriority(int value){
		String namePriority = null;
		if (value ==1)
			namePriority = "higher";
		else if (value ==2)
			namePriority = "high";
		else if (value==3)
			namePriority = "medium";
		else if (value ==4)
			namePriority = "low";
		else if (value==5)
			namePriority = "lower";
		return namePriority;
	}
	
	public static String returnNameProbability(int value){
		String returnNameProbability = null;
		if (value ==1)
			returnNameProbability = "high";
		else if (value ==2)
			returnNameProbability = "medium";
		else if (value==3)
			returnNameProbability = "low";
		
		return returnNameProbability;
	}
	
	public static String returnNameConsequence(int value){
		String returnNameConsequence = null;
		if (value ==1)
			returnNameConsequence = "higher";
		else if (value ==2)
			returnNameConsequence = "high";
		else if (value==3)
			returnNameConsequence = "medium";
		else if (value ==4)
			returnNameConsequence = "low";
		else if (value==5)
			returnNameConsequence = "lower";
		return returnNameConsequence;
	}

	
	public static String returnNameContext(int value){
		String nameContext = null;
		if (value ==1)
			nameContext = "internal";
		else if (value ==2)
			nameContext = "external";

		return nameContext;
	}
	
	public static String returnNameComponent(int value){
		String nameComponent = null;
		if (value ==1)
			nameComponent = "RCU500";
		else if (value ==2)
			nameComponent = "RCU510";
		else if (value==3)
			nameComponent = "RCU501";
		else if (value ==4)
			nameComponent = "RCU502";
		else if (value==5)
			nameComponent = "RCU602";
		return nameComponent;
	}
	
	public static String returnNameConstraint(int value){
		String nameConstraint = null;
		if (value ==1)
			nameConstraint = "Temperature Operation";
		else if (value ==2)
			nameConstraint = "Power Supply";
		else if (value==3)
			nameConstraint = "Nominal Current";
		else if (value ==4)
			nameConstraint = "Power Consumption";
		else if (value==5)
			nameConstraint = "Heat Dissipation";
		return nameConstraint;
	}
	
	public static String returnNameEffect(int value){
		String nameEffect = null;
		if (value ==1)
			nameEffect = "functionality";
		else if (value ==2)
			nameEffect = "usability";
		else if (value==3)
			nameEffect = "security";
		else if (value ==4)
			nameEffect = "performance";
		else if (value==5)
			nameEffect = "serviceability";
		else if (value==6)
			nameEffect = "robustness";
		else if (value ==7)
			nameEffect = "environment";
		else if (value==8)
			nameEffect = "other";
		return nameEffect;
	}
	
	
	
	
	public static void createFile() throws Exception{
		fileName = new File("C:\\Personal\\practice\\files\\case1900.txt");
		

		// if file does not exists, then create it
		if (!fileName.exists()) {
			fileName.createNewFile();
		}
	}
	public static int randInt(int min, int max) {

	    // NOTE: Usually this should be a field rather than a method
	    // variable so that it is not re-seeded every call.
	    Random rand = new Random();

	    // nextInt is normally exclusive of the top value,
	    // so add 1 to make it inclusive
	    int randomNum = rand.nextInt((max - min) + 1) + min;

	    return randomNum;
	}
	
	
	public static double addTime(int number){
		double num =0.5;
		switch (number) {
		case 1: num=  0.5;
				break;
		case 2: num=  1;
				break;
		case 3: num=  1.5;
				break;
		case 4: num=  2;
				break;
		case 5: num= 2.5;
				break;
		case 6: num=  3;
				break;
		case 7: num=  3.5;
				break;
		case 8: num=  4;
				break;
		case 9: num=  4.5;
				break;
		case 10: num=  5;
				break;
		case 11: num=  5.5;
				break;
		case 12: num=  6;
				break;
		case 13: num=  6.5;
				break;
		case 14: num=  7;
				break;
		case 15: num=  7.5;
				break;
		case 16: num=  8;
				break;

		}
		return num;
		
	}

	
	
}
