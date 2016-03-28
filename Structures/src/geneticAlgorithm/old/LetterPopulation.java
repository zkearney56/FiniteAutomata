package geneticAlgorithm.old;
/**
import geneticAlgorithm.Genome;
import geneticAlgorithm.AbstractPopulation;
import geneticAlgorithm.Algorithm.AlgorithmEnum;
import geneticAlgorithm.Algorithm.CrossoverEnum;

public class LetterPopulation extends AbstractPopulation{

	public LetterPopulation(int count, int geneLength, AlgorithmEnum alg, CrossoverEnum type) {
		super(count, geneLength, alg, type);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected Genome addNewChromosome(int geneLength, AlgorithmEnum alg) {
		return new LetterGenome(geneLength, alg);
	}

}
*/