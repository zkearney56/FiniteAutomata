package geneticAlgorithm;

import geneticAlgorithm.Algorithm.Algorithm;
import geneticAlgorithm.Algorithm.AlgorithmEnum;

public abstract class AbstractGenome implements Genome{

	private int size = 0;
	private AlgorithmEnum alg = AlgorithmEnum.BYTE_ALG1;
	protected Chromosome chromosome;
	private int fitness = 0;
	private boolean isElite = false;
	
	public AbstractGenome(int size, AlgorithmEnum alg){
		this.size = size;
		this.alg = alg;
		initGenome(size);
		chromosome.randomize();
		testFitness();
	}
	
	public AbstractGenome(Genome x){
		this.size = x.getSize();
		this.alg = x.getAlg();
		this.fitness = x.getFitness();
		isElite = false;
		this.chromosome = x.getGenome().clone();
	}
	
	
	public final Genome crossover(Genome mate, CrossoverEnum type){
		Genome clone = this.clone();
		if(type == CrossoverEnum.SPLIT_FIT_RATIO){
		clone.getGenome().crossover(mate.getGenome(), type, clone.getFitness(), mate.getFitness());
		}
		else{
		clone.getGenome().crossover(mate.getGenome(), type, 0, 0);
		}
		testFitness();
		return clone;
	}
	
	public abstract Genome clone();
	
	public final int getFitness(){
		return fitness;
	}
	
	public final int getSize(){
		return size;
	}
	
	public final void mutate(int index){
		chromosome.mutate(index);
		testFitness();
	}
	
	protected abstract void initGenome(int size);
	
	public final void testFitness(){
		fitness = Algorithm.calcFitness(chromosome.getChromosome(), alg);
	}
	
	public final AlgorithmEnum getAlg() {
		// TODO Auto-generated method stub
		return alg;
	}
	
	public final String toString(){
		return chromosome.toString();
	}
	
	public final Chromosome getGenome(){
		return chromosome;
	}
	
	public final byte getGene(int index){
		return chromosome.getGene(index);
	}
	
	public final void randomize(){
		chromosome.randomize();
	}
	
	public final void setElite(boolean bool){
		isElite = bool;
	}
	
	public final boolean isElite(){
		return isElite;
	}
}
