package geneticAlgorithm;

import java.util.Arrays;
import java.util.Random;

import geneticAlgorithm.Algorithm.Algorithm;
import geneticAlgorithm.Algorithm.AlgorithmEnum;

public abstract class AbstractGenome implements Genome{

	private int size = 0;
	private AlgorithmEnum alg = AlgorithmEnum.BYTE_ALG1;
	private byte[] genome;
	private int fitness = 0;
	private boolean isElite = false;
	
	public AbstractGenome(int size, AlgorithmEnum alg){
		this.size = size;
		this.alg = alg;
		genome = new byte[size];
		randomize();
		testFitness();
	}
	
	public AbstractGenome(Genome x){
		this.size = x.getSize();
		this.alg = x.getAlg();
		this.fitness = x.getFitness();
		isElite = false;
		this.genome = Arrays.copyOf(x.getGenome(), size);
	}
	
	/**
	public final Genome crossover(Genome mate, CrossoverEnum type){
		Genome clone = this.clone();
		byte[] y = mate.getGenome();

		switch(type){
		
		case EVERY_OTHER:{
			for(int i = 0; i < size; i ++){
				if (!( (i & 1) == 0 )) {
					clone.setGene(i, y[i]);
				}
			}
			break;
		}
		
		case SPLIT:{
			int splitNum = size/2;
			for(int i = splitNum; i < size; i++){
				clone.setGene(i, y[i]);
			}
			break;
			
		}
		case SPLIT_FIT_RATIO:
			Random rand = new Random();
			int split = rand.nextInt(2) + 1;
			int fit1 = clone.getFitness();
			int fit2 = mate.getFitness();
			switch(split){
			case 1:{
				int splitNum =(int)((fit1 / (fit1 + fit2)) * size);
				for(int i = splitNum; i < size; i++){
					clone.setGene(i, y[i]);
				}
			}
			case 2:{
				int splitNum =(int)((fit2 / (fit1 + fit2)) * size);
				for(int i = 0; i < splitNum; i++){
					clone.setGene(i, y[i]);
				}
			}
			}
			break;
		default:
			break;
		}
		clone.testFitness();
		return clone;
	}
	
	*/
	public abstract Genome clone();
	
	public final int getFitness(){
		return fitness;
	}
	
	public final int getSize(){
		return size;
	}
	
	public final void mutate(int index){
		genome[index] = mutateByte(genome[index]);
		testFitness();
	}
	
	public final void testFitness(){
		fitness = Algorithm.calcFitness(genome, alg);
	}
	
	public final AlgorithmEnum getAlg() {
		// TODO Auto-generated method stub
		return alg;
	}
	
	public final String toString(){
		return Algorithm.toString(genome, alg);
	}
	
	public final byte[] getGenome(){
		return genome;
	}
	
	public final byte getGene(int index){
		return genome[index];
	}
	
	public final void setElite(boolean bool){
		isElite = bool;
	}
	
	public final boolean isElite(){
		return isElite;
	}
	
	public final void randomize() {
		for(int i = 0; i < size; i++){
			genome[i] = randomByte();
		}
	}
	
	public void setGene(int index, byte dat){
		genome[index] = dat;
	}
	
	public abstract byte mutateByte(byte b);
	
	public abstract byte randomByte();

}
