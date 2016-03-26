package geneticAlgorithm.Letter;

import geneticAlgorithm.Chromosome;
import geneticAlgorithm.CrossoverEnum;
import geneticAlgorithm.AbstractPopulation;
import geneticAlgorithm.Algorithm.AlgorithmEnum;

public class LetterPopulation extends AbstractPopulation{

	public LetterPopulation(int count, int geneLength, AlgorithmEnum alg, CrossoverEnum type) {
		super(count, geneLength, alg, type);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected Chromosome addNewChromosome(int geneLength, AlgorithmEnum alg) {
		return new LetterChromosome(geneLength, alg);
	}

}
