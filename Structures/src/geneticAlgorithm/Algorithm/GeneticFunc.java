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
import geneticAlgorithm.genomes.ByteGenome;
import geneticAlgorithm.genomes.GeneGenome;
import geneticAlgorithm.genomes.LetterGenome;
import geneticAlgorithm.genomes.TowerGenome;
import list.ArrayList;

public class GeneticFunc {

	/**
	 * Generates a genome of the proper type for a specific algorithm.
	 * @param geneLength
	 * @param alg
	 * @return
	 */
	public static Genome generateGenome(int geneLength, AlgorithmEnum alg){
		switch(alg){
		case BYTE_ALG1:
			return new ByteGenome(geneLength, alg);
		case BYTE_ALG2:
			return new ByteGenome(geneLength, alg);
		case BYTE_ALG3:
			return new ByteGenome(geneLength, alg);
		case BYTE_ALG4:
			return new ByteGenome(geneLength, alg);
		case BYTE_ALG5:
			return new ByteGenome(geneLength, alg);
		case BYTE_ALG6:
			return new ByteGenome(geneLength, alg);
		case BYTE_ALG7:
			return new ByteGenome(geneLength, alg);
		case GENE_ALG1:
			return new GeneGenome(geneLength, alg);
		case LET_ALG1:
			return new LetterGenome(geneLength, alg);
		case TOWER_ALG1:
			return new TowerGenome(geneLength, alg);
		default:
			return null;
		}
	}
	
	/**
	 * Mutates population. If mutcount < 1, Does not mutate anything. 
	 * Else, mutates a random byte based on mutcoef and decrements mutcount until mutcount is less than 1.
	 * @param pop
	 */
	public static void mutate(Population pop){
		pop.incMutCount();
		int x = 1;
		while(x <= pop.getMutCount()){
			for(int i = pop.getEliteCount(); i < pop.size(); i++){
				Genome mutChrom = pop.getGenome(i);
				for(int y = 0; y < mutChrom.getSize(); y++){
					if(Math.random() < pop.getMutCoef()){
						mutChrom.mutate(y);
						pop.decMutCount();
					}
				}
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
		
		double probIndex = 0;
		while(true){
			int randIndex = rand.nextInt(pop.size());
			probIndex += pop.getGenome(randIndex).getFitness();
			if(probIndex >= prob){
				return pop.getGenome(randIndex);
			}
		}
	}
	
	public static void calcFitness(Population pop){
		for(int i = 0; i < pop.size(); i++){
			pop.getGenome(i).testFitness();
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
				setElite(pop, pop.getGenome(i));
			}
		
		}
	}
	
	/**
	 * Attempts to set a genome as elite, and sorts the elite list by descending order of fitness.
	 * @param pop
	 * @param newElite
	 */
	public static void setElite(Population pop, Genome newElite){
		ArrayList<Genome> elite = pop.getAllElite();
		int eliteCount = pop.getEliteCount();
		for(int i = 0; i < elite.size(); i++){
			if(GeneticFunc.compareGenomes(newElite, elite.get(i))) return;
		}
		double fit = newElite.getFitness();
		boolean added = false;
		for(int i = 0; i < elite.size(); i++){
			if(elite.get(i).getFitness() < fit){
				elite.add(i, newElite);
				newElite.setElite(true);
				added = true;
				break;
			}
		}
		if(elite.size() < eliteCount && !added){
			elite.add(newElite);
			newElite.setElite(true);
		}
		while(elite.size() > eliteCount){
			elite.remove(elite.size() - 1);
			}
		pop.setCurrentMaxFit(elite.get(0).getFitness());
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
	
	/**
	 * If an adjust flag is tripped, trims all genomes to length of the strongest genome with a flipped adjust flag.
	 * @param pop
	 */
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
	
	/**
	 * Compares two genomes. Returns true if equal.
	 * @param gen1
	 * @param gen2
	 * @return
	 */
	public static boolean compareGenomes(Genome gen1, Genome gen2){
		return Arrays.equals(gen1.getGenome(),gen2.getGenome());
	}
	
	/**
	 * Fills an initial population with genomes.
	 * @param pop
	 */
	public static void fillPopulation(Population pop){
		for(int i = 0; i < pop.size(); i++){
			pop.addGenome(GeneticFunc.generateGenome(pop.getGeneLength(), pop.getAlg()));
		}
		GeneticFunc.calcFitness(pop);
		double currMaxFit = 0;
		int index = 0;
		for(int x = 0; x < pop.getEliteCount(); x++){
		for(int i = 0; i < pop.size(); i++){
			if(currMaxFit < pop.getGenome(i).getFitness()){
				currMaxFit = pop.getGenome(i).getFitness();
				index = i;
			}
		}
		Genome newElite = pop.removeGenome(index);
		newElite.setElite(true);
		pop.addElite(newElite);
		}
		ArrayList<Genome>elite = pop.getAllElite();
		for(int i = elite.size() - 1; i >= 0; i--){
			pop.addGenome(elite.get(i), 0);
		}
		pop.setCurrentMaxFit(elite.get(0).getFitness());
		GeneticFunc.findElite(pop);
	}
}
