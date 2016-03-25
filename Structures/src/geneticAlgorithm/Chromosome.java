package geneticAlgorithm;

public interface Chromosome {

	public int getFitness();
	public int getSize();
	public void mutate(int index);
	public int getValue();
	public Object getGene(int index);
	public AlgorithmEnum getAlg();
	public Genome getGenome();
}
