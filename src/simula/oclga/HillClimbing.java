package simula.oclga;

import java.util.List;
import java.util.Random;

import javax.xml.soap.Node;

public class HillClimbing extends Search{

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

			applyHSSearch(current);
			
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
	
	protected void applyHSSearch(Individual ind)
	{
		
		final int[] directions = new int[]{0,+1};
		
		int last_improvement_index = -1;

		while(!this.isStoppingCriterionFulfilled())
		{
			for( int i=0; i<ind.v.length; i++)
			{
				int index = randInt(0, ind.v.length-1); 
				
				boolean improved = false;
			  
				for(int d : directions)
				{
					double current_fitness = ind.fitness_value;
					//exploratory search
					ind.v[index] = d;
						
					ind.evaluate();
					this.increaseIteration();

					if(ind.fitness_value == 0d)
						return;

					//is it better?
					if(ind.fitness_value < current_fitness)
					{
						current_fitness = ind.fitness_value;
						improved = true;
					}
					else
					{
						//undo change
						
						if (d == 0)
							ind.v[index] = 1;
							
						if (d== 1 )
							ind.v[index] = 0;
						ind.fitness_value = current_fitness;
						
					}

					if(improved)
					{
						int j=1;
						while(!this.isStoppingCriterionFulfilled())
						{
							current_fitness = ind.fitness_value;

							if (d==-1){
								if ((index-j)>=0)
									ind.v[index-j] = 1;
							}
							if (d==1){
								if ((index+j)<ind.v.length)
									ind.v[index+j] = 1;
							}

							ind.evaluate();
							this.increaseIteration();

							if(ind.fitness_value == 0d)
								return;

							//is it better?
							if(ind.fitness_value < current_fitness)
							{
								j++;
							}
							else
							{
								//undo change
								if (d==-1){
									if ((index-j)>=0)
										ind.v[index-j] = 0;
								}
								if (d==1){
									if ((index+j)<ind.v.length)
										ind.v[index+j] = 0;
								}
								ind.fitness_value = current_fitness;
								break;
							}
						}
					}	
				
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
		return "HC";
	}
	
}
