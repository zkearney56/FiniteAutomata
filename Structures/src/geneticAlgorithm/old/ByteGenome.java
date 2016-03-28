package geneticAlgorithm.old;
/**
import geneticAlgorithm.AbstractGenome;
import geneticAlgorithm.Genome;
import geneticAlgorithm.Algorithm.AlgorithmEnum;
import geneticAlgorithm.Algorithm.CrossoverEnum;

public class ByteGenome extends AbstractGenome implements Genome{

	public ByteGenome(int size, AlgorithmEnum alg) {
		super(size, alg);
		// TODO Auto-generated constructor stub
	}

	public ByteGenome(Genome x){
		super(x);
	}

	@Override
	protected void initGenome(int size) {
		chromosome = new ByteChromosome(size);	
	}

	@Override
	public Genome clone() {
		return new ByteGenome(this);
	}

}
*/