package geneticAlgorithm;

import geneticAlgorithm.Algorithm.AlgorithmEnum;

public interface Chromosome extends Cloneable{

	public int getFitness();
	public int getSize();
	public void mutate(int index);
	public Object getGene(int index);
	public AlgorithmEnum getAlg();
	public Genome getGenome();
	public void setElite(boolean bool);
	public boolean isElite();
	public Chromosome crossover(Chromosome mate, CrossoverEnum type);
	public Chromosome clone();
}
