package geneticAlgorithm;

import java.util.ArrayList;

import geneticAlgorithm.Algorithm.Algorithm;
import geneticAlgorithm.Algorithm.AlgorithmEnum;
import geneticAlgorithm.Algorithm.CrossoverEnum;
import geneticAlgorithm.Byte.BytePopulation;
import geneticAlgorithm.Letter.LetterPopulation;
import geneticAlgorithm.Tower.TowerPopulation;

public class Test {

	public static void main(String args[]){	
		
		AlgorithmEnum alg = AlgorithmEnum.BYTE_ALG7;
		ArrayList<byte[]>solutions = new ArrayList<byte[]>();
		int maxFit = 5;
		for(int i = 0; i < 10; i++){
		Population testPop = new BytePopulation(10, Algorithm.testSize(alg), alg, CrossoverEnum.SPLIT);
		testPop.setMutCoef(.015);
		testPop.setMaxFit(2500);
		testPop.setMaxGen(15000);
		testPop.execute();
		if(testPop.getElite().getFitness() == maxFit){
			if(!solutions.contains(testPop.getElite().getGenome())){
			solutions.add(testPop.getElite().getGenome());
			}
		}
		}
		System.out.println("Solutions: ");
		for(byte[] bytes : solutions){
			System.out.println(Algorithm.toString(bytes, alg));
		}
	}
}