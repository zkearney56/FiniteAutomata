package geneticAlgorithm;

import geneticAlgorithm.Algorithm.AlgorithmEnum;

public interface Genome extends Cloneable{

	public int getFitness();
	public int getSize();
	public void mutate(int index);
	public byte getGene(int index);
	public void setGene(int index, byte val);
	public AlgorithmEnum getAlg();
	public byte[] getGenome();
	public void setElite(boolean bool);
	public boolean isElite();
	public Genome clone();
	public void testFitness();
	public void addMate(Genome mate);
	public boolean hasMated(Genome mate);
	
}
