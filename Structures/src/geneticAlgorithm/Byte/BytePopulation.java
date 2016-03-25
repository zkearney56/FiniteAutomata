package geneticAlgorithm.Byte;

import geneticAlgorithm.Chromosome;
import geneticAlgorithm.Crossover;
import geneticAlgorithm.Population;
import geneticAlgorithm.Algorithm.AlgorithmEnum;

public class BytePopulation extends Population{

	public BytePopulation(int count, int geneLength, AlgorithmEnum alg, Crossover type) {
		super(count, geneLength, alg, type);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected Chromosome addNewChromosome(int geneLength, AlgorithmEnum alg) {
		return new ByteChromosome(geneLength, alg);
	}

}
