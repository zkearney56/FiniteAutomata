package geneticAlgorithm.Letter;

import geneticAlgorithm.AbstractChromosome;
import geneticAlgorithm.Chromosome;
import geneticAlgorithm.Crossover;
import geneticAlgorithm.Algorithm.AlgorithmEnum;

public class LetterChromosome extends AbstractChromosome implements Chromosome{

	public LetterChromosome(int size, AlgorithmEnum alg) {
		super(size, alg);
		// TODO Auto-generated constructor stub
	}

	public LetterChromosome(Chromosome x, Chromosome y, Crossover cross){
		super(x,y,cross);
	}

	@Override
	protected void crossover(int size, Chromosome x, Chromosome y, Crossover type) {
		genome = new LetterGenome(size);
		genome.crossover(x.getGenome(), y.getGenome(), type);
		
	}

	@Override
	protected void initGenome(int size) {
		genome = new LetterGenome(size);	
	}

}
