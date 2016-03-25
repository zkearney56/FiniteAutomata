package geneticAlgorithm;

import geneticAlgorithm.Byte.ByteChromosome;
import geneticAlgorithm.Letter.LetterChromosome;

public class CrossoverFunc {

	public static Chromosome crossover(Chromosome x, Chromosome y, Crossover cross){
		Chromosome returnVal = null;
		if(x instanceof ByteChromosome && y instanceof ByteChromosome){
			returnVal = new ByteChromosome((ByteChromosome)x, (ByteChromosome)y, cross);
			}
		else if(x instanceof LetterChromosome && y instanceof LetterChromosome){
			returnVal = new LetterChromosome((LetterChromosome)x, (LetterChromosome)y, cross);
			}
		return returnVal;
	}
}
