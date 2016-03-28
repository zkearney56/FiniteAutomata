package geneticAlgorithm;

/*
 * Written by: Zachary Kearney
 * Copyright by: Zachary Kearney, 2016
 *
 * Program: AbstractGenome.java
 * Date: March 22, 2016
 *
 * Description: Abstract class for implementation of a Genome.
 * 
 */


import java.util.Arrays;
import java.util.Random;

import geneticAlgorithm.Algorithm.Algorithm;
import geneticAlgorithm.Algorithm.AlgorithmEnum;
import list.ArrayList;

public abstract class AbstractGenome implements Genome{

	private int size = 0;
	private AlgorithmEnum alg = AlgorithmEnum.BYTE_ALG1;
	private byte[] genome;
	private ArrayList<Genome> mates;
	private int fitness = 0;
	private boolean isElite = false;
	
	public AbstractGenome(int size, AlgorithmEnum alg){
		this.size = size;
		this.alg = alg;
		genome = new byte[size];
		mates = new ArrayList<Genome>();
		randomize();
		testFitness();
	}
	
	public AbstractGenome(Genome x){
		this.size = x.getSize();
		this.alg = x.getAlg();
		this.fitness = x.getFitness();
		isElite = false;
		this.genome = Arrays.copyOf(x.getGenome(), size);
		mates = new ArrayList<Genome>();
	}
	
	public abstract Genome clone();
	
	public abstract byte mutateByte(byte b);
	
	public abstract byte randomByte();
	
	public final int getFitness(){
		return fitness;
	}
	
	public final int getSize(){
		return size;
	}
	
	public final void mutate(int index){
		genome[index] = mutateByte(genome[index]);
		testFitness();
	}
	
	public final void testFitness(){
		fitness = Algorithm.calcFitness(genome, alg);
	}
	
	public final AlgorithmEnum getAlg() {
		return alg;
	}
	
	public final String toString(){
		return Algorithm.toString(genome, alg);
	}
	
	public final byte[] getGenome(){
		return genome;
	}
	
	public final byte getGene(int index){
		return genome[index];
	}
	
	public final void setElite(boolean bool){
		isElite = bool;
	}
	
	public final boolean isElite(){
		return isElite;
	}
	
	public final void randomize() {
		for(int i = 0; i < size; i++){
			genome[i] = randomByte();
		}
	}
	
	public final void setGene(int index, byte dat){
		genome[index] = dat;
	}
	
	public final void addMate(Genome mate){
		mates.add(mate);
	}
	
	public final boolean hasMated(Genome mate){
		return mates.contains(mate);
	}

}
