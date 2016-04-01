package geneticAlgorithm.Algorithm;

import java.util.Arrays;

/*
 * Written by: Zachary Kearney
 * Copyright by: Zachary Kearney, 2016
 *
 * Program: GeneticFunc.java
 * Date: March 22, 2016
 *
 * Description: Static class containing methods for mutation, choosing random genomes, finding elite, and crossovers.
 * 
 */

import java.util.Random;

import geneticAlgorithm.Genome;
import geneticAlgorithm.Population;
import list.ArrayList;

public class GeneticFunc {

	/**
	 * Mutates population. If mutcount < 1, Does not mutate anything. 
	 * Else, mutates a random byte based on mutcoef and decrements mutcount until mutcount is less than 1.
	 * @param pop
	 */
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
				Genome mutChrom = pop.getGenome(i);
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
		
	/**
	 * Chooses a random genome from the specified population.
	 * The higher the fitness of the genome, the higher the probability of getting selected.
	 * @param pop
	 * @return
	 */
	public static Genome chooseRandom(Population pop){
		
		Random rand = new Random();
		int prob = 1;
		Genome elite = pop.getElite();
		if(elite.getFitness() >= 1){
			prob = rand.nextInt((int)elite.getFitness());	
		}
		
		int probIndex = 0;
		while(true){
			int randIndex = rand.nextInt(pop.size());
			probIndex += pop.getGenome(randIndex).getFitness();
			if(probIndex >= prob){
				return pop.getGenome(randIndex);
			}
		}
	}
	/**
	 * Finds the Genome with the highest fitness from a population.
	 * @param pop
	 * @return
	 */
	public static void findElite(Population pop){
		ArrayList<Genome> elite = pop.getAllElite();
		for(int i = 0; i < pop.size(); i++){
			if(pop.getGenome(i).getFitness() > elite.get(elite.size() - 1).getFitness()){
				pop.setElite(pop.getGenome(i));
			}
		
		}
	}
	
	/**
	 * Performs crossover mating on a population.
	 * @param pop
	 */
	public static void crossover(Population pop){
		for(int i = 0; i < pop.getEliteCount(); i++){
			pop.setGenome(pop.getAllElite().get(i), i);
		}
		for(int i = pop.getEliteCount(); i < pop.size(); i++){
			boolean hasMated = false;
			while(!hasMated){
				Genome xx = chooseRandom(pop);
				Genome yy = chooseRandom(pop);
				if(newMate(xx,yy)){
					pop.setGenome(cross(xx,yy,pop.getCross()), i);
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
			double fit1 = clone.getFitness();
			double fit2 = yy.getFitness();
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
		case SPLIT_4:{
			boolean switchBool = true;
			int x = 0;
			for(int i = 0; i < size; i++){
				if(x == 4){
					switchBool = !switchBool;
					x=0;
				}
				if(switchBool){
					clone.setGene(i, y[i]);
					x++;
				}
				else{
					x++;
				}
			}
			break;
		}
		case RANDOM:{
			if(Math.random() < .5){
				clone.setGene(0, y[0]);
			}
			if(Math.random() < .5){
				clone.setGene(1, y[1]);
			}
			for(int i = 2; i < size; i ++){
				double rand = Math.random();
				if(rand < .2){
					clone.setGene(i, y[i-2]);
				}
				else if(rand < .35){
					clone.setGene(i, y[i-1]);
				}
				else if(rand < .5){
					clone.setGene(i, y[i]);
				}
			}
			break;
		}
		default:
			break;
		}
		clone.testFitness();
		return clone;		
	}
	
	public static void adjustGenomeLength(Population pop){
		boolean adjust = false;
		int newSize = pop.getGeneLength();
		double maxFit = 0;
		for(int i = 0; i < pop.size(); i++){
			Genome currGene = pop.getGenome(i);
			if(currGene.adjustFlag()){
				if(maxFit < currGene.getFitness()){
					maxFit = currGene.getFitness();
					adjust = true;
					newSize = currGene.adjustSize();	
				}
			}
		}
		if(adjust){
			pop.setGeneLength(newSize);
			for(int i = 0; i < pop.size(); i++){
				pop.getGenome(i).adjustSize(newSize);
			}
		}

	}
	
	public static boolean compareGenomes(Genome gen1, Genome gen2){
		return Arrays.equals(gen1.getGenome(),gen2.getGenome());
	}
}
