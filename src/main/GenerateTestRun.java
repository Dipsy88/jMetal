package main;

import java.sql.SQLException;
import java.util.Random;

import model.Effect;


public class GenerateTestRun {


	public static void main(String[] args) {

		//generateRuns();
	
	}
	
	
	public static void generateRuns(){
		for (int j=80;j<90;j++)
			for (int i=101;i<400;i++){
				sqlCode("testrun",i,j,3,3);
				
			}
		
	}
	
	
	public static void sqlCode(String table, int i, int caseId, int min, int max){
		int num = randInt(min,max);
		String value= null;
		if (num ==1)
			value = "pass";
		else if (num ==2)
			value = "fail";
		else if (num==3)
			value = "undecided";
		String code= "insert into " + table + "(runid,caseid,result) values " + "(" + i + ","+ caseId+",'" + value + "');" ;
		
		
		
		
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
	

	
	
}
