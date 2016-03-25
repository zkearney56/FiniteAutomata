package geneticAlgorithm;

import geneticAlgorithm.Algorithm.Algorithm;
import geneticAlgorithm.Algorithm.AlgorithmEnum;

public abstract class AbstractChromosome implements Chromosome{

	private int fitness = 0;
	private int size = 0;
	private AlgorithmEnum alg = AlgorithmEnum.BYTE_ALG1;
	protected Genome genome;
	private boolean isElite = false;
	
	public AbstractChromosome(int size, AlgorithmEnum alg){
		this.size = size;
		this.alg = alg;
		initGenome(size);
		genome.randomize();
		testFitness();
	}
	
	public AbstractChromosome(Chromosome x, Chromosome y, Crossover type){
		this.size = x.getSize();
		this.alg = x.getAlg();
		crossover(size, x, y, type);
		testFitness();
	}
	
	protected abstract void crossover(int size, Chromosome x, Chromosome y, Crossover type);
	
	public final int getFitness(){
		return Algorithm.calcFitness(genome.getGenome(), alg);
	}
	
	public final int getSize(){
		return size;
	}
	
	public final void mutate(int index){
		genome.mutate(index);
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
