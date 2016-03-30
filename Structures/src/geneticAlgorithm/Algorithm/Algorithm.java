package geneticAlgorithm.Algorithm;

import java.util.Arrays;

import geneticAlgorithm.Tower.TowerOfHanoi;

/*
 * Written by: Zachary Kearney
 * Copyright by: Zachary Kearney, 2016
 *
 * Program: Algorithm.java
 * Date: March 22, 2016
 *
 * Description: Static class used to calculate fitness of a genome.
 * 
 */

public class Algorithm {
	
	public static byte[] SOLUTION = {1,1,1,1,1,1,1,1,0,0,0,0,0,0,0,0,1,1,1,1,0,0,0,0,1,1,1,1,0,0,0,0
											,1,0,1,0,1,0,1,0,1,0,1,0,1,0,1,0,1,1,0,0,1,1,0,0,1,1,0,0,1,1,0,0,
											1,1,1,1,0,0,0,0,1,1,1,1,0,0,0,0,1,1,1,1,0,0,0,0,1,1,1,1,0,0,0,0,
											1,1,1,1,1,1,1,1,0,0,0,0,0,0,0,0,1,0,1,0,1,0,1,0,1,0,1,0,1,0,1,0
											};
	
	public static byte[] LETTERSOLUTION = {'z','a','c','h','a','r','y','k','e','a','r','n','e','y'};

	public static byte[] GENESOLUTION = {01,11,11,00,11,01,10,10,01,11,00,11,10,01,11,01};
	
	public static byte[] CHARARRAY = {'a','b','c','d','e','f','g','h','i','j','k','l','m',
			'n','o','p','q','r','s','t','u','v','w','x','y','z'};
	
	
	public static void setLetterSolution(String input){
		LETTERSOLUTION = input.getBytes();
	}
	
	public static void setGeneSolution(byte[] gene){
		GENESOLUTION = gene;
	}
	/**
	 * Returns testSize of specified algorithm.
	 * @param alg
	 * @return
	 */
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
		case BYTE_ALG7: { //16bit
			return 16;
		}
		case LET_ALG1: { //14bit
			return LETTERSOLUTION.length;
		}
		case LET_ALG2:{
			return LETTERSOLUTION.length;
		}
		case LET_ALG3: {
			break;
		}
		case GENE_ALG1:
			return GENESOLUTION.length;
		case GENE_ALG2:
			break;
		case TOWER_ALG1:
			return 256;
		case TOWER_ALG2:
			return 512;
		default:
			break;
		}
		return 0;
	}
	/**
	 * Calculates fitness of genome for specified algorithm.
	 * @param genome
	 * @param alg
	 * @return
	 */
	public static Fitness calcFitness(byte[] genome, AlgorithmEnum alg){
		switch(alg){
		case BYTE_ALG1:{ //4bit
			int val = intVal(genome);
			return new Fitness(bytealg1(val));
			//-x^2 + 15x
		}
		case BYTE_ALG2: { //8bit
			int val = intVal(genome);
			return new Fitness(bytealg2(val));
		}
		case BYTE_ALG3: { //12bit
			int val = intVal(genome);
			return new Fitness(bytealg3(val));
		}
		case BYTE_ALG4: { //16 bit
			int val = intVal(genome);
			return new Fitness(bytealg4(val));
		}
		case BYTE_ALG5: { //20 bit
			int val = intVal(genome);
			return new Fitness(bytealg5(val));
		}
		case BYTE_ALG6: { //64 bit
			return new Fitness(bytealg6(genome));
		}
		case BYTE_ALG7: {
			return new Fitness(bytealg7(genome));
		}
		case LET_ALG1: { //14bit
			return new Fitness(letalg1(genome));
		}
		case LET_ALG2:{
			//return letalg2(genome);
			break;
		}
		case LET_ALG3: {
			break;
		}
		case GENE_ALG1:
			return new Fitness(genealg1(genome));
		case GENE_ALG2:
			break;
		case TOWER_ALG1:
			TowerOfHanoi tower = new TowerOfHanoi(3);
			return tower.test(genome);
		case TOWER_ALG2: TowerOfHanoi tower2 = new TowerOfHanoi(5);
			return tower2.test(genome);
		default:
			return null;
		}
		return null;
	}
	
	/**
	 * Returns unsigned int value of byte array.
	 * @param genome
	 * @return
	 */
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

	private static int bytealg6(byte[] genome){
		int newVal = 0;
		for(int i = 0; i < SOLUTION.length; i++){
			if(genome[i] == SOLUTION[i]){
				newVal++;
			}
		}
		return newVal;
	}
	
	public static int bytealg7(byte[]genome){
		int x1 = intVal(Arrays.copyOfRange(genome, 0, 4));
		int x2 = intVal(Arrays.copyOfRange(genome, 4, 8));
		int x3 = intVal(Arrays.copyOfRange(genome, 8, 12));
		int x4 = intVal(Arrays.copyOfRange(genome, 12, 16));
		int fitness = 1;
		if(4 * x1 + 3 * x2 + 2 * x3 + x4 == 10){
			fitness ++;
		}
		if(3 * x1 + 2*x2+x3+4*x4 == 6){
			fitness ++;
		}
		if(2 * x1 + x2 + 4 * x3 + 3 * x4 == 14){
			fitness ++;
		}
		if(x1 + 4 * x2 + 3 * x3 + 2 * x4 == 10){
			fitness ++;
		}
		return fitness;
		/**
		tot += 4 * x1 + 3 * x2 + 2 * x3 + x4 - 10; //10
		tot += 3 * x1 + 2*x2+x3+4*x4 - 6; //9
		tot += 2 * x1 + x2 + 4 * x3 + 3 * x4 - 14; //14
		tot += x1 + 4 * x2 + 3 * x3 + 2 * x4 - 10; //10
		
		if(tot < 0){
			tot = -tot;
		}
		if(tot == 0){
			return 100000;
		}
		else{
			return 1000/tot;
		}
		*/
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
			else if((val == 00 || val == 11) && (testVal == 01 || testVal == 10)){
				newVal +=1;
			}
		}
		return newVal;
		}
	/**
	 * Returns string value of genome for specified algorithm.
	 * @param genome
	 * @param alg
	 * @return
	 */
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
			return byteString(genome) + "\n" + byteString(SOLUTION);
		case BYTE_ALG7:
			return intValString(genome);
		case LET_ALG1:
			return charString(genome);
		case LET_ALG2:
			return charString(genome);
		case LET_ALG3:
			return charString(genome);
		case GENE_ALG1:
			String sol1 = geneString(genome);
			String sol2 = geneString(GENESOLUTION);
			sol1 += "\n" + sol2;
			return sol1;
		case TOWER_ALG1:
			String str = "";
			TowerOfHanoi tower = new TowerOfHanoi(3);
			tower.test(genome);
			str += byteString(genome);
			str += "\n" + tower.toString();
			return str;
		case TOWER_ALG2:
			String str2 = "";
			TowerOfHanoi tower2 = new TowerOfHanoi(5);
			tower2.test(genome);
			str2 += byteString(genome);
			str2 += "\n" + tower2.toString();
			return str2;
		default:
			return byteString(genome);
		
		}
	}
	
	private static String geneString(byte[] genome){
		String str = "";
		for(int i = 0; i < genome.length; i++){
			if(genome[i] == 0){
				str += "00 ";
			}
			else if(genome[i] == 11){
				str += "11 ";
			}
			else if(genome[i] == 10){
				str += "10 ";
			}
			else if(genome[i] == 01){
				str += "01 ";
			}
		}
		return str;
	}
	
	private static String byteString(byte[] genome){
		String str = "";
		for(int i = 0; i < genome.length; i++){
			str += (int)genome[i];
		}
		return str;
	}
	
	private static String intValString(byte[] genome){
		String str = "";
		for(int i = 0; i < genome.length - 3; i = i + 4){
			str += intVal(Arrays.copyOfRange(genome, i, i+4)) + " ";
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