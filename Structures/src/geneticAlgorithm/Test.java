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
		
		AlgorithmEnum alg = AlgorithmEnum.GENE_ALG1;
		//ArrayList<byte[]>solutions = new ArrayList<byte[]>();
		//ArrayList<Integer>solutFit = new ArrayList<Integer>();
		//Algorithm.setGeneSolution(new byte[] {11,11,11,11,11,11,11,11,11,11,11,11});
		int maxFit = 0;
		for(int i = 0; i < 5; i++){
		Population testPop = new GenePopulation(2000, Algorithm.testSize(alg), alg, CrossoverEnum.SPLIT_4);
		testPop.setMutCoef(.015);
		testPop.setMaxFit(200);
		testPop.setMaxGen(1000);
		testPop.execute();
		if(testPop.getElite().getFitness() > maxFit){
			maxFit = testPop.getElite().getFitness();
		}
		/**
			if(!solutions.contains(testPop.getElite().getGenome())){
			solutions.add(testPop.getElite().getGenome());
			solutFit.add(testPop.getElite().getFitness());
			}
			*/
		}
		System.out.println("maxFit: " + maxFit);
		/**
		for(int i = 0; i < solutions.size(); i++){
			System.out.println(Algorithm.toString(solutions.get(i), alg));
			System.out.println(solutFit.get(i));
		}
		*/
	}
}