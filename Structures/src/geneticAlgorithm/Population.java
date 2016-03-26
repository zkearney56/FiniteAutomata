package geneticAlgorithm;

import geneticAlgorithm.Algorithm.AlgorithmEnum;

public interface Population {

	void setMaxGen(int maxGen);

	void setMaxFit(int maxFit);

	void setMutCoef(double mutcoef);

	double getMutCoef();

	int getMaxGen();

	int getMaxFit();

	double getMutCount();
	
	AlgorithmEnum getAlg();
	
	CrossoverEnum getCross();

	Chromosome getChromosome(int index);
	
	void setChromosome(Chromosome e, int index);

	void execute();
	
	int size();
	
	void setElite(Chromosome elite);
	
	public void incMutCount();
	
	public void decMutCount();
	
	Chromosome getElite();

}