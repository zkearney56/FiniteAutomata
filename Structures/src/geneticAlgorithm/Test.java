package geneticAlgorithm;

import geneticAlgorithm.Algorithm.Algorithm;
import geneticAlgorithm.Algorithm.AlgorithmEnum;
import geneticAlgorithm.Algorithm.CrossoverEnum;
import geneticAlgorithm.Byte.BytePopulation;
import geneticAlgorithm.Letter.LetterPopulation;

public class Test {

	public static void main(String args[]){
		
		Algorithm.setLetterSolution("cccccccc");
		AlgorithmEnum alg = AlgorithmEnum.BYTE_ALG6;
		Population testPop = new BytePopulation(200, Algorithm.testSize(alg), alg, CrossoverEnum.SPLIT_FIT_RATIO);
		testPop.setMutCoef(.015);
		testPop.setMaxFit(200);
		testPop.setMaxGen(25000);
		testPop.execute();
		
	}
}
