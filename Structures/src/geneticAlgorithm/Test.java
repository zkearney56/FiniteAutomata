package geneticAlgorithm;

import geneticAlgorithm.Algorithm.Algorithm;
import geneticAlgorithm.Algorithm.AlgorithmEnum;
import geneticAlgorithm.Algorithm.CrossoverEnum;
import geneticAlgorithm.Byte.BytePopulation;
import geneticAlgorithm.Letter.LetterPopulation;
import geneticAlgorithm.Tower.TowerPopulation;

public class Test {

	public static void main(String args[]){	
		
		AlgorithmEnum alg = AlgorithmEnum.TOWER_ALG2;
		Population testPop = new TowerPopulation(5, Algorithm.testSize(alg), alg, CrossoverEnum.SPLIT_4);
		testPop.setMutCoef(.015);
		testPop.setMaxFit(200);
		testPop.setMaxGen(1000);
		testPop.execute();
		
	}
}