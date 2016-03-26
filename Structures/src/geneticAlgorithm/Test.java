package geneticAlgorithm;

import geneticAlgorithm.Algorithm.Algorithm;
import geneticAlgorithm.Algorithm.AlgorithmEnum;
import geneticAlgorithm.Byte.BytePopulation;
import geneticAlgorithm.Letter.LetterPopulation;

public class Test {

	public static void main(String args[]){
		Algorithm.setLetterSolution("testtesttesttesttesttesttesttest");
		AlgorithmEnum alg = AlgorithmEnum.BYTE_ALG3;
		Population testPop = new BytePopulation(200, Algorithm.testSize(alg), alg, CrossoverEnum.SPLIT_FIT_RATIO);
		testPop.setMutCoef(.2);
		testPop.setMaxFit(40);
		testPop.setMaxGen(2500);
		testPop.execute();
	}
}
