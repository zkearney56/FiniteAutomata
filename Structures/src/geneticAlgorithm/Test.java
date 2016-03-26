package geneticAlgorithm;

import geneticAlgorithm.Algorithm.Algorithm;
import geneticAlgorithm.Algorithm.AlgorithmEnum;
import geneticAlgorithm.Algorithm.CrossoverEnum;
import geneticAlgorithm.Byte.BytePopulation;
import geneticAlgorithm.Letter.LetterPopulation;

public class Test {

	public static void main(String args[]){
		
		Algorithm.setLetterSolution("harry");
		AlgorithmEnum alg = AlgorithmEnum.BYTE_ALG4;
		Population testPop = new BytePopulation(10, Algorithm.testSize(alg), alg, CrossoverEnum.SPLIT_FIT_RATIO);
		testPop.setMutCoef(.015);
		testPop.setMaxFit(250);
		testPop.setMaxGen(2500);
		testPop.execute();
		
	}
}
