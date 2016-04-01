package geneticAlgorithm;

import geneticAlgorithm.Algorithm.Algorithm;
import geneticAlgorithm.Algorithm.AlgorithmEnum;
import geneticAlgorithm.Algorithm.CrossoverEnum;


public class Test {

	public static void main(String args[]){	
		
		AlgorithmEnum alg = AlgorithmEnum.TOWER_ALG1;
		Algorithm.setTowerCount(4);
		System.out.println(Algorithm.getMaxFitness(alg));
		long numGens = 0;
		double avgFit = 0;
		int numIterations = 50;
		int lowestGen = 0;
		Genome elite = null;
		for(int i = 0; i < numIterations; i++){
		Population testPop = new GeneticPopulation(5, Algorithm.testSize(alg), alg, CrossoverEnum.SPLIT_4);
		testPop.setMutCoef(.015);
		testPop.setMaxGen(50000);
		testPop.setEliteCount(2);
		testPop.execute();
		if(i == 0){
			avgFit = testPop.getElite().getFitness();
			numGens = testPop.getGeneration();
			lowestGen = testPop.getGeneration();
			elite = testPop.getElite();
		}
		else{
			avgFit = (avgFit + testPop.getElite().getFitness())/2;
			numGens = (numGens + testPop.getGeneration())/2;
			if(testPop.getGeneration() < lowestGen){
				lowestGen = testPop.getGeneration();
			}
			if(elite.getFitness() < testPop.getElite().getFitness()){
				elite = testPop.getElite();
			}
		}
		}

		System.out.println("\nNUM ITERATIONS: " + numIterations);
		System.out.println("AVG FITNESS: " + avgFit);
		System.out.println("MAX FITNES : " + Algorithm.getMaxFitness(alg));
		System.out.println("AVG GENERATIONS: " + numGens);
		System.out.println("LOWEST GENERATION: " + lowestGen);
		System.out.println("BEST SOLUTION: " + elite.toString());

	}
}