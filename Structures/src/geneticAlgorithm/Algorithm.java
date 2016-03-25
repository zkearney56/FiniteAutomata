package geneticAlgorithm;

public class Algorithm {

	public static int calcFitness(int val, AlgorithmEnum alg){
		switch(alg){
		case ALG1:{
			return alg1(val);
			//-x^2 + 15x
		}
		case ALG2: {
			break;
		}
		case ALG3: {
			break;
		}
		case ALG4: {
			break;
		}
		case ALG5: {
			break;
		}
		}
		return 0;
	}
	
	private static int alg1(int val){
		val = -(val * val) + 15 * val;
		return val;
	}
}
