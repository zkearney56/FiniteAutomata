package geneticAlgorithm;

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
	private int MAX_NUM_FIT = 10;
	private CrossoverEnum crossType = CrossoverEnum.EVERY_OTHER;//Maximum number of gens with same max fitness
	private AlgorithmEnum alg = AlgorithmEnum.BYTE_ALG1;
	
	private double mutCount = 0;
	private int size = 0, currentElite = 0, generation = 0, genCount = 0, geneLength = 0;
	
	private Genome elite;
	private ArrayList<Genome> pop;
	private boolean maxFound = false;


	
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
	public final void setMaxFit(int maxFit){
		MAX_NUM_FIT = maxFit;
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
	public final int getMaxFit(){
		return MAX_NUM_FIT;
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
		firstRun();
		while(generation < MAX_NUM_GEN && !maxFound){
		newGeneration();
		}
		foundMax();
	}
	
	public final int size(){
		return size;
	}
	
	public final void setElite(Genome elite){
		this.elite = elite;
		elite.setElite(true);
	}
	
	public final Genome getElite(){
		return elite;
	}
	
	public final AlgorithmEnum getAlg(){
		return alg;
	}

	public final CrossoverEnum getCross(){
		return crossType;
	}
	
	private void fillPopulation(){
		pop = new ArrayList<Genome>(size);
		for(int i = 0; i < size; i++){
			pop.add(generateGenome(geneLength, alg));
		}
		elite = pop.get(0);
		elite.setElite(true);
		currentElite = elite.getFitness();
	}

	private boolean generationCount(){
		boolean cont = true;
		if(currentElite == elite.getFitness()){
			genCount++;
		}
		else{
			currentElite = elite.getFitness();
			genCount = 0;
		}
		if(genCount >= MAX_NUM_FIT){
			cont = false;
			maxFound = true;
		}
		return cont;
	}
	
	private void firstRun(){
		for(int i = 0; i < pop.size(); i++){
			if(pop.get(i).getFitness() >= currentElite){
				elite = pop.get(i);
				
			}
			currentElite = elite.getFitness();
			elite.setElite(true);
		}
	}
	
	private void newGeneration(){
		
		GeneticFunc.findElite(this);
		printData();
		if(!generationCount()) return;
		GeneticFunc.crossover(this);
		GeneticFunc.mutate(this);
		GeneticFunc.adjustGenomeLength(this);
		generation++;
		
	}
	
	private void printData(){
		System.out.println("GENERATION: " + generation);
		System.out.println("MAX FITNESS:" + elite.getFitness());
		System.out.println("VALUE: " + Algorithm.intVal(elite.getGenome()));
		System.out.println(elite.toString());
	}
	
	private void foundMax(){
		System.out.println("\nSOLUTION FOUND");
		System.out.println("GENERATION: " + generation);
		System.out.println("MAX FITNESS:" + elite.getFitness());
		System.out.println("VALUE: " + Algorithm.intVal(elite.getGenome()));
		System.out.println(elite.toString());
	}
	
}
