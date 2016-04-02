package genalg;

import genalg.algorithm.AlgorithmEnum;
import genalg.algorithm.CrossoverEnum;
import list.ArrayList;

public interface Population {

	/**
	 * Sets the maximum number of generations.
	 * 
	 * @param maxGen
	 */
	void setMaxGen(int maxGen);

	/**
	 * Sets the mutation coefficient.
	 * 
	 * @param mutcoef
	 */
	void setMutCoef(double mutcoef);

	/**
	 * Returns the mutation coefficient.
	 * 
	 * @return
	 */
	double getMutCoef();

	/**
	 * Returns the maximum number of generations.
	 * 
	 * @return
	 */
	int getMaxGen();

	/**
	 * Returns number of mutations for the current generation.
	 * 
	 * @return
	 */
	double getMutCount();

	/**
	 * Returns the Algorithm currently in use.
	 * 
	 * @return
	 */
	AlgorithmEnum getAlg();

	/**
	 * Returns the type of crossover currently in use.
	 * 
	 * @return
	 */
	CrossoverEnum getCross();

	/**
	 * Returns the Genome at the specified index.
	 * 
	 * @param index
	 * @return
	 */
	Genome getGenome(int index);

	/**
	 * Sets genome at specified index.
	 * 
	 * @param e
	 * @param index
	 */
	void setGenome(Genome e, int index);

	/**
	 * Adds a genome to a population.
	 * 
	 * @param e
	 */
	void addGenome(Genome e);

	/**
	 * Adds a genome at the specific index.
	 * 
	 * @param e
	 * @param index
	 */
	void addGenome(Genome e, int index);

	/**
	 * Remove the genome at a given index.
	 * 
	 * @param index
	 * @return
	 */
	Genome removeGenome(int index);

	/**
	 * Executes the genetic algorithm.
	 */
	void execute();

	/**
	 * Returns number of genomes.
	 * 
	 * @return
	 */
	int size();

	/**
	 * Sets a genome as elite.
	 * 
	 * @param elite
	 */
	void setElite(Genome elite);

	/**
	 * Sets the current maximum fitness;
	 * 
	 * @param fit
	 */
	void setCurrentMaxFit(double fit);

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
	 * 
	 * @return
	 */
	Genome getElite();

	/**
	 * Returns all elite genomes.
	 */
	public ArrayList<Genome> getAllElite();

	/**
	 * Adjusts genomeLength for population.
	 * 
	 * @param geneLength
	 */
	public void setGeneLength(int geneLength);

	/**
	 * Returns current geneLength.
	 * 
	 * @return
	 */
	public int getGeneLength();

	/**
	 * Sets the number of distinct elite genomes for every generation. Must be
	 * larger than 1.
	 */
	public void setEliteCount(int num);

	/**
	 * Returns number of distinct elite genomes for every generation.
	 * 
	 * @return
	 */
	public int getEliteCount();

	/**
	 * Returns the final generation count.
	 * 
	 * @return
	 */
	public int getGeneration();

	/**
	 * Returns the total runtime for this population.
	 * 
	 * @return
	 */
	public double getRunTime();

	/**
	 * Returns the average runtime for each generation.
	 * 
	 * @return
	 */
	public double getAvgRunTime();

	/**
	 * Returns the fitness of the elite genome.
	 * 
	 * @return
	 */
	public double getEliteFitness();

	/**
	 * Adds elite to elite population;
	 */
	public void addElite(Genome e);
}