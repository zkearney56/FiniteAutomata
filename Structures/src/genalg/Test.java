package genalg;

import genalg.algorithm.Algorithm;
import genalg.algorithm.AlgorithmEnum;
import genalg.algorithm.CrossoverEnum;

public class Test {

	public static void main(String args[]) {

		AlgorithmEnum alg = AlgorithmEnum.TOWER_ALG1;
		CrossoverEnum cross = CrossoverEnum.SPLIT;
		Algorithm.setTowerCount(4);
		long numGens = 0;
		double avgFit = 0;
		int numIterations = 25;
		int lowestGen = 0;
		double maxFit = 0;
		double avgRunTime = 0;
		Genome elite = null;
		//low 53
		//avg 1920
		for (int i = 0; i < numIterations; i++) {

			Population testPop = new GeneticPopulation(5, alg, cross);
			testPop.setMutCoef(.015);
			testPop.setMaxGen(25000);
			testPop.setEliteCount(2);
			testPop.execute();
			System.out.println("FINISHED TEST: " + i);

			if (i == 0) {
				avgFit = testPop.getElite().getFitness();
				numGens = testPop.getGeneration();
				lowestGen = testPop.getGeneration();
				elite = testPop.getElite();
				maxFit = testPop.getEliteFitness();
				avgRunTime += testPop.getRunTime();
			}

			else {
				avgRunTime = (avgRunTime + testPop.getRunTime())/2;
				if(maxFit < testPop.getEliteFitness()){
					maxFit = testPop.getEliteFitness();
				}
				avgFit = (avgFit + testPop.getElite().getFitness()) / 2;
				numGens = (numGens + testPop.getGeneration()) / 2;

				if (testPop.getGeneration() < lowestGen) {
					lowestGen = testPop.getGeneration();
				}

				if (elite.getFitness() < testPop.getElite().getFitness()) {
					elite = testPop.getElite();
				}

			}
		}

		System.out.println("\nNUM ITERATIONS: " + numIterations);
		System.out.println("AVG FITNESS: " + avgFit);
		System.out.println("MAX CALC FITNESS: " + maxFit);
		System.out.println("MAX FITNESS: " + Algorithm.getMaxFitness(alg));
		System.out.println("AVG RUNTIME: " + avgRunTime);
		System.out.println("AVG GENERATIONS: " + numGens);
		System.out.println("LOWEST GENERATION: " + lowestGen);
		System.out.println("BEST SOLUTION: \n" + elite.toString());

	}
}