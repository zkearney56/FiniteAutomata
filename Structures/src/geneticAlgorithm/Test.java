package geneticAlgorithm;

public class Test {

	public static void main(String args[]){
		Population bitPop = new BitPopulation(200, 4, AlgorithmEnum.ALG1);
		bitPop.execute();
	}
}
