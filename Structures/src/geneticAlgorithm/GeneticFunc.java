package geneticAlgorithm;

import java.util.Random;

import geneticAlgorithm.Byte.ByteChromosome;
import geneticAlgorithm.Letter.LetterChromosome;
import list.ArrayList;

public class GeneticFunc {

	public static void mutate(Population pop){
		pop.incMutCount();
		int x = 1;
		double mutCount = pop.getMutCount();
		while(x <= pop.getMutCount()){
			mutateLoop(pop);
			if(mutateLoop(pop)){
			}
		}
		}

	private static boolean mutateLoop(Population pop){
			for(int i = 1; i < pop.size(); i++){
				Chromosome mutChrom = pop.getChromosome(i);
				if(!mutChrom.isElite()){
					for(int y = 0; y < mutChrom.getSize(); y++){
						if(Math.random() < pop.getMutCoef()){						
							mutChrom.mutate(y);	
							pop.decMutCount();
							return true;
						}
					}
				}	
			}
			return false;		
		}
		
	public static Chromosome chooseRandom(Population pop){
		
		Random rand = new Random();
		int prob = 1;
		Chromosome elite = pop.getElite();
		if(elite.getFitness() > 0){
			prob = rand.nextInt(elite.getFitness());	
		}
		
		int probIndex = 0;
		while(true){
			int randIndex = rand.nextInt(pop.size());
			probIndex += pop.getChromosome(randIndex).getFitness();
			if(probIndex >= prob){
				return pop.getChromosome(randIndex);
			}
		}
	}
	
	public static Chromosome findElite(Population pop){
		Chromosome currElite = pop.getElite();
		for(int i = 0; i < pop.size(); i++){
			if(pop.getChromosome(i).getFitness() > currElite.getFitness()){
				currElite.setElite(false);
				pop.setElite(pop.getChromosome(i));
			}
		
		}
		return currElite;
	}
	
	public static void crossOver(Population pop){
		pop.setChromosome(pop.getElite(), 0);
		for(int i = 1; i < pop.size(); i++){
			pop.setChromosome(chooseRandom(pop).crossover(chooseRandom(pop), pop.getCross()), i);
		}
	}
	
}
