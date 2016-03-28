package geneticAlgorithm.Algorithm;

import java.util.Random;

import geneticAlgorithm.Genome;
import geneticAlgorithm.Population;

public class GeneticFunc {

	public static void mutate(Population pop){
		pop.incMutCount();
		int x = 1;
		while(x <= pop.getMutCount()){
			mutateLoop(pop);
			if(mutateLoop(pop)){
			}
		}
		}

	private static boolean mutateLoop(Population pop){
			for(int i = 1; i < pop.size(); i++){
				Genome mutChrom = pop.getChromosome(i);
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
		
	public static Genome chooseRandom(Population pop){
		
		Random rand = new Random();
		int prob = 1;
		Genome elite = pop.getElite();
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
	
	public static Genome findElite(Population pop){
		Genome currElite = pop.getElite();
		for(int i = 0; i < pop.size(); i++){
			if(pop.getChromosome(i).getFitness() > currElite.getFitness()){
				currElite.setElite(false);
				pop.setElite(pop.getChromosome(i));
			}
		
		}
		return currElite;
	}
	
	public static void crossover(Population pop){
		pop.setChromosome(pop.getElite(), 0);
		for(int i = 1; i < pop.size(); i++){
			boolean hasMated = false;
			while(!hasMated){
				Genome xx = chooseRandom(pop);
				Genome yy = chooseRandom(pop);
				if(newMate(xx,yy)){
					pop.setChromosome(cross(xx,yy,pop.getCross()), i);
					hasMated = true;
				}
			}
		}
	}
	
	private static boolean newMate(Genome xx, Genome yy){
		if(!xx.hasMated(yy) && !yy.hasMated(xx)){
			xx.addMate(yy);
			yy.addMate(xx);
			return true;
		}
		else{
			return false;
		}
	}
	
	private static Genome cross(Genome xx, Genome yy, CrossoverEnum type){
		
		Genome clone = xx.clone();
		int size = clone.getSize();
		byte[] y = yy.getGenome();

		switch(type){
		
		case EVERY_OTHER:{
			for(int i = 0; i < size; i ++){
				if (!( (i & 1) == 0 )) {
					clone.setGene(i, y[i]);
				}
			}
			break;
		}
		
		case SPLIT:{
			int splitNum = size/2;
			for(int i = splitNum; i < size; i++){
				clone.setGene(i, y[i]);
			}
			break;
			
		}
		case SPLIT_FIT_RATIO:{
			Random rand = new Random();
			int split = rand.nextInt(2) + 1;
			int fit1 = clone.getFitness();
			int fit2 = yy.getFitness();
			switch(split){
			case 1:{
				int splitNum =(int)((fit1 / (fit1 + fit2)) * size);
				for(int i = splitNum; i < size; i++){
					clone.setGene(i, y[i]);
				}
			}
			case 2:{
				int splitNum =(int)((fit2 / (fit1 + fit2)) * size);
				for(int i = 0; i < splitNum; i++){
					clone.setGene(i, y[i]);
				}
			}
			}
			break;
		}
		/**
		case LETTER:{
			for(int i = 0; i < size; i ++){
				clone.setGene(i, Algorithm.crossChar(clone.getGene(i), y[i]));
		}
		break;
		}
		*/
		default:
			break;
		}
		clone.testFitness();
		return clone;		
	}
}
