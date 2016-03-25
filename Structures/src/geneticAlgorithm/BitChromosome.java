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

	@Override
	protected void crossover(int size, Chromosome x, Chromosome y, Crossover type) {
		genome = new BitGenome(size, x.getGenome(), y.getGenome(), type);
		//System.out.println(x.getGenome().toString() + " " + x.getValue());
		//System.out.println(x.getGenome().toString() + " " + x.getGenome().getIntVal());
		
	}
	
}
