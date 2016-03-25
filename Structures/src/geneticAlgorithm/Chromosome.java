package geneticAlgorithm;

import geneticAlgorithm.Algorithm.AlgorithmEnum;

public interface Chromosome {

	public int getFitness();
	public int getSize();
	public void mutate(int index);
	public Object getGene(int index);
	public AlgorithmEnum getAlg();
	public Genome getGenome();
	public void setElite(boolean bool);
	public boolean isElite();
}
