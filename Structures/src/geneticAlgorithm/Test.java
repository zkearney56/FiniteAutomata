package geneticAlgorithm;

import java.util.ArrayList;

import geneticAlgorithm.Algorithm.Algorithm;
import geneticAlgorithm.Algorithm.AlgorithmEnum;
import geneticAlgorithm.Algorithm.CrossoverEnum;
import geneticAlgorithm.Byte.BytePopulation;
import geneticAlgorithm.Gene.GenePopulation;
import geneticAlgorithm.Letter.LetterPopulation;
import geneticAlgorithm.Tower.TowerPopulation;

public class Test {

	public static void main(String args[]){	
		
		ArrayList<Double> fits = new ArrayList<Double>();
		AlgorithmEnum alg = AlgorithmEnum.TOWER_ALG1;
		Algorithm.setTowerCount(3);
		System.out.println(Algorithm.getMaxFitness(alg));
		int maxFit = 0;
		//for(int i = 0; i < 5; i++){
		Population testPop = new TowerPopulation(5, Algorithm.testSize(alg), alg, CrossoverEnum.SPLIT_4);
		testPop.setMutCoef(.015);
		testPop.setMaxGen(1000);
		testPop.setEliteCount(2);
		testPop.execute();
		fits.add(testPop.getElite().getFitness());
		//if(testPop.getElite().getFitness() > maxFit){
			//maxFit = testPop.getElite().getFitness();
			
		//}
		/**
		 * AVERAGE RUN TIME: 0.005885474860335196
			if(!solutions.contains(testPop.getElite().getGenome())){
			solutions.add(testPop.getElite().getGenome());
			solutFit.add(testPop.getElite().getFitness());
			}
			*/
		//}
		System.out.println("\nMAXIMUM FITNESS FOUND: " + maxFit);
		System.out.println(fits.toString());
		System.out.println(Algorithm.getMaxFitness(alg));
		/**
		for(int i = 0; i < solutions.size(); i++){
			System.out.println(Algorithm.toString(solutions.get(i), alg));
			System.out.println(solutFit.get(i));
		}
		*/
	}
}