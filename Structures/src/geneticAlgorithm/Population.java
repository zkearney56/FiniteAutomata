package geneticAlgorithm;

/*
 * Written by: Zachary Kearney
 * Copyright by: Zachary Kearney, 2016
 *
 * Program: Population.java
 * Date: March 22, 2016
 *
 * Description: Interface for implementation of a Population of Genomes.
 * 
 */

import geneticAlgorithm.Algorithm.AlgorithmEnum;
import geneticAlgorithm.Algorithm.CrossoverEnum;
import list.ArrayList;

public interface Population {

	/**
	 * Sets the maximum number of generations.
	 * @param maxGen
	 */
	void setMaxGen(int maxGen);

	/**
	 * Sets the mutation coefficient.
	 * @param mutcoef
	 */
	void setMutCoef(double mutcoef);

	/**
	 * Returns the mutation coefficient.
	 * @return
	 */
	double getMutCoef();

	/**
	 * Returns the maximum number of generations.
	 * @return
	 */
	int getMaxGen();

	/**
	 * Returns number of mutations for the current generation.
	 * @return
	 */
	double getMutCount();
	
	/**
	 * Returns the Algorithm currently in use.
	 * @return
	 */
	AlgorithmEnum getAlg();
	
	/**
	 * Returns the type of crossover currently in use.
	 * @return
	 */
	CrossoverEnum getCross();

	/**
	 * Returns the Genome at the specified index.
	 * @param index
	 * @return
	 */
	Genome getGenome(int index);
	
	/**
	 * Sets genome at specified index.
	 * @param e
	 * @param index
	 */
	void setGenome(Genome e, int index);

	/**
	 * Executes the genetic algorithm.
	 */
	void execute();
	
	/**
	 * Returns number of genomes.
	 * @return
	 */
	int size();
	
	/**
	 * Sets a genome as elite.
	 * @param elite
	 */
	void setElite(Genome elite);
	
	/**
	 * Increments mutation counter.
	 */
	public void incMutCount();
	
	/**
	 * Decrements mutation counter.
	 */
	public void decMutCount();
	
	/**
	 * Returns current elite Genome.
	 * @return
	 */
	Genome getElite();
	
	/**
	 * Returns all elite genomes.
	 */
	public ArrayList<Genome> getAllElite();
	/**
	 * Returns a new Genome with specified geneLength and algorithm.
	 * @param geneLength
	 * @param alg
	 * @return
	 */
	Genome generateGenome(int geneLength, AlgorithmEnum alg);
	
	/**
	 * Adjusts genomeLength for population.
	 * @param geneLength
	 */
	public void setGeneLength(int geneLength);
	
	/**
	 * Returns current geneLength.
	 * @return
	 */
	public int getGeneLength();

	/**
	 * Sets the number of distinct elite genomes for every generation.
	 * Must be larger than 1.
	 */
	public void setEliteCount(int num);
	
	/**
	 * Returns number of distinct elite genomes for every generation.
	 * @return
	 */
	public int getEliteCount();
}