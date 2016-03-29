package geneticAlgorithm.Tower;

import geneticAlgorithm.AbstractPopulation;
import geneticAlgorithm.Genome;
import geneticAlgorithm.Algorithm.AlgorithmEnum;
import geneticAlgorithm.Algorithm.CrossoverEnum;

public class TowerPopulation extends AbstractPopulation{

	public TowerPopulation(int size, int geneLength, AlgorithmEnum alg, CrossoverEnum type) {
		super(size, geneLength, alg, type);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Genome generateGenome(int geneLength, AlgorithmEnum alg) {
		// TODO Auto-generated method stub
		return new TowerGenome(geneLength, alg);
	}

}
