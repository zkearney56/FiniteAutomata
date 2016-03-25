package geneticAlgorithm;

import list.ArrayList;

public class BitChromosome extends AbstractChromosome implements Chromosome{
	
	public BitChromosome(int size, AlgorithmEnum alg){
		super(size, alg);
	}
	
	public BitChromosome(Chromosome x, Chromosome y, Crossover cross){
		super(x,y,cross);
	}

	protected void initGenome(int size) {
		genome = new BitGenome(size);
	}
	
}
