package geneticAlgorithm;

public class CrossoverFunc {

	public static Chromosome crossover(Chromosome x, Chromosome y, Crossover cross){
		Chromosome returnVal = null;
		if(x instanceof BitChromosome && y instanceof BitChromosome){
			returnVal = new BitChromosome((BitChromosome)x, (BitChromosome)y, cross);
			}
		return returnVal;
	}
}
