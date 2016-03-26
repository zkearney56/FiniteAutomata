package geneticAlgorithm.Byte;

import geneticAlgorithm.Chromosome;
import geneticAlgorithm.CrossoverEnum;
import geneticAlgorithm.AbstractPopulation;
import geneticAlgorithm.Algorithm.AlgorithmEnum;

public class BytePopulation extends AbstractPopulation{

	public BytePopulation(int count, int geneLength, AlgorithmEnum alg, CrossoverEnum type) {
		super(count, geneLength, alg, type);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected Chromosome addNewChromosome(int geneLength, AlgorithmEnum alg) {
		return new ByteChromosome(geneLength, alg);
	}

}
