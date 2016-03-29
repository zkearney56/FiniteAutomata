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
import geneticAlgorithm.Algorithm.Fitness;
import list.ArrayList;

public abstract class AbstractGenome implements Genome{

	protected int size = 0;
	private AlgorithmEnum alg = AlgorithmEnum.BYTE_ALG1;
	protected byte[] genome;
	private ArrayList<Genome> mates;
	private Fitness fitnessobj;
	private int fitness = 0;
	private boolean isElite = false;
	
	public AbstractGenome(int size, AlgorithmEnum alg){
		this.size = size;
		this.alg = alg;
		fitnessobj = new Fitness(0,false, new byte[] {});
		genome = new byte[size];
		mates = new ArrayList<Genome>();
		randomize();
		testFitness();
	}
	
	public AbstractGenome(Genome x){
		this.size = x.getSize();
		this.alg = x.getAlg();
		this.fitness = x.getFitness();
		fitnessobj = new Fitness(fitness, false, new byte[] {});
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
		fitnessobj = Algorithm.calcFitness(genome, alg);
		fitness = fitnessobj.getFitness();
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
	
	public void randomize() {
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

	public final void adjustSize(int size){
		this.size = size;
		genome = Arrays.copyOf(fitnessobj.newByte(), size);	
		for(int i = fitnessobj.newByte().length; i < size; i++){
			genome[i] = randomByte();
		}
	}
	
	public final boolean adjustFlag(){
		return fitnessobj.adjustSize();
	}
	
	public final int adjustSize(){
		return fitnessobj.newByte.length;
	}
}
