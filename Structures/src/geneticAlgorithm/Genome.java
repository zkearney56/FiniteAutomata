package geneticAlgorithm;

/*
 * Written by: Zachary Kearney
 * Copyright by: Zachary Kearney, 2016
 *
 * Program: Genome.java
 * Date: March 22, 2016
 *
 * Description: Interface for implementation of a Genome.
 * 
 */

import geneticAlgorithm.Algorithm.AlgorithmEnum;

public interface Genome extends Cloneable{

	/**
	 * Returns fitness of genome.
	 * @return
	 */
	public int getFitness();
	/**
	 * Returns size of genome.
	 * @return
	 */
	public int getSize();
	/**
	 * Mutates byte at specified index.
	 * @param index
	 */
	public void mutate(int index);
	/**
	 * Returns byte at specified index.
	 * @param index
	 * @return
	 */
	public byte getGene(int index);
	/**
	 * Sets byte at specified index.
	 * @param index
	 * @param val
	 */
	public void setGene(int index, byte val);
	/**
	 * Returns current algorithm in use.
	 * @return
	 */
	public AlgorithmEnum getAlg();
	/**
	 * Returns whole genome as byte array.
	 * @return
	 */
	public byte[] getGenome();
	/**
	 * Sets genome as elite.
	 * @param bool
	 */
	public void setElite(boolean bool);
	/**
	 * Returns true if genome is elite.
	 * @return
	 */
	public boolean isElite();
	/**
	 * Clones genome.
	 * @return
	 */
	public Genome clone();
	/**
	 * Tests fitness of genome.
	 */
	public void testFitness();
	/**
	 * Adds a mate.
	 * @param mate
	 */
	public void addMate(Genome mate);
	/**
	 * Returns true if Genome has mated with specified genome.
	 * @param mate
	 * @return
	 */
	public boolean hasMated(Genome mate);
	/**
	 * Returns a mutated version of byte input.
	 * @param b
	 * @return
	 */
	public byte mutateByte(byte b);
	/**
	 * Method to choose a random byte.
	 * @return
	 */
	public byte randomByte();
	/**
	 * Method to adjust size of genome.
	 * @param size
	 */
	public void adjustSize(int size);
	/**
	 * Returns adjust flag of genome.
	 * @return
	 */
	public boolean adjustFlag();
	/**
	 * Returns new size of genome.
	 * @return
	 */
	public int adjustSize();
	
}
