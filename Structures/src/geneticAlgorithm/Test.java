package geneticAlgorithm;

import geneticAlgorithm.Algorithm.Algorithm;
import geneticAlgorithm.Algorithm.AlgorithmEnum;
import geneticAlgorithm.Algorithm.CrossoverEnum;
import geneticAlgorithm.Byte.BytePopulation;
import geneticAlgorithm.Letter.LetterPopulation;
import geneticAlgorithm.Tower.TowerPopulation;

public class Test {

	public static void main(String args[]){
		
		Algorithm.setLetterSolution("cccccccc");
		AlgorithmEnum alg = AlgorithmEnum.TOWER_ALG;
		Population testPop = new TowerPopulation(20, Algorithm.testSize(alg), alg, CrossoverEnum.SPLIT);
		testPop.setMutCoef(.015);
		testPop.setMaxFit(200);
		testPop.setMaxGen(25000);
		testPop.execute();
		
	}
}
