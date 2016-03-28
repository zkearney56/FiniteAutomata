package geneticAlgorithm.old;
/**
import geneticAlgorithm.AbstractGenome;
import geneticAlgorithm.Genome;
import geneticAlgorithm.Algorithm.AlgorithmEnum;
import geneticAlgorithm.Algorithm.CrossoverEnum;

public class LetterGenome extends AbstractGenome implements Genome{

	public LetterGenome(int size, AlgorithmEnum alg) {
		super(size, alg);
		// TODO Auto-generated constructor stub
	}

	public LetterGenome(Genome x){
		super(x);
	}


	@Override
	protected void initGenome(int size) {
		chromosome = new LetterChromosome(size);	
	}

	@Override
	public Genome clone() {
		return new LetterGenome(this);
	}

}
*/