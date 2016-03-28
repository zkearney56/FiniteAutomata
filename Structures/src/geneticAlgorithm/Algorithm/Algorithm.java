package geneticAlgorithm.Algorithm;

public class Algorithm {
	
	public static byte[] SOLUTION = {1,1,1,1,1,1,1,1,0,0,0,0,0,0,0,0,1,1,1,1,0,0,0,0,1,1,1,1,0,0,0,0
											,1,0,1,0,1,0,1,0,1,0,1,0,1,0,1,0,1,1,0,0,1,1,0,0,1,1,0,0,1,1,0,0};
	
	public static byte[] LETTERSOLUTION = {'z','a','c','h','a','r','y','k','e','a','r','n','e','y'};

	public static byte[] GENESOLUTION = {01,11,11,00,11,01,10,10,01,11,00,11,10,01,11,01,11,01};
	
	public static void setLetterSolution(String input){
		LETTERSOLUTION = input.getBytes();
	}
	
	public static int testSize(AlgorithmEnum alg){
		switch(alg){
		case BYTE_ALG1:{ 
			return 4;//4bit
		}
		case BYTE_ALG2: { //8bit
			return 8;
		}
		case BYTE_ALG3: { //12bit
			return 12;
		}
		case BYTE_ALG4: { //16 bit
			return 16;
		}
		case BYTE_ALG5: { //20 bit
			return 20;
		}
		case BYTE_ALG6: { //64 bit
			return SOLUTION.length;
		}
		case LET_ALG1: { //14bit
			return LETTERSOLUTION.length;
		}
		case LET_ALG2:{
			break;
		}
		case LET_ALG3: {
			break;
		}
		case GENE_ALG1:
			return GENESOLUTION.length;
		case GENE_ALG2:
			break;
		default:
			break;
		}
		return 0;
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
		case GENE_ALG1:
			return genealg1(genome);
		case GENE_ALG2:
			break;
		default:
			break;
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
	
	private static int genealg1(byte [] genome){
		int newVal = 0;
		for(int i = 0; i < GENESOLUTION.length; i++){
			byte val = genome[i];
			byte testVal = GENESOLUTION[i];
			if (val == testVal){
				newVal += 2;
			}
			else if ((val == 01 || val == 10) && (testVal == 00 || testVal == 11)){
				newVal += 1;
			}
		}
		return newVal;
		}

	public static String toString(byte[] genome, AlgorithmEnum alg){
		switch(alg){
		case BYTE_ALG1:
			return byteString(genome);
		case BYTE_ALG2:
			return byteString(genome);
		case BYTE_ALG3:
			return byteString(genome);
		case BYTE_ALG4:
			return byteString(genome);
		case BYTE_ALG5:
			return byteString(genome);
		case BYTE_ALG6:
			return byteString(genome);
		case LET_ALG1:
			return charString(genome);
		case LET_ALG2:
			return charString(genome);
		case LET_ALG3:
			return charString(genome);
		default:
			return byteString(genome);
		
		}
	}
	
	private static String byteString(byte[] genome){
		String str = "";
		for(int i = 0; i < genome.length; i++){
			str += (int)genome[i];
		}
		return str;
	}
	
	private static String charString(byte[] genome){
		String str = "";
		for(int i = 0; i < genome.length; i++){
			str += (char)genome[i];
		}
		return str;
	}
}