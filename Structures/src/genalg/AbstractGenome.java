package genalg;

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

import genalg.algorithm.Algorithm;
import genalg.algorithm.AlgorithmEnum;
import genalg.algorithm.Fitness;
import list.ArrayList;

public abstract class AbstractGenome implements Genome {

	protected int size = 0;
	private AlgorithmEnum alg = AlgorithmEnum.BYTE_ALG1;
	protected byte[] genome;
	private ArrayList<Genome> mates;
	private Fitness fitnessobj;
	private double fitness = 0;
	private boolean isElite = false;

	public AbstractGenome(int size, AlgorithmEnum alg) {
		this.size = size;
		this.alg = alg;
		fitnessobj = new Fitness(0, false, new byte[] {});
		genome = new byte[size];
		mates = new ArrayList<Genome>();
		randomize();
	}

	public AbstractGenome(Genome x) {
		this.size = x.getSize();
		this.alg = x.getAlg();
		this.fitness = x.getFitness();
		fitnessobj = new Fitness(fitness, false, new byte[] {});
		isElite = false;
		this.genome = Arrays.copyOf(x.getGenome(), size);
		mates = new ArrayList<Genome>();
	}

	@Override
	public abstract Genome clone();

	@Override
	public abstract byte mutateByte(byte b);

	@Override
	public abstract byte randomByte();

	@Override
	public final double getFitness() {
		return fitness;
	}

	@Override
	public final int getSize() {
		return size;
	}

	@Override
	public final void mutate(int index) {
		genome[index] = mutateByte(genome[index]);
	}

	@Override
	public final void testFitness() {
		fitnessobj = Algorithm.calcFitness(genome, alg);
		fitness = fitnessobj.getFitness();
	}

	@Override
	public final AlgorithmEnum getAlg() {
		return alg;
	}

	@Override
	public final String toString() {
		return Algorithm.toString(genome, alg);
	}

	@Override
	public final byte[] getGenome() {
		return genome;
	}

	@Override
	public final byte getGene(int index) {
		return genome[index];
	}

	@Override
	public final void setElite(boolean bool) {
		isElite = bool;
	}

	@Override
	public final boolean isElite() {
		return isElite;
	}

	@Override
	public void randomize() {
		for (int i = 0; i < size; i++) {
			genome[i] = randomByte();
		}
	}

	@Override
	public final void setGene(int index, byte dat) {
		genome[index] = dat;
	}

	@Override
	public final void addMate(Genome mate) {
		mates.add(mate);
	}

	@Override
	public final boolean hasMated(Genome mate) {
		return mates.contains(mate);
	}

	@Override
	public final void adjustSize(int size) {
		this.size = size;
		genome = Arrays.copyOf(fitnessobj.newByte(), size);
		for (int i = fitnessobj.newByte().length; i < size; i++) {
			genome[i] = randomByte();
		}
	}

	@Override
	public final boolean adjustFlag() {
		return fitnessobj.adjustSize();
	}

	@Override
	public final int adjustSize() {
		return fitnessobj.newByte.length;
	}
}
