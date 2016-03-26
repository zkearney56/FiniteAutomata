package geneticAlgorithm.Letter;

import geneticAlgorithm.AbstractChromosome;
import geneticAlgorithm.Chromosome;
import geneticAlgorithm.CrossoverEnum;
import geneticAlgorithm.Algorithm.AlgorithmEnum;

public class LetterChromosome extends AbstractChromosome implements Chromosome{

	public LetterChromosome(int size, AlgorithmEnum alg) {
		super(size, alg);
		// TODO Auto-generated constructor stub
	}

	public LetterChromosome(Chromosome x){
		super(x);
	}


	@Override
	protected void initGenome(int size) {
		genome = new LetterGenome(size);	
	}

	@Override
	public Chromosome clone() {
		return new LetterChromosome(this);
	}

}
