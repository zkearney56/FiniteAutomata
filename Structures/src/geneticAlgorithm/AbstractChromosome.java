package geneticAlgorithm;

import geneticAlgorithm.Algorithm.Algorithm;
import geneticAlgorithm.Algorithm.AlgorithmEnum;

public abstract class AbstractChromosome implements Chromosome{

	private int size = 0;
	private AlgorithmEnum alg = AlgorithmEnum.BYTE_ALG1;
	protected Genome genome;
	private int fitness = 0;
	private boolean isElite = false;
	
	public AbstractChromosome(int size, AlgorithmEnum alg){
		this.size = size;
		this.alg = alg;
		initGenome(size);
		genome.randomize();
		testFitness();
	}
	
	public AbstractChromosome(Chromosome x){
		this.size = x.getSize();
		this.alg = x.getAlg();
		this.fitness = x.getFitness();
		isElite = false;
		this.genome = x.getGenome().clone();
	}
	
	
	public final Chromosome crossover(Chromosome mate, CrossoverEnum type){
		Chromosome clone = this.clone();
		if(type == CrossoverEnum.SPLIT_FIT_RATIO){
		clone.getGenome().crossover(mate.getGenome(), type, clone.getFitness(), mate.getFitness());
		}
		else{
		clone.getGenome().crossover(mate.getGenome(), type, 0, 0);
		}
		testFitness();
		return clone;
	}
	
	public abstract Chromosome clone();
	
	public final int getFitness(){
		return fitness;
	}
	
	public final int getSize(){
		return size;
	}
	
	public final void mutate(int index){
		genome.mutate(index);
		testFitness();
	}
	
	protected abstract void initGenome(int size);
	
	public final void testFitness(){
		fitness = Algorithm.calcFitness(genome.getGenome(), alg);
	}
	
	public final AlgorithmEnum getAlg() {
		// TODO Auto-generated method stub
		return alg;
	}
	
	public final String toString(){
		return genome.toString();
	}
	
	public final Genome getGenome(){
		return genome;
	}
	
	public Object getGene(int index){
		return genome.get(index);
	}
	
	public final void randomize(){
		genome.randomize();
	}
	
	public final void setElite(boolean bool){
		isElite = bool;
	}
	
	public final boolean isElite(){
		return isElite;
	}
}
