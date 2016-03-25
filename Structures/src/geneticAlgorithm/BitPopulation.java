package geneticAlgorithm;

public class BitPopulation extends Population{

	public BitPopulation(int count, int geneLength, AlgorithmEnum alg) {
		super(count, geneLength, alg);
	}

	@Override
	protected Chromosome addNewChromosome(int geneLength, AlgorithmEnum alg) {
		return new BitChromosome(geneLength, alg);
	}

}
