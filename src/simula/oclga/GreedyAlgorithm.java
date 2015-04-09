package simula.oclga;

import java.util.List;
import java.util.Random;

import javax.xml.soap.Node;

public class GreedyAlgorithm extends Search{

	
	
	
	
	@Override
	public int[] getSolution(Problem problem)
	{
		Individual best = Individual.getRandomIndividual(problem);
		best.evaluate();
		this.increaseIteration();

		if(best.fitness_value == 0d)
			return best.v;
		
		while(!this.isStoppingCriterionFulfilled())
		{
			Individual current = Individual.getRandomIndividual(problem);
			current.evaluate();
			this.increaseIteration();

			if(current.fitness_value == 0d)
				return current.v;

			applyGrASearch(current);
			
			if(current.fitness_value == 0d)
				return current.v;

			if(current.fitness_value < best.fitness_value)
			{
				best.copyDataFrom(current);
				reportImprovement();				
			}
		
		
		}
		
		return best.v;
	
	}
	
	protected void applyGrASearch(Individual ind)
	{

		for( int i=0; i<=ind.v.length-1; i++)
		{
			ind.v[i] =0;
		
		}

		while(!this.isStoppingCriterionFulfilled())
		{
			double current_fitness = 1;
			for( int i=0; i<=ind.v.length-1; i++)
			{
	
				//exploratory search
				ind.v[i] = 1;
						
				ind.evaluate();
				this.increaseIteration();

				if(ind.fitness_value == 0d)
					return;

					//is it better?
				if(ind.fitness_value < current_fitness)
				{
					current_fitness = ind.fitness_value;
					
				}
				else
				{
				//undo change
					ind.v[i] = 0;
					ind.fitness_value = current_fitness;
						
				}
				
			}
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
	
	@Override
	public String getShortName() {
		// TODO Auto-generated method stub
		return "GrA";
	}
	
}
