package geneticAlgorithm;

import java.time.LocalTime;

/*
 * Written by: Zachary Kearney
 * Copyright by: Zachary Kearney, 2016
 *
 * Program: AbstractPopulation.java
 * Date: March 22, 2016
 *
 * Description: Abstract class for implementation of a Population.
 * 
 */

import geneticAlgorithm.Algorithm.Algorithm;
import geneticAlgorithm.Algorithm.AlgorithmEnum;
import geneticAlgorithm.Algorithm.CrossoverEnum;
import geneticAlgorithm.Algorithm.GeneticFunc;
import list.ArrayList;

public abstract class AbstractPopulation implements Population {
	
	private double MUT_COEF = .005; //Mutation coefficient
	private int MAX_NUM_GEN = 200; //Maximum number of generations
	private CrossoverEnum crossType = CrossoverEnum.EVERY_OTHER;//Maximum number of gens with same max fitness
	private AlgorithmEnum alg = AlgorithmEnum.BYTE_ALG1;
	
	private double mutCount = 0, currentElite = 0, maxFitness = 0;
	private int size = 0, generation = 0, geneLength = 0, eliteCount = 1;
	private ArrayList<Genome> pop;
	private ArrayList<Genome> elite;
	private LocalTime time;
	private double currTime;
	private double fitAvg = 0;


	
	public AbstractPopulation(int size, int geneLength, AlgorithmEnum alg, CrossoverEnum type){
		this.size = size;
		this.geneLength = geneLength;
		this.alg = alg;
		this.crossType = type;

		fillPopulation();
		setMutCoef(MUT_COEF);
	}
	
	public abstract Genome generateGenome(int geneLength, AlgorithmEnum alg);

	@Override
	public final void setMaxGen(int maxGen){
		MAX_NUM_GEN = maxGen;
	}
	
	@Override
	public final void setMutCoef(double mutcoef){
		MUT_COEF = mutcoef;
	}
	
	public final void incMutCount(){
		mutCount += (size * geneLength) * MUT_COEF;
	}
	
	public final void decMutCount(){
		mutCount--;
	}

	@Override
	public final double getMutCoef(){
		return MUT_COEF;
	}
	
	@Override
	public final int getMaxGen(){
		return MAX_NUM_GEN;
	}

	@Override
	public final double getMutCount(){
		return mutCount;
	}

	@Override
	public final Genome getGenome(int index){
		return pop.get(index);
	}
	
	public final void setGenome(Genome e, int index){
		pop.set(e, index);
	}
	
	public final int getGeneLength(){
		return geneLength;
	}
	
	public final void setGeneLength(int geneLength){
		this.geneLength = geneLength;
	}

	@Override
	public final void execute(){
		maxFitness = Algorithm.getMaxFitness(alg);
		fillPopulation();
		time = LocalTime.now();
		currTime = time.toNanoOfDay();
		fitAvg = currentElite;
		while(generation < MAX_NUM_GEN && currentElite < maxFitness){
		newGeneration();
		}
		foundMax();
	}
	
	public final int size(){
		return size;
	}
	
	public final void setElite(Genome newElite){
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
		currentElite = elite.get(0).getFitness();
		}
	
	public final Genome getElite(){
		return elite.get(0);
	}
	
	public final AlgorithmEnum getAlg(){
		return alg;
	}

	public final CrossoverEnum getCross(){
		return crossType;
	}
	
	private void fillPopulation(){
		pop = new ArrayList<Genome>(size);
		elite = new ArrayList<Genome>(eliteCount);
		for(int i = 0; i < size; i++){
			pop.add(generateGenome(geneLength, alg));
		}
		double currMaxFit = 0;
		int index = 0;
		for(int x = 0; x < eliteCount; x++){
		for(int i = 0; i < pop.size(); i++){
			if(currMaxFit < pop.get(i).getFitness()){
				currMaxFit = pop.get(i).getFitness();
				index = i;
			}
		}
		Genome addGen = pop.remove(index);
		addGen.setElite(true);
		elite.add(addGen);
		}
		for(int i = elite.size() - 1; i >= 0; i--){
			pop.add(0,elite.get(i));
		}
		currentElite = elite.get(0).getFitness();
		GeneticFunc.findElite(this);
	}

	private void newGeneration(){
		GeneticFunc.findElite(this);
		//printData();
		GeneticFunc.crossover(this);
		GeneticFunc.mutate(this);
		GeneticFunc.adjustGenomeLength(this);
		generation++;
		//time = LocalTime.now();
		//currTime = (time.toNanoOfDay() - currTime)/1000000000;
		//System.out.println("AVERAGE RUN TIME: " + currTime/2);
		//System.out.println("FITNESS INCREASE: " + ((currentElite/fitAvg)-1)*100 + "%");
		//System.out.println("CURRENTFITNESS: " + currentElite);
		///fitAvg = currentElite;
		//currTime = time.toNanoOfDay();
		//System.out.println("GENERATION: " + generation);	
	}
	
	private void printData(){
		System.out.println("GENERATION: " + generation);
		System.out.println("MAX FITNESS:" + elite.get(0).getFitness());
		System.out.println("VALUE: " + Algorithm.intVal(elite.get(0).getGenome()));
		System.out.println(elite.toString());
	}
	
	private void foundMax(){
		System.out.println("\nSOLUTION FOUND");
		time = LocalTime.now();
		currTime = (time.toNanoOfDay() - currTime)/1000000000;
		System.out.println("AVERAGE RUN TIME: " + currTime/generation);
		System.out.println("GENERATION: " + generation);
		System.out.println("MAX FITNESS:" + elite.get(0).getFitness());
		System.out.println("VALUE: " + Algorithm.intVal(elite.get(0).getGenome()));
		System.out.println(elite.get(0).toString());
	}
	
	public ArrayList<Genome> getAllElite(){
		return elite;
	}
	
	public void setEliteCount(int num){
		if(num <= num){
			eliteCount = 1;
		}
		else if (num >= size){
			eliteCount = size - 1;
		}
		else{
			eliteCount = size;
		}
	}
	
	public int getEliteCount(){
		return eliteCount;
	}
	
}
