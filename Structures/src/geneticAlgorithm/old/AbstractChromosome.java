package geneticAlgorithm.old;

/**
import java.util.Arrays;
import java.util.Random;

import geneticAlgorithm.Algorithm.CrossoverEnum;

public abstract class AbstractChromosome implements Chromosome, Cloneable{

	private byte[] genome;
	private int size = 0;
	
	public AbstractChromosome(int size){
		this.size = size;
		genome = new byte[size];
	}
	
	public AbstractChromosome(Chromosome x){
		this.size = x.size();
		genome = Arrays.copyOf(x.getChromosome(), size);
	}
	
	public abstract Chromosome clone();
	
	public final void randomize() {
		for(int i = 0; i < size; i++){
			genome[i] = randomByte();
		}
	}

	public final byte getGene(int index) {
		return genome[index];
	}
	
	public final int size() {
		return genome.length;
	}
	
	public abstract byte randomByte();
	
	public void mutate(int index){
		genome[index] = mutateByte(genome[index]);
	}
	
	public abstract byte mutateByte(byte b);
	
	public final byte[] getChromosome(){
		return genome;
	}
	
	public String toString(){
		String str = "";
		for(int i = 0; i <genome.length; i++){
			str += (int)genome[i];
		}
		str += "\n";
		for(int i = 0; i < genome.length; i++){
			str += (char)genome[i];
		}
		return str;
	}

	public final void crossover(Chromosome mate, CrossoverEnum type, int arg1, int arg2) {

		byte[] y = mate.getChromosome();

		switch(type){
		
		case EVERY_OTHER:{
			for(int i = 0; i < size; i ++){
				if (!( (i & 1) == 0 )) {
					genome[i] = y[i];
				}
			}
			break;
		}
		
		case SPLIT:{
			int splitNum = size/2;
			for(int i = splitNum; i < size; i++){
				genome[i] = y[i];
			}
			break;
			
		}
		case SPLIT_FIT_RATIO:
			Random rand = new Random();
			int split = rand.nextInt(2) + 1;
			switch(split){
			case 1:{
				int splitNum =(int)((arg1 / (arg1 + arg2)) * size);
				for(int i = splitNum; i < size; i++){
					genome[i] = y[i];
				}
			}
			case 2:{
				int splitNum =(int)((arg2 / (arg1 + arg2)) * size);
				for(int i = 0; i < splitNum; i++){
					genome[i] = y[i];
				}
			}
			}
			break;
		default:
			break;
		}
	}
}
*/