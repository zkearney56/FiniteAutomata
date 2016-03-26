package geneticAlgorithm.old;

import geneticAlgorithm.Algorithm.AlgorithmEnum;

public interface Genome extends Cloneable{

	public int getFitness();
	public int getSize();
	public void mutate(int index);
	public byte getGene(int index);
	public AlgorithmEnum getAlg();
	public Chromosome getGenome();
	public void setElite(boolean bool);
	public boolean isElite();
	public Genome crossover(Genome mate, CrossoverEnum type);
	public Genome clone();
}
