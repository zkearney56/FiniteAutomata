package geneticAlgorithm.Algorithm;

public class Algorithm {
	
	public static byte[] SOLUTION = {1,1,1,1,1,1,1,1,0,0,0,0,0,0,0,0,1,1,1,1,0,0,0,0,1,1,1,1,0,0,0,0
											,1,0,1,0,1,0,1,0,1,0,1,0,1,0,1,0,1,1,0,0,1,1,0,0,1,1,0,0,1,1,0,0};
	
	public static byte[] LETTERSOLUTION = {'z','a','c','h','a','r','y','k','e','a','r','n','e','y'};

	
	public static void setLetterSolution(String input){
		LETTERSOLUTION = input.getBytes();
	}
	
	public static int calcFitness(byte[] genome, AlgorithmEnum alg){
		switch(alg){
		case BYTE_ALG1:{ //4bit
			int val = intVal(genome);
			return bytealg1(val); //7|8
			//-x^2 + 15x
		}
		case BYTE_ALG2: { //8bit
			int val = intVal(genome);
			return bytealg2(val); //255
		}
		case BYTE_ALG3: { //12bit
			int val = intVal(genome);
			return bytealg3(val); // 2047
		}
		case BYTE_ALG4: { //16 bit
			int val = intVal(genome);
			return bytealg4(val); // 32767
		}
		case BYTE_ALG5: { //20 bit
			int val = intVal(genome);
			return bytealg5(val);
		}
		case BYTE_ALG6: { //64 bit
			return bytealg6(genome);
		}
		case LET_ALG1: { //14bit
			return letalg1(genome);
		}
		case LET_ALG2:{
			break;
		}
		case LET_ALG3: {
			break;
		}
		}
		return 0;
	}
	
	private static int bytealg1(int val){
		int newVal = 0;
		newVal = -(val * val) + 15 * val;
		return newVal;
	}
	
	private static int bytealg2(int val){
		int newVal = 0;
		newVal = -(val * val) + 512 * val;
		return newVal;
	}
	
	private static int bytealg3(int val){
		int newVal = 0;
		newVal = -(val * val) + 4096 * val;
		return newVal;
	}
	private static int bytealg4(int val){
		int newVal = 0;
		newVal = -(val * val) + 65536 * val;
		return newVal;
	}
	private static int bytealg5(int val){
		int newVal = 0;
		newVal = -(val * val) + 1048576 * val;
		return newVal;
	}
	
	public static int intVal(byte[] genome) {
		int value = 0;
		for(int i = genome.length - 1, x = 0; i >= 0; i--, x++){
			if(genome[i] == 1){
			value += Math.pow(2, x);
			}
		}
		// TODO Auto-generated method stub
		return value;
	}
	
	private static int bytealg6(byte[] genome){
		int newVal = 0;
		for(int i = 0; i < SOLUTION.length; i++){
			if(genome[i] == SOLUTION[i]){
				newVal++;
			}
		}
		return newVal;
	}
	
	private static int letalg1(byte[] genome){
		int newVal = 0;
		for(int i = 0; i < LETTERSOLUTION.length; i++){
			if(genome[i] == LETTERSOLUTION[i]){
				newVal++;
			}
		}
		return newVal;
	}
}
