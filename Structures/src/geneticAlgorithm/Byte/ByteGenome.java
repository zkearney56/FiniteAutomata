package geneticAlgorithm.Byte;

import geneticAlgorithm.Crossover;
import geneticAlgorithm.Genome;
import list.ArrayList;

public class ByteGenome implements Genome{

	private byte[] genome;
	int size = 0;

	public ByteGenome(int size){
		this.size = size;
		genome = new byte[size];
	}
	@Override
	public byte get(int index) {
		return genome[index];
	}

	@Override
	public void randomize() {
		for(int i = 0; i < size; i++){
			genome[i] = randomByte();
		}
	}
	
	private byte randomByte(){
		byte returnVal;
		if(Math.random() < .5){
			returnVal = (byte) 0;
		}
		else{
			returnVal = (byte) 1;
		}
		return returnVal;
	}

	@Override
	public int size() {
		return genome.length;
	}

	@Override
	public void mutate(int index) {
		if(genome[index] == 0){
			genome[index] = 1;
		}
		else{
			genome[index] = 0;
		}
		
	}

	@Override
	public void crossover(Genome x, Genome y, Crossover type) {
		
		byte[] xx = x.getGenome();
		byte[] yy = y.getGenome();

		switch(type){
		case EVERY_OTHER:{
			for(int i = 0; i < size; i ++){
				if ( (i & 1) == 0 ) {
					genome[i] = xx[i];
				} 
				else {
					genome[i] = yy[i];
				}
			}
			break;
		}
		case SPLIT:{
			int splitNum = size/2;
			for(int i = 0; i < splitNum; i++){
				genome[i] = xx[i];
			}
			for(int i = splitNum; i < size; i++){
				genome[i] = yy[i];
			}
			break;
		}
		}
	}
	
	public byte[] getGenome(){
		return genome;
	}

	public String toString(){
		String str = "";
		for(int i = 0; i <genome.length; i++){
			str += (int)genome[i];
		}
		return str;
	}
}
