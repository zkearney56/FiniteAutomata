package geneticAlgorithm.Byte;

import geneticAlgorithm.AbstractChromosome;
import geneticAlgorithm.Chromosome;
import geneticAlgorithm.CrossoverEnum;
import geneticAlgorithm.Algorithm.AlgorithmEnum;

public class ByteChromosome extends AbstractChromosome implements Chromosome{

	public ByteChromosome(int size, AlgorithmEnum alg) {
		super(size, alg);
		// TODO Auto-generated constructor stub
	}

	public ByteChromosome(Chromosome x){
		super(x);
	}

	@Override
	protected void initGenome(int size) {
		genome = new ByteGenome(size);	
	}

	@Override
	public Chromosome clone() {
		return new ByteChromosome(this);
	}

}
