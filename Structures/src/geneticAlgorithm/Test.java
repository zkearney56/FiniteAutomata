package geneticAlgorithm;

import geneticAlgorithm.Algorithm.Algorithm;
import geneticAlgorithm.Algorithm.AlgorithmEnum;
import geneticAlgorithm.Letter.LetterPopulation;

public class Test {

	public static void main(String args[]){
		Algorithm.setLetterSolution("savannahcurcio");
		Population testPop = new LetterPopulation(200, Algorithm.LETTERSOLUTION.length, AlgorithmEnum.LET_ALG1, Crossover.SPLIT);
		testPop.setMaxFit(200);
		testPop.setMaxGen(1000);
		testPop.execute();
	}
}
