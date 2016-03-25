package geneticAlgorithm;

public class Test {

	public static void main(String args[]){
		Population bitPop = new BitPopulation(25, 4, AlgorithmEnum.ALG1, Crossover.SPLIT);
		bitPop.execute();
	}
}
