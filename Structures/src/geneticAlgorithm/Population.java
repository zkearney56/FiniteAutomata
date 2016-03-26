package geneticAlgorithm;

import geneticAlgorithm.Algorithm.AlgorithmEnum;
import geneticAlgorithm.Algorithm.CrossoverEnum;

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

	Genome getChromosome(int index);
	
	void setChromosome(Genome e, int index);

	void execute();
	
	int size();
	
	void setElite(Genome elite);
	
	public void incMutCount();
	
	public void decMutCount();
	
	Genome getElite();

}