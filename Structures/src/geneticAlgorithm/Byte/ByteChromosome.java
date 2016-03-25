package geneticAlgorithm.Byte;

import geneticAlgorithm.AbstractChromosome;
import geneticAlgorithm.Chromosome;
import geneticAlgorithm.Crossover;
import geneticAlgorithm.Algorithm.AlgorithmEnum;

public class ByteChromosome extends AbstractChromosome implements Chromosome{

	public ByteChromosome(int size, AlgorithmEnum alg) {
		super(size, alg);
		// TODO Auto-generated constructor stub
	}

	public ByteChromosome(Chromosome x, Chromosome y, Crossover cross){
		super(x,y,cross);
	}

	@Override
	protected void crossover(int size, Chromosome x, Chromosome y, Crossover type) {
		genome = new ByteGenome(size);
		genome.crossover(x.getGenome(), y.getGenome(), type);
		
	}

	@Override
	protected void initGenome(int size) {
		genome = new ByteGenome(size);	
	}

}
